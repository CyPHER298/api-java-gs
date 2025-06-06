package br.com.fiap.resource;

import br.com.fiap.beans.Abrigo;
import br.com.fiap.bo.AbrigoBO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.ext.Provider;

import java.sql.SQLException;
import java.util.ArrayList;

@Provider

@Path("/abrigos")
public class AbrigoResource {

    private AbrigoBO abrigoBO = new AbrigoBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Abrigo> getAbrigos() throws SQLException, ClassNotFoundException {
        return (ArrayList<Abrigo>) abrigoBO.buscarAbrigos();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Abrigo getAbrigoById(@PathParam("id") int id) throws SQLException, ClassNotFoundException {
        return abrigoBO.buscarAbrigo(id);
    }

}
