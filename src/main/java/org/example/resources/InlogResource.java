package org.example.resources;

import org.example.domain.Gebruiker;
import org.example.domain.dao.GebruikerDao;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.Collection;

@Path("/inlog")
public class InlogResource implements JsonResource {

    @Inject
    private GebruikerDao dao;

    @GET
    public Collection<Gebruiker> allGebruikers(){
        return dao.getAll();
    }

    @POST
    public Gebruiker login(Gebruiker g) {
        try{
            Gebruiker gebruiker = dao.login(g.getEmailadres(), g.getWachtwoord());
            return gebruiker;
        } catch (Exception e){
            throw new NotAuthorizedException("User is not authorized.");
        }
    }
}
