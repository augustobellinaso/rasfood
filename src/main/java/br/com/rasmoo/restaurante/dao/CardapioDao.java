package br.com.rasmoo.restaurante.dao;

import br.com.rasmoo.restaurante.entity.Cardapio;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Collections;
import java.util.List;

public class CardapioDao {

	private final EntityManager entityManager;

	public CardapioDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void cadastrar(final Cardapio cardapio) {
		this.entityManager.persist(cardapio);
	}

	public Cardapio consultarPorId(final Integer id) {
		return this.entityManager.find(Cardapio.class, id);
	}

	public List<Cardapio> consultarTodos() {
		String sql = "SELECT c FROM Cardapio c";
		try {
			return this.entityManager.createQuery(sql, Cardapio.class).getResultList();
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	public List<Cardapio> consultarPorValor(final BigDecimal filtro) {
		String jpql = "SELECT c FROM Cardapio c WHERE c.valor = :valor";
		try {
			return this.entityManager.createQuery(jpql, Cardapio.class).setParameter("valor", filtro).getResultList();
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	public Cardapio consultarPorNome(final String filtro) {
		String jpql = "SELECT c FROM Cardapio c WHERE UPPER(c.nome) = UPPER(:nome)";
		try {
			return this.entityManager.createQuery(jpql, Cardapio.class).setParameter("nome", filtro).getSingleResult();

		} catch (Exception e) {
			return null;
		}
	}

	public void atualizar(final Cardapio cardapio) {
		this.entityManager.merge(cardapio);
	}

	public void excluir(final Cardapio cardapio) {
		this.entityManager.remove(cardapio);
	}
}
