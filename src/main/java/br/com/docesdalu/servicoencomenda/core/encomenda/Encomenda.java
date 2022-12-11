package br.com.docesdalu.servicoencomenda.core.encomenda;

import br.com.docesdalu.servicoencomenda.core.Pedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "encomenda_index")
public class Encomenda {
    @Id
    private String id;

    @Field(type = FieldType.Auto)
    List<Pedido> pedidos;
}
