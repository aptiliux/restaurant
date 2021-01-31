package org.aptiliux.restaurant.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "t_order")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@OneToOne
	@JoinColumn(name = "waiter_id", referencedColumnName = "id", nullable = false)
	private Waiter waiter;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderProduct> products;

	@Column(name = "date", columnDefinition = "DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	public Order() {
	}

	public Order(Waiter waiter, List<OrderProduct> products, Date date) {
		this.waiter = waiter;
		this.products = products;
		this.date = date;
	}

	public Order(Waiter waiter, Date date) {
		this(waiter, new ArrayList<>(), date);
	}

	public void addProduct(Product product, Integer quantity) {
		products.add(new OrderProduct(this, product, quantity));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Waiter getWaiter() {
		return waiter;
	}

	public void setWaiter(Waiter waiter) {
		this.waiter = waiter;
	}

	public List<OrderProduct> getProducts() {
		return products;
	}

	public void setProducts(List<OrderProduct> products) {
		this.products = products;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
