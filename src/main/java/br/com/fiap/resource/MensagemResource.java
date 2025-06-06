package br.com.fiap.resource;

import br.com.fiap.beans.Mensagem;
import br.com.fiap.bo.MensagemBO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.ext.Provider;

import java.sql.SQLException;
import java.util.ArrayList;

@Provider

@Path("/mensagens")
public class MensagemResource {

    private MensagemBO mensagemBO = new MensagemBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Mensagem> getMensagens() throws SQLException, ClassNotFoundException {
        return (ArrayList<Mensagem>) mensagemBO.buscarMensagens();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensagem getMensagemById(@PathParam("id") int id) throws SQLException, ClassNotFoundException {
        return mensagemBO.buscarMensagem(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postMensagem(Mensagem mensagem, @Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        mensagemBO.inserirMensagem(mensagem);

        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(mensagem.getId()));
        return Response.created(builder.build()).build();
    }

}
