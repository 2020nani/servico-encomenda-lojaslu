package br.com.docesdalu.servicoencomenda.core.encomenda;

import br.com.docesdalu.servicoencomenda.application.dto.output.EncomendaOutput;
import br.com.docesdalu.servicoencomenda.infrastructure.repository.EncomendaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    @Override
    @Cacheable(value = "encomendaCache")
    public List<EncomendaOutput> findAll() {
        System.out.println("chamou");
        return StreamSupport.stream(encomendaRepository.findAll().spliterator(), false)
                .map(EncomendaOutput::new)
                .collect(Collectors.toList());
    }
}
