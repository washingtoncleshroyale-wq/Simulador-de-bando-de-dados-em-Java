package br.edu.exercicio;

/**
 * Interface para conexões de banco de dados.
 * Permite diferentes implementações (SQLite, MySQL, etc.).
 */
public interface Connection {
    void connect(String url);
    void disconnect();
}