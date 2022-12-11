package br.com.docesdalu.servicoencomenda.infrastructure.repository;

import br.com.docesdalu.servicoencomenda.core.encomenda.Encomenda;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncomendaRepository extends ElasticsearchRepository<Encomenda, String> {
}
