package br.com.fiap.bo;

import br.com.fiap.beans.Comida;
import br.com.fiap.dao.ComidaDAO;
import br.com.fiap.exception.Exceptions;

import java.sql.SQLException;
import java.util.ArrayList;

public class ComidaBO {

    ComidaDAO comidaDAO;

    public ArrayList<Comida> buscarComidas() {
        try {
            comidaDAO = new ComidaDAO();
            ArrayList<Comida> listaComidas = (ArrayList<Comida>) comidaDAO.buscarTodos();

            if (listaComidas.isEmpty()) {
                throw new Exceptions("Nenhum ponto de abastecimento de comida encontrado no sistema.");
            }
            return listaComidas;

        } catch (SQLException | ClassNotFoundException e) {
            throw new Exceptions("Erro de conexão com o banco de dados." + e.getMessage(), e);
        }
    }

    public Comida buscarComida(int id) throws SQLException, ClassNotFoundException {
        comidaDAO = new ComidaDAO();
        Comida comida = comidaDAO.buscarPorId(id);
        if (comida == null) {
            throw new Exceptions("Nenhum ponto de abastecimento de comida encontrado no sistema.");
        }
        return comida;
    }

    public void inserirComida(Comida comida) throws SQLException, ClassNotFoundException {
        if (comida.getNome() == null || comida.getNome().isEmpty()) {
            throw new Exceptions("O nome do ponto de abastecimento de comida é obrigatório");
        }
        if (comida.getEndereco() == null || comida.getEndereco().isEmpty()) {
            throw new Exceptions("O endereço do ponto de abastecimento de comida é obrigatório");
        }
        if (comida.getZona() == null || comida.getZona().getNome().isEmpty()) {
            throw new Exceptions(("A zona do ponto de abastecimento de comida é obrigatório"));
        }

        comidaDAO = new ComidaDAO();
        comidaDAO.inserir(comida);
    }

    public void alterarComida(Comida comida) throws SQLException, ClassNotFoundException {
        if (comida.getId() <= 0) {
            throw new Exceptions("O id do ponto de abastecimento de comida está incorreto");
        }
        if (comida.getNome() == null || comida.getNome().isEmpty()) {
            throw new Exceptions("O nome do ponto de abastecimento de comida está incorreto");
        }
        if (comida.getEndereco() == null || comida.getEndereco().isEmpty()) {
            throw new Exceptions("O endereço do ponto de abastecimento de comida está incorreto");
        }
        if (comida.getZona() == null || comida.getZona().getNome().isEmpty()) {
            throw new Exceptions(("A zona do ponto de abastecimento de comida está incorreto"));
        }

        comidaDAO = new ComidaDAO();
        comidaDAO.atualizar(comida);
    }

    public void excluirComida(int id) throws SQLException, ClassNotFoundException {
        if (id <= 0) {
            throw new Exceptions("O id do ponto de abastecimento de comida está incorreto");
        }

        comidaDAO = new ComidaDAO();
        comidaDAO.deletar(id);
    }
}
