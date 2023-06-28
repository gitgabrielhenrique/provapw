package br.com.etec.gabriel.provapw.repository;

import br.com.etec.gabriel.provapw.model.ContasReceber;
import br.com.etec.gabriel.provapw.repository.contas.ContasReceberRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContasReceberRepository extends JpaRepository<ContasReceber,Integer>, ContasReceberRepositoryQuery {
}
