package br.com.mv.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mv.model.Pessoa;
import br.com.mv.repository.PessoaRepository;
import br.com.mv.service.PessoaService;

@Service
@Transactional
public class PessoaServiceImpl implements PessoaService {

	@Inject
	private PessoaRepository pessoaRepository;

	@Override
	public Pessoa save(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}

	@Override
	public Pessoa update(Pessoa pessoa) {
		return pessoaRepository.update(pessoa);
	}

	@Override
	public void delete(Long id) {
		pessoaRepository.delete(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Pessoa findById(Long id) {
		return pessoaRepository.findById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Pessoa> find(String nome, String cpf) {
		return pessoaRepository.find(nome, cpf);
	}

}
