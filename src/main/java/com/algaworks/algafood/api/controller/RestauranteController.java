package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.execptin.EntidadeEmUsoException;
import com.algaworks.algafood.domain.execptin.EntidadeNaoEncontradaExecption;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/restaurante")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;


    @GetMapping
    public List<Restaurante> todos(){return restauranteRepository.findAll();}

    @GetMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> buscar(@PathVariable ("restauranteId") Long id){
        Optional<Restaurante> restaurante = restauranteRepository.findById(id);
    if (restaurante.isPresent()){
            return ResponseEntity.ok(restaurante.get());
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
            System.out.println();
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{restaunteId}")
    public ResponseEntity<?> atulizar(@PathVariable Long restaunteId,
                                                 @RequestBody  Restaurante restaurante){
        try {
           Optional <Restaurante> restauranteAtual = restauranteRepository.findById(restaunteId);

        if (restauranteAtual.isPresent()){
            BeanUtils.copyProperties(restaurante, restauranteAtual.get(), "id");

         Restaurante restauranteUtil = cadastroRestaurante.salvar(restauranteAtual.get());
           return ResponseEntity.ok(restauranteUtil);
        }else{
        return ResponseEntity.notFound().build();
        }

        }catch (EntidadeNaoEncontradaExecption e){
                return ResponseEntity.badRequest()
                        .body(e.getMessage());
        }
    }
    @PatchMapping("/{restauranteId}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId,
                                              @RequestBody Map<String,Object> campos){
        Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);

        if (restauranteAtual.isPresent()){
            return ResponseEntity.notFound().build();
        }

        merge(campos,restauranteAtual.get());
        return atulizar(restauranteId, restauranteAtual.get());

    }

    private static void merge(Map<String, Object> dadosOrigem, Restaurante restaurantedestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante  restauranteOrigem = objectMapper.convertValue(dadosOrigem,Restaurante.class);
        dadosOrigem.forEach((nomePropriedade, valorPropriedade) ->{

            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            field.setAccessible(true);
            Object novoValor= ReflectionUtils.getField(field,restauranteOrigem);
            System.out.println(nomePropriedade + "=" + valorPropriedade);

            ReflectionUtils.setField(field, restaurantedestino, novoValor);
        });
    }

}
