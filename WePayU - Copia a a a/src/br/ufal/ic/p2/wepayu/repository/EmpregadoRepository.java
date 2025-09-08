package br.ufal.ic.p2.wepayu.repository;

import br.ufal.ic.p2.wepayu.models.Empregado;
import java.util.Map;

public class EmpregadoRepository extends Repository<Empregado> {

    private static EmpregadoRepository instance;

    private EmpregadoRepository() {
        super("empregados.xml");
    }

    public static synchronized EmpregadoRepository getInstance() {
        if (instance == null) {
            instance = new EmpregadoRepository();
        }
        return instance;
    }

    public Map<String, Empregado> getAll() {
        return this.data;
    }

    public Empregado getById(String id) {
        return this.data.get(id);
    }

    public void add(Empregado empregado) {
        String nextId = String.valueOf(getNextId());
        empregado.setId(nextId);
        this.data.put(empregado.getId(), empregado);
        salvarDados();
    }

    public boolean remove(String id) {
        if (this.data.containsKey(id)) {
            this.data.remove(id);
            salvarDados();
            return true;
        }
        return false;
    }

    private int getNextId() {
        return this.data.size() + 1;
    }
}