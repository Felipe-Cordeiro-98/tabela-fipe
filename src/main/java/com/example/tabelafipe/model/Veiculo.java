package com.example.tabelafipe.model;

public record Veiculo (String codigo, String nome) {

    @Override
    public String toString() {
        return "Descrição: " + nome + "\n"
                + "Código: " + codigo + "\n";
    }
}
