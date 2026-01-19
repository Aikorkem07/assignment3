package repositories.impl;

import db.IDB;
import entities.Customer;
import repositories.CustomerRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerRepositoryImpl implements CustomerRepository {

    private IDB db;

    public CustomerRepositoryImpl(IDB db) {
        this.db = db;
    }

    @Override
    public void create(Customer customer) {
        try (Connection c = db.getConnection()) {
            PreparedStatement ps = c.prepareStatement(
                    "INSERT INTO customers(name,email) VALUES (?,?)"
            );
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getEmail());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
