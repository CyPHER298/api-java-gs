package br.com.fiap.dao;

import br.com.fiap.beans.Abrigo;
import br.com.fiap.beans.Agua;
import br.com.fiap.beans.Zona;
import br.com.fiap.conexao.ConexaoFactory;
import io.agroal.pool.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AguaDAO {

    public Connection minhaConexao;

    public AguaDAO() throws SQLException, ClassNotFoundException {
        super();
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    public String inserir(Agua agua) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = minhaConexao.prepareStatement
                ("insert into agua (nome, endereco, zona_id) values (?,?,?) ");
        stmt.setString(1, agua.getNome());
        stmt.setString(2, agua.getEndereco());
        stmt.setInt(3, agua.getZona().getId());

        stmt.execute();
        stmt.close();

        return "Ponto de abastecimento de água inserido com sucesso!";
    }


    public String deletar(int id) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = minhaConexao.prepareStatement
                ("delete from agua where agua_id = ? ");
        stmt.setInt(1, id);
        stmt.execute();
        stmt.close();

        return "Ponto de abasteciment de água deletado com sucesso!";
    }

    public String atualizar(Agua agua) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = minhaConexao.prepareStatement
                ("update agua set nome = ?, endereco = ?, zona_id = ? where agua_id = ?");
        stmt.setString(1, agua.getNome());
        stmt.setString(2, agua.getEndereco());
        stmt.setInt(3, agua.getZona().getId());
        stmt.setInt(4, agua.getId());
        stmt.execute();
        stmt.close();

        return "Ponto de abastecimento de água atualizado com sucesso!";
    }

    public Agua buscarPorId(int id) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = minhaConexao.prepareStatement
                ("SELECT a.agua_id, a.nome, a.endereco, z.nome \n" +
                        "            FROM agua a \n" +
                        "            JOIN zona z ON a.zona_id = z.zona_id\n" +
                        "            WHERE z.zona_id = ?");
        ResultSet rs = stmt.executeQuery();
        Agua agua = new Agua();
        if (rs.next()) {
            agua.setId(rs.getInt(id));
            agua.setNome(rs.getString("nome"));
            agua.setEndereco(rs.getString("endereco"));
            agua.setZona(new Zona());
            agua.getZona().setId(rs.getInt("zona_id"));
        }
        stmt.close();
        return agua;
    }

    public List<Agua> selecionar() throws SQLException, ClassNotFoundException {
        List<Agua> listaAgua = new ArrayList<Agua>();
        PreparedStatement stmt = minhaConexao.prepareStatement
                ("SELECT a.agua_id, a.nome, a.endereco, z.nome \n" +
                        "FROM agua a " +
                        "JOIN zona z ON a.zona_id = z.zona_id");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Agua agua = new Agua();
            agua.setId(rs.getInt("agua_id"));
            agua.setNome(rs.getString("nome"));
            agua.setEndereco(rs.getString("endereco"));
            agua.setZona(new Zona());
            agua.getZona().setNome(rs.getString("nome"));
            listaAgua.add(agua);
        }
        rs.close();
        stmt.close();
        return listaAgua;
    }
}
