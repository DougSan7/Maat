package main.maat.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    private static final String URL = "jdbc:mysql://localhost:3306/projeto_maat";
    private static final String USER = "root";
    private static final String PASSWORD = "sua_senha";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            throw new RuntimeException("Erro na conexão: " + e.getMessage());
        }
    }
}
