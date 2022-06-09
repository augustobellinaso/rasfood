package br.com.rasmoo.restaurante.srvice.teste;

import br.com.rasmoo.restaurante.dao.CardapioDao;
import br.com.rasmoo.restaurante.entity.Cardapio;
import br.com.rasmoo.restaurante.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CardapioService {

	public static void main(String[] args) {

		Cardapio risoto = new Cardapio();
		risoto.setNome("Risoto de frutos do mar");
		risoto.setDescricao("Risoto acompanhado de lula, polvo e mariscos");
		risoto.setDisponivel(true);
		risoto.setValor(BigDecimal.valueOf(88.50));

		Cardapio salmao = new Cardapio();
		salmao.setNome("Salmão ao molho de maracujá");
		salmao.setDescricao("Salmão grelhado ao molho de maracujá");
		salmao.setDisponivel(true);
		salmao.setValor(BigDecimal.valueOf(60.00));

		EntityManager entityManager = JPAUtil.getEntityManagerRasfood();
		CardapioDao cardapioDao = new CardapioDao(entityManager);

		entityManager.getTransaction().begin();
		cardapioDao.cadastrar(risoto);
		cardapioDao.cadastrar(salmao);

		logaPratoConsultado(1, cardapioDao);

		cardapioDao.excluir(salmao);
		logaPratoConsultado(2, cardapioDao);

		risoto.setDisponivel(false);
		cardapioDao.atualizar(risoto);

		logaPratoConsultado(1, cardapioDao);
		entityManager.getTransaction().commit();
		entityManager.close();

	}

	private static void logaPratoConsultado(Integer id, CardapioDao cardapioDao) {
		System.out.println("O prato consultado foi ==> " + cardapioDao.consultar(id));
	}
}
