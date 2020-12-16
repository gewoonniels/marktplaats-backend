package org.example.domain.dao;

import org.example.App;
import org.example.domain.Gebruiker;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(Arquillian.class)
public class DaoTestIT {

    @Inject
    GebruikerDao dao;

    Gebruiker gebruiker;
    Gebruiker gebruiker2;

    @Before
    public void setup() {
        gebruiker = new Gebruiker("test@test.com", "wachtwoord", "naam");
        gebruiker2 = new Gebruiker("test2@test.com", "wachtwoord2", "naam2");
        dao.add(gebruiker);
        dao.add(gebruiker2);
    }

    @Deployment
    public static Archive<?> createDeployment() {
        WebArchive archive = ShrinkWrap.create(WebArchive.class, "ArtikelResourceIT.war")
                .addPackages(true, App.class.getPackage())
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml");

        System.out.println(archive.toString(true));
        return archive;
    }

    @Test
    public void getById(){
        Gebruiker g = dao.getById(1);
        Gebruiker g1 = dao.getById(2);

        assertThat(g.getNaam(), is("naam"));
        assertThat(g1.getNaam(), is("naam2"));

        assertThat(g.getEmailadres(), is("test@test.com"));
        assertThat(g1.getEmailadres(), is("test2@test.com"));

        assertThat(g.getWachtwoord(), is("wachtwoord"));
        assertThat(g1.getWachtwoord(), is("wachtwoord2"));
    }

    @Test
    public void getAll(){
        Collection<Gebruiker> lijstGebruikers = dao.getAll();

        assertThat(lijstGebruikers.size(), is(2));
    }

    @Test
    public void update(){
        //Given
        gebruiker.setNaam("nieuwenaam");
        //When
        dao.update(gebruiker.getID(), gebruiker);
        //then
        Gebruiker test = dao.getById(gebruiker.getID());
        assertThat(test.getNaam(), is("nieuwenaam"));
    }

}