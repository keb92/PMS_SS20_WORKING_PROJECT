package de.thd.projektverwaltung.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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
    @Column(name = "Projektleiter")
    private String projektleiter;
    @ManyToOne(targetEntity = Customer.class, cascade = CascadeType.ALL)
    @JoinColumn(name="cp_fk", referencedColumnName = "c_id")
    private List<Customer> customers;



    public Integer getpid() {
        return pid;
    }

    public void setpid(Integer id) {
        this.pid = id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getScope() {
        return scope;
    }

    public void setScope (String scope) { this.scope = scope; }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }


    /*public String getProjektleiter() {
        return projektleiter;
    }

    public void setProjektleiter (String projektleiter){
        this.projektleiter = projektleiter;

    }*/

}
