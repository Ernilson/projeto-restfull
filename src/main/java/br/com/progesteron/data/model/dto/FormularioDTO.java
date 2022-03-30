package br.com.progesteron.data.model.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import br.com.progesteron.data.model.FormularioModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
public class FormularioDTO {


	private Long id_c;
	@NotBlank
	private String nome;
	private String fone;
	private String email;
	private String data;
	private String status;
	private String cep;
	private String logradouro;
	private String bairro;
	private String localidade;
	private String uf;

	
	public FormularioDTO(FormularioModel model) {
		id_c = model.getId_c();
		nome = model.getNome();
		fone = model.getFone();
		email = model.getEmail();
		data = model.getData();
		status = model.getStatus();
		cep = model.getCep();
		logradouro = model.getLogradouro();
		bairro = model.getBairro();
		localidade = model.getLocalidade();
		uf = model.getUf();
	}
	
	public static List<FormularioDTO> convert(List<FormularioModel> model){
		return model.stream().map(FormularioDTO::new).collect(Collectors.toList());
	}
	
}
