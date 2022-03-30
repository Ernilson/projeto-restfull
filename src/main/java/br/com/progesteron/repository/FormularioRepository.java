package br.com.progesteron.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.progesteron.data.model.FormularioModel;

@Repository
public interface FormularioRepository extends JpaRepository<FormularioModel, Long>{

	List<FormularioModel> findByNomeContainingIgnoreCase(String nome);
}
