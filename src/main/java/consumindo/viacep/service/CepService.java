package consumindo.viacep.service;

import consumindo.viacep.dto.CepDTO;
import consumindo.viacep.integration.ViaCepIntegration;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@AllArgsConstructor
@Service
public class CepService {

    private final ViaCepIntegration viaCepIntegration;

    public CepDTO getCep (Integer cep) {
        int cepSize = cep.toString().length();
        CepDTO cepDTO = null;

        if(cepSize != 8 ){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CEP deve conter 8 dígitos.");
        } else {
            cepDTO = viaCepIntegration.buscaPeloCep(cep);
            if(Objects.isNull(cepDTO.getCep())){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CEP não encontrado.");
            }
        }
        return cepDTO;
    }
}
