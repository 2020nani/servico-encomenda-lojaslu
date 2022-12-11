package br.com.docesdalu.servicoencomenda.application;

import br.com.docesdalu.servicoencomenda.core.Pedido;
import br.com.docesdalu.servicoencomenda.core.encomenda.Encomenda;
import br.com.docesdalu.servicoencomenda.core.encomenda.EncomendaService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class CadastroEncomendaListener {

    private EncomendaService encomendaService;

    public CadastroEncomendaListener(EncomendaService encomendaService) {
        this.encomendaService = encomendaService;
    }

    @RabbitListener(queues = "encomenda_queue")
    public void consumeMessageFromQueue(List<Pedido> pedidos) {
        Encomenda encomenda = Encomenda.builder()
                .id(UUID.randomUUID().toString())
                .pedidos(pedidos)
                .build();
        encomendaService.salvarEncomenda(encomenda);
    }

}
