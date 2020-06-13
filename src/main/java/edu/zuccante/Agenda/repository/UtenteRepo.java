package edu.zuccante.Agenda.repository;

import edu.zuccante.Agenda.entity.Utente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UtenteRepo extends JpaRepository<Utente, Long> {

    public List<Utente> findAll();

    public Utente findByEmail(String email);

    public Utente findByUsername(String username);

    public Utente findByIdUtente(Long idUtente);
}
