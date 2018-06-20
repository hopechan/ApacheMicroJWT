/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.edu.sv.tpi135.apachemicroweb.backend.Marca;

import java.util.Properties;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ues.edu.sv.tpi135.apachemicroweb.backend.Marca.modelo.MarcaLista;

/**
 *
 * @author esperanza
 */
@RequestScoped
@Path("systems")
public class MarcaResource {
  @Inject
  MarcaManager manager;

  @GET
  @RolesAllowed({ "admin", "user" })
  @Path("{hostname}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getPropertiesForHost(@PathParam("hostname") String hostname,
      @Context HttpHeaders httpHeaders) {
    String authHeader = httpHeaders.getRequestHeaders()
                                   .getFirst(HttpHeaders.AUTHORIZATION);
    Properties props = manager.get(hostname, authHeader);
    if (props == null) {
      return Response.status(Response.Status.NOT_FOUND)
                     .entity(
                         "ERROR: hostname desconocido")
                     .build();
    }
    return Response.ok(props).build();
  }

  @GET
  @RolesAllowed({ "admin" })
  @Produces(MediaType.APPLICATION_JSON)
  public MarcaLista listContents() {
    return manager.list();
  }
}
