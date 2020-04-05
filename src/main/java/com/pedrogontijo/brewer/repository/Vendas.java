package com.pedrogontijo.brewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pedrogontijo.brewer.model.Venda;
import com.pedrogontijo.brewer.repository.helper.venda.VendasQueries;

public interface Vendas extends JpaRepository<Venda, Long>, VendasQueries {

}
		