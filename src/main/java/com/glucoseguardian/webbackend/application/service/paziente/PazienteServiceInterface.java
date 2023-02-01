package com.glucoseguardian.webbackend.application.service.paziente;

import com.glucoseguardian.webbackend.exceptions.UserNotFoundException;
import com.glucoseguardian.webbackend.storage.dto.ListDto;
import com.glucoseguardian.webbackend.storage.dto.PazienteDto;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * This is a PazienteService Interface.
 */
public interface PazienteServiceInterface {

  @PreAuthorize("hasAuthority('PAZIENTE') or hasAuthority('DOTTORE') or hasAuthority('TUTORE')")
  PazienteDto findByCodiceFiscale(String codiceFiscalePaziente) throws UserNotFoundException;

  @PreAuthorize("hasAuthority('DOTTORE')")
  ListDto<PazienteDto> findByDottore(String codiceFiscaleDottore) throws UserNotFoundException;

  @PreAuthorize("hasAuthority('TUTORE')")
  ListDto<PazienteDto> findByTutore(String codiceFiscaleTutore) throws UserNotFoundException;

  @PreAuthorize("hasAuthority('DOTTORE')")
  ListDto<PazienteDto> findPaziente(String query);

  @PreAuthorize("hasAuthority('DOTTORE')")
  boolean save(PazienteDto dto);

}
