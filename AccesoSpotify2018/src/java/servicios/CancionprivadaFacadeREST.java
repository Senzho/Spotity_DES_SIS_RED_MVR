/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import Modelo.Cancionprivada;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
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

/**
 *
 * @author Desktop
 */
@Stateless
@Path("modelo.cancionprivada")
public class CancionprivadaFacadeREST extends AbstractFacade<Cancionprivada> {

    @PersistenceContext(unitName = "AccesoSpotify2018PU")
    private EntityManager em;

    public CancionprivadaFacadeREST() {
        super(Cancionprivada.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Cancionprivada entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Cancionprivada entity) {
        super.edit(entity);
    }
    
    @GET
    @Path("disponible/{idUsuario}/{idCancion}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Cancionprivada establecerComoDisponible(@PathParam("idUsuario") Integer idUsuario, @PathParam("idCancion") Integer idCancion){
        Cancionprivada cancion = new Cancionprivada();
        try{
            cancion = (Cancionprivada) this.getEntityManager().createNamedQuery("Cancionprivada.findByCancion")
                    .setParameter("idUsuario", idUsuario)
                    .setParameter("idCancion", idCancion)
                    .getSingleResult();
            cancion.setDisponibleSnConexion(true);
            super.edit(cancion);
        }catch(Exception excepcion){
            cancion = new Cancionprivada();
            cancion.setId(0);
        }
        return cancion;
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Cancionprivada find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    @GET
    @Path("de_cancion/{idUsuario}/{idCancion}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Cancionprivada obtenerDeCancion(@PathParam("idUsuario") Integer idUsuario, @PathParam("idCancion") Integer idCancion) {
        Cancionprivada cancion = new Cancionprivada();
        try{
            cancion = (Cancionprivada) this.getEntityManager().createNamedQuery("Cancionprivada.findByCancion")
                    .setParameter("idUsuario", idUsuario)
                    .setParameter("idCancion", idCancion)
                    .getSingleResult();
            if (!cancion.getDisponibleSnConexion()){
                cancion = new Cancionprivada();
                cancion.setId(0);
            }
        }catch(Exception excepcion){
            cancion.setId(0);
        }
        return cancion;
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Cancionprivada> findAll() {
        return super.findAll();
    }
    @GET
    @Path("de_usuario/{idUsuario}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Cancionprivada> obtenerDeUsuario(@PathParam("idUsuario") Integer idUsuario) {
        List<Cancionprivada> canciones = new ArrayList();
        try{
            canciones = this.getEntityManager().createNamedQuery("Cancionprivada.findByUsuario")
                    .setParameter("idUsuario", idUsuario)
                    .getResultList();
        }catch (Exception excepcion){
            //logger
        }
        return canciones;
    }
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Cancionprivada> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
