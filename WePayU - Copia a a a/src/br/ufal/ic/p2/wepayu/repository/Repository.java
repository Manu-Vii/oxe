package br.ufal.ic.p2.wepayu.repository;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Repository<T extends Serializable> {

    protected Map<String, T> data = new LinkedHashMap<>();
    protected String filename;

    public Repository(String filename) {
        this.filename = filename;
        carregarDados();
    }

    public void carregarDados() {
        try (XMLDecoder decoder = new XMLDecoder(new FileInputStream(filename))) {
            this.data = (Map<String, T>) decoder.readObject();
        } catch (IOException e) {
            // Ignora o erro se o arquivo não existir.
        }
    }

    public void salvarDados() {
        try (XMLEncoder encoder = new XMLEncoder(new FileOutputStream(filename))) {
            encoder.writeObject(this.data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void zerarDados() {
        this.data.clear();
        salvarDados();
    }
}