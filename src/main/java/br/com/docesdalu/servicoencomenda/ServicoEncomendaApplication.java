package br.com.docesdalu.servicoencomenda;

import br.com.docesdalu.servicoencomenda.application.utils.ExcelBuilder;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.docesdalu.servicoencomenda")
@EnableCaching
@EnableAsync
public class ServicoEncomendaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicoEncomendaApplication.class, args);
	}

}
