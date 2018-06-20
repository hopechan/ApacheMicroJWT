
package ues.edu.sv.tpi135.apachemicrojwt.web.tokencontroller;
import java.security.Principal;
import java.util.Optional;
import java.util.Set;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;

/**
 *
 * @author esperanza
 */
@RequestScoped
@Path("jwt")
public class JwtController {
    @Inject
    private JsonWebToken jwtPrincipal;
    
    @GET
    @RolesAllowed({"admin", "standar"})
    @Path("/username")
    public Response getJwtUsername(){
        return Response.ok(this.jwtPrincipal.getName()).build();
    }
    
    @GET
    @RolesAllowed({"admin", "standar"})
    @Path("/grupos")
    public Response getJwtGrupos(@Context SecurityContext securityContext){
        Set<String> grupos= null;
        Principal usuario = securityContext.getUserPrincipal();
        if(usuario instanceof JsonWebToken){
            JsonWebToken jwt = (JsonWebToken) usuario;
            grupos = jwt.getGroups();
        }
        return Response.ok(grupos.toString()).build();
    }
    //claim -> reclamacion
    @GET
    @RolesAllowed({"admin", "standar"})
    @Path("/customClaim")
    public Response getCustonClaim(@Context SecurityContext securityContext){
        if(securityContext.isUserInRole("admin")){
            String customClaim = jwtPrincipal.getClaim("customClaim");
            return Response.ok(customClaim).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }
}
