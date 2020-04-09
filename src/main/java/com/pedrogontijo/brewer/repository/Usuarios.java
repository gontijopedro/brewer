package com.pedrogontijo.brewer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pedrogontijo.brewer.model.Usuario;
import com.pedrogontijo.brewer.repository.helper.usuario.UsuariosQueries;


public interface Usuarios extends JpaRepository<Usuario, Long>, UsuariosQueries {

	public Optional<Usuario> findByEmail(String email);

	public List<Usuario> findByCodigoIn(Long[] codigos);

}