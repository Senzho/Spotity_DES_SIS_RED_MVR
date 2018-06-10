package servicios;

import java.util.ArrayList;
import Modelo.GeneroArtista;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

@Stateless
@Path("modelo.generoartista")
public class GeneroArtistaFacadeREST extends AbstractFacade<GeneroArtista> {

    @PersistenceContext(unitName = "AccesoSpotify2018PU")
    private EntityManager em;

    public GeneroArtistaFacadeREST() {
        super(GeneroArtista.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(GeneroArtista entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, GeneroArtista entity) {
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
    public GeneroArtista find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<GeneroArtista> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<GeneroArtista> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("de_artista/{idArtista}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<GeneroArtista> obtenerGenerosArtista(@PathParam("idArtista") Integer idArtista){
        List<GeneroArtista> generos = new ArrayList();
        try{
            generos = this.getEntityManager().createNamedQuery("GeneroArtista.findByArtista")
                    .setParameter("idArtista", idArtista)
                    .getResultList();
        }catch(NoResultException excepcion){
            //loger
        }
        return generos;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
