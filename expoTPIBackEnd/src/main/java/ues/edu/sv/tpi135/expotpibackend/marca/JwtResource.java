
package ues.edu.sv.tpi135.expotpibackend.marca;

import java.security.Principal;
import java.util.Set;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import org.eclipse.microprofile.jwt.JsonWebToken;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 *
 * @author esperanza
 */
public class JwtResource {
    // The JWT of the current caller. Since this is a request scoped resource, the
  // JWT will be injected for each JAX-RS request. The injection is performed by
  // the mpJwt-1.0 feature.
  @Inject
  private JsonWebToken jwtPrincipal;

  @GET
  @RolesAllowed({ "admin", "user" })
  @Path("/username")
  public Response getJwtUsername() {
    return Response.ok(this.jwtPrincipal.getName()).build();
  }

  @GET
  @RolesAllowed({ "admin", "user" })
  @Path("/groups")
  public Response getJwtGroups(@Context SecurityContext securityContext) {
    Set<String> groups = null;
    Principal user = securityContext.getUserPrincipal();
    if (user instanceof JsonWebToken) {
      JsonWebToken jwt = (JsonWebToken) user;
      groups = jwt.getGroups();
    }
    return Response.ok(groups.toString()).build();
  }

  @GET
  @RolesAllowed({ "admin", "user" })
  @Path("/customClaim")
  public Response getCustomClaim(@Context SecurityContext securityContext) {
    if (securityContext.isUserInRole("admin")) {
      String customClaim = jwtPrincipal.getClaim("customClaim");
      return Response.ok(customClaim).build();
    }
    return Response.status(Response.Status.FORBIDDEN).build();
  }
}
