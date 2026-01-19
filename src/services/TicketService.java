package services;

import exceptions.*;
import repositories.*;

public class TicketService {

    private EventRepository eventRepo;
    private SeatRepository seatRepo;
    private TicketRepository ticketRepo;

    public TicketService(EventRepository e, SeatRepository s, TicketRepository t) {
        eventRepo = e;
        seatRepo = s;
        ticketRepo = t;
    }

    public void buyTicket(int eventId, int seatId, int customerId, String code) {
        if (eventRepo.isCancelled(eventId))
            throw new EventCancelledException();

        if (seatRepo.isBooked(seatId))
            throw new SeatAlreadyBookedException();

        ticketRepo.create(eventId, seatId, customerId, code);
        seatRepo.book(seatId);
    }
}

