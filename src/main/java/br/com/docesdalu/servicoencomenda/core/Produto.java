package br.com.docesdalu.servicoencomenda.core;

import br.com.docesdalu.servicoencomenda.core.enums.Categoria;

import java.math.BigDecimal;

public class Produto {
    private Long id;
    private String nome;
    private Categoria categoria;
    private BigDecimal preco;
    private Integer quantidade;
    private String pathImagem;
}
