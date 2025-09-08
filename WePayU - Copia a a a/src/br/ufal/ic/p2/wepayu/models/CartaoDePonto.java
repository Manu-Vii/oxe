package br.ufal.ic.p2.wepayu.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter; // Importar para formatar a data

public class CartaoDePonto implements Serializable {
    private LocalDate data;
    private BigDecimal horas;

    public CartaoDePonto() {}

    public CartaoDePonto(LocalDate data, BigDecimal horas) {
        this.data = data;
        this.horas = horas;
    }

    public String getDataStr() {
        return data != null ? data.format(DateTimeFormatter.ofPattern("d/M/uuuu")) : null;
    }

    public void setDataStr(String dataStr) {
        this.data = dataStr != null ? LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("d/M/uuuu")) : null;
    }

    public String getHorasStr() {
        return horas != null ? horas.toPlainString() : null;
    }

    public void setHorasStr(String horasStr) {
        this.horas = horasStr != null ? new BigDecimal(horasStr) : null;
    }

    //Getters e setters originais viram publicos
    //idsso esconde-os do XMLEncoder, mas os mantem para a lógica interna
    public LocalDate getData() {
        return data;
    }

    private void setData(LocalDate data) {
        this.data = data;
    }

    public BigDecimal getHoras() {
        return horas;
    }

    private void setHoras(BigDecimal horas) {
        this.horas = horas;
    }
}