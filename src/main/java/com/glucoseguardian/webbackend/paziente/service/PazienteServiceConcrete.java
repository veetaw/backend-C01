package com.glucoseguardian.webbackend.paziente.service;

import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.storage.dao.AssunzioneFarmacoDao;
import com.glucoseguardian.webbackend.storage.dao.DottoreDao;
import com.glucoseguardian.webbackend.storage.dao.FarmacoDao;
import com.glucoseguardian.webbackend.storage.dao.NumeroTelefonoDao;
import com.glucoseguardian.webbackend.storage.dao.PazienteDao;
import com.glucoseguardian.webbackend.storage.dao.TerapiaDao;
import com.glucoseguardian.webbackend.storage.dao.TutoreDao;
import com.glucoseguardian.webbackend.storage.dto.AssunzioneFarmacoDto;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.dto.NumeroTelefonoDto;
import com.glucoseguardian.webbackend.storage.dto.PazienteDto;
import com.glucoseguardian.webbackend.storage.entity.AssunzioneFarmaco;
import com.glucoseguardian.webbackend.storage.entity.Dottore;
import com.glucoseguardian.webbackend.storage.entity.Farmaco;
import com.glucoseguardian.webbackend.storage.entity.NumeroTelefono;
import com.glucoseguardian.webbackend.storage.entity.Paziente;
import com.glucoseguardian.webbackend.storage.entity.Terapia;
import com.glucoseguardian.webbackend.storage.entity.Tutore;
import java.security.SecureRandom;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * This is an implementation of PazienteServiceInterface.
 */
@Service
public class PazienteServiceConcrete implements PazienteServiceInterface {

  @Autowired
  private PazienteDao pazienteDao;
  @Autowired
  private DottoreDao dottoreDao;
  @Autowired
  private TutoreDao tutoreDao;
  @Autowired
  private NumeroTelefonoDao numeroTelefonoDao;
  @Autowired
  private TerapiaDao terapiaDao;
  @Autowired
  private FarmacoDao farmacoDao;
  @Autowired
  private AssunzioneFarmacoDao assunzioneFarmacoDao;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public PazienteDto findByCodiceFiscale(String codiceFiscalePaziente)
      throws UserNotFoundException {
    Paziente result = pazienteDao.findById(codiceFiscalePaziente).orElse(null);
    if (result != null) {
      return PazienteDto.valueOf(result);
    } else {
      throw new UserNotFoundException("Paziente non trovato.");
    }
  }

  @Override
  public ListDto<PazienteDto> findByDottore(String codiceFiscaleDottore)
      throws UserNotFoundException {
    Dottore result = dottoreDao.findById(codiceFiscaleDottore).orElse(null);
    if (result == null) {
      throw new UserNotFoundException("Dottore non trovato.");
    }
    List<Paziente> list = result.getPazientes();
    List<PazienteDto> pazienteDtos = new ArrayList<>();
    for (Paziente paziente : list) {
      pazienteDtos.add(PazienteDto.valueOf(paziente));
    }
    ListDto<PazienteDto> listDto = new ListDto<>(pazienteDtos);
    return listDto;
  }

  @Override
  public ListDto<PazienteDto> findByTutore(String codiceFiscaleTutore)
      throws UserNotFoundException {
    Tutore result = tutoreDao.findById(codiceFiscaleTutore).orElse(null);
    if (result == null) {
      throw new UserNotFoundException("Tutore non trovato.");
    }
    List<Paziente> list = result.getPazienteList();
    List<PazienteDto> pazienteDtos = new ArrayList<>();
    for (Paziente paziente : list) {
      pazienteDtos.add(PazienteDto.valueOf(paziente));
    }
    ListDto<PazienteDto> listDto = new ListDto<>(pazienteDtos);
    return listDto;
  }

  @Override
  public ListDto<PazienteDto> findPaziente(String query) {
    List<Paziente> listCf = pazienteDao.findByCodiceFiscaleContainingIgnoreCase(query);
    List<Paziente> listEmail = pazienteDao.findByEmailContainingIgnoreCase(query);
    List<Paziente> listCognome = pazienteDao.findByCognomeContainingIgnoreCase(query);
    List<Paziente> listNome = pazienteDao.findByNomeContainingIgnoreCase(query);

    Set<Paziente> set = new LinkedHashSet<>(listCf);
    set.addAll(listEmail);
    set.addAll(listCognome);
    set.addAll(listNome);

    List<PazienteDto> ricercaPaziente = new ArrayList<>();
    for (Paziente paziente : set) {
      ricercaPaziente.add(PazienteDto.valueOf(paziente));
    }
    ListDto<PazienteDto> listDto = new ListDto<>(ricercaPaziente);
    return listDto;
  }

  @Override
  public boolean save(PazienteDto dto) {

    //TODO: send email with password

    String randomPassword = RandomStringUtils.random(16, 0, 0, true, true, null,
        new SecureRandom());

    // Create entity
    Paziente pazienteEntity = new Paziente(dto.getCodiceFiscale(), dto.getNome(), dto.getCognome(),
        Date.valueOf(dto.getDataNascita()), dto.getIndirizzo(), dto.getTelefono(), dto.getEmail(),
        passwordEncoder.encode(randomPassword), dto.getSesso().charAt(0), null,
        dto.getTipoDiabete(), dto.getComorbilita(), dto.getFarmaciAssunti(),
        dto.getPeriodoDiMonitoraggio());

    Dottore dottoreEntity = dottoreDao.findById(dto.getIdDottore()).orElse(null);
    pazienteEntity.setDottore(dottoreEntity);

    pazienteDao.saveAndFlush(pazienteEntity);

    List<NumeroTelefonoDto> listNumeroDto = dto.getNumeriUtili();

    for (NumeroTelefonoDto numeroDto : listNumeroDto) {
      NumeroTelefono numero = new NumeroTelefono(numeroDto.getNumero(), pazienteEntity);
      numeroTelefonoDao.save(numero);
    }

    Terapia terapiaEntity = new Terapia();
    terapiaEntity.setDataInizio(new Date(System.currentTimeMillis()));
    terapiaEntity.setPaziente(pazienteEntity);
    terapiaEntity.setDottore(dottoreEntity);
    terapiaDao.saveAndFlush(terapiaEntity);

    for (AssunzioneFarmacoDto farmacoDto : dto.getTerapia().getFarmaci()) {
      Farmaco farmaco = farmacoDao.findById(farmacoDto.getIdFarmaco()).orElse(null);
      if (farmaco != null) {
        AssunzioneFarmaco assFarmaco = new AssunzioneFarmaco(farmaco, farmacoDto.getDosaggio(),
            Time.valueOf(farmacoDto.getOrarioAssunzione()), farmacoDto.getViaDiSomministrazione(),
            farmacoDto.getViaDiSomministrazione());
        assunzioneFarmacoDao.save(assFarmaco);
      }
    }

    // persist the new entity
    pazienteDao.saveAndFlush(pazienteEntity);

    // Check if entity is correctly saved
    return pazienteDao.existsById(pazienteEntity.getCodiceFiscale());
  }
}