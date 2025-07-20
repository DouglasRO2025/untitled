package Modelo;

public class Apartamento extends Financiamento {
    private int numeroVagasGaragem;
    private int numerosoAndares;

    public Apartamento(double valorImovel, double taxaJurosAnual, int prazoFinanciamento, int numeroVagasGaragem, int numerosoAndares) {
        super(valorImovel, taxaJurosAnual, prazoFinanciamento);
        this.numeroVagasGaragem = numeroVagasGaragem;
        this.numerosoAndares = numerosoAndares;
    }

    @Override
    public double calcularPagamentoMensal() {
        double taxaMensal = taxaJurosAnual / 12;
        int n = prazoFinanciamento * 12;
        double fator = Math.pow(1 + taxaMensal, n);
        return (valorImovel * taxaMensal * fator) / (fator - 1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tipo: Apartamento\n")
                .append("Vagas de garagem: ").append(numeroVagasGaragem).append("\n")
                .append("Número de andares: ").append(numerosoAndares).append("\n")
                .append("Valor do Imóvel: R$ ").append(String.format("%.2f", valorImovel)).append("\n")
                .append("Taxa de Juros Anual: ").append(String.format("%.2f%%", taxaJurosAnual * 100)).append("\n")
                .append("Prazo do Financiamento: ").append(prazoFinanciamento).append(" anos\n")
                .append("Parcela mensal: R$ ").append(String.format("%.2f", calcularPagamentoMensal()));
        return sb.toString();
    }

    public int getNumeroVagasGaragem() {
        return numeroVagasGaragem;
    }

    public void setNumeroVagasGaragem(int numeroVagasGaragem) {
        this.numeroVagasGaragem = numeroVagasGaragem;
    }

    public int getNumerosoAndares() {
        return numerosoAndares;
    }

    public void setNumerosoAndares(int numerosoAndares) {
        this.numerosoAndares = numerosoAndares;
    }

    @Override
    public String getTipo() {
        return "Apartamento";
    }
}
