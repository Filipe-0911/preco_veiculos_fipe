package br.com.application.fipe.principal;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import br.com.application.fipe.model.Dados;
import br.com.application.fipe.model.DadosModelos;
import br.com.application.fipe.model.DadosVeiculo;
import br.com.application.fipe.service.ConsumoApi;
import br.com.application.fipe.service.ConverteDados;

public class Principal {
    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/%s/marcas/";
    private Scanner in = new Scanner(System.in);
    private ConsumoApi connect = new ConsumoApi();
    private ConverteDados<Dados> conversor = new ConverteDados<>();
    private String json;
    private String numeroMarca;
    private String opcao;

    public void exibeMenu() {
        System.out.println("Digite o NÚMERO da opção que deseja pesquisar: ");
        System.out.println("1- Carros;");
        System.out.println("2- Motos;");
        System.out.println("3- Caminhões;");

        int escolha = in.nextInt();

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

        List<Dados> listaDadosMarcas = conversor.obterLista(json, Dados.class);

        listaDadosMarcas.stream()
                .sorted(Comparator.comparing(Dados::nome))
                .forEach(System.out::println);

        // MODELOS
        System.out.println("Digite o numero da marca que deseja verificar: ");
        numeroMarca = in.next();
        url += numeroMarca + "/modelos/";

        json = connect.obterDados(url);

        DadosModelos listaDadosModelos = conversor.obterDados(json, DadosModelos.class);

        // Anos | modelo carros
        System.out.println("Digite o nome de um carro da marca escolhida (%s)".formatted("teste"));
        String nomeVeiculo = in.next();

        listaDadosModelos.listaModelos().stream()
            .filter(v -> v.nome().toLowerCase().contains(nomeVeiculo.toLowerCase()))
            .forEach(System.out::println);

        System.out.println("Digite o código do carro que você deseja verificar: ");
        String codigoModeloVeiculo = in.next();

        url+= codigoModeloVeiculo + "/anos/";

        json = connect.obterDados(url);
        
        List<Dados> listaDosAnosVeiculo = conversor.obterLista(json, Dados.class);

        listaDosAnosVeiculo.stream().forEach(System.out::println);


        // Escolha o ano do seu veiculo
        System.out.println("Escolha o ano do seu veículo: ");
        String anoModeloVeiculo = in.next();

        url += anoModeloVeiculo;

        json = connect.obterDados(url);
        DadosVeiculo dadosVeiculo = conversor.obterDados(json, DadosVeiculo.class);


        // Dados do veículo escolhido
        System.out.println("Estes são os dados do veículo que você escolheu: ");
        System.out.println(dadosVeiculo);

    }

}
