package br.com.progesteron.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.progesteron.model.PatrimonioModel;

public interface InventarioRepository extends JpaRepository<PatrimonioModel, Long>{

}
