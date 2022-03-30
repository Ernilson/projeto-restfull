package br.com.progesteron.service;

import java.util.List;

import br.com.progesteron.data.model.FormularioModel;

public interface FormularioService {
	
	List<FormularioModel> listAll();

	List<FormularioModel> findByNomeContainingIgnoreCase(String nome);
	
	FormularioModel saveOrUpdate(FormularioModel cm) throws Exception;

	boolean alterar(FormularioModel dto);

	FormularioModel getId(Long id);	

	void delete(Long id);

}
