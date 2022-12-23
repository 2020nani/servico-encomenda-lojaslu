package br.com.docesdalu.servicoencomenda.application.dto.output;

import br.com.docesdalu.servicoencomenda.core.Pedido;
import br.com.docesdalu.servicoencomenda.core.Produto;
import br.com.docesdalu.servicoencomenda.core.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoOutput {
    private String id;
    private Usuario usuario;
    private Produto produto;
    private String email;
    private Integer quantidadePedido;
    private Double valorTotal;
    private String statusPedido;
    private Date dataPedido;

    public PedidoOutput(Pedido pedido) {
        this.id = pedido.getId();
        this.usuario=pedido.getUsuario();
        this.produto=pedido.getProduto();
        this.email=pedido.getEmail();
        this.quantidadePedido=pedido.getQuantidadePedido();
        this.valorTotal=pedido.getValorTotal();
        this.statusPedido=pedido.getStatusPedido();
        this.dataPedido=pedido.getDataPedido();
    }

}
