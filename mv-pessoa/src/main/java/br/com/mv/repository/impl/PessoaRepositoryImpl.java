package br.com.mv.repository.impl;

import static java.util.Objects.isNull;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.google.common.base.Throwables;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import br.com.mv.model.Pessoa;
import br.com.mv.model.QPessoa;
import br.com.mv.model.QTelefone;
import br.com.mv.repository.PessoaRepository;
import javassist.NotFoundException;

@Repository
public class PessoaRepositoryImpl implements PessoaRepository {

	@PersistenceContext
	protected EntityManager entityManager;

	@Inject
	private JPAQueryFactory queryFactory;

	@Override
	public Pessoa save(Pessoa pessoa) {
		entityManager.persist(pessoa);
		return pessoa;
	}

	@Override
	public Pessoa update(Pessoa pessoa) {
		entityManager.merge(pessoa);
		return pessoa;
	}

	@Override
	public void delete(Pessoa pessoa) {
		entityManager.remove(pessoa);
	}

	@Override
	public void delete(Long id) {
		Pessoa pessoa = findById(id);
		if (isNull(pessoa)) {
			Throwables.propagate(new NotFoundException("Pessoa não encontrada."));
		}
		delete(pessoa);
	}

	@Override
	public Pessoa findById(Long id) {
		QPessoa pessoa = QPessoa.pessoa;
		QTelefone telefone = QTelefone.telefone;

		Pessoa p = queryFactory.selectFrom(pessoa).leftJoin(pessoa.telefones, telefone).fetchJoin().where(pessoa.id.eq(id)).fetchOne();

		return p;
	}

	@Override
	public List<Pessoa> find(String nome, String cpf) {

		QPessoa pessoa = QPessoa.pessoa;
		BooleanBuilder parametros = new BooleanBuilder();

		if (nome != null && !nome.isEmpty()) {
			parametros.and(pessoa.nome.containsIgnoreCase(nome));
		}

		if (cpf != null && !cpf.isEmpty()) {
			parametros.and(pessoa.cpf.equalsIgnoreCase(cpf));
		}

		return queryFactory.selectFrom(pessoa).where(parametros).fetch();

	}

}
