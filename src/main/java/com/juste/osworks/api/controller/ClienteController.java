package com.juste.osworks.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.juste.osworks.domain.model.Cliente;
import com.juste.osworks.domain.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping
	public List<Cliente> listar() {
		return clienteRepository.findAll();	
//		return clienteRepository.findByNome("Glenio"); // usando o método de procura no repository por nome
	}
	
	@GetMapping("{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);
		
		if(cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	@PutMapping("/{clienteId}") // tem que ver o id do cliente e o cliente
	public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteId, @RequestBody Cliente cliente) {
		
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(clienteId);
		cliente = clienteRepository.save(cliente);
		
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId) {
		
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		clienteRepository.deleteById(clienteId);
	
		return ResponseEntity.noContent().build();
	}

}