package br.com.fiap.beans;

public class Comida {
    private int id;
    private String nome;
    private String endereco;
    private Zona zona;

    public Comida(int id, String nome, String endereco, Zona zona) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.zona = zona;
    }

    public Comida() {
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }
}
