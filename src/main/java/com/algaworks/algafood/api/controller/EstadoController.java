package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.execptin.EntidadeEmUsoException;
import com.algaworks.algafood.domain.execptin.EntidadeNaoEncontradaExecption;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroEstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estado")

public class EstadoController {


   @Autowired
   private EstadoRepository estadoRepository;

  @Autowired
  private CadastroEstadoService cadastroEstadoService;

  @GetMapping
    private List<Estado> todos(){
      return estadoRepository.todos();
  }
  @GetMapping("/{estadoId}")
    private ResponseEntity<Estado> buscar(@PathVariable ("estadoId") long id){
      Estado estado = estadoRepository.buscar(id);

      if (estado != null){
          return ResponseEntity.ok(estado);
      }
      else {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      }

  }

  @PostMapping
    private ResponseEntity<?> adicionar(@RequestBody Estado estado ){

      try{
             estado = cadastroEstadoService.salvar(estado);

             return ResponseEntity.status(HttpStatus.CREATED).body(estado);
       } catch (Exception e){
          return ResponseEntity.badRequest().build();
      }


  }
  @PutMapping("/{estadoId}")
    private ResponseEntity<?> atualizar(@PathVariable Long estadoId,
                                @RequestBody Estado estado){
      try {
          Estado estadoAtual = estadoRepository.buscar(estadoId);

          BeanUtils.copyProperties(estado, estadoAtual,"id");
          estadoAtual = estadoRepository.salvar(estadoAtual);
          return ResponseEntity.ok(estadoAtual);


      }catch (Exception e){
          return ResponseEntity.notFound().build();
      }
  }
  @DeleteMapping("/{estadoId}")
  public ResponseEntity<Estado> excluir(@PathVariable Long estadoId) {
      try {
          cadastroEstadoService.excluir(estadoId);
          return ResponseEntity.noContent().build();
      }catch (EntidadeNaoEncontradaExecption e){
          return ResponseEntity.notFound().build();
      } catch (EntidadeEmUsoException e){
          return ResponseEntity.status(HttpStatus.CONFLICT).build();
      }

  }
}



