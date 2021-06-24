package com.os.linesystem.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.os.linesystem.dtos.OsDTO;
import com.os.linesystem.enums.Prioridade;
import com.os.linesystem.enums.Status;
import com.os.linesystem.model.Cliente;
import com.os.linesystem.model.Os;
import com.os.linesystem.model.Tecnico;
import com.os.linesystem.repositories.OsRepository;
import com.os.linesystem.services.exceptions.ObjectNotFoundException;

@Service
public class OsService {

	@Autowired
	private OsRepository repository;
	@Autowired
	private TecnicoService tecnicoService;
	@Autowired
	private ClienteService clienteService;

	/**
	 * Busca um Chamado por ID
	 * 
	 * @param id
	 * @return
	 */
	public Os findById(Integer id) {
		Optional<Os> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo " + Os.class.getSimpleName()));
	}

	/**
	 * Lista todos os Chamados do banco
	 * 
	 * @return
	 */
	public List<Os> findAll() {
		return repository.findAll();
	}

	/**
	 * Cria um novo chamado
	 * 
	 * @param objDTO
	 * @return
	 */
	public Os create(OsDTO obj) {
		return repository.save(newOs(obj));
	}
	
	/**
	 * Atualiza Chamado
	 * @param id
	 * @param objDTO
	 * @return
	 */
	public Os update(Integer id, OsDTO objDTO) {
		objDTO.setId(id);
		Os oldObj = findById(id);
		oldObj = newOs(objDTO);
		return repository.save(oldObj);
	}

	/*
	 * Metodo para criar novo chamado
	 */
	private Os newOs(OsDTO obj) {
		Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
		Cliente cliente = clienteService.findById(obj.getCliente());

		Os newObj = new Os();
		
		if(obj.getId() != null) {
			newObj.setId(obj.getId());
		}
		
		newObj.setDataAbertura(LocalDate.now());

		if (obj.getStatus().equals(2)) {
			newObj.setDataFechamento(LocalDate.now());
		}

		newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		newObj.setStatus(Status.toEnum(obj.getStatus()));
		newObj.setCliente(cliente);
		newObj.setTecnico(tecnico);
		newObj.setTitulo(obj.getTitulo());
		newObj.setDescricao(obj.getDescricao());
		return newObj;
	}

}
