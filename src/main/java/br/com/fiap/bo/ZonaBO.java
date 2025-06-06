package br.com.fiap.bo;

import br.com.fiap.beans.Zona;
import br.com.fiap.dao.ZonaDAO;
import br.com.fiap.exception.Exceptions;

import java.sql.SQLException;
import java.util.ArrayList;

public class ZonaBO {

    ZonaDAO zonaDAO;

    public ArrayList<Zona> buscarZonas() throws SQLException, ClassNotFoundException {
        zonaDAO = new ZonaDAO();
        ArrayList<Zona> listaZonas = (ArrayList<Zona>) zonaDAO.buscarTodos();

        if (listaZonas.isEmpty()) {
            throw new Exceptions("Nenhuma zona encontrada no sistema");
        }
        return listaZonas;
    }

    public Zona buscarZona(int id) throws SQLException, ClassNotFoundException {
        zonaDAO = new ZonaDAO();
        Zona zona = zonaDAO.buscarPorId(id);
        if (zona == null) {
            throw new Exceptions("Essa zona não foi encontrado no sistema");
        }
        return zona;
    }

    public void inserirZona(Zona zona) throws SQLException, ClassNotFoundException {
        if (zona.getNome() == null || zona.getNome().isEmpty()) {
            throw new Exceptions("O nome da zona é obrigatorio");
        }

        zonaDAO = new ZonaDAO();
        zonaDAO.inserir(zona);
    }

    public void alterarZona(Zona zona) throws SQLException, ClassNotFoundException {
        if (zona.getNome() == null || zona.getNome().isEmpty()) {
            throw new Exceptions("O nome da zona está inválido");
        }
        if (zona.getId() <= 0) {
            throw new Exceptions("O id da zona está inválido");
        }

        zonaDAO = new ZonaDAO();
        zonaDAO.alterar(zona);
    }

    public void excluirZona(int id) throws SQLException, ClassNotFoundException {
        if (id <= 0) {
            throw new Exceptions("O id da zona está inválido");
        }
        zonaDAO = new ZonaDAO();
        zonaDAO.deletar(id);
    }
}
