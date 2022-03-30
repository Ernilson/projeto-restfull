package br.com.progesteron.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.progesteron.model.PatrimonioModel;

public interface InventarioRepository extends JpaRepository<PatrimonioModel, Long>{

	//List<PatrimonioModel> findByNomeContainingIgnoreCase(String nome);
}
