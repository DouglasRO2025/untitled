package Main;

import Modelo.Apartamento;
import Modelo.Casa;
import Modelo.Financiamento;
import Modelo.Terreno;
import Util.Interface;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Interface ui = new Interface();

        ArrayList<Financiamento> lista = new ArrayList<>();

        // 1. Financiamento digitado pelo usuário
        System.out.println("Digite o tipo do imóvel (1-Casa, 2-Apartamento, 3-Terreno):");
        int tipo = scanner.nextInt();

        double valor = 0;
        boolean valorOK = false;
        do {
            System.out.println("Informe o valor do imovel (mínimo R$10.000):");
            try {
                valor = scanner.nextDouble();
                if (valor >= 10000) {
                    valorOK = true;
                } else {
                    System.out.println("❌ Valor inválido! O valor deve ser maior ou igual a R$10.000.");
                }
            } catch (InputMismatchException e) {
                System.out.println("❌ Entrada inválida! Digite um número válido.");
                scanner.nextLine();
            }
        } while (!valorOK);

        // Taxa de juros anual
        double taxa = -1;
        do {
            System.out.println("Digite a taxa de juros anual (ex: 0,10 para 10%):");
            try {
                taxa = scanner.nextDouble();
                if (taxa >= 0) {
                    break; // Taxa válida, sai do loop
                } else {
                    System.out.println("❌ A taxa de juros não pode ser negativa.");
                }
            } catch (InputMismatchException e) {
                System.out.println("❌ Entrada inválida! Digite um número válido para a taxa de juros.");
                scanner.nextLine();
            }
        } while (true);

        int anos = 0;
        boolean anosValidos = false;
        do {
            System.out.println("Anos de financiamento:");
            try {
                anos = scanner.nextInt();
                if (anos > 0) {
                    anosValidos = true;
                } else {
                    System.out.println("❌ O prazo deve ser maior que zero.");
                }
            } catch (InputMismatchException e) {
                System.out.println("❌ Entrada inválida! Digite um número inteiro válido.");
                scanner.nextLine();
            }
        } while (!anosValidos);

        Financiamento f1;
        if (tipo == 1) f1 = new Casa(valor, taxa, anos, 7, 10);
        else if (tipo == 2) f1 = new Apartamento(valor, taxa, anos, 10, 6);
        else f1 = new Terreno(valor, taxa, anos, "Urbano");

        lista.add(f1);

        // 2. Financiamento via interface
        Financiamento financiamentoUsuario = ui.executar();
        if (financiamentoUsuario != null) {
            lista.add(financiamentoUsuario);
        }

        // 3. Financiamentos prontos
        lista.add(new Casa(25000, 0.09, 10, 5, 8));
        lista.add(new Apartamento(50000, 0.05, 10, 8, 12));
        lista.add(new Terreno(20000, 0.07, 8, "Urbano"));

        double totalImoveis = 0;
        double totalFinanciamentos = 0;

        System.out.println("\nRelatório dos Financiamentos:");
        System.out.println("---- Financiamentos para amostra abaixo! ----");
        for (Financiamento f : lista) {
            if (f == null) continue; // Evita erro se alguém adicionou null

            double parcela = f.calcularPagamentoMensal();
            double total = parcela * f.getPrazoFinanciamento() * 12;

            totalImoveis += f.getValorImovel();
            totalFinanciamentos += total;

            System.out.printf("\nTipo: %s\n", f.getTipo());
            if (f instanceof Casa) {
                Casa casa = (Casa) f;
                System.out.printf("Área da casa construída: %.2f m², Área do terreno: %.2f metros\n",
                        casa.getAreaConstruida(), casa.getAreaTerreno());

            } else if (f instanceof Apartamento) {
                Apartamento apto = (Apartamento) f;
                System.out.printf("Vagas de garagem: %d, Número de andares: %d\n", apto.getNumeroVagasGaragem(), apto.getNumerosoAndares());
            } else if (f instanceof Terreno) {
                Terreno terreno = (Terreno) f;
                System.out.printf("Tipo de zona: %s\n", terreno.getTipoZona());
            }
            System.out.printf("Valor do Imóvel: R$ %.2f\n", f.getValorImovel());
            System.out.printf("Parcela mensal: R$ %.2f\n", parcela);
            System.out.printf("Prazo do financiamento: %d anos\n", f.getPrazoFinanciamento());
            System.out.printf("Total do financiamento: R$ %.2f\n", total);

        }

        System.out.printf("\nSoma total dos imóveis: R$ %.2f\n", totalImoveis);
        System.out.printf("Soma total dos financiamentos: R$ %.2f\n", totalFinanciamentos);

        // 4. Salvar dados em arquivo
        try (FileWriter escritor = new FileWriter("ImoveisFinanciados.txt")) {
            for (Financiamento f : lista) {
                escritor.write(f.toString() + "\n");
                escritor.write("------------------------------------\n");
            }
            System.out.println("\n✅ Dados salvos no arquivo ImoveisFinanciados.txt com sucesso!");
        } catch (IOException e) {
            System.out.println("❌ Erro ao salvar no arquivo: " + e.getMessage());
        }

        scanner.close();
    }
}
