package org.example.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.example.domain.dao.AbstractEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQuery(name = "Gebruiker.findAll", query = "select g from Gebruiker g")
public class Gebruiker implements AbstractEntity<Long>  {

    @Id
    @GeneratedValue
    private long id;

    private String emailadres;
    private String wachtwoord;
    private String naam;
    private String straat;
    private String huisnummer;
    private String postcode;
    private String woonplaats;

    private boolean isAkkoord;
    private boolean isActief;

    @OneToMany(mappedBy = "eigenaar",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Artikel> artikelen = new ArrayList<>();

    @OneToOne(mappedBy = "eigenaar", cascade = CascadeType.ALL)
    private Winkelmand winkelmand;

    public Gebruiker() {

    }

    public void addArtikelToWinkelmand(Artikel a) {
        this.winkelmand.addArtikel(a);
    }

    public void removeArtikelFromWinkelmand(Artikel a) {
        this.winkelmand.removeArtikel(a);
    }

    public void setWinkelmand(Winkelmand winkelmand) {
        this.winkelmand = winkelmand;
        winkelmand.setEigenaar(this);
    }

    public Winkelmand getWinkelmand() {
        return this.winkelmand;
    }

    public Gebruiker(String email, String wachtwoord, String naam) {
        this.emailadres = email;
        this.wachtwoord = wachtwoord;
        this.naam = naam;
    }

    public String getStraat() {
        return straat;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setEmailadres(String emailadres) {
        this.emailadres = emailadres;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public String getEmailadres() {
        return emailadres;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public Long getID() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public void addArtikel(Artikel a) {
        this.artikelen.add(a);
        a.setEigenaar(this);
    }

    @Override
    public String toString() {
        return "Gebruiker{" +
                "id=" + id +
                ", emailadres='" + emailadres + '\'' +
                ", wachtwoord='" + wachtwoord + '\'' +
                ", naam='" + naam + '\'' +
                ", straat='" + straat + '\'' +
                ", huisnummer='" + huisnummer + '\'' +
                ", postcode='" + postcode + '\'' +
                ", woonplaats='" + woonplaats + '\'' +
                ", isAkkoord=" + isAkkoord +
                ", isActief=" + isActief +
//                ", Winkeldmand=" + winkelmand +
                '}';
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
