package repositories;

import entities.Seat;
import java.util.List;

public interface SeatRepository {
    boolean isBooked(int seatId);
    void book(int seatId);
    List<Seat> findByEvent(int eventId);
}
