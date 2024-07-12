package com.example.tabelafipe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({"TipoVeiculo", "Combustivel", "CodigoFipe", "MesReferencia", "SiglaCombustivel"})
public record Fipe(
        @JsonProperty("Marca")
        String marca,
        @JsonProperty("Modelo")
        String modelo,
        @JsonProperty("AnoModelo")
        String anoModelo,
        @JsonProperty("Valor")
        String valor) {

    @Override
    public String toString() {
        return "Marca: " + marca + "\n"
                + "Modelo: " + modelo + "\n"
                + "AnoModelo: " + anoModelo + "\n"
                + "Valor: " + valor;
    }
}
