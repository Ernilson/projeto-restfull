package br.com.progesteron.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.progesteron.model.PatrimonioModel;
import br.com.progesteron.repository.InventarioRepository;
import br.com.progesteron.service.PatrimonioService;

@Service
public class PatrimonioServiceImpl implements PatrimonioService{

	@Autowired
	private InventarioRepository repository;
	
	@Override
	public List<PatrimonioModel> listAll() {
		List<PatrimonioModel> cm = new ArrayList<>();
		repository.findAll().forEach(cm::add); // fun with Java 8
		return cm;
	}

	@Override
	public List<PatrimonioModel> findByNomeContainingIgnoreCase(String nome) {
		// TODO Auto-generated method stub  repository.findByNomeContainingIgnoreCase(nome)
		return null;
	}

	@Override
	public PatrimonioModel saveOrUpdate(PatrimonioModel cm) throws Exception {
		try {
			repository.save(cm);
			return cm;
		} catch (Exception e) {
			throw new Exception("Erro ao tentar inserir dados");
		}
	}

	@Override
	public boolean alterar(PatrimonioModel dto) {
		try {
			repository.save(dto);
	            return true;
	        } catch (Exception e) {
	            return false;
	        }
	}

	@Override
	public PatrimonioModel getId(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).get();
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
		
	}

}
