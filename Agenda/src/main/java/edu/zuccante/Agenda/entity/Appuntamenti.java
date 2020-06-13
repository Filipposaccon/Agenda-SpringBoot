package edu.zuccante.Agenda.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "appuntamenti")
public class Appuntamenti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAppuntamento;
    @Column(nullable = false)
    private String nome;
    private String descrizione;
    private String data;

    public Appuntamenti() { // usato da JPA
    }

    public Appuntamenti(long idAppuntamento, String nome, String descrizione, String data) {
        this.idAppuntamento = idAppuntamento;
        this.nome = nome;
        this.descrizione = descrizione;
        this.data = data;
    }

    public long getIdAppuntamento() {
        return idAppuntamento;
    }

    public void setIdAppuntamento(long idAppuntamento) {
        this.idAppuntamento = idAppuntamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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
        final Appuntamenti other = (Appuntamenti) obj;
        if (this.idAppuntamento != other.idAppuntamento) {
            return false;
        }
        return true;
    }
}
