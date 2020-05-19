package de.thd.projektverwaltung.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "c_id")
    private Long c_id;
    @Column(name = "kundenname")
    private String kundenname;

    @OneToMany(targetEntity = Projekt.class, mappedBy = "customers", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Projekt> projekt;

    public Long getC_id() {
        return c_id;
    }

    public void setC_id(Long c_id) {
        this.c_id = c_id;
    }

    public String getKundenname() {
        return kundenname;
    }

    public void setKundenname(String kundenname) {
        this.kundenname = kundenname;
    }

    public List<Projekt> getProjekt() {
        return projekt;
    }

    public void setProjekt(List<Projekt> projekt) {
        this.projekt = projekt;
    }
}
