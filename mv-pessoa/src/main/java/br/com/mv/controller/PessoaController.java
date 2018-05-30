package br.com.mv.controller;

import static java.util.Objects.isNull;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Throwables;

import br.com.mv.model.Pessoa;
import br.com.mv.service.PessoaService;

@RestController
@CrossOrigin
@RequestMapping(value = "api/pessoa")
public class PessoaController {

	@Inject
	private PessoaService pessoaService;

	@ResponseBody
	@RequestMapping(value = "/{id}", method = GET, produces = { APPLICATION_JSON_VALUE })
	public ResponseEntity<Pessoa> findById(@PathVariable Long id) {
		Pessoa pessoa = pessoaService.findById(id);
		if (isNull(pessoa)) {
			return new ResponseEntity<Pessoa>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Pessoa>(pessoa, HttpStatus.OK);
	}
	
	@RequestMapping(method=GET, produces={APPLICATION_JSON_VALUE})
	@ResponseBody

	public List<Pessoa> find(@RequestParam(value = "nome", required = false) String nome, @RequestParam(value = "cpf", required = false) String cpf) {
		List<Pessoa> pessoas = pessoaService.find(nome, cpf);

		return pessoas;
	}

	@ResponseBody
	@RequestMapping(method = POST, consumes = { APPLICATION_JSON_VALUE }, produces = { APPLICATION_JSON_VALUE })
	public ResponseEntity<Pessoa> create(@RequestBody Pessoa pessoa) {
		Pessoa p = pessoaService.save(pessoa);
		return new ResponseEntity<Pessoa>(p, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = PUT, consumes = { APPLICATION_JSON_VALUE }, produces = {
			APPLICATION_JSON_VALUE })
	public ResponseEntity<Pessoa> update(@PathVariable Long id, @RequestBody Pessoa pessoa) {
		Pessoa p = pessoaService.findById(id);

		if (isNull(p)) {
			return new ResponseEntity<Pessoa>(HttpStatus.NOT_FOUND);
		}

		try {
			BeanUtils.copyProperties(p,pessoa);
		} catch (Exception e) {
			throw Throwables.propagate(e);
		}

		pessoaService.update(p);

		return new ResponseEntity<Pessoa>(p, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = DELETE, produces = { APPLICATION_JSON_VALUE })
	public ResponseEntity<Pessoa> delete(@PathVariable Long id) {
		pessoaService.delete(id);
		return new ResponseEntity<Pessoa>(HttpStatus.NO_CONTENT);
	}

}
