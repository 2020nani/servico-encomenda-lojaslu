package br.com.docesdalu.servicoencomenda.application.dto.output;

import br.com.docesdalu.servicoencomenda.core.Pedido;
import br.com.docesdalu.servicoencomenda.core.encomenda.Encomenda;
import br.com.docesdalu.servicoencomenda.core.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EncomendaOutput {
    private String id;
    private StatusPedido statusPedido;
    List<PedidoOutput> pedidos;
    private Double valorTotal;
    private Date dataPedido;

    public EncomendaOutput(Encomenda encomenda) {
        this.id=encomenda.getId();
        this.pedidos=encomenda.getPedidos().stream().map(PedidoOutput::new).collect(Collectors.toList());
        this.statusPedido=encomenda.getStatusPedido();
        this.dataPedido=encomenda.getDataPedido();
    }

}
