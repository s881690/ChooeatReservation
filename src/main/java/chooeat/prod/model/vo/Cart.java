package chooeat.prod.model.vo;

import java.io.Serializable;
import java.util.List;

public class Cart implements Serializable {
	private static final long serialVersionUID = 1L;

	private String userId;
	private List<CartItem> items;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<CartItem> getItems() {
		return items;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}

	public Cart(String userId, List<CartItem> items) {
		super();
		this.userId = userId;
		this.items = items;
	}

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

}
