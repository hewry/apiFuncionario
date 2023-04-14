package com.sisgesfun;

import java.util.List;
import lombok.AllArgsConstructor;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;
@RestController
@AllArgsConstructor
public class FuncionarioController {
    FuncionarioRepository repository;
    @GetMapping("/funcionario")
    public List<Funcionario> getAllFuncionarios() {
        return repository.findAll();
    }
    @GetMapping("/funcionario/{id}")
    public Funcionario getFuncionarioById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Funcionario não encontrado :" + id));
    }
    @PostMapping("/funcionario")
    public Funcionario createFuncionario(@RequestBody Funcionario funcionario) {
        return repository.save(funcionario);
    }

    @PutMapping("/funcionario/{id}")
    public Funcionario updateFuncionario(@PathVariable Long id, @RequestBody Funcionario funcionario){
        Funcionario funcionarioExistente = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Funcionario não encontrado com id: " + id));

        funcionarioExistente.setNome(funcionario.getNome());
        funcionarioExistente.setDesignacao(funcionario.getDesignacao());
        funcionarioExistente.setSalario(funcionario.getSalario());
        funcionarioExistente.setTelefone(funcionario.getTelefone());
        funcionarioExistente.setEndereco(funcionario.getEndereco());

        return repository.save(funcionarioExistente);
    }
    @DeleteMapping("/funcionario/{id}")
    public void deleteFuncionario(@PathVariable Long id) {
        repository.deleteById(id) ;
    }
}
