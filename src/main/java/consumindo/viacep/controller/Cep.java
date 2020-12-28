package consumindo.viacep.controller;

import consumindo.viacep.dto.CepDTO;
import consumindo.viacep.service.CepService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping
public class Cep {

    private final CepService cepService;

    @GetMapping("/cep/{cep}")
    public ResponseEntity getCep(@PathVariable Integer cep){
        ResponseEntity responseEntity;
        int cepSize = cep.toString().length();

        if(cepSize != 8 ){
            responseEntity = ResponseEntity.badRequest().body("O cep deve conter 8 dígitos.");
        } else {
            CepDTO cepDTO = cepService.getCep(cep);
            if(Objects.isNull(cepDTO.getCep())){
                responseEntity = ResponseEntity.notFound().build();
            } else {
                responseEntity = ResponseEntity.ok().body(cepDTO);
            }
        }
        return responseEntity;
    }
}
