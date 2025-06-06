package br.com.fiap.dao;

import br.com.fiap.beans.Mensagem;
import br.com.fiap.beans.Usuario;
import br.com.fiap.conexao.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MensagemDAO {

    public Connection minhaConexao;

    public MensagemDAO() throws ClassNotFoundException, SQLException {
        super();
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    public String inserir(Mensagem mensagem) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement
                ("insert into mensagem (endereco, texto, usuario_id) values (?,?,?)");
        stmt.setString(1, mensagem.getEndereco());
        stmt.setString(2, mensagem.getTexto());
        stmt.setInt(3, mensagem.getUsuario().getId());
        stmt.execute();
        stmt.close();
        return "Mensagem de alerta inserida com sucesso!";
    }

    public String deletar(int id) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement
                ("delete from mensagem where id = ?");
        stmt.setInt(1, id);
        stmt.execute();
        stmt.close();
        return "Mensagem de alerta deletada com sucesso!";
    }

    public String alterar(Mensagem mensagem) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement
                ("update mensagem set endereco = ?, texto = ?, usuario_id = ? where mensagem_id = ?");
        stmt.setString(1, mensagem.getEndereco());
        stmt.setString(2, mensagem.getTexto());
        stmt.setInt(3, mensagem.getUsuario().getId());
        stmt.setInt(4, mensagem.getId());
        stmt.execute();
        stmt.close();
        return "Mensagem de alerta alterada com sucesso!";
    }

    public Mensagem buscarPorId(int id) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement
                ("select * from mensagem where mensagem_id = ?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        Mensagem mensagem = new Mensagem();
        if (rs.next()) {
            mensagem.setId(rs.getInt("mensagem_id"));
            mensagem.setEndereco(rs.getString("endereco"));
            mensagem.setTexto(rs.getString("texto"));
            mensagem.setUsuario(new Usuario());
            mensagem.setId(mensagem.getId());
        }
        stmt.close();
        rs.close();
        return mensagem;
    }

    public List<Mensagem> buscarTodos() throws SQLException {
        List<Mensagem> listaMensagem = new ArrayList<Mensagem>();
        PreparedStatement stmt = minhaConexao.prepareStatement
                ("SELECT m.mensagem_id, m.endereco, m.texto, u.nome\n" +
                        "        FROM mensagem m\n" +
                        "        JOIN usuario u ON m.usuario_id = u.usuario_id");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Mensagem mensagem = new Mensagem();
            mensagem.setId(rs.getInt("mensagem_id"));
            mensagem.setEndereco(rs.getString("endereco"));
            mensagem.setTexto(rs.getString("texto"));
            mensagem.setUsuario(new Usuario());
            mensagem.setId(mensagem.getId());
            listaMensagem.add(mensagem);
        }
        stmt.close();
        rs.close();
        return listaMensagem;
    }
}
