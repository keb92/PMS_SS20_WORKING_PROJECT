package de.thd.projektverwaltung.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "time")
public class Time {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "t_id")
    private int tid;
    private int zeit;
    @OneToOne()
    @JoinColumn(name = "aufgabe", referencedColumnName = "a_id")
    private Aufgabe aufgabe;


    public Aufgabe getAufgabe() {
        return aufgabe;
    }

    public void setAufgabe(Aufgabe aufgabe) {
        this.aufgabe = aufgabe;
    }

    public int getZeit() {
        return zeit;
    }

    public void setZeit(int zeit) {
        this.zeit = zeit;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }
}
