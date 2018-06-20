/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.edu.sv.tpi135.apachemicroweb.system;

import java.util.Properties;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author esperanza
 */
@RequestScoped
@Path("propiedades")
public class SystemResource {
  @GET
  @RolesAllowed({ "admin", "user" })
  @Produces(MediaType.APPLICATION_JSON)
  public Properties getProperties() {
    return System.getProperties();
  }
}
