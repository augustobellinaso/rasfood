package br.com.rasmoo.restaurante.service.teste;

import br.com.rasmoo.restaurante.dao.CardapioDao;
import br.com.rasmoo.restaurante.dao.ClienteDao;
import br.com.rasmoo.restaurante.dao.OrdemDao;
import br.com.rasmoo.restaurante.entity.Cardapio;
import br.com.rasmoo.restaurante.entity.Cliente;
import br.com.rasmoo.restaurante.entity.Ordem;
import br.com.rasmoo.restaurante.entity.OrdensCardapio;
import br.com.rasmoo.restaurante.util.JPAUtil;
import br.com.rasmoo.restaurante.util.PopulaDadosUtil;

import javax.persistence.EntityManager;

public class OrdemService {

	public static void main(String[] args) {
		EntityManager entityManager = JPAUtil.getEntityManagerRasfood();
		entityManager.getTransaction().begin();
		PopulaDadosUtil.cadastarCategorias(entityManager);
		PopulaDadosUtil.cadastrarProdutosCardapio(entityManager);

		CardapioDao cardapioDao = new CardapioDao(entityManager);
		ClienteDao clienteDao = new ClienteDao(entityManager);
		OrdemDao ordemDao = new OrdemDao(entityManager);

		Cliente augusto = new Cliente("12345678900", "Augusto", "12345000");
		Ordem ordem = new Ordem(augusto);
		ordem.addOrdensCardapio(new OrdensCardapio(cardapioDao.consultarPorId(1), 2));

		clienteDao.cadastrar(augusto);
		ordemDao.cadastrar(ordem);

		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
