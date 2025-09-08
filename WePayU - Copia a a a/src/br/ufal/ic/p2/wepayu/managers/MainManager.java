package br.ufal.ic.p2.wepayu.managers;

public class MainManager {
    public EmpregadoManager empregadoManager = new EmpregadoManager();
    public LancaCartaoPontoManager lancaCartaoPontoManager = new LancaCartaoPontoManager();
    public LancaResultadoVendaManager lancaResultadoVendaManager = new LancaResultadoVendaManager();
    public LancaTaxaServicoManager lancaTaxaServicoManager = new LancaTaxaServicoManager();

    public EmpregadoManager getEmpregadoManager() {
        return empregadoManager;
    }

    public LancaCartaoPontoManager getLancaCartaoPontoManager() {
        return lancaCartaoPontoManager;
    }

    public LancaResultadoVendaManager getLancaResultadoVendaManager() {
        return lancaResultadoVendaManager;
    }

    public LancaTaxaServicoManager getLancaTaxaServicoManager() {
        return lancaTaxaServicoManager;
    }
}