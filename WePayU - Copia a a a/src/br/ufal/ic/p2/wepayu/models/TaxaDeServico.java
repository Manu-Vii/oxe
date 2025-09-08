package br.ufal.ic.p2.wepayu.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class TaxaDeServico implements Serializable {
    private LocalDate data;
    private BigDecimal valor;

    public TaxaDeServico() {}

    public TaxaDeServico(LocalDate data, BigDecimal valor) {
        this.data = data;
        this.valor = valor;
    }

    public String getDataStr() {
        return data != null ? data.format(DateTimeFormatter.ofPattern("d/M/uuuu").withResolverStyle(ResolverStyle.STRICT)) : null;
    }
    public void setDataStr(String dataStr) {
        this.data = dataStr != null ? LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("d/M/uuuu").withResolverStyle(ResolverStyle.STRICT)) : null;
    }
    public String getValorStr() {
        return valor != null ? valor.toPlainString() : null;
    }
    public void setValorStr(String valorStr) {
        this.valor = valorStr != null ? new BigDecimal(valorStr) : null;
    }

    public LocalDate getData() { return data; }
    private void setData(LocalDate data) { this.data = data; }
    public BigDecimal getValor() { return valor; }
    private void setValor(BigDecimal valor) { this.valor = valor; }
}