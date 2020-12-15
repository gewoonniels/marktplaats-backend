package org.example.resources;
import org.example.domain.Artikel;
import org.example.domain.Gebruiker;
import org.example.domain.Winkelmand;
import org.example.domain.dao.ArtikelDao;
import org.example.domain.dao.GebruikerDao;
import org.example.domain.dao.WinkelmandDao;

import javax.inject.Inject;
import javax.ws.rs.*;

@Path("winkelmand")
public class WinkelmandResource implements JsonResource {

    @Inject
    private WinkelmandDao winkelmandDao;

    @Inject
    private GebruikerDao gebruikerDao;

    @Inject
    private ArtikelDao artikelDao;

    @GET
    @Path("/{id}")
    public Winkelmand getWinkelmandById(@PathParam("id") Long id){
        Gebruiker ingelogdeGebruiker = gebruikerDao.getById(id);
        if(ingelogdeGebruiker.getWinkelmand() == null){
            Winkelmand w = new Winkelmand();
            ingelogdeGebruiker.setWinkelmand(w);
            gebruikerDao.update(ingelogdeGebruiker.getID(),ingelogdeGebruiker);
        }
        return ingelogdeGebruiker.getWinkelmand();
    }

    @POST
    @Path("/{id}")
    public Winkelmand addArtikelToWinkelmand(@PathParam("id") Long id, Artikel a){
        Gebruiker ingelogdeGebruiker = gebruikerDao.getById(id);
        if(ingelogdeGebruiker.getWinkelmand() == null){
            Winkelmand w = new Winkelmand();
            ingelogdeGebruiker.setWinkelmand(w);
            gebruikerDao.update(ingelogdeGebruiker.getID(),ingelogdeGebruiker);
        }
        Artikel foundArtikel = artikelDao.getById(a.getId());
        ingelogdeGebruiker.addArtikelToWinkelmand(foundArtikel);
        gebruikerDao.update(ingelogdeGebruiker.getId(),ingelogdeGebruiker);
        return ingelogdeGebruiker.getWinkelmand();
    }

    @DELETE
    @Path("/{gebruikerId}/{artikelid}")
    public Winkelmand deleteArtikelFromWinkelmand(@PathParam("gebruikerId") Long gebruikerId, @PathParam("artikelid") Long artikelId){
        Gebruiker ingelogdeGebruiker = gebruikerDao.getById(gebruikerId);
        Artikel foundArtikel = artikelDao.getById(artikelId);
        ingelogdeGebruiker.removeArtikelFromWinkelmand(foundArtikel);
        gebruikerDao.update(ingelogdeGebruiker.getId(),ingelogdeGebruiker);
        return ingelogdeGebruiker.getWinkelmand();
    }


}
