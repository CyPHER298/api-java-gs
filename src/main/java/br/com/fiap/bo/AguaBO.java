package br.com.fiap.bo;

import br.com.fiap.beans.Agua;
import br.com.fiap.dao.AguaDAO;
import br.com.fiap.exception.Exceptions;

import java.sql.SQLException;
import java.util.ArrayList;

public class AguaBO {

    AguaDAO aguaDAO;

    public ArrayList<Agua> bucarAguas() {
        try {
            aguaDAO = new AguaDAO();
            ArrayList<Agua> listaAguas = (ArrayList<Agua>) aguaDAO.selecionar();

            if (listaAguas.isEmpty()) {
                throw new Exceptions("Nenhum ponto de abastecimento de água encontrado");
            }
            return listaAguas;
        } catch (SQLException | ClassNotFoundException e) {
            throw new Exceptions("Erro de conexão com o banco de dados" + e.getMessage(), e);
        }
    }

    public Agua buscarAgua(int id) {
        try {
            aguaDAO = new AguaDAO();
            Agua agua = aguaDAO.buscarPorId(id);
            if (agua == null) {
                throw new Exceptions("Esse ponto de abastecimento de água não foi encontrado");
            }
            return agua;
        } catch (SQLException | ClassNotFoundException e) {
            throw new Exceptions("Erro de conexão com o banco de dados" + e.getMessage(), e);
        }
    }

    public void inserirAgua(Agua agua) {
        try {
            if (agua.getNome() == null || agua.getNome().isEmpty()) {
                throw new Exceptions("O nome do ponto de abastecimento de agua é obrigatorio.");
            }
            if (agua.getEndereco() == null || agua.getEndereco().isEmpty()) {
                throw new Exceptions("O endereço do ponto de abastecimento de agua é obrigatorio.");
            }
            if (agua.getZona() == null || agua.getZona().getNome().isEmpty()) {
                throw new Exceptions("A zona do ponto de abastecimento de agua é obrigatorio.");
            }

            AguaDAO aguaDAO = new AguaDAO();
            aguaDAO.inserir(agua);
        } catch (SQLException | ClassNotFoundException e) {
            throw new Exceptions("Erro ao inserir o ponto de abastecimento de agua " + e.getMessage(), e);
        }
    }

    public void alterarAgua(Agua agua) {
        try {
            if (agua.getId() <= 0) {
                throw new Exceptions("O id do ponto de abastecimento de agua esta incorreto.");
            }
            if (agua.getNome() == null || agua.getNome().isEmpty()) {
                throw new Exceptions("O nome do ponto de abastecimento de agua esta incorreto.");
            }
            if (agua.getEndereco() == null || agua.getEndereco().isEmpty()) {
                throw new Exceptions("O endereço do ponto de abastecimento de agua esta incorreto.");
            }
            if (agua.getZona() == null || agua.getZona().getNome().isEmpty()) {
                throw new Exceptions("A zona do ponto de abastecimento de agua esta incorreto.");
            }

            AguaDAO aguaDAO = new AguaDAO();
            aguaDAO.atualizar(agua);
        } catch (SQLException | ClassNotFoundException e) {
            throw new Exceptions("Erro ao atualizar o ponto de abastecimento de água" + e.getMessage(), e);
        }
    }

    public void excluirAgua(int id) {
        try {
            if (id <= 0) {
                throw new Exceptions("O id do ponto de abastecimento de água inválido.");
            }
            AguaDAO AaguaDAO = new AguaDAO();
            aguaDAO.deletar(id);
        } catch (SQLException | ClassNotFoundException e) {
            throw new Exceptions("Erro ao deletar o ponto de abastecimento de água" + e.getMessage(), e);
        }
    }
}
