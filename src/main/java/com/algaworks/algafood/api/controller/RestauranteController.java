package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.execptin.EntidadeEmUsoException;
import com.algaworks.algafood.domain.execptin.EntidadeNaoEncontradaExecption;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurante")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;


    @GetMapping
    public List<Restaurante> todos(){return restauranteRepository.todos();}

    @GetMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> buscar(@PathVariable ("restauranteId") Long id){
        Restaurante restaurante = restauranteRepository.buscar(id);
        if (restaurante != null){
            return ResponseEntity.ok(restaurante);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante){
        try{
            restaurante = cadastroRestaurante.salvar(restaurante);

            return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
        }catch (EntidadeNaoEncontradaExecption e){
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{restaunteId}")
    public ResponseEntity<?> atulizar(@PathVariable Long restaunteId,
                                                 @RequestBody  Restaurante restaurante){
        try {
            Restaurante restauranteAtual = restauranteRepository.buscar(restaunteId);

        if (restauranteAtual != null){
            BeanUtils.copyProperties(restaurante, restauranteAtual, "id");

          restauranteAtual = restauranteRepository.salvar(restauranteAtual);
           return ResponseEntity.ok(restauranteAtual);
        }else{
        return ResponseEntity.notFound().build();
        }

        }catch (EntidadeNaoEncontradaExecption e){
                return ResponseEntity.badRequest()
                        .body(e.getMessage());
        }
    }


}
