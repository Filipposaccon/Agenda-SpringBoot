package edu.zuccante.Agenda.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "utentiapp")
public class Utentiapp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUtentiapp;
    @Column(nullable = false)
    private long idUtente;
    private long idAppuntamento;

    public Utentiapp() { // usato da JPA
    }

    public Utentiapp(long idUtentiapp, long idUtente, long idAppuntamento) {
        this.idUtentiapp = idUtentiapp;
        this.idUtente = idUtente;
        this.idAppuntamento = idAppuntamento;
    }

    public long getIdUtentiapp() {
        return idUtentiapp;
    }

    public void setIdUtentiapp(long idUtentiapp) {
        this.idUtentiapp = idUtentiapp;
    }

    public long getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(long idUtente) {
        this.idUtente = idUtente;
    }

    public long getIdAppuntamento() {
        return idAppuntamento;
    }

    public void setIdAppuntamento(long idAppuntamento) {
        this.idAppuntamento = idAppuntamento;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Utentiapp other = (Utentiapp) obj;
        if (this.idUtentiapp != other.idUtentiapp) {
            return false;
        }
        return true;
    }
}
