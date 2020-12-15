package org.example.domain.dao;

import org.example.domain.Gebruiker;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

@Stateless
public class GebruikerDao extends Dao<Gebruiker> {

    public Gebruiker login(String email, String wachtwoord) {
        TypedQuery<Gebruiker> query = em.createQuery("SELECT u FROM Gebruiker u WHERE u.emailadres = :login AND u.wachtwoord = :password", Gebruiker.class);
        query.setParameter("login", email);
        query.setParameter("password", wachtwoord);
        Gebruiker gebruiker = query.getSingleResult();

        if (gebruiker == null) throw new SecurityException("Invalid user/password");

        return gebruiker;
    }
}
