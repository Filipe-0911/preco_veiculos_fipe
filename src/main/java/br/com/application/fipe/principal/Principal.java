package br.com.application.fipe.principal;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import br.com.application.fipe.model.DadosMarcas;
import br.com.application.fipe.model.DadosModelos;
import br.com.application.fipe.model.DadosVeiculo;
import br.com.application.fipe.service.ConsumoApi;
import br.com.application.fipe.service.ConverteDados;

public class Principal {
    private String URL_BASE = "https://parallelum.com.br/fipe/api/v1/%s/marcas/";
    private Scanner in = new Scanner(System.in);
    private ConsumoApi connect = new ConsumoApi();
    private ConverteDados<DadosMarcas> conversor = new ConverteDados<>();
    private String json;

    public void exibeMenu() {
        System.out.println("Digite o NÚMERO da opção que deseja pesquisar: ");
        System.out.println("1- Carros;");
        System.out.println("2- Motos;");
        System.out.println("3- Caminhões;");

        int escolha = in.nextInt();
        String opcao = "";

        switch (escolha) {
            case 1:
                opcao = "carros";
                break;
            case 2:
                opcao = "motos";
                break;
            case 3:
                opcao = "caminhoes";
                break;
        }

        String url = URL_BASE.formatted(opcao);
        json = connect.obterDados(url);

        List<DadosMarcas> listaDadosMarcas = conversor.obterLista(json, DadosMarcas.class);

        listaDadosMarcas.stream()
                .sorted(Comparator.comparing(DadosMarcas::nome))
                .forEach(System.out::println);

        // MODELOS
        System.out.println("Digite o numero da marca que deseja verificar: ");
        String numeroMarca = in.next();
        json = connect.obterDados(url + numeroMarca + "/modelos/");

        DadosModelos listaDadosModelos = conversor.obterDados(json, DadosModelos.class);

        // Anos | modelo carros
        System.out.println("Digite o nome de um carro da marca escolhida (%s)".formatted("teste"));
        String nomeVeiculo = in.next();

        listaDadosModelos.listaModelos().stream()
            .filter(v -> v.nome().toLowerCase().contains(nomeVeiculo.toLowerCase()))
            .forEach(System.out::println);

        System.out.println("Digite o código do carro que você deseja verificar: ");
        String codigoModeloVeiculo = in.next();

        json = connect.obterDados(url + numeroMarca + "/modelos/" + codigoModeloVeiculo + "/anos/");
        
        List<DadosMarcas> listaDosAnosVeiculo = conversor.obterLista(json, DadosMarcas.class);

        listaDosAnosVeiculo.stream().forEach(System.out::println);

        // Escolha o ano do seu veiculo
        System.out.println("Escolha o ano do seu veículo: ");
        String anoModeloVeiculo = in.next();
        json = connect.obterDados(url + numeroMarca + "/modelos/" + codigoModeloVeiculo + "/anos/" + anoModeloVeiculo);
        DadosVeiculo dadosVeiculo = conversor.obterDados(json, DadosVeiculo.class);

        // Dados do veículo escolhido
        System.out.println("Estes são os dados do veículo que você escolheu: ");
        System.out.println(dadosVeiculo);

    }

}
