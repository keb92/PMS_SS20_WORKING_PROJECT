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
    private Integer aid;

    @Column(name = "aufgabe")
    private String aufgabe;
    @Column(name = "aufwand")
    private Integer aufwand;


    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getAufgabe() {
        return aufgabe;
    }

    public void setAufgabe(String aufgabe) {
        this.aufgabe = aufgabe;
    }

    public Integer getAufwand() {
        return aufwand;
    }

    public void setAufwand(Integer aufwand) {
        this.aufwand = aufwand;
    }

}