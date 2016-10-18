package com.sample.jpa;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.sample.entity.Item;
import com.sample.entity.User;

@Stateless
@Local(ItemLocal.class)
@Remote(ItemRemote.class)
public class ItemManagerImpl implements ItemLocal,ItemRemote {
	
	
	@EJB
	private UserLocal userService;
	
	@PersistenceContext(unitName = "itemDB")
	private EntityManager em;

	public ItemManagerImpl() {
	}

	public String sayHello() {
		return "Hello World !!!";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> getItems() {
		Query query = em.createQuery("SELECT i FROM Item i ORDER BY i.id");
		List<Item> users = (List<Item>) query.getResultList();
		return users;
	}
	
	@Override
	public Item getItemById(final Integer id){
		return em.find(Item.class,id);
	}

	@Override
	public Item createItem(final Item item) {
		em.persist(item);
		return item;
	}

	@Override
	public Item updateItem(final Item item) {
		return em.merge(item);
	}

	@Override
	public void removeItem(final Item item) {
		if(item == null){
			System.out.println(item.getName());
		}
		if("1".equals(1)){
			System.out.println("1");
		}
		Item itemToBeRemoved = em.getReference(Item.class, item.getId());
	    em.remove(itemToBeRemoved);
	}

	@Override
	public Item transactionTest(Item item, User user) {
		userService.createUser(user);
		createItem(item);
		return item;
	}

}
