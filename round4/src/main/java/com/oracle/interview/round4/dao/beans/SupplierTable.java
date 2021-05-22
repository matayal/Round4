package com.oracle.interview.round4.dao.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SUPPLIER_DETAIL")
public class SupplierTable implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ITEM_ID")
	private String itemId;

	@Column(name = "ITEM_COUNT")
	private int itemCount;
	
	@Column(name = "ITEM_NAME")
	private String itemName;

	@Column(name = "SUPPLIER_NAME")
	private String supplierName;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

}
