package consumindo.viacep.integration;


import consumindo.viacep.dto.CepDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "${url.via-cep}", name = "${url.name}")
public interface ViaCepIntegration {

    @GetMapping("{cep}/json/")
    CepDTO buscaPeloCep(@PathVariable("cep") Integer cep);

}

