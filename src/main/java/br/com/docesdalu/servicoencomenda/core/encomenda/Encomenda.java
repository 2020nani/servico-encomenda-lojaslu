package br.com.docesdalu.servicoencomenda.core.encomenda;

import br.com.docesdalu.servicoencomenda.core.Pedido;
import br.com.docesdalu.servicoencomenda.core.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "encomenda_index")
public class Encomenda {
    @Id
    private String id;

    @Field(type = FieldType.Text, name = "status_pedido")
    private StatusPedido statusPedido;

    @Field(type = FieldType.Auto)
    List<Pedido> pedidos;

    @Field(type = FieldType.Double, name = "valor_total")
    private Double valorTotal;

    @Field(type = FieldType.Date, name = "data_pedido")
    private Date dataPedido = new Date();


}
