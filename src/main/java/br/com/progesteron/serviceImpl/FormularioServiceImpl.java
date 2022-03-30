package br.com.progesteron.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.progesteron.data.model.FormularioModel;
import br.com.progesteron.repository.FormularioRepository;
import br.com.progesteron.service.FormularioService;
 

@Service
public class FormularioServiceImpl implements FormularioService{
	
	@Autowired
	private FormularioRepository repository;

	@Override
	public List<FormularioModel> listAll() {
		List<FormularioModel> cm = new ArrayList<>();
		repository.findAll().forEach(cm::add); // fun with Java 8
		return cm;
	}

	@Override
	public List<FormularioModel> findByNomeContainingIgnoreCase(String nome) {
		// TODO Auto-generated method stub
		return repository.findByNomeContainingIgnoreCase(nome);
	}

	@Override
	public boolean alterar(FormularioModel dto) {
		try {
			repository.save(dto);
	            return true;
	        } catch (Exception e) {
	            return false;
	        }
	}

	@Override
	public FormularioModel getId(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).get();
	}

	@Override
	public FormularioModel saveOrUpdate(FormularioModel cm) throws Exception {
		try {
			repository.save(cm);
			return cm;
		} catch (Exception e) {
			throw new Exception("Erro ao tentar inserir dados");
		}
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
		
	}

	
}
