package org.example.resources;

import org.example.domain.Artikel;
import org.example.domain.Gebruiker;
import org.example.domain.dao.ArtikelDao;
import org.example.domain.dao.GebruikerDao;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
    public Artikel addArtikel(Artikel a){
        Gebruiker gebruiker = gebruikerDao.getById(a.getId());
        gebruiker.addArtikel(a);
        gebruikerDao.update(gebruiker.getID(), gebruiker);
        return a;
    };

}
