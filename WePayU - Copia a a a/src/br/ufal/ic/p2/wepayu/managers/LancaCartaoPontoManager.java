package br.ufal.ic.p2.wepayu.managers;

import br.ufal.ic.p2.wepayu.ExceptionPonto.DataInvalidaException;
import br.ufal.ic.p2.wepayu.ExceptionPonto.DataInicialAposFinalException;
import br.ufal.ic.p2.wepayu.ExceptionPonto.EmpregadoNaoEhHoristaException;
import br.ufal.ic.p2.wepayu.ExceptionEmpregados.EmpregadoNaoExisteException;
import br.ufal.ic.p2.wepayu.ExceptionPonto.HorasNaoNumericasException;
import br.ufal.ic.p2.wepayu.ExceptionPonto.HorasNaoPositivasException;
import br.ufal.ic.p2.wepayu.ExceptionPonto.IdentificacaoNulaException;
import br.ufal.ic.p2.wepayu.models.CartaoDePonto;
import br.ufal.ic.p2.wepayu.models.Empregado;
import br.ufal.ic.p2.wepayu.models.EmpregadoHorista;
import br.ufal.ic.p2.wepayu.repository.EmpregadoRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LancaCartaoPontoManager {
    private final EmpregadoRepository empregadoRepository = EmpregadoRepository.getInstance();

    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("d/M/uuuu").withResolverStyle(ResolverStyle.STRICT);

    public void lancaCartao(String id, String data, String horas) throws Exception {
        if (id == null || id.isEmpty()) {
            throw new IdentificacaoNulaException();
        }

        Empregado empregado = empregadoRepository.getById(id);
        if (empregado == null) {
            throw new EmpregadoNaoExisteException();
        }

        if (!(empregado instanceof EmpregadoHorista)) {
            throw new EmpregadoNaoEhHoristaException();
        }

        LocalDate dataFormatada;
        try {
            dataFormatada = LocalDate.parse(data, formatter);
        } catch (DateTimeParseException e) {
            throw new DataInvalidaException();
        }

        BigDecimal horasBigDecimal;
        try {
            horasBigDecimal = new BigDecimal(horas.replace(",", "."));
        } catch (NumberFormatException e) {
            throw new HorasNaoNumericasException();
        }

        if (horasBigDecimal.compareTo(BigDecimal.ZERO) <= 0) {
            throw new HorasNaoPositivasException();
        }

        empregado.getCartoesPonto().add(new CartaoDePonto(dataFormatada, horasBigDecimal));
        empregadoRepository.salvarDados();
    }

    // Em LancaCartaoPontoManager.java

// ... (código anterior)

    public String getHorasNormaisTrabalhadas(String id, String dataInicial, String dataFinal) throws Exception {
        Empregado empregado = empregadoRepository.getById(id);
        if (!(empregado instanceof EmpregadoHorista)) {
            throw new EmpregadoNaoEhHoristaException();
        }

        LocalDate dataInicio;
        try {
            dataInicio = LocalDate.parse(dataInicial, formatter);
        } catch (DateTimeParseException e) {
            throw new DataInvalidaException("inicial");
        }

        LocalDate dataFim;
        try {
            dataFim = LocalDate.parse(dataFinal, formatter);
        } catch (DateTimeParseException e) {
            throw new DataInvalidaException("final");
        }

        if (dataInicio.isAfter(dataFim)) {
            throw new DataInicialAposFinalException();
        }

        Map<LocalDate, BigDecimal> horasPorDia = new HashMap<>();
        for (CartaoDePonto cartao : empregado.getCartoesPonto()) {
            LocalDate dataCartao = cartao.getData();
            // A data do cartao deve ser maior ou igual a data inicial E menor que a data final.
            if (!dataCartao.isBefore(dataInicio) && dataCartao.isBefore(dataFim)) {
                horasPorDia.put(dataCartao, horasPorDia.getOrDefault(dataCartao, BigDecimal.ZERO).add(cartao.getHoras()));
            }
        }

        BigDecimal totalHorasNormais = BigDecimal.ZERO;
        for (BigDecimal horasDoDia : horasPorDia.values()) {
            if (horasDoDia.compareTo(new BigDecimal("8")) > 0) {
                totalHorasNormais = totalHorasNormais.add(new BigDecimal("8"));
            } else {
                totalHorasNormais = totalHorasNormais.add(horasDoDia);
            }
        }
        return formatarValor(totalHorasNormais);
    }

    public String getHorasExtrasTrabalhadas(String id, String dataInicial, String dataFinal) throws Exception {
        Empregado empregado = empregadoRepository.getById(id);
        if (!(empregado instanceof EmpregadoHorista)) {
            throw new EmpregadoNaoEhHoristaException();
        }

        LocalDate dataInicio;
        try {
            dataInicio = LocalDate.parse(dataInicial, formatter);
        } catch (DateTimeParseException e) {
            throw new DataInvalidaException("inicial");
        }

        LocalDate dataFim;
        try {
            dataFim = LocalDate.parse(dataFinal, formatter);
        } catch (DateTimeParseException e) {
            throw new DataInvalidaException("final");
        }

        if (dataInicio.isAfter(dataFim)) {
            throw new DataInicialAposFinalException();
        }

        Map<LocalDate, BigDecimal> horasPorDia = new HashMap<>();
        for (CartaoDePonto cartao : empregado.getCartoesPonto()) {
            LocalDate dataCartao = cartao.getData();
            // A data do cartao deve ser maior ou igual a data inicial E menor que a data final.
            if (!dataCartao.isBefore(dataInicio) && dataCartao.isBefore(dataFim)) {
                horasPorDia.put(dataCartao, horasPorDia.getOrDefault(dataCartao, BigDecimal.ZERO).add(cartao.getHoras()));
            }
        }

        BigDecimal totalHorasExtras = BigDecimal.ZERO;
        for (BigDecimal horasDoDia : horasPorDia.values()) {
            if (horasDoDia.compareTo(new BigDecimal("8")) > 0) {
                totalHorasExtras = totalHorasExtras.add(horasDoDia.subtract(new BigDecimal("8")));
            }
        }
        return formatarValor(totalHorasExtras);
    }

// ... (método formatarValor)

    private String formatarValor(BigDecimal valor) {
        if (valor == null) {
            return "0";
        }
        valor = valor.stripTrailingZeros();
        if (valor.scale() <= 0) {
            return String.valueOf(valor.intValue());
        }
        return valor.toPlainString().replace(".", ",");
    }
}