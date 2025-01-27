package com.glucoseguardian.webbackend.storage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.glucoseguardian.webbackend.storage.entity.Notifica;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import org.apache.commons.lang3.Validate;

/**
 * Rappresenta il dto dell'entita notifica.
 */
@JsonInclude(Include.NON_ABSENT)
public class NotificaDto extends RisultatoDto implements Serializable {

  private Long id;
  private String pazienteOggetto;
  private String pazienteDestinatario;
  private String tutoreDestinatario;
  private String adminDestinatario;
  private String dottoreDestinatario;
  private String messaggio;
  private String data;
  private String time;
  private Integer stato;

  /**
   * Costruttore con tutti i campi.
   */
  public NotificaDto(Long id, String pazienteOggetto, String pazienteDestinatario,
      String tutoreDestinatario, String adminDestinatario, String dottoreDestinatario,
      String messaggio, String data, String time, Integer stato) {
    this.id = id;
    this.pazienteOggetto = pazienteOggetto;
    this.pazienteDestinatario = pazienteDestinatario;
    this.tutoreDestinatario = tutoreDestinatario;
    this.adminDestinatario = adminDestinatario;
    this.dottoreDestinatario = dottoreDestinatario;
    this.messaggio = messaggio;
    this.data = data;
    this.time = time;
    this.stato = stato;
  }

  public NotificaDto() {
  }

  /**
   * Costruttore per l'input.
   */
  public NotificaDto(Long id) {
    this.id = id;
  }

  /**
   * Costruttore con tutti i campi tranne i destinatari.
   */
  public NotificaDto(Long id, String pazienteOggetto, String messaggio, String data, String time,
      Integer stato) {
    this.id = id;
    this.pazienteOggetto = pazienteOggetto;
    this.messaggio = messaggio;
    this.data = data;
    this.time = time;
    this.stato = stato;
  }

  /**
   * Costruisce un {@link NotificaDto} a partire da un {@link Notifica}.
   */
  public static NotificaDto valueOf(Notifica notifica) {
    NotificaDto notificaDto = new NotificaDto();
    notificaDto.setId(notifica.getId());
    if (notifica.getPazienteOggetto() != null) {
      notificaDto.setPazienteOggetto(notifica.getPazienteOggetto().getCodiceFiscale());
    }
    if (notifica.getPazienteDestinatario() != null) {
      notificaDto.setPazienteDestinatario(notifica.getPazienteDestinatario().getCodiceFiscale());
    }
    if (notifica.getTutoreDestinatario() != null) {
      notificaDto.setTutoreDestinatario(notifica.getTutoreDestinatario().getCodiceFiscale());
    }
    if (notifica.getAdminDestinatario() != null) {
      notificaDto.setAdminDestinatario(notifica.getAdminDestinatario().getCodiceFiscale());
    }
    if (notifica.getDottoreDestinatario() != null) {
      notificaDto.setDottoreDestinatario(notifica.getDottoreDestinatario().getCodiceFiscale());
    }
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    String dataNotificaDto = dateFormat.format(notifica.getData());
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    String timeString = sdf.format(notifica.getOra());
    notificaDto.setMessaggio(notifica.getMessaggio());
    notificaDto.setData(dataNotificaDto);
    notificaDto.setTime(timeString);
    notificaDto.setStato(notifica.getStato());
    return notificaDto;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPazienteOggetto() {
    return pazienteOggetto;
  }

  public void setPazienteOggetto(String pazienteOggetto) {
    this.pazienteOggetto = pazienteOggetto;
  }

  public String getPazienteDestinatario() {
    return pazienteDestinatario;
  }

  public void setPazienteDestinatario(String pazienteDestinatario) {
    this.pazienteDestinatario = pazienteDestinatario;
  }

  public String getTutoreDestinatario() {
    return tutoreDestinatario;
  }

  public void setTutoreDestinatario(String tutoreDestinatario) {
    this.tutoreDestinatario = tutoreDestinatario;
  }

  public String getAdminDestinatario() {
    return adminDestinatario;
  }

  public void setAdminDestinatario(String adminDestinatario) {
    this.adminDestinatario = adminDestinatario;
  }

  public String getDottoreDestinatario() {
    return dottoreDestinatario;
  }

  public void setDottoreDestinatario(String dottoreDestinatario) {
    this.dottoreDestinatario = dottoreDestinatario;
  }

  public String getMessaggio() {
    return messaggio;
  }

  public void setMessaggio(String messaggio) {
    this.messaggio = messaggio;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public Integer getStato() {
    return stato;
  }

  public void setStato(Integer stato) {
    this.stato = stato;
  }

  public void validateStato() throws IllegalArgumentException {
    Validate.notNull(stato, "lo stato del messaggio non può essere assente");
    Validate.isTrue(stato >= 0 && stato <= 4, "lo stato del messaggio è errato");
  }

  /**
   *  validazione della notifica.
   */
  public void validateNotifica() throws IllegalArgumentException {
    Validate.notNull(messaggio, "il messaggio della notifica non può essere assente");
    Validate.isTrue(messaggio.length() <= 1024 && messaggio.length() >= 1,
        "il messaggio della notifica è di lunghezza errata");
    Validate.notNull(pazienteOggetto, "il paziente oggetto non può essere assente");
    Validate.isTrue(pazienteOggetto.length() == 16,
        "la lunghezza del codice fiscale deve essere 16 caratteri");
    if (pazienteDestinatario == null && tutoreDestinatario == null && adminDestinatario == null
        && dottoreDestinatario == null) {
      throw new IllegalArgumentException("Tutti i destinatari sono vuoti");
    }
    if (pazienteDestinatario != null) {
      Validate.isTrue(pazienteDestinatario.length() == 16,
          "il codice fiscale è di lunghezza errata");
    }
    if (tutoreDestinatario != null) {
      Validate.isTrue(tutoreDestinatario.length() == 16, "il codice fiscale è di lunghezza errata");
    }
    if (adminDestinatario != null) {
      Validate.isTrue(adminDestinatario.length() == 16, "il codice fiscale è di lunghezza errata");
    }
    if (dottoreDestinatario != null) {
      Validate.isTrue(dottoreDestinatario.length() == 16,
          "il codice fiscale è di lunghezza errata");
    }
  }
}