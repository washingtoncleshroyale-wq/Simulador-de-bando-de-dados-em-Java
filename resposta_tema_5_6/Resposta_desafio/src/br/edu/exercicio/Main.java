package br.edu.exercicio;

import java.util.Scanner;

/**
 * Cliente Altamente Acoplado ao Singleton.
 *
 * Este cliente demonstra a dependência direta de DatabaseManager para logar, validar e exibir dados.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Bem-vindo ao Sistema Acadêmico (Versão Altamente Acoplada)");

        // Acesso Global ao Singleton
        DatabaseManager dbManager = DatabaseManager.getInstance();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        // Acoplamento Direto: DatabaseManager valida e salva ao mesmo tempo
        dbManager.adicionarUsuario(nome, email);

        // Chamada de método de interface de usuário dentro do DatabaseManager
        dbManager.imprimirRelatorioUsuarios();

        System.out.println("Fim da Execução.");
    }
}
