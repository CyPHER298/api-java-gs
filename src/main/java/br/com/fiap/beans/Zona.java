package br.com.fiap.beans;

public class Zona {
    private int id;
    private String nome;

    public Zona(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Zona() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
