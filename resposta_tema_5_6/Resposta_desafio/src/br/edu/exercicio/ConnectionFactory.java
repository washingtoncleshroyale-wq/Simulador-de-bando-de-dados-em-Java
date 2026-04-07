package br.edu.exercicio;

/**
 * Factory Method para criar conexões de banco de dados.
 * Permite criar diferentes tipos de conexões sem acoplar o código cliente.
 */
public class ConnectionFactory {

    /**
     * Factory Method: Cria uma conexão baseada no tipo fornecido.
     * @param type Tipo da conexão (ex: "sqlite", "mysql").
     * @return Instância da conexão apropriada.
     */
    public static Connection createConnection(String type) {
        switch (type.toLowerCase()) {
            case "sqlite":
                return new SQLiteConnection();
            // Futuramente, adicionar outros tipos:
            // case "mysql":
            //     return new MySQLConnection();
            default:
                throw new IllegalArgumentException("Tipo de conexão não suportado: " + type);
        }
    }
}