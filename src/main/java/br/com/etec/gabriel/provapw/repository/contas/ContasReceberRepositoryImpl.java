package br.com.etec.gabriel.provapw.repository.contas;

import br.com.etec.gabriel.provapw.model.ContasReceber;
import br.com.etec.gabriel.provapw.repository.filter.ContasReceberFilter;
import br.com.etec.gabriel.provapw.repository.projections.ContasDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ContasReceberRepositoryImpl implements ContasReceberRepositoryQuery {


  @Autowired
  private EntityManager manager;

  @Override
  public Page<ContasDto> filtrar(ContasReceberFilter contasReceberFilter, Pageable pageable) {
    CriteriaBuilder builder = manager.getCriteriaBuilder();
    CriteriaQuery<ContasDto> criteria = builder.createQuery(ContasDto.class);
    Root<ContasReceber> root = criteria.from(ContasReceber.class);

    criteria.select(builder.construct(ContasDto.class,
      root.get("id")
      ,root.get("dataconta")
      ,root.get("valorConte")
      ,root.get("cliente").get("nomeCliente")
    ));

    Predicate[] predicates = criarRestricoes(builder, contasReceberFilter, root);
    criteria.where(predicates);
    criteria.orderBy(builder.asc(root.get("cliente").get("nomeCliente")));

    TypedQuery<ContasDto> query = manager.createQuery(criteria);
    adicionarRestricoesDaPaginacao(query, pageable);

    return new PageImpl<>(query.getResultList(),pageable, total(contasReceberFilter));
  }
  private void adicionarRestricoesDaPaginacao(TypedQuery<?> query, Pageable pageable)
  {
    int paginaatual = pageable.getPageNumber();
    int totalRegistroPorPagina = pageable.getPageSize();
    int primeiroRegistroDaPagina = paginaatual * totalRegistroPorPagina;

    query.setFirstResult(primeiroRegistroDaPagina);
    query.setMaxResults(totalRegistroPorPagina);
  }
  private Long total(ContasReceberFilter contasReceberFilter)
  {
    CriteriaBuilder builder = manager.getCriteriaBuilder();
    CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
    Root<ContasReceber> root = criteria.from(ContasReceber.class);

    Predicate[] predicates = criarRestricoes(builder, contasReceberFilter, root);
    criteria.where(predicates);
    criteria.orderBy(builder.asc(root.get("cliente").get("nomeCliente")));
    criteria.select(builder.count(root));
    return manager.createQuery(criteria).getSingleResult();
  }

  private Predicate[] criarRestricoes(CriteriaBuilder builder, ContasReceberFilter contasReceberFilter, Root<ContasReceber> root) {
    List<Predicate> predicates = new ArrayList<>();

    if (contasReceberFilter.getDataconta() !=null)
    {
    predicates.add(builder.greaterThanOrEqualTo(root.get("dataconta"), contasReceberFilter.getDataconta())
    );
    }

    if (!StringUtils.isEmpty(contasReceberFilter.getNomeCliente()))
    {
      predicates.add(builder.like(builder.lower(root.get("cliente").get("nomeCliente")),
        "%" + contasReceberFilter.getNomeCliente().toLowerCase() + "%"
      ));
    }

    if (contasReceberFilter.getValorConta()!=null)
    {
      predicates.add(builder.greaterThanOrEqualTo(root.get("valorConta"), contasReceberFilter.getValorConta())
      );
    }

    return predicates.toArray(new Predicate[predicates.size()]);

  }


}
