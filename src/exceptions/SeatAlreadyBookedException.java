package exceptions;

public class SeatAlreadyBookedException extends RuntimeException {
    public SeatAlreadyBookedException() {
        super("Seat already bookedd");
    }
}
