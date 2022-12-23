package br.com.docesdalu.servicoencomenda.application;

import br.com.docesdalu.servicoencomenda.application.utils.EmailService;
import br.com.docesdalu.servicoencomenda.application.utils.ExcelBuilder;
import br.com.docesdalu.servicoencomenda.core.Pedido;
import br.com.docesdalu.servicoencomenda.core.encomenda.Encomenda;
import br.com.docesdalu.servicoencomenda.core.encomenda.EncomendaService;
import br.com.docesdalu.servicoencomenda.core.enums.StatusPedido;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;

import static br.com.docesdalu.servicoencomenda.application.utils.CalculoValorEncomenda.valorTotalEncomenda;

@Component
public class CadastroEncomendaListener {

    private EncomendaService encomendaService;
    private EmailService emailService;

    public CadastroEncomendaListener(EncomendaService encomendaService, EmailService emailService) {
        this.encomendaService = encomendaService;
        this.emailService = emailService;
    }

    @RabbitListener(queues = "encomenda_queue")
    public void consumeMessageFromQueue(List<Pedido> pedidos) throws InterruptedException {
        if(ExcelBuilder.blackListCpf().contains("336.072.623-77")){
            emailService.senderEmailFraude();
            Encomenda encomenda = Encomenda.builder()
                    .id(UUID.randomUUID().toString())
                    .pedidos(pedidos)
                    .statusPedido(StatusPedido.FRAUDE)
                    .valorTotal(valorTotalEncomenda(pedidos))
                    .build();
            encomendaService.salvarEncomenda(encomenda);
        };
        Encomenda encomenda = Encomenda.builder()
                .id(UUID.randomUUID().toString())
                .pedidos(pedidos)
                .statusPedido(StatusPedido.AGUARDANDO_ATENDIMENTO)
                .valorTotal(valorTotalEncomenda(pedidos))
                .build();
        encomendaService.salvarEncomenda(encomenda);
    }

    @Async
    public Future<String> statusPedido(Boolean fraude) throws InterruptedException {
        emailService.senderEmail();
        Thread.sleep(5000);
        emailService.senderEmailOk();
        if(fraude){
            emailService.senderEmailFraude();
        }
        return new AsyncResult<>("ok");
    };

}
