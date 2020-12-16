package org.example.resources;

import org.example.domain.Artikel;
import org.example.domain.Gebruiker;
import org.example.domain.dao.ArtikelDao;
import org.example.domain.dao.GebruikerDao;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.Collection;

@Path("/artikel")
public class ArtikelResource implements JsonResource {

    @Inject
    private ArtikelDao dao;

    @Inject
    private GebruikerDao gebruikerDao;

    @GET
    public Collection<Artikel> allArtikelen(){
        return dao.findAllBuyableArtikelen();
    }

    @POST
    @Path("/{eigenaarid}")
    public Artikel addArtikel(@PathParam("eigenaarid") Long id, Artikel a){
        Gebruiker gebruiker = gebruikerDao.getById(id);
        gebruiker.addArtikel(a);
        gebruikerDao.update(gebruiker.getID(), gebruiker);
        return a;
    };

}
