package br.com.fiap.resource;

import br.com.fiap.beans.Zona;
import br.com.fiap.bo.ZonaBO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.ext.Provider;

import java.sql.SQLException;
import java.util.ArrayList;

@Provider

@Path("/zonas")
public class ZonaResource {

    private ZonaBO zonaBO = new ZonaBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Zona> getZonas() throws SQLException, ClassNotFoundException {
        return (ArrayList<Zona>) zonaBO.buscarZonas();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Zona getZonaById(@PathParam("id") int id) throws SQLException, ClassNotFoundException {
        return zonaBO.buscarZona(id);
    }

}
