package br.com.mv.service;

import java.util.List;

import br.com.mv.model.Pessoa;

public interface PessoaService {

	public Pessoa save(Pessoa pessoa);

	public Pessoa update(Pessoa pessoa);

	public void delete(Long id);
	
	public Pessoa findById(Long id);

	public List<Pessoa> find(String nome, String cpf);

}
