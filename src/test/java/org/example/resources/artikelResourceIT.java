package org.example.resources;

import org.example.App;
import org.example.domain.Artikel;
import org.example.domain.Categorie;
import org.example.domain.Gebruiker;
import org.example.domain.Soort;
import org.example.domain.dao.AbstractEntity;
import org.example.domain.dao.ArtikelDao;
import org.example.domain.dao.Dao;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.net.URL;

import static javax.ws.rs.client.Entity.entity;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Arquillian.class)
public class artikelResourceIT {

    @ArquillianResource
    private URL deploymentURL;

    private String artikelResource;
    private String artikelUri = "resources/artikel";

    @Before
    public void setup() {
        artikelResource = deploymentURL + artikelUri;
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
    public void whenArtikelIsPostedICanGetIt() {
        Client http = ClientBuilder.newClient();
        Artikel a = new Artikel("artikel", Soort.PRODUCT, Categorie.HOBBY, 20D, "een hele mooie omschrijving", "nvt.");

        String postedContact = http
                .target(artikelResource)
                .request().post(entity(a, APPLICATION_JSON), String.class);

        String allArtikelen = http
                .target(artikelResource)
                .request().get(String.class);

        assertThat(allArtikelen, containsString("1"));
        assertThat(allArtikelen, containsString("artikel"));
        assertThat(allArtikelen, containsString("een hele mooie omschrijving"));
        assertThat(allArtikelen, containsString("nvt"));
        assertThat(allArtikelen, containsString("HOBBY"));
        assertThat(allArtikelen, containsString("PRODUCT"));
    }

}