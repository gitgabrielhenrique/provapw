package br.com.etec.gabriel.provapw.repository.filter;

import java.math.BigDecimal;
import java.util.Date;

public class ContasFilter {
  private Integer contasid;
  private Date dataconta;
  private BigDecimal valorConte;
private String nomeCliente;

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

  public BigDecimal getValorConte() {
    return valorConte;
  }

  public void setValorConte(BigDecimal valorConte) {
    this.valorConte = valorConte;
  }

  public String getNomeCliente() {
    return nomeCliente;
  }

  public void setNomeCliente(String nomeCliente) {
    this.nomeCliente = nomeCliente;
  }
}
