package com.pedrogontijo.brewer.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pedrogontijo.brewer.model.Cidade;
import com.pedrogontijo.brewer.model.Cliente;
import com.pedrogontijo.brewer.repository.Cidades;
import com.pedrogontijo.brewer.repository.Clientes;
import com.pedrogontijo.brewer.service.exception.CpfCnpjClienteJaCadastradoException;
import com.pedrogontijo.brewer.service.exception.ImpossivelExcluirEntidadeException;

@Service
public class CadastroClienteService {

	@Autowired
	private Clientes clientes;
	
	@Autowired
	private Cidades cidades;
	
	@Transactional
	public void salvar(Cliente cliente) {
	   Optional<Cliente> clienteExistente = clientes.findByCpfOuCnpj(cliente.getCpfOuCnpjSemFormatacao());
	   
	   if (clienteExistente.isPresent() && !clienteExistente.get().equals(cliente)) {
	      throw new CpfCnpjClienteJaCadastradoException("CPF/CNPJ já cadastrado");
	   }

	   clientes.save(cliente);
	}

	@Transactional(readOnly = true)
	public void comporDadosEndereco(Cliente cliente) {
		if (cliente.getEndereco() == null 
				|| cliente.getEndereco().getCidade() == null
				|| cliente.getEndereco().getCidade().getCodigo() == null) {
			return;
		}

		Cidade cidade = this.cidades.findByCodigoFetchingEstado(cliente.getEndereco().getCidade().getCodigo());

		cliente.getEndereco().setCidade(cidade);
		cliente.getEndereco().setEstado(cidade.getEstado());
	}
	
	@Transactional
	public void excluir(Cliente cliente) {
		try {
			this.clientes.delete(cliente);
			this.clientes.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar cliente. Já está atrelado a alguma venda.");
		}
	}

}