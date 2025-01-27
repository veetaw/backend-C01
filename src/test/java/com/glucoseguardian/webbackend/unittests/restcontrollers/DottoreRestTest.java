package com.glucoseguardian.webbackend.unittests.restcontrollers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.glucoseguardian.webbackend.dottore.rest.DottoreRest;
import com.glucoseguardian.webbackend.dottore.service.DottoreServiceStub;
import com.glucoseguardian.webbackend.dottore.service.TestDottoreService;
import com.glucoseguardian.webbackend.storage.dto.DottoreDto;
import com.glucoseguardian.webbackend.storage.dto.RisultatoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultMatcher;

@WebMvcTest(DottoreRest.class)
// Remove security filters
@AutoConfigureMockMvc(addFilters = false)
// Import Test service and Service Stub
@Import({DottoreServiceStub.class, TestDottoreService.class})
public class DottoreRestTest extends AbstractRestTest {

  @Autowired
  DottoreServiceStub serviceStub;

  /**
   * Test ID TC_2.1.
   */
  @Test
  public void testSave1() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setNome("");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("5545 San nicola la strada");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    RisultatoDto oracolo = new RisultatoDto("La lunghezza del campo nome non è valida");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_2.2.
   */
  @Test
  public void testSave2() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setNome("Matteo");
    input.setCognome(
        "Aldiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("5545 San nicola la strada");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    RisultatoDto oracolo = new RisultatoDto("La lunghezza del cognome non è valida");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_2.3.
   */
  @Test
  public void testSave3() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963YXYALA");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("5545 San nicola la strada");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    RisultatoDto oracolo = new RisultatoDto("La lunghezza del codice fiscale deve essere di 16 caratteri");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_2.4.
   */
  @Test
  public void testSave4() throws Exception {
    serviceStub.duplicatedEmail = true;

    DottoreDto input = new DottoreDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("5545 San nicola la strada");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    RisultatoDto oracolo = new RisultatoDto(
        "Email già presente nel database");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_2.5.
   */
  @Test
  public void testSave5() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("N");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("5545 San nicola la strada");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    RisultatoDto oracolo = new RisultatoDto("il sesso non è valido");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_2.6.
   */
  @Test
  public void testSave6() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06-2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("5545 San nicola la strada");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    RisultatoDto oracolo = new RisultatoDto("la data nascita inserita non è valida");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_2.7.
   */
  @Test
  public void testSave7() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/3001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("5545 San nicola la strada");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    RisultatoDto oracolo = new RisultatoDto("La data di nascita è nel futuro");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_2.8.
   */
  @Test
  public void testSave8() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldihotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("5545 San nicola la strada");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    RisultatoDto oracolo = new RisultatoDto("L'email non è valida");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_2.9.
   */
  @Test
  public void testSave9() throws Exception {
    serviceStub.duplicatedId = true;

    DottoreDto input = new DottoreDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("5545 San nicola la strada");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    RisultatoDto oracolo = new RisultatoDto(
        "Codice fiscale già presente nel database");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_2.10.
   */
  @Test
  public void testSave10() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("393877654222222222222222222222222222222222");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("5545 San nicola la strada");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    RisultatoDto oracolo = new RisultatoDto("il campo numero di telefono non rispetta il formato");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_2.11.
   */
  @Test
  public void testSave11() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("5545 San nicola la strada");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    RisultatoDto oracolo = new RisultatoDto("La lunghezza dell'indirizzo non è valida");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_2.12.
   */
  @Test
  public void testSave12() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setPassword("");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("5545 San nicola la strada");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    RisultatoDto oracolo = new RisultatoDto("La lunghezza del campo password non è valida");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_2.13.
   */
  @Test
  public void testSave13() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione(
        "DiabetologoDiabetologoDiabetologoDiabetologoDiabetologoDiabetologoDiabetologoDiabetologoDiabetologoDiabetologoDiabetologoDiabetologoDiabetologoDiabetologoDiabetologoDiabetologoDiabetologoDiabetologo");
    input.setCodiceAlbo("5545 San nicola la strada");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    RisultatoDto oracolo = new RisultatoDto("la lunghezza del campo Specializzazione non è valida");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_2.14.
   */
  @Test
  public void testSave14() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("5545 San nicola la strada5545 San nicola la strada5545 San nicola la strada5545 San nicola la strada5545 San nicola la strada5545 San nicola la strada5545 San nicola la strada5545 San nicola la strada5545 San nicola la strada5545 San nicola la strada");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    RisultatoDto oracolo = new RisultatoDto("La lunghezza del campo Codice Albo non è valida");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_2.15.
   */
  @Test
  public void testSave15() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("5545 San nicola la strada");
    input.setNomeStruttura("");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    RisultatoDto oracolo = new RisultatoDto("La lunghezza del campo Nome Struttura non è valida");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_2.16.
   */
  @Test
  public void testSave16() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("5545 San nicola la strada");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura(
        "Caserta Via Roma 52Caserta Via Roma 52Caserta Via Roma 52Caserta Via Roma 52Caserta Via Roma 52Caserta Via Roma 52Caserta Via Roma 52Caserta Via Roma 52Caserta Via Roma 52Caserta Via Roma 52Caserta Via Roma 52Caserta Via Roma 52Caserta Via Roma 52Caserta Via Roma 52Caserta Via Roma 52Caserta Via Roma 52Caserta Via Roma 52Caserta Via Roma 52Caserta Via Roma 52Caserta Via Roma 52Caserta Via Roma 52Caserta Via Roma 52");

    RisultatoDto oracolo = new RisultatoDto(
        "La lunghezza del campo Indirizzo Struttura non è valida");
    testSave(input, status().isBadRequest(), oracolo);
  }

  /**
   * Test ID TC_2.17.
   */
  @Test
  public void testSave17() throws Exception {
    DottoreDto input = new DottoreDto();
    input.setNome("Matteo");
    input.setCognome("Aldi");
    input.setCodiceFiscale("LDAMTT01H09B963Y");
    input.setSesso("M");
    input.setDataNascita("09/06/2001");
    input.setEmail("matteo.aldi@hotmail.it");
    input.setTelefono("3938776542");
    input.setIndirizzo("Caserta Via Vico 1");
    input.setPassword("blabla*blabla-");
    input.setSpecializzazione("Diabetologo");
    input.setCodiceAlbo("5545 San nicola la strada");
    input.setNomeStruttura("Studio Medico  Nuova Salute");
    input.setIndirizzoStruttura("Caserta Via Roma 52");

    RisultatoDto oracolo = new RisultatoDto("Dottore registrato correttamente");
    testSave(input, status().isOk(), oracolo);
  }

  @WithMockUser(username = "dottore", authorities = {"DOTTORE"})
  // Mock User dottore with tipo Dottore
  private void testSave(RisultatoDto input, ResultMatcher status, RisultatoDto oracolo)
      throws Exception {

    performSync(post("/dottore/save").contentType(MediaType.APPLICATION_JSON)
        .content(toJsonString(input))).andExpect(status)
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(toJsonString(oracolo)));
  }

  @BeforeEach
  public void resetServiceStub() {
    serviceStub.duplicatedId = false;
    serviceStub.duplicatedEmail = false;
  }

}


