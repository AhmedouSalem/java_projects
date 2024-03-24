package com.beans;

import java.math.BigDecimal;
import java.util.Date;

public class OrderBean {
	private int orderId;
	private int customerId;
	private int orderStatus;
	private Date requiredDate;
	private Date shippedDate;
	private Integer discount;
	private BigDecimal totalPrice;
	private String receiver;
	private int orderAddress;
	private BigDecimal orderPriceDelivery;
	private Date orderDate;

	// Constructeurs
	public OrderBean() {
	}

	public OrderBean(int customerId, int orderStatus, Date requiredDate, int orderAddress,
			BigDecimal orderPriceDelivery) {
		this.customerId = customerId;
		this.orderStatus = orderStatus;
		this.requiredDate = requiredDate;
		this.orderAddress = orderAddress;
		this.orderPriceDelivery = orderPriceDelivery;
	}

	// Getters et Setters
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getRequiredDate() {
		return requiredDate;
	}

	public void setRequiredDate(Date requiredDate) {
		this.requiredDate = requiredDate;
	}

	public Date getShippedDate() {
		return shippedDate;
	}

	public void setShippedDate(Date shippedDate) {
		this.shippedDate = shippedDate;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public int getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(int orderAddress) {
		this.orderAddress = orderAddress;
	}

	public BigDecimal getOrderPriceDelivery() {
		return orderPriceDelivery;
	}

	public void setOrderPriceDelivery(BigDecimal orderPriceDelivery) {
		this.orderPriceDelivery = orderPriceDelivery;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
}
