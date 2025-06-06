package br.com.fiap.resource;

import br.com.fiap.beans.Agua;
import br.com.fiap.bo.AguaBO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.ext.Provider;

import java.sql.SQLException;
import java.util.ArrayList;

@Provider

@Path("/aguas")
public class AguaResource {

    private AguaBO aguaBO = new AguaBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Agua> getAguas()throws SQLException, ClassNotFoundException {
        return (ArrayList<Agua>) aguaBO.bucarAguas();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Agua getAguaByID(@PathParam("id") int id)throws SQLException, ClassNotFoundException {
        return aguaBO.buscarAgua(id);
    }

}
