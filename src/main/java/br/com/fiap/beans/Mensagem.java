package br.com.fiap.beans;

public class Mensagem {
    private int id;
    private String endereco;
    private String texto;
    private Usuario usuario;

    public Mensagem(int id, String endereco, String texto, Usuario usuario) {
        this.id = id;
        this.endereco = endereco;
        this.texto = texto;
        this.usuario = usuario;
    }

    public Mensagem() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
