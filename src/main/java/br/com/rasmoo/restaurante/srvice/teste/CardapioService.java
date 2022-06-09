package br.com.rasmoo.restaurante.srvice.teste;

import br.com.rasmoo.restaurante.dao.CardapioDao;
import br.com.rasmoo.restaurante.util.JPAUtil;
import br.com.rasmoo.restaurante.util.PopulaDadosUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CardapioService {

	public static void main(String[] args) {

		EntityManager entityManager = JPAUtil.getEntityManagerRasfood();
		entityManager.getTransaction().begin();
		PopulaDadosUtil.cadastarCategorias(entityManager);
		PopulaDadosUtil.cadastrarProdutosCardapio(entityManager);

		CardapioDao cardapioDao = new CardapioDao(entityManager);
		System.out.println("LISTA DE PRODUTOS POR VALOR " + cardapioDao.consultarPorValor(new BigDecimal("59.00")));

		System.out.println("BUSCANDO PRODUTO POR NOME " + cardapioDao.consultarPorNome("moqueca"));

		entityManager.close();

	}

}
