package edu.zuccante.Agenda;

import edu.zuccante.Agenda.entity.Appuntamenti;
import edu.zuccante.Agenda.entity.Promemoria;
import edu.zuccante.Agenda.entity.Utente;
import edu.zuccante.Agenda.entity.Utentiapp;
import edu.zuccante.Agenda.repository.AppuntamentiRepo;
import edu.zuccante.Agenda.repository.PromemoriaRepo;
import edu.zuccante.Agenda.repository.UtenteRepo;

import java.util.ArrayList;
import java.util.List;

import edu.zuccante.Agenda.repository.UtentiappRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class MioController {

    @Autowired
    UtenteRepo utenteRepo;
    @Autowired
    AppuntamentiRepo appuntamentiRepo;
    @Autowired
    UtentiappRepo utentiappRepo;
    @Autowired
    PromemoriaRepo promemoriaRepo;

    @RequestMapping("/utenti") //ritorna lista utenti
    public List<Utente> listautenti() {
        return utenteRepo.findAll();
    }


    @GetMapping("/utentereg/{nome},{cognome},{email},{telefono},{username},{password}")
    //registrazione di un nuovo utente
    public void personareg(@PathVariable("nome") String nome, @PathVariable("cognome") String cognome, @PathVariable("email") String email, @PathVariable("telefono") String telefono, @PathVariable("username") String username, @PathVariable("password") String password) {
        Utente insPersona = new Utente(-1, nome, cognome, email, telefono, username, password);
        utenteRepo.save(insPersona);
    }

    @GetMapping("/utentebyid/{id}") //ritona un utente in basa al suo id
    public Utente utentebyid(@PathVariable("id") Long id) {
        return utenteRepo.findById(id).orElse(null);

    }

    @GetMapping("/utentebyemail/{email}") //ritorna un utente in base alla sua email
    public Utente utenteemail(@PathVariable("email") String email) {
        if (utenteRepo.findByEmail(email) == null) {
            Utente n = new Utente(-2, null, null, null, null, null, null);
            return n;
        } else {
            return utenteRepo.findByEmail(email);
        }
    }

    @GetMapping("/modificaUtente/{idUtente},{nome},{cognome},{email},{telefono},{username},{password}")
    //modifica di un utente
    public void modificaUtente(@PathVariable("idUtente") Long idUtente, @PathVariable("nome") String nome, @PathVariable("cognome") String cognome, @PathVariable("email") String email, @PathVariable("telefono") String telefono, @PathVariable("username") String username, @PathVariable("password") String password) {
        Utente utente = utenteRepo.findByIdUtente(idUtente);
        utente.setNome(nome);
        utente.setCognome(cognome);
        utente.setEmail(email);
        utente.setTelefono(telefono);
        utente.setUsername(username);
        utente.setPassword(password);
        utenteRepo.save(utente);
    }

    @GetMapping("/utentebyuser/{username}") //ritorna utente in base allo username
    public Utente utenteuser(@PathVariable("username") String username) {
        if (utenteRepo.findByUsername(username) == null) {
            Utente n = new Utente(-2, null, null, null, null, null, null);
            return n;
        } else {
            return utenteRepo.findByUsername(username);
        }
    }

    @GetMapping("/promemoriabyidUtente/{idUtente}") //ritorna promemoria in basa all id utente
    public List<Promemoria> promemoriabyidUtente(@PathVariable("idUtente") int idUtente) {
        return promemoriaRepo.findByidUtente(idUtente);
    }

    @GetMapping("/promemoriabyidPromemoria/{idPromemoria}")//ritorna promemoria in basa all id promemoria
    public Promemoria promemoriabyidPromemoria(@PathVariable("idPromemoria") Long idPromemoria) {
        return promemoriaRepo.findByidPromemoria(idPromemoria);
    }

    @GetMapping("/deletepromemoria/{idPromemoria}") //cancella promemoria in base all'id
    public void deletepromemoria(@PathVariable("idPromemoria") Long idPromemoria) {
        promemoriaRepo.deleteById(idPromemoria);
    }

    @GetMapping("/modpromemoria/{idPromemoria},{nome},{descrizione},{data},{idUtente}")
    //modifica di un promemoria esistente
    public void modpromemoria(@PathVariable("idPromemoria") Long idPromemoria, @PathVariable("nome") String nome, @PathVariable("descrizione") String descrizione, @PathVariable("data") String data, @PathVariable("idUtente") int idUtente) {
        Promemoria promemoria = promemoriaRepo.findByidPromemoria(idPromemoria);
        promemoria.setNome(nome.replace("@", " "));
        promemoria.setDescrizione(descrizione.replace("@", " "));
        promemoria.setData(data.replace("-", "/"));
        promemoria.setIdUtente(idUtente);
        promemoriaRepo.save(promemoria);
    }

    @GetMapping("/addpromemoria/{nome},{descrizione},{data},{idUtente}") //aggiunge un nuovo promemoria
    public void addpromemoria(@PathVariable("nome") String nome, @PathVariable("descrizione") String descrizione, @PathVariable("data") String data, @PathVariable("idUtente") int idUtente) {
        Promemoria promemoria = new Promemoria(-1, nome.replace("@", " "), descrizione.replace("@", " "), data.replace("-", "/"), idUtente);
        promemoriaRepo.save(promemoria);

    }

    @GetMapping("/addappuntamento/{nome},{descrizione},{data}") //aggiunge un nuovo appuntamento
    public Appuntamenti addpromemoria(@PathVariable("nome") String nome, @PathVariable("descrizione") String descrizione, @PathVariable("data") String data) {
        Appuntamenti app = new Appuntamenti(-1, nome.replace("@", " "), descrizione.replace("@", " "), data.replace("-", "/"));
        appuntamentiRepo.save(app);
        return appuntamentiRepo.findBynome(nome.replace("@", " "));

    }

    @GetMapping("/addutentiapp/{idUtente},{idAppuntamento}")//aggiunge utente in utentiapp
    public void addutentiapp(@PathVariable("idUtente") Long idUtente, @PathVariable("idAppuntamento") Long idAppuntamento) {
        Utentiapp ua = new Utentiapp(-1, idUtente, idAppuntamento);
        utentiappRepo.save(ua);
    }

    @GetMapping("/getappuntamentibyidUtente/{idUtente}") //ritorna gli appuntamenti di un utente
    public List<Appuntamenti> addutentiapp(@PathVariable("idUtente") Long idUtente) {
        List<Utentiapp> list = utentiappRepo.findAll();
        System.out.println(list.size());
        List<Appuntamenti> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getIdUtente() == idUtente) {
                result.add(appuntamentiRepo.findByidAppuntamento(list.get(i).getIdAppuntamento()));
            }

        }
        return result;
    }

    @GetMapping("/getappuntamentibyidAppuntamento/{idAppuntamento}") //ritorna l'oggetto appuntamento completo
    public Appuntamenti getappbyidApp(@PathVariable("idAppuntamento") Long idAppuntamento) {
        return appuntamentiRepo.findByidAppuntamento(idAppuntamento);
    }

    @GetMapping("/getpartecipantibyidAppuntamento/{idAppuntamento}") //ritorna partecipanti di un appuntamento
    public List<Utente> getpartecipantibyidapp(@PathVariable("idAppuntamento") Long idAppuntamento) {
        List<Utentiapp> list = utentiappRepo.findAll();
        List<Utente> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getIdAppuntamento() == idAppuntamento) {
                result.add(utenteRepo.findByIdUtente(list.get(i).getIdUtente()));
            }
        }
        return result;
    }

    @GetMapping("/eliminaApp/{idAppuntamento}")//elimina appuntamento
    public void eliminaapp(@PathVariable("idAppuntamento") Long idAppuntamento) {
        appuntamentiRepo.deleteById(idAppuntamento);
        List<Utentiapp> list = utentiappRepo.findAll();
        System.out.println("Size=" + list.size());
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getIdAppuntamento() == idAppuntamento)
                utentiappRepo.deleteById(list.get(i).getIdUtentiapp());
        }
    }

    @GetMapping("/modificaApp/{idAppuntamento},{nome},{descrizione},{data}") //modifica di un appuntamento
    public void modicaapp(@PathVariable("idAppuntamento") Long idAppuntamento, @PathVariable("nome") String nome, @PathVariable("descrizione") String descrizione, @PathVariable("data") String data) {

        Appuntamenti app = appuntamentiRepo.findByidAppuntamento(idAppuntamento);
        app.setNome(nome.replace("@", " "));
        app.setDescrizione(descrizione.replace("@", " "));
        app.setData(data.replace("-", "/"));
        appuntamentiRepo.save(app);
    }

    @GetMapping("/modificaPartecipanti/{idpartecipanti},{idAppuntamento}") //modifica partecipanti di un appuntamento
    public void modificapart(@PathVariable("idpartecipanti") String idpartecipanti, @PathVariable("idAppuntamento") Long idAppuntamento) {
        String[] parts = idpartecipanti.split("-");
        List<Utentiapp> list = utentiappRepo.findAll();
        System.out.println("Size=" + list.size());
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getIdAppuntamento() == idAppuntamento)
                utentiappRepo.deleteById(list.get(i).getIdUtentiapp());
        }

        for (int i = 0; i < parts.length; i++) {
            Utentiapp utentiapp = new Utentiapp(-1, Long.parseLong(parts[i]), idAppuntamento);
            utentiappRepo.save(utentiapp);
        }

    }


}

