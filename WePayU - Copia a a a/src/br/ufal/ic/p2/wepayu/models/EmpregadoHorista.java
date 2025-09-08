package br.ufal.ic.p2.wepayu.models;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class EmpregadoHorista extends Empregado {
    private BigDecimal salarioHora;

    public EmpregadoHorista() {
        super();
    }

    public EmpregadoHorista(String id, String nome, String endereco, BigDecimal salarioHora) {
        super(id, nome, endereco);
        this.salarioHora = salarioHora.setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public String getTipo() {
        return "horista";
    }

    @Override
    public BigDecimal getSalario() {
        return salarioHora;
    }

    public void setSalarioHora(BigDecimal salarioHora) {
        this.salarioHora = salarioHora;
    }

    public String getSalarioHoraStr() {
        return salarioHora != null ? salarioHora.toPlainString() : null;
    }

    public void setSalarioHoraStr(String salarioHoraStr) {
        if (salarioHoraStr != null) {
            this.salarioHora = new BigDecimal(salarioHoraStr);
        }
    }
}