package Modelo;

public class Terreno extends Financiamento  {
    private String tipoZona;


    public Terreno(double valorImovel,double taxaJurosAnual, int prazoFinanciamento, String tipoZona) {
        super(valorImovel,taxaJurosAnual, prazoFinanciamento);
        this.tipoZona = tipoZona;
    }
    @Override
    public double calcularPagamentoMensal() {
        return super.calcularPagamentoMensal() * 1.02;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tipo: ").append(getTipo()).append("\n")
                .append("Tipo de zona: ").append(getTipoZona()).append("\n")
                .append("Valor do Imóvel: R$ ").append(String.format("%.2f", getValorImovel())).append("\n")
                .append("Taxa de Juros Anual: ").append(String.format("%.2f%%", getTaxaJurosAnual() * 100)).append("\n")
                .append("Prazo do Financiamento: ").append(getPrazoFinanciamento()).append(" anos\n")
                .append("Parcela mensal: R$ ").append(String.format("%.2f", calcularPagamentoMensal()));
        return sb.toString();
    }


    public String getTipoZona() {
        return tipoZona;
    }

    public void setTipoZona(String tipoZona) {
        this.tipoZona = tipoZona;
    }

    @Override
    public String getTipo() {
        return "Terreno";
    }
}