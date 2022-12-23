package br.com.docesdalu.servicoencomenda.application.dto.output;

import br.com.docesdalu.servicoencomenda.core.Pedido;
import br.com.docesdalu.servicoencomenda.core.enums.Categoria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdutoOutput {
    private Long id;
    private String nome;
    private Categoria categoria;
    private BigDecimal preco;
    private Integer quantidade;
    private String pathImagem;
}
