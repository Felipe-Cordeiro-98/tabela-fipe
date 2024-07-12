package com.example.tabelafipe.principal;

import com.example.tabelafipe.model.Fipe;
import com.example.tabelafipe.model.Veiculo;
import com.example.tabelafipe.service.ApiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public final static String URL_BASE = "https://parallelum.com.br/fipe/api/v1";

    public static void exibeMenu() throws JsonProcessingException {

        Scanner sc = new Scanner(System.in);

        ApiService api = new ApiService();

        System.out.println("\n=====TABELA FIPE=====\n");
        System.out.println("Escolha o tipo de veículo que você deseja");
        System.out.println("1 - Carro");
        System.out.println("2 - Moto");
        System.out.println("3 - Caminhão\n");
        System.out.print("Digite sua opção: ");
        int n = sc.nextInt();

        String veiculo = "";
        switch (n) {
            case 1:
                veiculo = "/carros/marcas";
                break;
            case 2:
                veiculo = "/motos/marcas";
                break;
            case 3:
                veiculo = "/caminhoes/marcas";
                break;
            default:
                System.out.println("Escolha uma opção valida!!");
        }

        String endereco = URL_BASE + veiculo;
        String json = api.obterDados(endereco);

        ObjectMapper mapper = new ObjectMapper();

        List<Veiculo> veiculoList = mapper.readValue(json, new TypeReference<List<Veiculo>>() {
        });

        System.out.println("\n====Veiculos====");

        veiculoList.forEach(System.out::println);

        System.out.print("Digite o código da marca desejada: ");
        int codigoMarca = sc.nextInt();

        endereco = URL_BASE + veiculo + "/" + codigoMarca + "/modelos/";
        json = api.obterDados(endereco);

        JsonNode jsonNode = mapper.readTree(json);
        JsonNode modelos = jsonNode.get("modelos");
        Iterator<JsonNode> elements = modelos.elements();

        System.out.println("\n====Modelos====");

        while (elements.hasNext()) {
            JsonNode node = elements.next();
            System.out.println("Descrição: " + node.get("nome").asText());
            System.out.println("Código: " + node.get("codigo").asText() + "\n");
        }

        System.out.print("Digite o código do modelo desejado: ");
        int codigoModelo = sc.nextInt();

        endereco = URL_BASE + veiculo + "/" + codigoMarca + "/modelos/" + codigoModelo + "/anos";
        json = api.obterDados(endereco);
        System.out.println("\n====Ano do veiculo====");

        List<Veiculo> anoList = mapper.readValue(json, new TypeReference<List<Veiculo>>() {
        });
        anoList.forEach(System.out::println);

        System.out.print("Digite o código referente ao ano do veiculo: ");
        String codigoAno = sc.next();

        endereco = URL_BASE + veiculo + "/" + codigoMarca + "/modelos/" + codigoModelo + "/anos/" + codigoAno;
        json = api.obterDados(endereco);
        System.out.println("\n====Tabela Fipe Do Veiculo====");

        Fipe fipe = mapper.readValue(json, Fipe.class);
        System.out.println(fipe);
    }
}
