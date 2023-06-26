package br.com.etec.gabriel.provapw.repository.projections;

import java.math.BigDecimal;
import java.util.Date;

public class ContasDto {
  private Integer contasid;
    private Date dataconta;
    private BigDecimal valorconta;
    private String nomeCliente;

  public ContasDto(Integer contasid, Date dataconta, BigDecimal valorconta, String nomecliente) {
    this.contasid = contasid;
    this.dataconta = dataconta;
    this.valorconta = valorconta;
    this.nomeCliente = nomecliente;
  }

  public Integer getContasid() {
    return contasid;
  }

  public void setContasid(Integer contasid) {
    this.contasid = contasid;
  }

  public Date getDataconta() {
    return dataconta;
  }

  public void setDataconta(Date dataconta) {
    this.dataconta = dataconta;
  }

  public BigDecimal getValorconta() {
    return valorconta;
  }

  public void setValorconta(BigDecimal valorconta) {
    this.valorconta = valorconta;
  }

  public String getNomecliente() {
    return nomeCliente;
  }

  public void setNomecliente(String nomecliente) {
    this.nomeCliente = nomecliente;
  }
}
