package consumindo.viacep.controller;

import consumindo.viacep.service.CepService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping
public class Cep {

    private final CepService cepService;
    public static final String URL = "/cep/";

    @GetMapping(URL + "{cep}")
    public ResponseEntity getCep(@PathVariable Integer cep){
        return ResponseEntity.ok().body(cepService.getCep(cep));
    }
}
