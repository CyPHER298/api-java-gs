package br.com.fiap.bo;

import br.com.fiap.beans.Mensagem;
import br.com.fiap.dao.MensagemDAO;
import br.com.fiap.exception.Exceptions;

import java.sql.SQLException;
import java.util.ArrayList;

public class MensagemBO {

    MensagemDAO mensagemDAO;

    public ArrayList<Mensagem> buscarMensagens() throws SQLException, ClassNotFoundException {
        mensagemDAO = new MensagemDAO();
        ArrayList<Mensagem> listaMensagens = (ArrayList<Mensagem>) mensagemDAO.buscarTodos();
        if (listaMensagens.isEmpty()) {
            throw new Exceptions("Nenhuma mensagem encontrada no sistema");
        }
        return listaMensagens;
    }

    public Mensagem buscarMensagem(int id) throws SQLException, ClassNotFoundException {
        mensagemDAO = new MensagemDAO();
        Mensagem mensagem = mensagemDAO.buscarPorId(id);
        if (mensagem == null) {
            throw new Exceptions("Essa mensagem não foi encontrada");
        }
        return mensagem;
    }

    public void inserirMensagem(Mensagem mensagem) throws SQLException, ClassNotFoundException {
        if (mensagem.getEndereco() == null || mensagem.getEndereco().isEmpty()) {
            throw new Exceptions("O endereço da mensagem é obrigatorio");
        }
        if (mensagem.getTexto() == null || mensagem.getTexto().isEmpty()) {
            throw new Exceptions("O texto do mensagem é obrigatorio");
        }
        if (mensagem.getUsuario() == null || mensagem.getUsuario().getNome().isEmpty()) {
            throw new Exceptions("O usuario da mensagem é obrigatorio");
        }

        MensagemDAO mensagemDAO = new MensagemDAO();
        mensagemDAO.inserir(mensagem);
    }

    public void alterarMensagem(Mensagem mensagem) throws SQLException, ClassNotFoundException {
        if (mensagem.getEndereco() == null || mensagem.getEndereco().isEmpty()) {
            throw new Exceptions("O endereco da mensagem está invalido");
        }
        if (mensagem.getTexto() == null || mensagem.getTexto().isEmpty()) {
            throw new Exceptions("O texto do mensagem está invalido");
        }
        if (mensagem.getUsuario() == null || mensagem.getUsuario().getNome().isEmpty()) {
            throw new Exceptions("O usuario da mensagem está invalido");
        }
        if (mensagem.getId() <= 0) {
            throw new Exceptions("O id da mensagem está invalido");
        }
        MensagemDAO mensagemDAO = new MensagemDAO();
        mensagemDAO.alterar(mensagem);
    }

    public void excluirMensagem(int id) throws SQLException, ClassNotFoundException {
        if (id <= 0) {
            throw new Exceptions("O id da mensagem está inválido");
        }
        mensagemDAO = new MensagemDAO();
        mensagemDAO.deletar(id);
    }
}
