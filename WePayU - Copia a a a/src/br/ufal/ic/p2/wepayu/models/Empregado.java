package br.ufal.ic.p2.wepayu.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public abstract class Empregado implements Serializable {
    private String id;
    private String nome;
    private String endereco;
    private boolean sindicalizado;
    private String salarioStr;
    private List<CartaoDePonto> cartoesPonto = new ArrayList<>();

    // ATRIBUTOS PARA O SINDICATO (Novos para US 5)
    private String idSindicato;
    private BigDecimal taxaSindical;
    private List<TaxaDeServico> taxasDeServico = new ArrayList<>();

    public Empregado() { }

    public Empregado(String id, String nome, String endereco) {
        this();
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.sindicalizado = false;
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    public boolean isSindicalizado() { return sindicalizado; }

    public String getSalarioFormatado() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
        DecimalFormat df = new DecimalFormat("#,##0.00", symbols);
        df.setGroupingUsed(false);
        return df.format(getSalario());
    }

    public abstract String getTipo();
    public abstract BigDecimal getSalario();

    public void setId(String id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public void setSindicalizado(boolean sindicalizado) { this.sindicalizado = sindicalizado; }

    public String getSalarioStr() { return this.getSalario().toString(); }
    public void setSalarioStr(String salarioStr) { /* subclasses tratam */ }

    public List<CartaoDePonto> getCartoesPonto() {
        return cartoesPonto;
    }

    public void setCartoesPonto(List<CartaoDePonto> cartoesPonto) {
        this.cartoesPonto = cartoesPonto;
    }

    // NOVOS GETTERS E SETTERS PARA O SINDICATO
    public String getIdSindicato() { return idSindicato; }
    public void setIdSindicato(String idSindicato) { this.idSindicato = idSindicato; }

    public void setTaxaSindical(BigDecimal taxaSindical) { this.taxaSindical = taxaSindical; }

    public List<TaxaDeServico> getTaxasDeServico() {
        return taxasDeServico;
    }
    public void setTaxasDeServico(List<TaxaDeServico> taxasDeServico) {
        this.taxasDeServico = taxasDeServico;
    }

    // Método auxiliar para serialização da taxa sindical
    public String getTaxaSindicalStr() {
        return taxaSindical != null ? taxaSindical.toPlainString() : null;
    }

    public void setTaxaSindicalStr(String taxaSindicalStr) {
        if (taxaSindicalStr != null) {
            this.taxaSindical = new BigDecimal(taxaSindicalStr);
        }
    }
}