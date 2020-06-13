package edu.zuccante.Agenda.repository;

import edu.zuccante.Agenda.entity.Appuntamenti;
import edu.zuccante.Agenda.entity.Promemoria;
import edu.zuccante.Agenda.entity.Utente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PromemoriaRepo extends JpaRepository<Promemoria, Long> {
    public List<Promemoria> findAll();

    public List<Promemoria> findByidUtente(int idUtente);

    public Promemoria findByidPromemoria(Long idPromemoria);
}
