package Modelo;

import Util.AumentoMaiorDoQueJurosException;

public class Casa extends Financiamento {

    private double areaConstruida;
    private double areaTerreno;

    public Casa(double valorImovel, double taxaJurosAnual, int prazoFinanciamento, double areaConstruida, double areaTerreno) {
        super(valorImovel, taxaJurosAnual, prazoFinanciamento);
        this.areaConstruida = areaConstruida;
        this.areaTerreno = areaTerreno;
    }

    private void validarSeAcrescimoMaiorQueMetadeDosJuros(double valorJuros, double valorAcrescimo) throws AumentoMaiorDoQueJurosException {
        if (valorAcrescimo > valorJuros / 2) {
            throw new AumentoMaiorDoQueJurosException("O acréscimo não pode ser maior que a metade dos juros do mês. Ajustando para o valor dos juros.");
        }
    }

    @Override
    public double calcularPagamentoMensal() {
        double taxaMensal = taxaJurosAnual / 12;
        int n = prazoFinanciamento * 12;
        double fator = Math.pow(1 + taxaMensal, n);

        double pagamentoMensal = (valorImovel * taxaMensal * fator) / (fator - 1);

        double jurosDoMes = valorImovel * taxaMensal;

        // Acréscimo fixo de R$ 80,00 que sempre será somado
        double acrescimoFixo = 80;

        // Por enquanto, vou colocar 0, você pode alterar para o valor desejado
        double outroAcrescimo = 50;

        try {
            validarSeAcrescimoMaiorQueMetadeDosJuros(jurosDoMes, outroAcrescimo);
        } catch (AumentoMaiorDoQueJurosException e) {
            System.out.println(e.getMessage());
            outroAcrescimo = jurosDoMes; // Ajusta o outro acréscimo para o valor dos juros
        }

        // Retorna a soma da parcela, acrescimo fixo e outro acréscimo ajustado
        return pagamentoMensal + acrescimoFixo + outroAcrescimo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tipo: Casa\n")
                .append("Área construída: ").append(String.format("%.2f", areaConstruida)).append(" m²\n")
                .append("Área do terreno: ").append(String.format("%.2f", areaTerreno)).append(" m²\n")
                .append("Valor do Imóvel: R$ ").append(String.format("%.2f", valorImovel)).append("\n")
                .append("Taxa de Juros Anual: ").append(String.format("%.2f%%", taxaJurosAnual * 100)).append("\n")
                .append("Prazo do Financiamento: ").append(prazoFinanciamento).append(" anos\n")
                .append("Parcela mensal: R$ ").append(String.format("%.2f", calcularPagamentoMensal()));
        return sb.toString();
    }


    @Override
    public String getTipo() {
        return "Casa";
    }

    public double getAreaConstruida() {
        return areaConstruida;
    }

    public void setAreaConstruida(double areaConstruida) {
        this.areaConstruida = areaConstruida;
    }

    public double getAreaTerreno() {
        return areaTerreno;
    }

    public void setAreaTerreno(double areaTerreno) {
        this.areaTerreno = areaTerreno;
    }

}
