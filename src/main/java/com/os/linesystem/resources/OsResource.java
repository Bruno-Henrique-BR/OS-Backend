package com.os.linesystem.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.os.linesystem.dtos.OsDTO;
import com.os.linesystem.model.Os;
import com.os.linesystem.services.OsService;

@RestController
@RequestMapping(value = "/os")
public class OsResource {

	@Autowired
	private OsService service;
	
	/**
	 * Busca um Chamado por ID
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<OsDTO> findById(@PathVariable Integer id) {
		Os obj = service.findById(id);
		return ResponseEntity.ok().body(new OsDTO(obj));
	}

	/**
	 * Lista todos os dos do banco
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<List<OsDTO>> findAll() {
		List<Os> list = service.findAll();
		List<OsDTO> listDTO = list.stream().map(obj -> new OsDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	/**
	 * Cria um novo do
	 * @param objDTO
	 * @return
	 */
	@PostMapping
	public ResponseEntity<OsDTO> create(@Valid @RequestBody OsDTO objDTO) {
		Os newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).body(new OsDTO(newObj));
	}
	
	/**
	 * Atualiza do
	 * @param id
	 * @param objDTO
	 * @return
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<OsDTO> update(@PathVariable Integer id, @Valid @RequestBody OsDTO objDTO) {
		Os obj = service.update(id, objDTO);
		return ResponseEntity.ok().body(new OsDTO(obj));
	}
	
}
