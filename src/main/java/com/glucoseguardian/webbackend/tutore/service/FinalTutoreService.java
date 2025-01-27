package com.glucoseguardian.webbackend.tutore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * This is an extension of the abstract class.
 */
@Service
@Primary
public class FinalTutoreService extends AbstractTutoreService {
  @Autowired
  @Qualifier("tutoreServiceConcrete")
  TutoreServiceInterface tutoreService;

  @Override
  public TutoreServiceInterface getImplementation() {
    return tutoreService;
  }
}
