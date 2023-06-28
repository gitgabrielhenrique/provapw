package br.com.etec.gabriel.provapw.repository.contas;

import br.com.etec.gabriel.provapw.repository.filter.ContasReceberFilter;
import br.com.etec.gabriel.provapw.repository.projections.ContasDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContasReceberRepositoryQuery {
public Page<ContasDto> filtrar(ContasReceberFilter contasReceberFilter, Pageable pageable);
}
