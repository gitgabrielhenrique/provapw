package br.com.etec.gabriel.provapw.repository;

import br.com.etec.gabriel.provapw.model.Cliente;
import br.com.etec.gabriel.provapw.repository.Cliente.ClienteRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Integer>, ClienteRepositoryQuery {
}
