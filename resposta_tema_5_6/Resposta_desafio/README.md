# Projeto: Exercício de Refatoração (Singleton Acoplado)

Este projeto é um exemplo didático de **mau design de software**. Ele contém uma classe Singleton altamente acoplada e com baixa coesão, desafiando alunos a aplicarem padrões **GoF** e princípios **GRASP** para refatorá-la.

## 🚀 Como Executar

Siga os passos abaixo para compilar e rodar o projeto usando o terminal (PowerShell ou CMD).

### Pré-requisitos
- JDK (Java Development Kit) instalado e configurado no PATH.

### 1. Compilar o Projeto
No diretório raiz do projeto, execute:

```powershell
# Cria a pasta para os arquivos binários (caso não exista)
mkdir bin

# Compila todos os arquivos .java da pasta 'src' para a pasta 'bin'
javac src/br/edu/exercicio/*.java -d bin
```

### 2. Rodar a Aplicação
Após a compilação bem-sucedida, execute o comando:

```powershell
java -cp bin br.edu.exercicio.Main
```

---

## 🎯 O Desafio de Refatoração

O objetivo é transformar a classe `DatabaseManager` em um componente limpo e desacoplado.

### O que está errado atualmente?
*   **Singleton "Deus"**: O `DatabaseManager` faz tudo (Gerencia instância, conexão, banco, usuários, logs e relatórios).
*   **Acoplamento**: URLs de banco de dados estão fixas (hardcoded) no código.
*   **Interface no Back-end**: O uso de `System.out` dentro de uma classe de "banco de dados" viola a separação de responsabilidades.

### Padrões Sugeridos para Aplicação:
1.  **GoF - Factory Method**: Para criar diferentes tipos de conexão.
2.  **GoF - Strategy**: Para validar usuários de formas diferentes.
3.  **GoF - Observer**: Para lidar com logs sem acoplar o `System.out`.
4.  **GRASP - Low Coupling & High Cohesion**: Reduzir dependências e focar cada classe em uma única tarefa.

---
*Criado para fins educacionais.*
