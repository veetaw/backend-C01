package com.glucoseguardian.webbackend.unittests.restcontrollers;

import static org.hamcrest.Matchers.anything;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.glucoseguardian.webbackend.configuration.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@WebMvcTest
public abstract class AbstractRestTest {

  private final ObjectMapper mapper = new ObjectMapper();
  @MockBean
  private JwtAuthenticationFilter authenticationFilter;
  @Autowired
  private MockMvc mvc;

  /**
   * Esegue una chiamata MVC attendendo per la risposta nel caso sia asincrona o restituendola se è
   * sincrona.
   */
  protected ResultActions performSync(MockHttpServletRequestBuilder requestBuilder)
      throws Exception {
    ResultActions resultActions = mvc.perform(requestBuilder);
    if (!resultActions.andReturn().getRequest().isAsyncStarted()) {
      return resultActions;
    }
    return
        mvc.perform(asyncDispatch(resultActions.andExpect(request().asyncResult(anything())).andReturn()));
  }

  /**
   * Serializza un oggetto nel formato json.
   */
  protected String toJsonString(Object obj) {
    try {
      return mapper.writeValueAsString(obj);
    } catch (JsonProcessingException ex) {
      ex.printStackTrace();
      return null;
    }
  }

}
