package br.com.fiap.resource;

import br.com.fiap.beans.Usuario;
import br.com.fiap.bo.UsuarioBO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.ext.Provider;

import java.sql.SQLException;
import java.util.ArrayList;

@Provider

@Path("/usuarios")
public class UsuarioResource {

    private UsuarioBO usuarioBO = new UsuarioBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Usuario> getUsuarios() throws SQLException, ClassNotFoundException {
        return (ArrayList<Usuario>) usuarioBO.buscarUsuarios();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getUsuarioById(@PathParam("id") Integer id) throws SQLException, ClassNotFoundException {
        return usuarioBO.buscarUsuario(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postUsuario(Usuario usuario, @Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        usuarioBO.inserirUsuario(usuario);

        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(usuario.getId()));
        return Response.created(builder.build()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putUsuario(Usuario usuario, @PathParam("id") int id) throws SQLException, ClassNotFoundException {
        usuarioBO.alterarUsuario(usuario);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteUsuario(@PathParam("id") int id) throws SQLException, ClassNotFoundException {
        usuarioBO.excluirUsuario(id);
        return Response.ok().build();
    }
}
