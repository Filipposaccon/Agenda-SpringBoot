package edu.zuccante.Agenda.repository;

import edu.zuccante.Agenda.entity.Appuntamenti;
import edu.zuccante.Agenda.entity.Promemoria;
import edu.zuccante.Agenda.entity.Utente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AppuntamentiRepo extends JpaRepository<Appuntamenti, Long> {
    public List<Appuntamenti> findAll();

    public Appuntamenti findBynome(String nome);

    public Appuntamenti findByidAppuntamento(Long idAppuntamento);
}
