package br.com.application.fipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Dados(
    @JsonAlias("codigo") String codigo,
    @JsonAlias("nome") String nome
    ) {
        @Override 
        public String toString() {
            return "Código:%s - Nome:%s".formatted(this.codigo, this.nome);
        }
}
