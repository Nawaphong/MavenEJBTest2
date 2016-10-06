package com.sample.jpa;

import java.util.List;

import com.sample.entity.Item;
import com.sample.entity.User;

public interface ItemManager {
	public String sayHello();
	public List<Item> getItems();
	public Item createItem(Item item);
	public Item updateItem(Item item);
	public void removeItem(Item item);
	public Item getItemById(Integer id);
	public Item transactionTest(Item item,User user);
}
