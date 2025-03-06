package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        double saldo = 1000.00;
        int escolha;

        String dados = """
                ----------------------------------------
                 Dados iniciais do cliente:
                
                 Nome:   Arthur Antonio Rabelo de Souza
                 Conta:  Corrente
                 Saldo:  R$\s""" + saldo + """
                \n----------------------------------------
                """;

        String opcoes = """
                1 - Consultar saldo
                2 - Receber valor
                3 - Transferir valor
                4 - Sair
                """;

        System.out.println("\n" + dados);
        System.out.print(opcoes);

        while (true) {
            System.out.print("\nDigite uma opção: ");
            escolha = leitura.nextInt();

            if (escolha == 1) {
                System.out.println("O valor do seu saldo é: R$ " + saldo);
            } else if (escolha == 2) {
                System.out.print("Digite um valor para receber: R$ ");
                double valor = leitura.nextDouble();
                saldo += valor;
            } else if (escolha == 3) {
                System.out.print("Digite um valor para transferir: R$ ");
                double valor = leitura.nextDouble();
                saldo -= valor;
            } else if (escolha == 4) {
                System.out.println("Saindo...");
                break;
            } else {
                System.out.println("Digite uma opção válida!");
            }

        }

    }

}