package br.ufal.ic.p2.wepayu.models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EmpregadoComissionado extends Empregado {
    private BigDecimal salarioMensal;
    private BigDecimal comissao;
    private List<ResultadoVenda> resultadosVendas = new ArrayList<>();

    public EmpregadoComissionado() {
        super();
    }

    public EmpregadoComissionado(String id, String nome, String endereco, BigDecimal salarioMensal, BigDecimal comissao) {
        super(id, nome, endereco);
        this.salarioMensal = salarioMensal.setScale(2, RoundingMode.HALF_EVEN);
        this.comissao = comissao.setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public String getTipo() {
        return "comissionado";
    }

    @Override
    public BigDecimal getSalario() {
        return salarioMensal;
    }

    public String getComissaoFormatada() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
        DecimalFormat df = new DecimalFormat("#,##0.00", symbols);
        df.setGroupingUsed(false);
        return df.format(comissao);
    }

    public void setSalarioMensal(BigDecimal salarioMensal) {
        this.salarioMensal = salarioMensal;
    }

    public void setComissao(BigDecimal comissao) {
        this.comissao = comissao;
    }

    public List<ResultadoVenda> getResultadosVendas() {
        return resultadosVendas;
    }

    public void setResultadosVendas(List<ResultadoVenda> resultadosVendas) {
        this.resultadosVendas = resultadosVendas;
    }

    public String getSalarioMensalStr() {
        return salarioMensal != null ? salarioMensal.toPlainString() : null;
    }

    public void setSalarioMensalStr(String salarioMensalStr) {
        if (salarioMensalStr != null) {
            this.salarioMensal = new BigDecimal(salarioMensalStr);
        }
    }

    public String getComissaoStr() {
        return comissao != null ? comissao.toPlainString() : null;
    }

    public void setComissaoStr(String comissaoStr) {
        if (comissaoStr != null) {
            this.comissao = new BigDecimal(comissaoStr);
        }
    }
}