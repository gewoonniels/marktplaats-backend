package org.example.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Bestelling {

        @Id
        @GeneratedValue
        private long id;

        private String straat;
        private String huisnummer;
        private String plaats;
        private String postcode;
        private LocalDate datum;

        @Enumerated(value = EnumType.STRING)
        private Betaalwijze betaalwijze;

        @Enumerated(value = EnumType.STRING)
        private Bezorgwijze bezorgwijze;

        @OneToOne(cascade = CascadeType.ALL)
        Winkelmand winkelmand;

        public Bestelling() {
        }

        public Winkelmand getWinkelmand() {
            return winkelmand;
        }

        public Bestelling(String straat, String huisnummer, String plaats, String postcode, LocalDate datum, Betaalwijze betaalwijze, Bezorgwijze bezorgwijze, Winkelmand winkelmand) {
            this.straat = straat;
            this.huisnummer = huisnummer;
            this.plaats = plaats;
            this.postcode = postcode;
            this.datum = datum;
            this.betaalwijze = betaalwijze;
            this.bezorgwijze = bezorgwijze;
            this.winkelmand = winkelmand;
        }

        public void printCost() {
            winkelmand.artikelen.forEach(a -> System.out.println(a.getNaam() + ":   " + a.getPrijs()));
            double extraCost = 0;
            if (bezorgwijze == Bezorgwijze.REMBOURS) {
                System.out.println(winkelmand.artikelen.size() + "x Rembours: " + (winkelmand.artikelen.size() * 7.50));
                extraCost += (winkelmand.artikelen.size() * 7.50);
            }
            if (betaalwijze == Betaalwijze.CREDITCARD) {
                System.out.println("Creditcard: 5");
                extraCost += 5;

            }
            Double totalPrice = winkelmand.artikelen.stream().mapToDouble(Artikel::getPrijs).sum();
            System.out.println("---------------------");
            System.out.println("Totaal:" + (totalPrice + extraCost));
        }
}
