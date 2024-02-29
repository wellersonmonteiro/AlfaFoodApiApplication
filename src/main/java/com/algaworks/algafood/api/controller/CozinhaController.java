package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.model.CozinhasXmlWrapper;
import com.algaworks.algafood.domain.execptin.EntidadeEmUsoException;
import com.algaworks.algafood.domain.execptin.EntidadeNaoEncontradaExecption;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinha;
    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping
    public List<Cozinha> todas() {
       return cozinhaRepository.todas();
    }

    @GetMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> buscar(@PathVariable("cozinhaId") long id){
        Cozinha cozinha = cozinhaRepository.buscar(id);

        if(cozinha != null){
        return ResponseEntity.ok(cozinha);
        }
        else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhasXmlWrapper listarXml(){

        return new CozinhasXmlWrapper(cozinhaRepository.todas());
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adicionar(@RequestBody Cozinha cozinha){
      return   cadastroCozinha.salvar(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable long cozinhaId,
        @RequestBody Cozinha cozinha){
        Cozinha cozinhaAtual =cozinhaRepository.buscar(cozinhaId);
        ///cozinhaAtual.setName(cozinha.getName());

        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");

        cozinhaRepository.salvar(cozinhaAtual);

        return ResponseEntity.ok(cozinhaAtual);
    }
    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> remover(@PathVariable Long cozinhaId){
        try {

          cadastroCozinha.excluir(cozinhaId);
            return ResponseEntity.noContent().build();
        }catch (EntidadeNaoEncontradaExecption e){
            return ResponseEntity.notFound().build();
        }
        catch (EntidadeEmUsoException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }
}
