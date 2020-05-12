package de.thd.projektverwaltung.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "aufgaben")
public class Aufgabe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "a_id")
    private int a_id;
    @Column(name = "beschreibung")
    private String beschreibung;
    @Column(name = "aufwand")
    private String aufwand;


    public Integer getAid() {
        return a_id;
    }

    public void setAid(Integer a_id) {
        this.a_id = a_id;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getAufwand() {
        return aufwand;
    }

    public void setAufwand(String aufwand) {
        this.aufwand = aufwand;
    }

}