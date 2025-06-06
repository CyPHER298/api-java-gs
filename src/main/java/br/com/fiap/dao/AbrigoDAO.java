package br.com.fiap.dao;

import br.com.fiap.beans.Abrigo;
import br.com.fiap.beans.Zona;
import br.com.fiap.conexao.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AbrigoDAO {

    public Connection minhaConexao;

    public AbrigoDAO() throws SQLException, ClassNotFoundException {
        super();
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    public String inserir(Abrigo abrigo) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement
                ("insert into (nome, endereco, telefone, zona_id) abrigo values (?,?,?,?)");
        stmt.setString(1, abrigo.getNome());
        stmt.setString(2, abrigo.getEndereco());
        stmt.setString(3, abrigo.getTelefone());
        stmt.setInt(4, abrigo.getZona().getId());

        stmt.execute();
        stmt.close();

        return "Abrigo inserido com sucesso!";
    }

    public String deletar(int id) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement
                ("delete from abrigo where abrigo_id = ?");
        stmt.setInt(1, id);

        stmt.execute();
        stmt.close();

        return "Abrigo deletado com sucesso!";
    }

    public String atualizar(Abrigo abrigo) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement
                ("update abrigo set nome = ?, endereco = ?, telefone = ?, zona_id = ? where abrigo_id = ?");
        stmt.setString(1, abrigo.getNome());
        stmt.setString(2, abrigo.getEndereco());
        stmt.setString(3, abrigo.getTelefone());
        stmt.setInt(4, abrigo.getZona().getId());
        stmt.setInt(5, abrigo.getId());
        stmt.execute();
        stmt.close();

        return "Abrigo atualizado com sucesso!";
    }

    public Abrigo buscarPorId(int id) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement
                ("SELECT a.abrigo_id, a.nome, a.endereco, a.telefone, z.nome\n FROM abrigo a JOIN zona z ON a.zona_id = z.zona_id WHERE a.abrigo_id = ?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        Abrigo abrigo = new Abrigo();
        if (rs.next()) {
            abrigo.setId(rs.getInt("abrigo_id"));
            abrigo.setNome(rs.getString("nome"));
            abrigo.setEndereco(rs.getString("endereco"));
            abrigo.setTelefone(rs.getString("telefone"));
            abrigo.setZona(new Zona());
            abrigo.getZona().setNome(rs.getString("nome"));
        }
        rs.close();
        stmt.close();
        return abrigo;
    }

    public List<Abrigo> selecionar() throws SQLException {
        List<Abrigo> listaAbrigos = new ArrayList<Abrigo>();
        PreparedStatement stmt = minhaConexao.prepareStatement
                ("SELECT a.abrigo_id, a.nome, a.endereco, a.telefone, z.nome \n" +
                        "FROM abrigo a \n" +
                        "JOIN zona z ON a.zona_id = z.zona_id");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Abrigo abrigo = new Abrigo();
            abrigo.setId(rs.getInt("abrigo_id"));
            abrigo.setNome(rs.getString("nome"));
            abrigo.setEndereco(rs.getString("endereco"));
            abrigo.setTelefone(rs.getString("telefone"));
            abrigo.setZona(new Zona());
            abrigo.getZona().setNome(rs.getString("nome"));
            listaAbrigos.add(abrigo);
        }
        rs.close();
        stmt.close();
        return listaAbrigos;
    }
}
