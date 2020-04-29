package de.thd.projektverwaltung.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "projects")
public class Projekt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "p_id")
    private int pid;
    @Column(name = "bezeichnung")
    private String bezeichnung;
    @Column(name = "budget")
    private String budget;
    @Column(name = "scope")
    private String scope;




}
