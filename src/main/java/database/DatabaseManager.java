package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    // Veritabanı dosyası, uygulama dizinine oluşturulur
    private static final String DB_URL = "jdbc:sqlite:work_sessions.db";

    // Veritabanı bağlantısı sağlayan static metot
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    // Uygulama başında çağrılabilir: tablo kurulumları buraya toplanabilir
    public static void initializeDatabase() {
        System.out.println("Database is initialized");
        // Genişletmeye açık: Örneğin ileride genel tablo kurulumları buraya alınabilir
    }
}

