package org.example.domain;

import org.example.domain.dao.AbstractEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQuery(name = "Artikel.findAll", query = "select a from Artikel a")
public class Artikel implements AbstractEntity<Long> {

    @Id
    @GeneratedValue
    private long id;

    private String naam;

    @Enumerated(value = EnumType.STRING)
    private Soort soort;

    @Enumerated(value = EnumType.STRING)
    private Categorie categorie;

    private Double prijs;

    private String omschrijving;
    private String bijlage;

//    @Convert(converter = BooleanConverter.class)
//    Boolean isVerkocht;

    @ManyToOne(fetch = FetchType.EAGER)
    private Gebruiker eigenaar;

    public Artikel() {
    }

    public Artikel(String naam, Soort soort, Categorie categorie, Double prijs) {
        this.naam = naam;
        this.soort = soort;
        this.categorie = categorie;
        this.prijs = prijs;
    }

    public Artikel(String naam, Soort soort, Categorie categorie, Double prijs, String omschrijving) {
        this(naam, soort, categorie, prijs);
        this.omschrijving = omschrijving;
    }

    public Artikel(String naam, Soort soort, Categorie categorie, Double prijs, String omschrijving, String bijlage) {
        this(naam, soort, categorie, prijs, omschrijving);
        this.bijlage = bijlage;
    }

    public Artikel(String naam, Soort soort, Categorie categorie, Double prijs, String omschrijving, String bijlage, long id) {
        this(naam, soort, categorie, prijs, omschrijving, bijlage);
        this.id = id;
    }

    public void setEigenaar(Gebruiker eigenaar) {
        this.eigenaar = eigenaar;
    }

    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Soort getSoort() {
        return soort;
    }

    public void setSoort(Soort soort) {
        this.soort = soort;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Double getPrijs() {
        return prijs;
    }

    public void setPrijs(Double prijs) {
        this.prijs = prijs;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public String getBijlage() {
        return bijlage;
    }

    public void setBijlage(String bijlage) {
        this.bijlage = bijlage;
    }

//    public void setIsVerkocht(boolean isVerkocht) {
//        this.isVerkocht = isVerkocht;
//    }
//
//    public Boolean getIsVerkocht() {
//        return this.isVerkocht;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artikel artikel = (Artikel) o;
        return id == artikel.id &&
                Objects.equals(naam, artikel.naam) &&
                soort == artikel.soort &&
                categorie == artikel.categorie &&
                Objects.equals(prijs, artikel.prijs) &&
                Objects.equals(omschrijving, artikel.omschrijving);
//                Objects.equals(bijlage, artikel.bijlage) &&
////                Objects.equals(isVerkocht, artikel.isVerkocht) &&
//                Objects.equals(eigenaar, artikel.eigenaar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, naam, soort, categorie, prijs, omschrijving);
    }

    @Override
    public String toString() {
        return "========================\n" +
                naam + "\n" +
                soort + "  " + categorie + "\n" +
                omschrijving + "\n" +
                "Prijs: €" + prijs.doubleValue() + "\n" +
                "========================\n";
    }
}
