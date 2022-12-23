package br.com.docesdalu.servicoencomenda.core.encomenda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EncomendaBlackList {
    @NotNull
    @Length(min = 3, message = "O nome tem que ter mais de 2 caracteres")
    private String nome;

    @NotNull
    @CPF
    private String cpf;
}
