package consumindo.viacep.controller;

import consumindo.viacep.dto.CepDTO;
import consumindo.viacep.integration.ViaCepIntegration;
import consumindo.viacep.service.CepService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource(properties = "url.via-cep=http://localhost:${wiremock.server.port}")
@ContextConfiguration(classes = {CepService.class, Cep.class})
@RunWith(SpringRunner.class)
@ImportAutoConfiguration({HttpMessageConvertersAutoConfiguration.class})
@AutoConfigureWireMock(port = 0)
public class CepIT {
    private MockMvc mockMvc;

    @MockBean(name = "consumindo.viacep.integration.ViaCepIntegration")
    private ViaCepIntegration viaCepIntegration;

    @Autowired
    private Cep cep;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(cep).setControllerAdvice().build();
    }

    @Test
    public void shouldFindCep() throws Exception {
        Integer cep = 17012170;
        CepDTO cepDTO = CepDTO.builder()
                .cep("17012170")
                .bairro("Vila Altinópolis")
                .ddd("14")
                .complemento("de Quadra 4 ao fim")
                .gia("2094")
                .ibge("3506003")
                .localidade("Bauru")
                .logradouro("Rua São Gonçalo")
                .uf("SP")
                .siafi("6219")
                .build();

        Mockito.when(viaCepIntegration.buscaPeloCep(cep)).thenReturn(cepDTO);
        mockMvc.perform(
                get(Cep.URL + cep).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void notFindCep() throws Exception {
        Integer cep = 99999999;
        CepDTO cepDTO = CepDTO.builder()
                .build();

        Mockito.when(viaCepIntegration.buscaPeloCep(cep)).thenReturn(cepDTO);
        mockMvc.perform(
                get(Cep.URL + cep).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldBadReturnRequest() throws Exception {
        int cep = 17012;

        mockMvc.perform(
                get(Cep.URL + cep).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
