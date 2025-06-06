package br.com.fiap.bo;

import br.com.fiap.beans.Usuario;
import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.exception.Exceptions;

import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioBO {

    UsuarioDAO usuarioDAO;

    public ArrayList<Usuario> buscarUsuarios() throws SQLException, ClassNotFoundException {
        usuarioDAO = new UsuarioDAO();
        ArrayList<Usuario> listaUsuarios = (ArrayList<Usuario>) usuarioDAO.selecionar();

        if (listaUsuarios.isEmpty()) {
            throw new Exceptions("Usuários não encontrado no sistema");
        }

        return listaUsuarios;
    }

    public Usuario buscarUsuario(int id) throws SQLException, ClassNotFoundException {
        usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.buscarPorId(id);

        if (usuario == null) {
            throw new Exceptions("Este usuario não foi encontrado");
        }

        return usuario;
    }

    public void inserirUsuario(Usuario usuario) throws SQLException, ClassNotFoundException {
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            throw new Exceptions("O nome do usuario é obrigatorio");
        }
        usuarioDAO = new UsuarioDAO();
        usuarioDAO.inserir(usuario);
    }

    public void alterarUsuario(Usuario usuario) throws SQLException, ClassNotFoundException {
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            throw new Exceptions("O nome do usuario está invalido");
        }
        if (usuario.getId() <= 0) {
            throw new Exceptions("O id do usuario está inválido");
        }
        usuarioDAO = new UsuarioDAO();
        usuarioDAO.atualizar(usuario);
    }

    public void excluirUsuario(int id) throws SQLException, ClassNotFoundException {
        if (id <= 0) {
            throw new Exceptions("O id do usuario está inválido");
        }
        usuarioDAO = new UsuarioDAO();
        usuarioDAO.deletar(id);
    }
}
