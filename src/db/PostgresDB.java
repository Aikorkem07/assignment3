package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresDB implements IDB {

    @Override
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:postgresql://aws-1-ap-southeast-2.pooler.supabase.com:5432/postgres?sslmode=require",
                    "postgres.olpszwzwhedzckiiraxm",
                    "Aikosha.2007"
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
