package br.com.application.fipe;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.application.fipe.model.DadosMarcas;
import br.com.application.fipe.service.ConsumoApi;
import br.com.application.fipe.service.ConverteDados;

@SpringBootApplication
public class FipeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FipeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String url = "https://parallelum.com.br/fipe/api/v1/carros/marcas/";
		
		List<DadosMarcas> listaMarcas = new ArrayList<>(); 
		
		ConsumoApi connect = new ConsumoApi();
		String json = connect.obterDados(url);
		ConverteDados conversor = new ConverteDados();

		DadosMarcas dados = conversor.obterDados(json, DadosMarcas.class);

		System.out.println(dados);
	}

}
