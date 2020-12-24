package consumindo.viacep.service.impl;

import consumindo.viacep.dto.CepDTO;
import consumindo.viacep.integration.ViaCepIntegration;
import consumindo.viacep.service.CepService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CepServiceImpl implements CepService {

    @NonNull
    private final ViaCepIntegration viaCepIntegration;

    @Override
    public CepDTO getCep(Integer cep) {
        return viaCepIntegration.buscaPeloCep(cep);
    }
}
