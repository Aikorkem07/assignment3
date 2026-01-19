import db.IDB;
import db.PostgresDB;
import entities.Event;
import entities.Customer;
import repositories.*;
import repositories.impl.*;
import services.*;

public class Main {
    public static void main(String[] args) {


        IDB db = new PostgresDB();


        EventRepository eventRepo = new EventRepositoryImpl(db);
        SeatRepository seatRepo = new SeatRepositoryImpl(db);
        CustomerRepository customerRepo = new CustomerRepositoryImpl(db);
        TicketRepository ticketRepo = new TicketRepositoryImpl(db);

        SeatAllocationService seatService = new SeatAllocationService(seatRepo);
        TicketService ticketService = new TicketService(eventRepo, seatRepo, ticketRepo);

        Event event = new Event("Cinema Night", "2026-03-01");
        eventRepo.create(event);
        System.out.println("Event created: " + event.getName() + " on " + event.getDate());


        try {
            seatService.reserveSeat(1);
            System.out.println("Seat 1 reserved successfully");
        } catch (exceptions.SeatAlreadyBookedException e) {
            System.out.println("Error: " + e.getMessage());
        }


        try {
            seatService.reserveSeat(1);
            System.out.println("Seat 1 reserved successfully again");
        } catch (exceptions.SeatAlreadyBookedException e) {
            System.out.println("Error: " + e.getMessage());
        }


        Customer customer = new Customer("John Doe", "john@example.com");
        customerRepo.create(customer);
        System.out.println("Customer created: " + customer.getName() + " (" + customer.getEmail() + ")");


        try {
            ticketService.buyTicket(1, 2, 1, "CODE123"); // eventId=1, seatId=2, customerId=1
            System.out.println("Ticket purchased successfully: CODE123");
        } catch (exceptions.SeatAlreadyBookedException | exceptions.EventCancelledException e) {
            System.out.println("Error buying ticket: " + e.getMessage());
        }

        System.out.println("Simulating cancelled event...");
        try {

            ticketService.buyTicket(1, 3, 1, "CODE124");
        } catch (exceptions.EventCancelledException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (exceptions.SeatAlreadyBookedException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("RUN SUCCESS");
    }
}
