package com.glucoseguardian.webbackendstorage.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Classe che rappresenta un'istanza dell'entity Dottore.
 */
@Entity
public class Dottore implements Serializable {

  @Id
  @Column(length = 16, nullable = false)
  private String codiceFiscale;
  @Column(length = 30, nullable = false)
  private String nome;
  @Column(length = 30, nullable = false)
  private String cognome;
  @Column(nullable = false)
  private Date dataNascita;
  @Column(length = 50, nullable = false)
  private String indirizzo;
  @Column(length = 15, nullable = false)
  private String telefono;
  @Column(nullable = false)
  private String email;
  @Column(nullable = false)
  private String password;
  @Column(columnDefinition = "CHAR(1)")
  private char sesso;
  @Column()
  private String totpKey;
  @Column(length = 100, nullable = false)
  private String specializzazione;
  @Column(length = 50, nullable = false)
  private String codiceAlbo;
  @Column(length = 100, nullable = false)
  private String nomeStruttura;
  @Column(length = 100, nullable = false)
  private String indirizzoStruttura;
  @Column(columnDefinition = "UNSIGNED INT(1)")
  private int stato = 0;

  public String getCodiceFiscale() {
    return codiceFiscale;
  }

  public void setCodiceFiscale(String codiceFiscale) {
    this.codiceFiscale = codiceFiscale;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCognome() {
    return cognome;
  }

  public void setCognome(String cognome) {
    this.cognome = cognome;
  }

  public Date getDataNascita() {
    return dataNascita;
  }

  public void setDataNascita(Date dataNascita) {
    this.dataNascita = dataNascita;
  }

  public String getIndirizzo() {
    return indirizzo;
  }

  public void setIndirizzo(String indirizzo) {
    this.indirizzo = indirizzo;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public char getSesso() {
    return sesso;
  }

  public void setSesso(char sesso) {
    this.sesso = sesso;
  }

  public String getTotpKey() {
    return totpKey;
  }

  public void setTotpKey(String totpKey) {
    this.totpKey = totpKey;
  }

  public String getSpecializzazione() {
    return specializzazione;
  }

  public void setSpecializzazione(String specializzazione) {
    this.specializzazione = specializzazione;
  }

  public String getCodiceAlbo() {
    return codiceAlbo;
  }

  public void setCodiceAlbo(String codiceAlbo) {
    this.codiceAlbo = codiceAlbo;
  }

  public String getNomeStruttura() {
    return nomeStruttura;
  }

  public void setNomeStruttura(String nomeStruttura) {
    this.nomeStruttura = nomeStruttura;
  }

  public String getIndirizzoStruttura() {
    return indirizzoStruttura;
  }

  public void setIndirizzoStruttura(String indirizzoStruttura) {
    this.indirizzoStruttura = indirizzoStruttura;
  }

  public int getStato() {
    return stato;
  }

  public void setStato(int stato) {
    this.stato = stato;
  }

  /**
   * Costruttore predefinito della classe Dottore.
   */
  public Dottore(String codiceFiscale, String nome, String cognome, Date dataNascita,
      String indirizzo, String telefono,
      String email, String password, char sesso, String totpKey, String specializzazione,
      String codiceAlbo, String nomeStruttura, String indirizzoStruttura, int stato) {
    this.codiceFiscale = codiceFiscale;
    this.nome = nome;
    this.cognome = cognome;
    this.dataNascita = dataNascita;
    this.indirizzo = indirizzo;
    this.telefono = telefono;
    this.email = email;
    this.password = password;
    this.sesso = sesso;
    this.totpKey = totpKey;
    this.specializzazione = specializzazione;
    this.codiceAlbo = codiceAlbo;
    this.nomeStruttura = nomeStruttura;
    this.indirizzoStruttura = indirizzoStruttura;
    this.stato = stato;
  }

  public Dottore() {
  }

  @Override
  public String toString() {
    return "Dottore{"
        + "codice_fiscale='" + codiceFiscale + '\''
        + ", nome='" + nome + '\''
        + ", cognome='" + cognome + '\''
        + ", data_nascita=" + dataNascita
        + ", indirizzo='" + indirizzo + '\''
        + ", telefono='" + telefono + '\''
        + ", email='" + email + '\''
        + ", password='" + password + '\''
        + ", sesso='" + sesso + '\''
        + ", totp_key='" + totpKey + '\''
        + ", specializzazione='" + specializzazione + '\''
        + ", codice_albo='" + codiceAlbo + '\''
        + ", nome_struttura='" + nomeStruttura + '\''
        + ", indirizzo_struttura='" + indirizzoStruttura + '\''
        + ", stato=" + stato
        + '}';
  }
}