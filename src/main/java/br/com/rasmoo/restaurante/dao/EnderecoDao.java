package br.com.rasmoo.restaurante.dao;

import br.com.rasmoo.restaurante.entity.Endereco;
import br.com.rasmoo.restaurante.vo.ClienteVo;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;

public class EnderecoDao {

	private final EntityManager entityManager;

	public EnderecoDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void cadastrar(final Endereco endereco) {
		this.entityManager.persist(endereco);
	}

	public Endereco consultarPorId(final Integer id) {
		return this.entityManager.find(Endereco.class, id);
	}

	public List<Endereco> consultarTodos() {
		String sql = "SELECT e FROM Endereco e";
		return this.entityManager.createQuery(sql, Endereco.class).getResultList();
	}

	public List<ClienteVo> consultarClientes(final String estado, final String cidade, final String rua) {
		String sql = "SELECT new br.com.rasmoo.restaurante.vo.ClienteVo(e.cliente.cpf, e.cliente.nome) " +
				"FROM Endereco e " +
				"WHERE 1 = 1 ";

		if (Objects.nonNull(estado)) {
			sql = sql.concat("AND UPPER(e.estado) = UPPER(:estado) ");
		}

		if (Objects.nonNull(cidade)) {
			sql = sql.concat("AND UPPER(e.cidade) = UPPER(:cidade) ");
		}

		if (Objects.nonNull(rua)) {
			sql = sql.concat("AND UPPER(e.rua) = UPPER(:rua) ");
		}

		TypedQuery<ClienteVo> typedQuery = this.entityManager.createQuery(sql, ClienteVo.class);

		if (Objects.nonNull(estado)) {
			typedQuery.setParameter("estado", estado);
		}

		if (Objects.nonNull(cidade)) {
			typedQuery.setParameter("cidade", cidade);
		}

		if (Objects.nonNull(rua)) {
			typedQuery.setParameter("rua", rua);
		}

		return typedQuery.getResultList();
	}

	public void atualizar(final Endereco endereco) {
		this.entityManager.merge(endereco);
	}

	public void excluir(final Endereco endereco) {
		this.entityManager.remove(endereco);
	}
}
