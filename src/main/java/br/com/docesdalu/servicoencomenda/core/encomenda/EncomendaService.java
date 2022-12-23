package br.com.docesdalu.servicoencomenda.core.encomenda;

import br.com.docesdalu.servicoencomenda.application.dto.output.EncomendaOutput;

import java.util.List;

public interface EncomendaService {
    public void salvarEncomenda(Encomenda encomenda);

    List<EncomendaOutput> findAll();
}
