package consumindo.viacep.service;

import consumindo.viacep.dto.CepDTO;
import consumindo.viacep.integration.ViaCepIntegration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.server.ResponseStatusException;

@RunWith(MockitoJUnitRunner.class)
public class CepServiceTest {

    @InjectMocks
    private CepService cepService;

    @Mock
    private ViaCepIntegration viaCepIntegration;

    @Test(expected = ResponseStatusException.class)
    public void whenCepMinusThenEight(){
        cepService.getCep(1234567);
    }

    @Test(expected = ResponseStatusException.class)
    public void whenCepNotFound(){
        Integer cep = 99999999;
        CepDTO cepDTO = CepDTO.builder()
                .build();

        Mockito.when(viaCepIntegration.buscaPeloCep(cep)).thenReturn(cepDTO);
        cepService.getCep(cep);
    }

    @Test
    public void whenCepFound(){
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
        cepService.getCep(cep);
    }
}
