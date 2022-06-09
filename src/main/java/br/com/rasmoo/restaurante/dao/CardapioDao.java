package br.com.rasmoo.restaurante.dao;

import br.com.rasmoo.restaurante.entity.Cardapio;

import javax.persistence.EntityManager;

public class CardapioDao {

	private final EntityManager entityManager;

	public CardapioDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void cadastrar(final Cardapio cardapio) {
		this.entityManager.persist(cardapio);
	}

	public Cardapio consultar(final Integer id) {
		return this.entityManager.find(Cardapio.class, id);
	}

	public void atualizar(final Cardapio cardapio) {
		this.entityManager.merge(cardapio);
	}

	public void excluir(final Cardapio cardapio) {
		this.entityManager.remove(cardapio);
	}
}