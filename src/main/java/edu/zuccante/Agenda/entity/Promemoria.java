package edu.zuccante.Agenda.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "promemoria")
public class Promemoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPromemoria;
    @Column(nullable = false)
    private String nome;
    private String descrizione;
    private String data;
    private int idUtente;

    public Promemoria() { // usato da JPA
    }

    public Promemoria(long idPromemoria, String nome, String descrizione, String data, int idUtente) {
        this.idPromemoria = idPromemoria;
        this.nome = nome;
        this.descrizione = descrizione;
        this.data = data;
        this.idUtente = idUtente;
    }

    public long getIdPromemoria() {
        return idPromemoria;
    }

    public void setIdPromemoria(long idPromemoria) {
        this.idPromemoria = idPromemoria;
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

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
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
        final Promemoria other = (Promemoria) obj;
        if (this.idPromemoria != other.idPromemoria) {
            return false;
        }
        return true;
    }
}
