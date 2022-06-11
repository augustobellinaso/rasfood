package br.com.rasmoo.restaurante.dao;

import br.com.rasmoo.restaurante.entity.Endereco;
import br.com.rasmoo.restaurante.vo.ClienteVo;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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

	public List<ClienteVo> consultarClientesUsandoCriteria(final String estado, final String cidade, final String rua) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ClienteVo> criteriaQuery = builder.createQuery(ClienteVo.class);
		Root<Endereco> root = criteriaQuery.from(Endereco.class);
		criteriaQuery.multiselect(root.get("cliente").get("cpf"), root.get("cliente").get("nome"));
		Predicate predicate = builder.and();

		if (Objects.nonNull(estado)) {
			predicate = builder.and(predicate, builder.equal(builder.upper(root.get("estado")), estado.toUpperCase()));
		}

		if (Objects.nonNull(cidade)) {
			predicate = builder.and(predicate, builder.equal(builder.upper(root.get("cidade")), cidade.toUpperCase()));
		}

		if (Objects.nonNull(rua)) {
			predicate = builder.and(predicate, builder.equal(builder.upper(root.get("rua")), rua.toUpperCase()));
		}

		criteriaQuery.where(predicate);
		return entityManager.createQuery(criteriaQuery).getResultList();

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
