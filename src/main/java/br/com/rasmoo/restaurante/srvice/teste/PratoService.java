package br.com.rasmoo.restaurante.srvice.teste;

import br.com.rasmoo.restaurante.dao.PratoDao;
import br.com.rasmoo.restaurante.entity.Prato;
import br.com.rasmoo.restaurante.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class PratoService {

	public static void main(String[] args) {

		Prato risoto = new Prato();
		risoto.setNome("Risoto de frutos do mar");
		risoto.setDescricao("Risoto acompanhado de lula, polvo e mariscos");
		risoto.setDisponivel(true);
		risoto.setValor(BigDecimal.valueOf(88.50));

		Prato salmao = new Prato();
		salmao.setNome("Salmão ao molho de maracujá");
		salmao.setDescricao("Salmão grelhado ao molho de maracujá");
		salmao.setDisponivel(true);
		salmao.setValor(BigDecimal.valueOf(60.00));

		EntityManager entityManager = JPAUtil.getEntityManagerRasfood();
		PratoDao pratoDao = new PratoDao(entityManager);

		entityManager.getTransaction().begin();
		pratoDao.cadastrar(risoto);
		pratoDao.cadastrar(salmao);

		logaPratoConsultado(1, pratoDao);

		pratoDao.excluir(salmao);
		logaPratoConsultado(2, pratoDao);

		risoto.setDisponivel(false);
		pratoDao.atualizar(risoto);

		logaPratoConsultado(1, pratoDao);
		entityManager.getTransaction().commit();
		entityManager.close();

	}

	private static void logaPratoConsultado(Integer id, PratoDao pratoDao) {
		System.out.println("O prato consultado foi ==> " + pratoDao.consultar(id));
	}
}
