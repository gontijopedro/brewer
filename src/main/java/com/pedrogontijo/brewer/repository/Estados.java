package com.pedrogontijo.brewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedrogontijo.brewer.model.Estado;

@Repository
public interface Estados  extends JpaRepository<Estado, Long> {

	
}
