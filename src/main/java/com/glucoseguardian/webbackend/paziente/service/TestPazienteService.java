package com.glucoseguardian.webbackend.paziente.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * This is an extension of the abstract class PazienteService for testing.
 */
@Service
public class TestPazienteService extends AbstractPazienteService {
  @Autowired
  @Qualifier("pazienteServiceStub")
  PazienteServiceInterface pazienteService;

  @Override
  public PazienteServiceInterface getImplementation() {
    return pazienteService;
  }
}