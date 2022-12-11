package br.com.docesdalu.servicoencomenda.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    private String id;
    private Usuario usuario;
    private Produto produto;
    private String email;
    private Integer quantidadePedido;
    private Double valorTotal;
    private String statusPedido;
    private Date dataPedido = new Date();
}