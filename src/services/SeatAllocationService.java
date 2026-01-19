package services;

import exceptions.SeatAlreadyBookedException;
import repositories.SeatRepository;

public class SeatAllocationService {

    private SeatRepository repo;

    public SeatAllocationService(SeatRepository repo) {
        this.repo = repo;
    }

    public void reserveSeat(int seatId) {
        if (repo.isBooked(seatId))
            throw new SeatAlreadyBookedException();
        repo.book(seatId);
    }
}
