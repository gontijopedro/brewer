package com.pedrogontijo.brewer.service;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pedrogontijo.brewer.model.Estilo;
import com.pedrogontijo.brewer.repository.Estilos;
import com.pedrogontijo.brewer.service.exception.ImpossivelExcluirEntidadeException;
import com.pedrogontijo.brewer.service.exception.NomeEstiloJaCadastradoException;

@Service
public class CadastroEstiloService {

	@Autowired
	private Estilos estilos;
	
	@Transactional
	public Estilo salvar(Estilo estilo) {
		if(estilo.getCodigo() == null || nomeDoEstiloMudou(estilo)) {
			verificaSeNomeJaExiste(estilo.getNome());
		}
		
		return estilos.saveAndFlush(estilo);
	}
	
	@Transactional
	public void excluir(Estilo estilo) {
		try {
			estilos.delete(estilo);
			estilos.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar estilo. Já foi usado em alguma venda.");
		}
	}
	
	private void verificaSeNomeJaExiste(String nome) {
		if (estilos.findByNomeIgnoreCase(nome).isPresent()) {
			throw new NomeEstiloJaCadastradoException("Nome do estilo já cadastrado");
		}
	}
	
	private boolean nomeDoEstiloMudou(Estilo estilo) {
		final String nomeNovo = estilo.getNome();
		final String nomeAntigo = estilos.findByCodigo(estilo.getCodigo()).getNome();
		return !nomeAntigo.equalsIgnoreCase(nomeNovo);
	}
	
}