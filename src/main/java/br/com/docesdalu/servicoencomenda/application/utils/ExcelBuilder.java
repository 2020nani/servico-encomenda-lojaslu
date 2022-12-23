package br.com.docesdalu.servicoencomenda.application.utils;


import br.com.docesdalu.servicoencomenda.application.exceptions.InvalidTemplateFormatException;
import com.google.gson.Gson;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class builds an Excel spreadsheet document using Apache POI library.
 * @author www.codejava.net
 *
 */
@Component
public class ExcelBuilder {

    private final List<String> columnNames = List.of("nome", "cpf");
    private final DataFormatter dataFormatter = new DataFormatter();
    private static Set<String> blackListCpf = new HashSet<>();

    public ResponseEntity<?> buildExcelDocument(final MultipartFile file) throws IOException, InvalidFormatException, NoSuchAlgorithmException {
        final var workBook = new XSSFWorkbook(file.getInputStream());
        final var workSheet = workBook.getSheetAt(0);
        final var initialRow = workSheet.getRow(0);
        final var numberOfRowsInWorkSheet = workSheet.getLastRowNum();
        checkForValidFormat(workBook, initialRow);
        final var errorMessages = new ArrayList<String>();

        for (int row = 0; row < numberOfRowsInWorkSheet; row++) {
            final var currentRow = workSheet.getRow(row + 1);

            for (int cell = 0; cell < currentRow.getLastCellNum(); cell++) {
                var currentCell = currentRow.getCell(cell);
                if (currentCell != null) {
                    if (cell == 0)
                        validateCellForName(dataFormatter.formatCellValue(currentCell), errorMessages, row + 1,
                                cell + 1);
                    if (cell == 1)
                        validateCellForCPF(dataFormatter.formatCellValue(currentCell), errorMessages, row + 1,
                                cell + 1);
                } else
                    errorMessages.add("No Value Present At Row " + row + 1 + " Cell " + cell + 1);
            }

        }
        workBook.close();
        if (errorMessages.isEmpty()) {
            final var response = new JSONObject();
            final var fileMessageDigest = MessageDigest.getInstance("MD5");
            response.put("message", "Nenhum erro encontrado nos cpfs cadastrados");
            response.put("timestamp", LocalDateTime.now().toString());
            return ResponseEntity.ok(response.toString());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Gson().toJson(errorMessages));
    }

    private void validateCellForName(final String cellValue, final ArrayList<String> errorMessages, Integer rowIndex,
                                      Integer cellIndex) {
        if (cellValue.length() <= 3)
            errorMessages.add("Nome precisa ter mais que 3 caracteres " + rowIndex + " Cell " + cellIndex);

    }

    private void validateCellForCPF(@Valid final String cellValue, final ArrayList<String> errorMessages, Integer rowIndex,
                                      Integer cellIndex) {

        if (!cellValue.matches("(^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$)|(^\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}$)"))
            errorMessages.add("Numero do cpf " + cellValue + " precisa seguir o padrao xxx.xxx.xxx-xx " + rowIndex + " Cell "
                    + cellIndex);
        this.getBlackListCpf().add(cellValue);
        System.out.println(blackListCpf);
    }

    private void checkForValidFormat(final XSSFWorkbook workBook, final XSSFRow initialRow) throws IOException {
        if (initialRow.getLastCellNum() != columnNames.size())
            throw new InvalidTemplateFormatException();

        for (int cell = 0; cell < initialRow.getLastCellNum(); cell++) {
            final var currentCell = initialRow.getCell(cell);
            if (!dataFormatter.formatCellValue(currentCell).equals(columnNames.get(cell))) {
                workBook.close();
                throw new InvalidTemplateFormatException();
            }
        }
    }

    public static Set<String> blackListCpf() {
        return blackListCpf;
    }

    public Set<String> getBlackListCpf() {
        return blackListCpf;
    }

    public void setBlackListCpf(Set<String> blackListCpf) {
        this.blackListCpf = blackListCpf;
    }
}
