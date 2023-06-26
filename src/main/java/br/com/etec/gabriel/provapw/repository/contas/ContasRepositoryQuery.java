package br.com.etec.gabriel.provapw.repository.contas;

import br.com.etec.gabriel.provapw.repository.filter.ContasFilter;
import br.com.etec.gabriel.provapw.repository.projections.ContasDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContasRepositoryQuery {
public Page<ContasDto> filtrar(ContasFilter contasFilter, Pageable pageable);
}
