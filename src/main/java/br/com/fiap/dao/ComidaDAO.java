package br.com.fiap.dao;


import br.com.fiap.beans.Comida;
import br.com.fiap.beans.Zona;
import br.com.fiap.conexao.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComidaDAO {

    public Connection minhaConexao;

    public ComidaDAO() throws ClassNotFoundException, SQLException {
        super();
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    public String inserir(Comida comida) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement
                ("insert into (nome, endereco, zona_id) comida values (?,?,?)");
        stmt.setString(1, comida.getNome());
        stmt.setString(2, comida.getEndereco());
        stmt.setInt(3, comida.getZona().getId());
        stmt.execute();
        stmt.close();
        return "Ponto de abastecimento de comida inserido com sucesso!";
    }

    public String deletar(int id) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement
                ("delete from comida where comida_id = ?");
        stmt.setInt(1, id);
        stmt.execute();
        stmt.close();
        return "Ponto de abastecimento de comida deletado com sucesso!";
    }

    public String atualizar(Comida comida) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement
                ("update comida set nome = ?, endereco = ?, zona_id = ? where comida_id = ?");
        stmt.setString(1, comida.getNome());
        stmt.setString(2, comida.getEndereco());
        stmt.setInt(3, comida.getZona().getId());
        stmt.setInt(4, comida.getId());
        stmt.execute();
        stmt.close();
        return "Ponto de abastecimento de comida atualizado com sucesso!";
    }

    public Comida buscarPorId(int id) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement
                ("SELECT c.comida_id, c.nome, c.endereco, z.nome \n" +
                        "            FROM comida c \n" +
                        "            JOIN zona z ON c.zona_id = z.zona_id\n" +
                        "            WHERE z.zona_id = ?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        Comida comida = new Comida();
        if (rs.next()) {
            comida.setId(rs.getInt(""));
            comida.setNome(rs.getString("nome"));
            comida.setEndereco(rs.getString("endereco"));
            comida.setZona(new Zona());
            comida.getZona().setId(rs.getInt("zona_id"));
        }
        stmt.close();
        return comida;
    }

    public List<Comida> buscarTodos() throws SQLException {
        List<Comida> listaComidas = new ArrayList<Comida>();
        PreparedStatement stmt = minhaConexao.prepareStatement
                (" SELECT c.comida_id, c.nome, c.endereco, z.nome \n" +
                        "            FROM comida c \n" +
                        "            JOIN zona z ON c.zona_id = z.zona_id");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Comida comida = new Comida();
            comida.setId(rs.getInt("comida_id"));
            comida.setNome(rs.getString("nome"));
            comida.setEndereco(rs.getString("endereco"));
            comida.setZona(new Zona());
            comida.getZona().setNome(rs.getString("nome"));
            listaComidas.add(comida);
        }
        rs.close();
        stmt.close();
        return listaComidas;

    }
}
