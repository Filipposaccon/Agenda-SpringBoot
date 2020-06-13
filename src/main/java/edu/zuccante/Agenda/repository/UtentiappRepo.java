package edu.zuccante.Agenda.repository;

import edu.zuccante.Agenda.entity.Utente;

import java.util.List;

import edu.zuccante.Agenda.entity.Utentiapp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UtentiappRepo extends JpaRepository<Utentiapp, Long> {
    public List<Utentiapp> findAll();

    public void deleteByidAppuntamento(Long idAppuntamento);

    public void deleteUtentiappByIdAppuntamento(Long idAppuntamento);
}
