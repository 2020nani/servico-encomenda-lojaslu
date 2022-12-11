package br.com.docesdalu.servicoencomenda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.docesdalu.servicoencomenda")
public class ServicoEncomendaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicoEncomendaApplication.class, args);
	}

}
