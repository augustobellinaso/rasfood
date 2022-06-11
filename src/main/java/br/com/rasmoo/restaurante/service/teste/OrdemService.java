package br.com.rasmoo.restaurante.service.teste;

import br.com.rasmoo.restaurante.dao.OrdemDao;
import br.com.rasmoo.restaurante.entity.Ordem;
import br.com.rasmoo.restaurante.util.JPAUtil;
import br.com.rasmoo.restaurante.util.PopulaDadosUtil;

import javax.persistence.EntityManager;

public class OrdemService {

	public static void main(String[] args) {
		EntityManager entityManager = JPAUtil.getEntityManagerRasfood();
		entityManager.getTransaction().begin();
		PopulaDadosUtil.cadastarCategorias(entityManager);
		PopulaDadosUtil.cadastrarProdutosCardapio(entityManager);
		PopulaDadosUtil.cadastrarClientes(entityManager);
		PopulaDadosUtil.cadastrarOrdensClientes(entityManager);
		OrdemDao ordemDao = new OrdemDao(entityManager);

		Ordem ordem = ordemDao.consultarPorId(2);
		System.out.println(ordem.getValorTotal());
		entityManager.getTransaction().commit();
		entityManager.close();

	}
}
