package br.com.fiap.bo;

import br.com.fiap.beans.Abrigo;
import br.com.fiap.dao.AbrigoDAO;
import br.com.fiap.exception.Exceptions;

import java.sql.SQLException;
import java.util.ArrayList;

public class AbrigoBO {

    AbrigoDAO abrigoDAO;

    public ArrayList<Abrigo> buscarAbrigos() {
        try {
            abrigoDAO = new AbrigoDAO();
            ArrayList<Abrigo> listaAbrigos = (ArrayList<Abrigo>) abrigoDAO.selecionar();

            if (listaAbrigos.isEmpty()) {
                throw new Exceptions("Nenhum abrigo encontrado no sistema.");
            }
            return listaAbrigos;

        } catch (SQLException | ClassNotFoundException e) {
            throw new Exceptions("Erro de conexão com o banco de dados." + e.getMessage(), e);
        }
    }

    public Abrigo buscarAbrigo(int id) {
        try {
            abrigoDAO = new AbrigoDAO();
            Abrigo abrigo = abrigoDAO.buscarPorId(id);
            if (abrigo == null) {
                throw new Exceptions("Este abrigo não foi encontrado no sistema.");
            }
            return abrigo;

        } catch (SQLException | ClassNotFoundException e) {
            throw new Exceptions("Erro de conexão com o banco de dados." + e.getMessage(), e);
        }
    }

    public void inserirAbrigo(Abrigo abrigo) {
        try {
            if (abrigo.getNome() == null || abrigo.getNome().isEmpty()) {
                throw new Exceptions("O nome do abrigo é obrigatorio.");
            }
            if (abrigo.getEndereco() == null || abrigo.getEndereco().isEmpty()) {
                throw new Exceptions("O endereço do abrigo é obrigatorio.");
            }
            if (abrigo.getTelefone() == null || abrigo.getTelefone().isEmpty()) {
                throw new Exceptions("O telefone do abrigo é obrigatorio.");
            }
            if (abrigo.getZona() == null || abrigo.getZona().getNome().isEmpty()) {
                throw new Exceptions("A zona do abrigo é obrigatorio.");
            }

            AbrigoDAO abrigoDAO = new AbrigoDAO();
            abrigoDAO.inserir(abrigo);


        } catch (SQLException | ClassNotFoundException e) {
            throw new Exceptions("Erro ao inserir o abrigo." + e.getMessage(), e);
        }
    }

    public void alterarAbrigo(Abrigo abrigo) {
        try {
            if (abrigo.getId() <= 0) {
                throw new Exceptions("O nome do abrigo esta incorreto.");
            }
            if (abrigo.getNome() == null || abrigo.getNome().isEmpty()) {
                throw new Exceptions("O nome do abrigo esta incorreto.");
            }
            if (abrigo.getEndereco() == null || abrigo.getEndereco().isEmpty()) {
                throw new Exceptions("O endereço do abrigo esta incorreto.");
            }
            if (abrigo.getTelefone() == null || abrigo.getTelefone().isEmpty()) {
                throw new Exceptions("O telefone do abrigo esta incorreto.");
            }
            if (abrigo.getZona() == null || abrigo.getZona().getNome().isEmpty()) {
                throw new Exceptions("A zona do abrigo esta incorreto.");
            }

            AbrigoDAO abrigoDAO = new AbrigoDAO();
            abrigoDAO.atualizar(abrigo);
        } catch (SQLException | ClassNotFoundException e) {
            throw new Exceptions("Erro ao atualizar abrigo." + e.getMessage(), e);
        }
    }

    public void excluirAbrigo(int id) {
        try {
            if (id <= 0) {
                throw new Exceptions("O id do abrigo <UNK> inválido.");
            }
            AbrigoDAO abrigoDAO = new AbrigoDAO();
            abrigoDAO.deletar(id);
        } catch (SQLException | ClassNotFoundException e) {
            throw new Exceptions("Erro ao excluir abrigo." + e.getMessage(), e);
        }
    }
}
