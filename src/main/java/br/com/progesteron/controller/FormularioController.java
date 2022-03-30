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

import br.com.progesteron.model.FormularioModel;
import br.com.progesteron.serviceImpl.FormularioServiceImpl;

@RestController
@RequestMapping(value = "/formulario")
public class FormularioController {

	@Autowired
	private FormularioServiceImpl service;

	@GetMapping("/listarCadastro")
	public ResponseEntity<List<FormularioModel>> listTodos() {
		List<FormularioModel> fmlist = service.listAll();
		if (fmlist.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			for (FormularioModel formularioModel : fmlist) {
				long id = formularioModel.getId_c();
				formularioModel.add(linkTo(methodOn(FormularioController.class). buscaPorId(id)).withSelfRel());
			}
		}
		return new ResponseEntity<List<FormularioModel>>(fmlist, HttpStatus.OK);
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<FormularioModel> buscaPorId(@PathVariable(value = "id") long id) {
		Optional<FormularioModel> fmodel = Optional.ofNullable(service.getId(id));
		if (!fmodel.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			fmodel.get().add(linkTo(methodOn(FormularioController.class).listTodos()).withSelfRel());

			return new ResponseEntity<FormularioModel>(fmodel.get(), HttpStatus.OK);

		}
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Boolean> updateFormulario(@PathVariable(name = "id") Long id, @RequestBody FormularioModel fm,
			BindingResult result) {
		if (result.hasErrors()) {
			List<String> erros = new ArrayList<String>();
			result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
			return null;
		}
		fm.setId_c(id);
		boolean cad = service.alterar(fm);
		fm.add(linkTo(methodOn(FormularioController.class). buscaPorId(id)).withSelfRel());
		return ResponseEntity.ok(cad);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/salvar")
	public ResponseEntity<FormularioModel> salvaFormulario(@RequestBody FormularioModel c) throws Exception {
		service.saveOrUpdate(c);
		c.add(linkTo(methodOn(FormularioController.class).listTodos()).withSelfRel());
		return new ResponseEntity<FormularioModel>(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> removeFormulario(@PathVariable("id_c") Long id_c) {
		FormularioModel fm = service.getId(id_c);
		if (fm != null) {
			service.delete(id_c);
			return ResponseEntity.ok().build();
		}
		return null;
	}
}