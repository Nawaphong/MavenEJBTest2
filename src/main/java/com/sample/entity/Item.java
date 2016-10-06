package com.sample.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "TEST.Items")
public class Item implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7332489645449155302L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name = null;
	private Integer quantity = null;
	private Integer userId = null;
	@Version
	private Timestamp updateDate;

	public Item() {
	}

	public Item(String name, Integer quantity, Integer userId) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.userId = userId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getQuantity() {
		return quantity;
		
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

}
