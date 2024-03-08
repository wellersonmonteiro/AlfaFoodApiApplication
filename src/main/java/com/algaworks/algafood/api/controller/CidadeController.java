package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.execptin.EntidadeEmUsoException;
import com.algaworks.algafood.domain.execptin.EntidadeNaoEncontradaExecption;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cidade")
public class CidadeController {
    @Autowired
   private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cidadeService;

    @GetMapping
    public List<Cidade> listar(){
        return cidadeRepository.findAll();
    }

    @GetMapping("/{cidadeId}")
    public ResponseEntity<Cidade> buscar(@PathVariable("cidadeId") Long id){
       Optional<Cidade> cidade = cidadeRepository.findById(id);
        if (cidade.isPresent()){
            return ResponseEntity.ok(cidade.get());
        }
        else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Cidade cidade){
        try {
            cidade = cidadeService.salvar(cidade);

            return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
        }catch (EntidadeNaoEncontradaExecption e){
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }
    @PutMapping("/{cidadeId}")
    public ResponseEntity<?> Atualizar(@PathVariable Long cidadeId,
                                       @RequestBody Cidade cidade){
        try {
            Optional<Cidade> cidadeaAtual = cidadeRepository.findById(cidadeId);
            if (cidadeaAtual.isPresent()){
                BeanUtils.copyProperties(cidade,cidadeaAtual.get(),"id");

               Cidade cidadea1 = cidadeService.salvar(cidadeaAtual.get());
                return ResponseEntity.ok(cidadea1);
            }else {
                return ResponseEntity.notFound().build();}
        } catch (EntidadeNaoEncontradaExecption e){
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }
    @DeleteMapping("/{cidadeId}")
    public ResponseEntity<Cidade> remover(@PathVariable Long cidadeId){
        try {
            cidadeService.excluir(cidadeId);
            return ResponseEntity.noContent().build();
        }catch (EntidadeNaoEncontradaExecption e ){
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        }
    }


