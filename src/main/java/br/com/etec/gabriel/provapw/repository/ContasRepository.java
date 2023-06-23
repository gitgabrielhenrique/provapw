package br.com.etec.gabriel.provapw.repository;

import br.com.etec.gabriel.provapw.model.ContasReceber;
import br.com.etec.gabriel.provapw.repository.contas.ContasRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContasRepository extends JpaRepository<ContasReceber,Integer>, ContasRepositoryQuery {
}
