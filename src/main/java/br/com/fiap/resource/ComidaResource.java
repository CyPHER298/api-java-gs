package br.com.fiap.resource;

import br.com.fiap.beans.Comida;
import br.com.fiap.bo.ComidaBO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.ext.Provider;

import java.sql.SQLException;
import java.util.ArrayList;

@Provider

@Path("/comidas")
public class ComidaResource {

    private ComidaBO comidaBO = new ComidaBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Comida> getComidas() throws SQLException, ClassNotFoundException {
        return (ArrayList<Comida>) comidaBO.buscarComidas();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Comida getComidaById(@PathParam("id") int id) throws SQLException, ClassNotFoundException {
        return comidaBO.buscarComida(id);
    }
}
