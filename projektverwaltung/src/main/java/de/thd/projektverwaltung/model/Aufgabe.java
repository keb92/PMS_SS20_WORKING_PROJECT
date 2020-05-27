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
import java.util.List;

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
    private int aid;
    @Column(name = "beschreibung", nullable = false, updatable = false)
    private String beschreibung;
    @Column(name = "aufwand", nullable = false, updatable = false)
    private int aufwand;
    @ManyToOne()
    @JoinColumn(name = "projekt", referencedColumnName = "p_id", nullable = false, updatable = false)
    private Projekt projekt;
    @ManyToOne()
    @JoinColumn(name = "user", referencedColumnName = "user_id")
    private User user;


    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer a_id) {
        this.aid = a_id;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public int getAufwand() {
        return aufwand;
    }

    public void setAufwand(int aufwand) {
        this.aufwand = aufwand;
    }

    public Projekt getProjekt() {
        return projekt;
    }

    public void setProjekt(Projekt projekt) {
        this.projekt = projekt;
    }

    public User getUser(){return user;}

    public void setUser(User user){this.user = user;}

}