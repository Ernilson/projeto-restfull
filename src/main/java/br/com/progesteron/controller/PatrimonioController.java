package br.com.progesteron.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.progesteron.data.model.PatrimonioModel;
import br.com.progesteron.serviceImpl.PatrimonioServiceImpl;


@RestController
@RequestMapping(value="/patrimonio")
public class PatrimonioController {
	
	@Autowired
	private PatrimonioServiceImpl psi;
	
	@GetMapping("/listaPatrimonio")
	public ResponseEntity<List<PatrimonioModel>> listTodos() {
		List<PatrimonioModel> fmlist = psi.listAll();
		if (fmlist.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			for (PatrimonioModel patrimonioModel : fmlist) {
				long id = patrimonioModel.getId_p();
				patrimonioModel.add(linkTo(methodOn(PatrimonioController.class). buscaPorId(id)).withSelfRel());
			}
		}
		return new ResponseEntity<List<PatrimonioModel>>(fmlist, HttpStatus.OK);
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<PatrimonioModel> buscaPorId(@PathVariable(value = "id") long id) {
		Optional<PatrimonioModel> fmodel = Optional.ofNullable(psi.getId(id));
		if (!fmodel.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			fmodel.get().add(linkTo(methodOn(PatrimonioController.class).listTodos()).withSelfRel());

			return new ResponseEntity<PatrimonioModel>(fmodel.get(), HttpStatus.OK);

		}
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Boolean> updatePatrimonio(@PathVariable(name = "id") Long id, @RequestBody PatrimonioModel fm,
			BindingResult result) {
		if (result.hasErrors()) {
			List<String> erros = new ArrayList<String>();
			result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
			return null;
		}
		fm.setId_p(id);
		boolean cad = psi.alterar(fm);
		fm.add(linkTo(methodOn(PatrimonioController.class). buscaPorId(id)).withSelfRel());
		return ResponseEntity.ok(cad);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/salvar")
	public ResponseEntity<PatrimonioModel> salvaFormulario(@RequestBody PatrimonioModel c) throws Exception {
		psi.saveOrUpdate(c);
		c.add(linkTo(methodOn(FormularioController.class).listTodos()).withSelfRel());
		return new ResponseEntity<PatrimonioModel>(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> removePatrimonio(@PathVariable("id_c") Long id_c) {
		PatrimonioModel fm = psi.getId(id_c);
		if (fm != null) {
			psi.delete(id_c);
			return ResponseEntity.ok().build();
		}
		return null;
	}
	
	

}
