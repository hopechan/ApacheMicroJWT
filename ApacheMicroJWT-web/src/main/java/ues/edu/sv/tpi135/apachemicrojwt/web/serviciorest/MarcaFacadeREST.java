/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.edu.sv.tpi135.apachemicrojwt.web.serviciorest;

import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import ues.edu.sv.tpi135.apachemicrojwt.ejb.acceso.AbstractFacade;
import ues.edu.sv.tpi135.apachemicrojwt.ejb.acceso.MarcaFacade;
import ues.edu.sv.tpi135.apachemicrojwt.lib.entidades.Marca;

/**
 *
 * @author esperanza
 */
@RequestScoped
@Path("/marca")
public class MarcaFacadeREST extends AbstractFacade<Marca> {

    @PersistenceContext(unitName = "mantenimientoPU")
    private EntityManager em;
    
    @Inject
    private MarcaFacade servicioMarca;

    public MarcaFacadeREST() {
        super(Marca.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    @RolesAllowed({"admin", "user"})
    public void create(Marca entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Marca entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Marca find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @RolesAllowed({"admin", "user"})
    @Produces({MediaType.APPLICATION_JSON})
    public List<Marca> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Marca> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
