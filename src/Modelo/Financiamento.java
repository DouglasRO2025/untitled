package Modelo;

public abstract class Financiamento {

    // Atributos Privados
    protected double valorImovel;
    protected double taxaJurosAnual;
    protected int prazoFinanciamento; // em anos;


    // Construtor
    public Financiamento(double valorImovel,double taxaJurosAnual, int prazoFinanciamento) {
        this.valorImovel = valorImovel;
        this.taxaJurosAnual = taxaJurosAnual;
        this.prazoFinanciamento = prazoFinanciamento;
    }

    // Getters e Setters
    public double getValorImovel() {
        return valorImovel;
    }

    public int getPrazoFinanciamento() {
        return prazoFinanciamento;
    }

    public double getTaxaJurosAnual() {
        return taxaJurosAnual;
    }

    public void setValorImovel(double valorImovel) {
        this.valorImovel = valorImovel;
    }

    public void setPrazoFinanciamento(int prazoFinanciamento) {
        this.prazoFinanciamento = prazoFinanciamento;
    }

    public void setTaxaJurosAnual(double taxaJurosAnual) {
        this.taxaJurosAnual = taxaJurosAnual;
    }

    public String getTipo() {
        return "Genérico";
    }

    // Método para calcular o pagamento mensal (tabela Price)
    public double calcularPagamentoMensal() {
        int totalMeses = prazoFinanciamento * 12;
        double taxaMensal = taxaJurosAnual / 12;
        double pagamento = (valorImovel * taxaMensal) / (1 - Math.pow(1 + taxaMensal, -totalMeses));
        return pagamento;
    }

    // Método para calcular o valor total pago ao longo do financiamento
    public double calcularTotalPagamento() {
        return calcularPagamentoMensal() * prazoFinanciamento * 12;
    }

    // Mostra amortização detalhada mês a mês
    public void mostrarAmortizacaoDetalhada() {
        int totalMeses = prazoFinanciamento * 12;
        double taxaMensal = taxaJurosAnual / 12;
        double parcelaFixa = calcularPagamentoMensal();
        double saldoDevedor = valorImovel;

        System.out.println("\nDetalhamento mês a mês da amortização:");
        System.out.printf("%-5s %-15s %-15s %-15s %-15s%n",
                "Mês", "Parcela", "Juros", "Amortização", "Saldo Devedor");

        for (int mes = 1; mes <= totalMeses; mes++) {
            double jurosMes = saldoDevedor * taxaMensal;
            double amortizacaoMes = parcelaFixa - jurosMes;
            saldoDevedor -= amortizacaoMes;

            if (saldoDevedor < 0) saldoDevedor = 0;

            System.out.printf("%-5d R$ %-13.2f R$ %-13.2f R$ %-13.2f R$ %-13.2f%n",
                    mes, parcelaFixa, jurosMes, amortizacaoMes, saldoDevedor);

        }
    }
}
