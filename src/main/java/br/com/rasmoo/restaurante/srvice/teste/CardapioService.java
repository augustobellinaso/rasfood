package br.com.rasmoo.restaurante.srvice.teste;

import br.com.rasmoo.restaurante.dao.CardapioDao;
import br.com.rasmoo.restaurante.dao.CategoriaDao;
import br.com.rasmoo.restaurante.entity.Cardapio;
import br.com.rasmoo.restaurante.entity.Categoria;
import br.com.rasmoo.restaurante.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CardapioService {

	public static void main(String[] args) {

		EntityManager entityManager = JPAUtil.getEntityManagerRasfood();
		Categoria categoria = cadastrarCategoria(entityManager);
		cadastrarProdutoCardapio(entityManager, categoria);

	}

	private static Categoria cadastrarCategoria(EntityManager entityManager) {
		CategoriaDao categoriaDao = new CategoriaDao(entityManager);
		Categoria pratoPrincipal = new Categoria("Prato Principal");
		entityManager.getTransaction().begin();
		categoriaDao.cadastrar(pratoPrincipal);
		entityManager.getTransaction().commit();
		entityManager.clear();

		return pratoPrincipal;
	}

	private static void cadastrarProdutoCardapio(EntityManager entityManager, Categoria categoria) {
		Cardapio risoto = new Cardapio();
		risoto.setNome("Risoto de frutos do mar");
		risoto.setDescricao("Risoto acompanhado de lula, polvo e mariscos");
		risoto.setDisponivel(true);
		risoto.setValor(BigDecimal.valueOf(88.50));
		risoto.setCategoria(categoria);

		Cardapio salmao = new Cardapio();
		salmao.setNome("Salmão ao molho de maracujá");
		salmao.setDescricao("Salmão grelhado ao molho de maracujá");
		salmao.setDisponivel(true);
		salmao.setValor(BigDecimal.valueOf(60.00));
		salmao.setCategoria(categoria);

		CardapioDao cardapioDao = new CardapioDao(entityManager);
		entityManager.getTransaction().begin();
		cardapioDao.cadastrar(risoto);
		entityManager.flush();
		cardapioDao.cadastrar(salmao);
		entityManager.flush();
		logaPratoConsultado(1, cardapioDao);
		logaPratoConsultado(2, cardapioDao);

		entityManager.close();
	}

	private static void logaPratoConsultado(Integer id, CardapioDao cardapioDao) {
		System.out.println("O prato consultado foi ==> " + cardapioDao.consultar(id));
	}
}
