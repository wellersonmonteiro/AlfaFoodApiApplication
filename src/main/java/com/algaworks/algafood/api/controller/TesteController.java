package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teste")
public class TesteController {
    @Autowired
    private CozinhaRepository cozinhaRepository;

//    @GetMapping("/cozinha/por-nome")
//    public List<Cozinha> cozinhaPorNome(@RequestParam ("name") String name){
//         return cozinhaRepository.consultarPorNome(name);
//    }
}
