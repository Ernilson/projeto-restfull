package br.com.progesteron.config;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.progesteron.controller.FormularioController;
import br.com.progesteron.controller.PatrimonioController;
import br.com.progesteron.model.RootEntryPointModel;

@RestController
public class RootEntryPointController {

@GetMapping
 public RootEntryPointModel root() {
		
		var model = new RootEntryPointModel();
		
		model.add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(PatrimonioController.class)
				.listTodos()).withRel("patrimonio"));	
		
		model.add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(FormularioController.class)
				.listTodos()).withRel("Formulario"));
		
		return model;
		
	}
}
