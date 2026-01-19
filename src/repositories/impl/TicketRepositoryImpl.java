package repositories.impl;

import db.IDB;
import java.sql.*;
import repositories.TicketRepository;

public class TicketRepositoryImpl implements TicketRepository {

    private IDB db;

    public TicketRepositoryImpl(IDB db) {
        this.db = db;
    }

    @Override
    public void create(int eventId, int seatId, int customerId, String code) {
        try (Connection c = db.getConnection()) {
            PreparedStatement ps = c.prepareStatement(
                    "INSERT INTO tickets(event_id, seat_id, customer_id, ticket_code) VALUES (?,?,?,?)"
            );
            ps.setInt(1, eventId);
            ps.setInt(2, seatId);
            ps.setInt(3, customerId);
            ps.setString(4, code);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean exists(String code) {
        try (Connection c = db.getConnection()) {
            PreparedStatement ps = c.prepareStatement(
                    "SELECT 1 FROM tickets WHERE ticket_code=?"
            );
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
