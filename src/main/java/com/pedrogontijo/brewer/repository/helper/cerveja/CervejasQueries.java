package com.pedrogontijo.brewer.repository.helper.cerveja;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pedrogontijo.brewer.dto.CervejaDTO;
import com.pedrogontijo.brewer.dto.ValorItensEstoque;
import com.pedrogontijo.brewer.model.Cerveja;
import com.pedrogontijo.brewer.repository.filter.CervejaFilter;

public interface CervejasQueries {

	public Page<Cerveja> filtrar(CervejaFilter filtro, Pageable pageable);
	
	public List<CervejaDTO> porSkuOuNome(String skuOuNome);
	
	public ValorItensEstoque valorItensEstoque();
	
}