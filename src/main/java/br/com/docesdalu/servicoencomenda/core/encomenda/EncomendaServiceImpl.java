package br.com.docesdalu.servicoencomenda.core.encomenda;

import br.com.docesdalu.servicoencomenda.infrastructure.repository.EncomendaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EncomendaServiceImpl implements EncomendaService{

    private EncomendaRepository encomendaRepository;

    public EncomendaServiceImpl(EncomendaRepository encomendaRepository) {
        this.encomendaRepository = encomendaRepository;
    }

    @Override
    public void salvarEncomenda(Encomenda encomenda) {
        encomendaRepository.save(encomenda);
        log.info("Encomenda salva com sucesso");
    }
}
