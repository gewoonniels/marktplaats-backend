package org.example.domain.dao;

import org.example.domain.Artikel;
import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ArtikelDao extends Dao<Artikel> {

    @SuppressWarnings("unchecked")
    public List<Artikel> findAllBuyableArtikelen() {
        Query nativeQuery = em.createNativeQuery("select * from artikel LEFT JOIN winkelmand_artikel wa on artikel.id = wa.artikelen_id WHERE wa.artikelen_id IS NULL", Artikel.class);
        return nativeQuery.getResultList();
    }

}
