/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import Modelo.Cancion;
import Util.Ruta;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@Path("modelo.cancion")
public class CancionFacadeREST extends AbstractFacade<Cancion> {

    @PersistenceContext(unitName = "AccesoSpotify2018PU")
    private EntityManager em;

    public CancionFacadeREST() {
        super(Cancion.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Cancion entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Cancion entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Cancion find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Cancion> findAll() {
        return super.findAll();
    }
    @GET
    @Path("de_album/{idAlbum}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Cancion> obtenerPorAlbum(@PathParam("idAlbum") Integer idAlbum){
        List<Cancion> canciones = new ArrayList();
        try{
            canciones = this.getEntityManager().createNamedQuery("Cancion.findByAlbum")
                    .setParameter("idAlbum", idAlbum)
                    .getResultList();
        }catch(Exception excepcion){
            //logger
        }
        return canciones;
    }
    @GET
    @Path("descargar/{idCancion}")
    @Produces({MediaType.TEXT_PLAIN})
    public byte[] descargar(@PathParam("idCancion") Integer idCancion){
        byte[] buffer = null;
        FileInputStream imputStream = null;
        try {
            File file = new File(Ruta.getRutaCancion(idCancion));
            imputStream = new FileInputStream(file);
            buffer = new byte[(int) file.length()];
            imputStream.read(buffer);
        } catch (IOException ex) {
            Logger.getLogger(CancionFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                imputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(CancionFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return buffer;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Cancion> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
