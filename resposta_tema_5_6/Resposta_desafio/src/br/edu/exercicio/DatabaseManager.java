package br.edu.exercicio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DatabaseManager {

    private static DatabaseManager instance;
    private List<String> usuarios; // Cache em memória para relatórios
    private String databaseUrl = "jdbc:sqlite:./data/usuarios_exercicio.db";
    private Connection connection; // Usando Factory Method para criar a conexão

    // Construtor privado para Singleton
    private DatabaseManager() {
        System.out.println("[LOG] Inicializando DatabaseManager...");
        this.usuarios = new ArrayList<>();
        // Usando Factory Method para criar a conexão
        this.connection = ConnectionFactory.createConnection("sqlite");
        conectarNoBanco();
        // Carregar usuários existentes do banco
        carregarUsuariosDoBanco();
    }

    // Método Global de Acesso (GoF Singleton)
    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    private void conectarNoBanco() {
        connection.connect(databaseUrl);
    }

    private void carregarUsuariosDoBanco() {
        try {
            String sql = "SELECT nome, email FROM usuarios ORDER BY id";
            PreparedStatement pstmt = ((SQLiteConnection) connection).getJdbcConnection().prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                usuarios.add(nome + " (" + email + ")");
            }
            
            rs.close();
            pstmt.close();
            System.out.println("[DB] Carregados " + usuarios.size() + " usuários do banco.");
            
        } catch (SQLException e) {
            System.err.println("[ERRO] Falha ao carregar usuários: " + e.getMessage());
        }
    }

    // Violação de SRP: Lógica de negócio (validação) misturada com persistência
    public void adicionarUsuario(String nome, String email) {
        System.out.println("[LOG] Tentando adicionar usuário: " + nome);
        
        if (nome == null || nome.trim().isEmpty()) {
            System.err.println("[ERRO] Nome inválido!");
            return;
        }

        if (email == null || !email.contains("@")) {
            System.err.println("[ERRO] Email inválido!");
            return;
        }

        // Tentar inserir no banco
        try {
            String sql = "INSERT INTO usuarios (nome, email) VALUES (?, ?)";
            PreparedStatement pstmt = ((SQLiteConnection) connection).getJdbcConnection().prepareStatement(sql);
            pstmt.setString(1, nome);
            pstmt.setString(2, email);
            pstmt.executeUpdate();
            pstmt.close();
            
            // Adicionar ao cache em memória
            usuarios.add(nome + " (" + email + ")");
            System.out.println("[DB] Usuário salvo com sucesso: " + nome);
            
        } catch (SQLException e) {
            System.err.println("[ERRO] Falha ao salvar usuário: " + e.getMessage());
        }
    }

    // Violação de SRP: Lógica de apresentação/relatório no "Banco de Dados"
    public void imprimirRelatorioUsuarios() {
        System.out.println("\n--- RELATÓRIO DE USUÁRIOS ---");
        System.out.println("Data: 30/03/2026");
        System.out.println("-----------------------------");
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            for (int i = 0; i < usuarios.size(); i++) {
                System.out.println((i + 1) + ". " + usuarios.get(i).toUpperCase());
            }
        }
        System.out.println("-----------------------------\n");
    }

    public List<String> getUsuarios() {
        return usuarios;
    }
}
