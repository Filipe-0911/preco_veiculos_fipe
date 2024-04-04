package br.com.application.fipe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosVeiculo(String TipoVeiculo, 
                    String Valor, 
                    String Marca, 
                    String Modelo,
                    String AnoModelo,
                    String Combustivel,
                    String CodigoFipe,
                    String MesReferencia,
                    String SiglaCombustivel) {

    @Override
    public String toString() {
        String dadosVeiculo = "Modelo: %s;\nMarca:%s;\nValor: %s;\nAno Modelo: %s;\nCombustível: %s;\nMes de Referência: %s;\nCódigo Fipe: %s"
        .formatted(this.TipoVeiculo, this.Marca, this.Valor, this.AnoModelo, this.Combustivel, this.MesReferencia, this.CodigoFipe);

        return dadosVeiculo;
    }

}
