package br.com.etec.gabriel.provapw.repository.contas;

import br.com.etec.gabriel.provapw.model.ContasReceber;
import br.com.etec.gabriel.provapw.repository.filter.ContasFilter;
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

public class ContasRepositoryimpl {


  @Autowired
  private EntityManager manager;

  @Override
  public Page<ContasDto> filtrar(ContasFilter contasFilter, Pageable pageable) {
    CriteriaBuilder builder = manager.getCriteriaBuilder();
    CriteriaQuery<ContasDto> criteria = builder.createQuery(ContasDto.class);
    Root<ContasReceber> root = criteria.from(ContasReceber.class);

    criteria.select(builder.construct(ContasDto.class,
      root.get("contasid")
      ,root.get("dataconta")
      ,root.get("valorconta")
      ,root.get("cidade").get("nomecidade")
      ,root.get("Cliente").get("nomecliente")
    ));

    Predicate[] predicates = criarRestricoes(builder, alunoFilter, root);
    criteria.where(predicates);
    criteria.orderBy(builder.asc(root.get("nomealuno")));

    TypedQuery<AlunoDto> query = manager.createQuery(criteria);
    adicionarRestricoesDaPaginacao(query, pageable);

    return new PageImpl<>(query.getResultList(),pageable, total(alunoFilter));
  }
  private void adicionarRestricoesDaPaginacao(TypedQuery<?> query, Pageable pageable)
  {
    int paginaatual = pageable.getPageNumber();
    int totalRegistroPorPagina = pageable.getPageSize();
    int primeiroRegistroDaPagina = paginaatual * totalRegistroPorPagina;

    query.setFirstResult(primeiroRegistroDaPagina);
    query.setMaxResults(totalRegistroPorPagina);
  }
  private Long total(AlunoFilter alunoFilter)
  {
    CriteriaBuilder builder = manager.getCriteriaBuilder();
    CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
    Root<Aluno> root = criteria.from(Aluno.class);

    Predicate[] predicates = criarRestricoes(builder, alunoFilter, root);
    criteria.where(predicates);
    criteria.orderBy(builder.asc(root.get("nomealuno")));
    criteria.select(builder.count(root));
    return manager.createQuery(criteria).getSingleResult();
  }

  private Predicate[] criarRestricoes(CriteriaBuilder builder, AlunoFilter alunoFilter, Root<Aluno> root) {
    List<Predicate> predicates = new ArrayList<>();

    if (!StringUtils.isEmpty(alunoFilter.getNomealuno()))
    {
      predicates.add(builder.like(builder.lower(root.get("nomealuno ")),
        "%" + alunoFilter.getNomealuno().toLowerCase() + "%"
      ));
    }

    if (!StringUtils.isEmpty(alunoFilter.getNomecidade()))
    {
      predicates.add(builder.like(builder.lower(root.get("cidade").get("nomecidade")),
        "%" + alunoFilter.getNomecidade().toLowerCase() + "%"
      ));
    }

    if (!StringUtils.isEmpty(alunoFilter.getUf() ))
    {
      predicates.add(builder.equal(builder.lower(root.get("cidade").get("uf")),
        alunoFilter.getUf().toLowerCase()
      ));

      if (!StringUtils.isEmpty(alunoFilter.getNomecurso()))
      {
        predicates.add(builder.like(builder.lower(root.get("curso").get("nomecurso")),
          "%" + alunoFilter.getNomecurso().toLowerCase() + "%"
        ));
      }
    }

    return predicates.toArray(new Predicate[predicates.size()]);

  }


}
