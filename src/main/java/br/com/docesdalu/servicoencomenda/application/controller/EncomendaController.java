package br.com.docesdalu.servicoencomenda.application.controller;

import br.com.docesdalu.servicoencomenda.application.dto.output.EncomendaOutput;
import br.com.docesdalu.servicoencomenda.application.utils.ExcelBuilder;
import br.com.docesdalu.servicoencomenda.core.encomenda.Encomenda;
import br.com.docesdalu.servicoencomenda.core.encomenda.EncomendaService;
import lombok.AllArgsConstructor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@AllArgsConstructor
public class EncomendaController {

    private EncomendaService encomendaService;
    private ExcelBuilder excelBuilder;

    @PostMapping(path = "cpf/blacklist",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> cadastraCpfBlackList(MultipartFile file) throws IOException, NoSuchAlgorithmException, InvalidFormatException {
        return excelBuilder.buildExcelDocument(file);
    };
    @GetMapping("encomenda")
    public List<EncomendaOutput> findAll(){
        return encomendaService.findAll();
    }
}
