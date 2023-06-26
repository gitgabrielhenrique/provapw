package br.com.etec.gabriel.provapw.resource;

import br.com.etec.gabriel.provapw.model.ContasReceber;
import br.com.etec.gabriel.provapw.repository.ContasRepository;
import br.com.etec.gabriel.provapw.repository.filter.ContasFilter;
import br.com.etec.gabriel.provapw.repository.projections.ContasDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@RequestMapping("/contas")
public class ContasResource {
  @Autowired
private ContasRepository contasRepository;

  @GetMapping("/todos")
public List<ContasReceber> ListarTodasAsContas(){
return contasRepository.findAll();
  }

@GetMapping
  public Page<ContasDto> pesquisar(ContasFilter contasFilter, Pageable pageable){
return contasRepository.filtrar(contasFilter,pageable);
}

}
