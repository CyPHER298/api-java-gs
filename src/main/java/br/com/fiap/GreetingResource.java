package br.com.fiap;

import br.com.fiap.conexao.ConexaoFactory;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class GreetingResource {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String home() {
        return "API Ajuda - Usuário, Zona, Abrigo, Água, Comida, Mensagem";
    }

    @GET
    @Path("/hello")
    public String hello() {
        return "Hello World";
    }
}
