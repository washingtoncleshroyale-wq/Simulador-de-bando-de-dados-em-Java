package br.edu.exercicio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Implementação concreta para conexão SQLite com JDBC real.
 */
public class SQLiteConnection implements br.edu.exercicio.Connection {

    private java.sql.Connection jdbcConnection;

    @Override
    public void connect(String url) {
        try {
            // Carregar driver JDBC (opcional em versões recentes)
            Class.forName("org.sqlite.JDBC");
            
            // Conectar ao banco
            jdbcConnection = DriverManager.getConnection(url);
            System.out.println("[DB] Conectado ao banco SQLite: " + url);
            
            // Criar tabela se não existir
            createTableIfNotExists();
            System.out.println("[DB] Tabela 'usuarios' verificada/criada.");
            
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("[ERRO] Falha ao conectar ao banco: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void disconnect() {
        try {
            if (jdbcConnection != null && !jdbcConnection.isClosed()) {
                jdbcConnection.close();
                System.out.println("[DB] Desconectado do banco SQLite.");
            }
        } catch (SQLException e) {
            System.err.println("[ERRO] Falha ao desconectar: " + e.getMessage());
        }
    }

    private void createTableIfNotExists() throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS usuarios (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                email TEXT NOT NULL UNIQUE
            );
            """;
        try (Statement stmt = jdbcConnection.createStatement()) {
            stmt.execute(sql);
        }
    }

    // Método para obter a conexão JDBC (usado por DatabaseManager)
    public java.sql.Connection getJdbcConnection() {
        return jdbcConnection;
    }
}