package repositories.impl;

import db.IDB;
import entities.Seat;
import repositories.SeatRepository;

import java.sql.*;
import java.util.*;

public class SeatRepositoryImpl implements SeatRepository {

    private IDB db;

    public SeatRepositoryImpl(IDB db) {
        this.db = db;
    }

    public boolean isBooked(int seatId) {
        try (Connection c = db.getConnection()) {
            PreparedStatement ps =
                    c.prepareStatement("select booked from seats where id=?");
            ps.setInt(1, seatId);
            ResultSet rs = ps.executeQuery();
            return rs.next() && rs.getBoolean("booked");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void book(int seatId) {
        try (Connection c = db.getConnection()) {
            PreparedStatement ps =
                    c.prepareStatement("update seats set booked=true where id=?");
            ps.setInt(1, seatId);
            ps.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Seat> findByEvent(int eventId) {
        List<Seat> list = new ArrayList<>();
        try (Connection c = db.getConnection()) {
            PreparedStatement ps =
                    c.prepareStatement("select * from seats where event_id=?");
            ps.setInt(1, eventId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Seat s = new Seat();
                s.id = rs.getInt("id");
                s.seatNumber = rs.getString("seat_number");
                s.booked = rs.getBoolean("booked");
                list.add(s);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
