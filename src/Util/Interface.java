package Util;

import Modelo.Financiamento;
import Modelo.Casa;
import Modelo.Apartamento;
import Modelo.Terreno;
import java.util.Scanner;

public class Interface {

    public Financiamento executar() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nDeseja simular mais financiamento? (s/n): ");
        String opcao = scanner.nextLine();
        if (opcao.equalsIgnoreCase("s")) {
            int tipo = 0;
            boolean tipoValido = false;
            do {
                System.out.print("Informe o tipo do imóvel (1 - Casa, 2 - Apartamento, 3 - Terreno): ");
                try {
                    tipo = scanner.nextInt();
                    if (tipo >= 1 && tipo <= 3) {
                        tipoValido = true;
                    } else {
                        System.out.println("❌ Tipo inválido! Digite 1, 2 ou 3.");
                    }
                } catch (Exception e) {
                    System.out.println("❌ Entrada inválida! Digite um número inteiro.");
                    scanner.nextLine(); // limpar buffer
                }
            } while (!tipoValido);

            // Valor do imóvel
            double valorImovel = 0;
            boolean valorValido = false;
            do {
                System.out.print("Informe o valor do financiamento (mínimo R$10.000): R$ ");
                try {
                    valorImovel = scanner.nextDouble();
                    if (valorImovel >= 10000) {
                        valorValido = true;
                    } else {
                        System.out.println("❌ Valor inválido! O valor deve ser maior ou igual a R$10.000.");
                    }
                } catch (Exception e) {
                    System.out.println("❌ Entrada inválida! Digite um número válido.");
                    scanner.nextLine();
                }
            } while (!valorValido);

            // Prazo financiamento
            int prazoFinanciamento = 0;
            boolean prazoValido = false;
            do {
                System.out.print("Informe o prazo do financiamento (em anos - número inteiro): ");
                try {
                    prazoFinanciamento = scanner.nextInt();
                    if (prazoFinanciamento > 0 && prazoFinanciamento <= 100) {
                        prazoValido = true;
                    } else {
                        System.out.println("❌ Prazo inválido! Digite um valor entre 1 e 100.");
                    }
                } catch (Exception e) {
                    System.out.println("❌ Entrada inválida! Digite um número inteiro.");
                    scanner.nextLine();
                }
            } while (!prazoValido);

            // Taxa de juros anual
            double taxaJurosPercentual = 0;
            boolean taxaValida = false;
            do {
                System.out.print("Informe a taxa de juros anual (ex: 3 para 3%): ");
                try {
                    scanner.nextLine(); // limpar buffer pendente do nextInt()
                    String entrada = scanner.nextLine().replace(",", "."); // permite vírgula ou ponto
                    taxaJurosPercentual = Double.parseDouble(entrada);
                    if (taxaJurosPercentual > 0) {
                        taxaValida = true;
                    } else {
                        System.out.println("❌ Taxa inválida! Deve ser maior que zero.");
                    }
                } catch (Exception e) {
                    System.out.println("❌ Entrada inválida! Digite um número válido (ex: 3 ou 3,5).");
                }
            } while (!taxaValida);


            double taxaJuros = taxaJurosPercentual / 100;


            // Criar o financiamento correto conforme tipo
            Financiamento financiamento;
            if (tipo == 1) {
                financiamento = new Casa(valorImovel, taxaJuros, prazoFinanciamento, 7, 10);
                System.out.println("Informações adicionais: Casa com 7 cômodos e 10 anos de construção.");
            } else if (tipo == 2) {
                System.out.println("Informações adicionais: Apartamento com 10 vagas de garagem e 6 andares.");
                financiamento = new Apartamento(valorImovel, taxaJuros, prazoFinanciamento,10, 6);
            } else {
                financiamento = new Terreno(valorImovel, taxaJuros, prazoFinanciamento, "Urbano");
                System.out.println("Informações adicionais: Terreno localizado em zona urbana.");
            }

            System.out.println("\n--- Resumo do Financiamento ---");
            System.out.printf("Tipo do imóvel: %s\n", financiamento.getTipo());
            System.out.printf("Valor do imóvel: R$ %.2f\n", financiamento.getValorImovel());
            System.out.printf("Prazo: %d anos\n", financiamento.getPrazoFinanciamento());
            System.out.printf("Taxa de Juros Anual: %.2f%%\n", taxaJurosPercentual);
            System.out.printf("Pagamento mensal: R$ %.2f\n", financiamento.calcularPagamentoMensal());
            System.out.printf("Total pago: R$ %.2f\n", financiamento.calcularTotalPagamento());

            // Detalhamento mês a mês
            System.out.print("\nDeseja ver o detalhamento mês a mês? (s/n): ");
            scanner.nextLine(); // consumir \n pendente
            String resposta = scanner.nextLine();

            if (resposta.equalsIgnoreCase("s")) {
                financiamento.mostrarAmortizacaoDetalhada();
            }


            return financiamento;
        }

        // Caso o usuário não deseje simular
        System.out.println("Nenhuma simulação realizada. Retornando ao sistema.");
        return null;
    }
}
