package br.com.fiap.dao;

import br.com.fiap.beans.Zona;
import br.com.fiap.conexao.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ZonaDAO {

    public Connection minhaConexao;

    public ZonaDAO() throws SQLException, ClassNotFoundException {
        super();
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    public String inserir(Zona zona) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = minhaConexao.prepareStatement
                ("insert into (nome) values (?)");
        stmt.setString(1, zona.getNome());
        stmt.execute();
        stmt.close();
        return "Zona inserido com sucesso!";
    }

    public String deletar(int id) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = minhaConexao.prepareStatement
                ("delete from Zona where zona_id = ?");
        stmt.setInt(1, id);
        stmt.execute();
        stmt.close();
        return "Zona deletado com sucesso!";
    }

    public String alterar(Zona zona) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = minhaConexao.prepareStatement
                ("update Zona set nome = ? where zona_id = ?");
        stmt.setString(1, zona.getNome());
        stmt.setInt(2, zona.getId());
        stmt.execute();
        stmt.close();
        return "Zona alterado com sucesso!";
    }

    public Zona buscarPorId(int id) throws SQLException, ClassNotFoundException {
        Zona zona = new Zona();
        PreparedStatement stmt = minhaConexao.prepareStatement
                ("select * from Zona where zona_id = ?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            zona.setId(rs.getInt("zona_id"));
            zona.setNome(rs.getString("nome"));
        }
        stmt.close();
        rs.close();
        return zona;
    }

    public List<Zona> buscarTodos() throws SQLException, ClassNotFoundException {
        List<Zona> listaZonas = new ArrayList<Zona>();
        PreparedStatement stmt = minhaConexao.prepareStatement
                ("select * from Zona");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Zona zona = new Zona();
            zona.setId(rs.getInt("zona_id"));
            zona.setNome(rs.getString("nome"));
            listaZonas.add(zona);
        }
        stmt.close();
        rs.close();
        return listaZonas;
    }

}
