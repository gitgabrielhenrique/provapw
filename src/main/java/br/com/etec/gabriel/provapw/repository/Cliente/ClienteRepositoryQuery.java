package br.com.etec.gabriel.provapw.repository.Cliente;

import br.com.etec.gabriel.provapw.model.Cliente;
import br.com.etec.gabriel.provapw.repository.filter.ClienteFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClienteRepositoryQuery {
  public Page<Cliente> Filtrar(ClienteFilter clienteFilter, Pageable pageable);
}
