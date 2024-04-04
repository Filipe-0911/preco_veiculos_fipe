package br.com.application.fipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosMarcas(
    @JsonAlias("codigo") String codigo,
    @JsonAlias("nome") String nome
) {

}
