package db;

import java.sql.Connection;

public interface IDB {
    Connection getConnection();
}
