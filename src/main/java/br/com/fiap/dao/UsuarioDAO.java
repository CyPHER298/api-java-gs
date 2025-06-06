package br.com.fiap.dao;

import br.com.fiap.beans.Usuario;
import br.com.fiap.conexao.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    public Connection minhaConexao;

    public UsuarioDAO() throws SQLException, ClassNotFoundException {
        super();
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    public String inserir(Usuario usuario) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = minhaConexao.prepareStatement
                ("insert into usuario (nome) values (?)");
        stmt.setString(1, usuario.getNome());
        stmt.execute();
        stmt.close();
        return "Usuário inserido com sucesso!";
    }

    public String deletar (int id) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = minhaConexao.prepareStatement
                ("delete from usuario where id = ?");
        stmt.setInt(1, id);
        stmt.execute();
        stmt.close();
        return "Usuario deletado com sucesso!";
    }

    public String atualizar(Usuario usuario) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = minhaConexao.prepareStatement
                ("update usuario set nome = ? where usuario_id = ?");
        stmt.setString(1, usuario.getNome());
        stmt.setInt(2, usuario.getId());
        stmt.execute();
        stmt.close();
        return "Usuario atualizado com sucesso!";
    }

    public Usuario buscarPorId(int id) throws SQLException, ClassNotFoundException {
        Usuario usuario = new Usuario();
        PreparedStatement stmt = minhaConexao.prepareStatement
                ("select * from usuario where ususario_id = ?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            usuario.setId(rs.getInt("id"));
            usuario.setNome(rs.getString("nome"));
        }
        stmt.close();
        rs.close();
        return usuario;
    }

    public List<Usuario> selecionar() throws SQLException, ClassNotFoundException {
        List<Usuario> listaUsuarios = new ArrayList<Usuario>();
        PreparedStatement stmt = minhaConexao.prepareStatement
                ("select * from usuario");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setId(rs.getInt("usuario_id"));
            usuario.setNome(rs.getString("nome"));
            listaUsuarios.add(usuario);
        }
        stmt.close();
        rs.close();
        return listaUsuarios;
    }

}
