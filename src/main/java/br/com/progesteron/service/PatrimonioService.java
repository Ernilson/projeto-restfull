package br.com.progesteron.service;

import java.util.List;

import br.com.progesteron.data.model.PatrimonioModel;

public interface PatrimonioService {

	List<PatrimonioModel> listAll();

	List<PatrimonioModel> findByNomeContainingIgnoreCase(String nome);
	
	PatrimonioModel saveOrUpdate(PatrimonioModel cm) throws Exception;

	boolean alterar(PatrimonioModel dto);

	PatrimonioModel getId(Long id);	

	void delete(Long id);
}
