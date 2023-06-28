package br.com.etec.gabriel.provapw.resource;

import br.com.etec.gabriel.provapw.model.Cliente;
import br.com.etec.gabriel.provapw.repository.ClienteRepository;
import br.com.etec.gabriel.provapw.repository.filter.ClienteFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {


  @Autowired
  private ClienteRepository clienteRepository;


  @GetMapping("/todos")
  public List<Cliente> ListartodosCLientes(){
    return clienteRepository.findAll();

  }
  @GetMapping()
  public Page<Cliente> pesquisar(ClienteFilter clienteFilter, Pageable pageable){
    return clienteRepository.Filtrar(clienteFilter,pageable);
  }


}
