package repositories.impl;

import db.IDB;
import entities.Event;
import repositories.EventRepository;

import java.sql.*;

public class EventRepositoryImpl implements EventRepository {

    private IDB db;

    public EventRepositoryImpl(IDB db) {
        this.db = db;
    }

    public void create(Event event) {
        try (Connection c = db.getConnection()) {
            PreparedStatement ps =
                    c.prepareStatement("insert into events(name,date) values (?,?)");
            ps.setString(1, event.getName());
            ps.setDate(2, Date.valueOf(event.getDate()));
            ps.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isCancelled(int id) {
        try (Connection c = db.getConnection()) {
            PreparedStatement ps =
                    c.prepareStatement("select cancelled from events where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next() && rs.getBoolean("cancelled");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
