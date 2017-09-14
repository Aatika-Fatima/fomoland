package com.fomo.persistence.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
public class ProductCategory implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private int productId;
	private String productName;

	public ProductCategory() {
		// TODO Auto-generated constructor stub
	}

	public ProductCategory(int productId, String productName) {
		super();
		this.productId = productId;
		this.productName = productName;
	}

	public final int getProductId() {
		return productId;
	}

	public final void setProductId(int productId) {
		this.productId = productId;
	}

	public final String getProductName() {
		return productName;
	}

	public final void setProductName(String productName) {
		this.productName = productName;
	}

}
