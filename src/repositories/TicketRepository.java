package repositories;

public interface TicketRepository {
    void create(int eventId, int seatId, int customerId, String code);
    boolean exists(String code);
}

