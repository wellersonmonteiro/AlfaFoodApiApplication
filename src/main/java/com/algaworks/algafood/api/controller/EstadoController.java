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
import java.util.Optional;

@RestController
@RequestMapping("/estado")

public class EstadoController {


   @Autowired
   private EstadoRepository estadoRepository;

  @Autowired
  private CadastroEstadoService cadastroEstadoService;

  @GetMapping
    private List<Estado> todos(){
      return estadoRepository.findAll();
  }
  @GetMapping("/{estadoId}")
    private ResponseEntity<Estado> buscar(@PathVariable ("estadoId") long id){
      Optional<Estado> estado = estadoRepository.findById(id);

      if (estado.isPresent()){
          return ResponseEntity.ok(estado.get());
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
      Optional<Estado> estadoAtual = estadoRepository.findById(estadoId);
      try {

          BeanUtils.copyProperties(estado, estadoAtual.get(),"id");
          Estado estadoNovo = cadastroEstadoService.salvar(estadoAtual.get());

          return ResponseEntity.ok(estadoNovo);


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



