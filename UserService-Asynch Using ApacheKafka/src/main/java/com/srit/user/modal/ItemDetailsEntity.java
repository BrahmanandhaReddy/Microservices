package com.srit.user.modal;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "itemdetail")
public class ItemDetailsEntity implements Serializable{
	@Id
	@Column(name = "itemid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected int itemId;
	@Column(name = "itemname")
	protected String itemName;
	protected int qty;
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	@Override
	public String toString() {
		return "ItemDetailsEntity [itemId=" + itemId + ", itemName=" + itemName + ", qty=" + qty + "]";
	}
}
