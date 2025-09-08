package br.ufal.ic.p2.wepayu.models;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class EmpregadoAssalariado extends Empregado {
    private BigDecimal salarioMensal;

    public EmpregadoAssalariado() {
        super();
    }

    public EmpregadoAssalariado(String id, String nome, String endereco, BigDecimal salarioMensal) {
        super(id, nome, endereco);
        this.salarioMensal = salarioMensal.setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public String getTipo() {
        return "assalariado";
    }

    @Override
    public BigDecimal getSalario() {
        return salarioMensal;
    }

    public void setSalarioMensal(BigDecimal salarioMensal) {
        this.salarioMensal = salarioMensal;
    }

    public String getSalarioMensalStr() {
        return salarioMensal != null ? salarioMensal.toPlainString() : null;
    }

    public void setSalarioMensalStr(String salarioMensalStr) {
        if (salarioMensalStr != null) {
            this.salarioMensal = new BigDecimal(salarioMensalStr);
        }
    }
}