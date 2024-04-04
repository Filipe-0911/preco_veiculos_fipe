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

}
