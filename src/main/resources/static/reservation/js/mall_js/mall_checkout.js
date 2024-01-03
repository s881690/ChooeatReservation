// ================================== 後端 ===================================
// 獲取購物車內容並顯示在畫面上
function displayCart() {
	var cartContainer = document.getElementById("prod");
	cartContainer.innerHTML = "";
	const url = "get-cart";
	fetch(url)
		.then(function(response) { return response.json(); })
		.then(data => {
			console.log(data);
			const productsMap = data;
			for (let [productId, value] of Object.entries(productsMap)) {
				const key = productId;
				const productName = value.productName;
				const productPrice = value.price.toLocaleString();
				const productqty = value.qty;
				cartContainer.innerHTML += `
							<table>
								<thead>
									<tr>
										<th><h4>訂單內容</h4></th>
										<th></th>
										<th>單價</th>
										<th>數量</th>
										<th>單價</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><img src="./chooeat/images/mall_image/mall3.jpg"
											class="pord_img" /></td>
										<td><h5>餐廳名稱｜餐券名稱</h5>
											<span style="color: gray;">單人｜平日晚餐</span></td>
										<td>NT $1,280</td>
										<td>1</td>
										<td>NT $1,280</td>
									</tr>
									<tr>
										<td colspan="5" style="padding: 0;"><hr></td>
									</tr>
									<tr>
										<td style="width: 120px;"><img
											src="./chooeat/images/mall_image/mall5.jpg" class="pord_img" /></td>
										<td><h5>餐廳名稱｜餐券名稱</h5>
											<span style="color: gray;">單人｜假日晚餐</span></td>
										<td>NT $1,580</td>
										<td>1</td>
										<td>NT $1,580</td>
									</tr>
									<tr>
										<td colspan="5" style="padding: 0;"><hr></td>
									</tr>
								</tbody>
								<tfoot>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td>NT $2,860</td>
									</tr>
								</tfoot>
							</table>
						`;

			};
			bindEventsToElements();
		})
		.catch(function(error) {
			console.log(error);
		});
}

document.addEventListener("DOMContentLoaded", function() {
	displayCart();
});

// ================================== 前端 ===================================
function bindEventsToElements() {
	//=== qty輸入框 ===
	const qtyInputs = document.querySelectorAll(".qty");
	qtyInputs.forEach((input) => {
		input.addEventListener("keydown", function(event) {
			if (event.key === "Enter") {
				event.preventDefault();
				input.blur();
			}
		});
		input.addEventListener("blur", function() {
			const prod = button.closest(".prod");
			const qty = parseInt(input.value);
			if (qty < 1 || isNaN(qty)) {
				input.value = "1";
			} else if (qty > 100) {
				alert("超過可購買上限");
				input.value = "1";
				return;
			}
			updatePrice(input);
			// fetch
			const productId = prod.dataset.productId; 
			const operation = "updateQuantity";
			const productName = prod.querySelector('[data-product-name]').dataset.productName.toLowerCase();
			const price = parseInt(prod.querySelector('[data-product-price]').dataset.productPrice.toString().replace(/,/g, ""));
			const productqty = qty;
			const url = "get-cart?operation=" + operation + "&productId=" + productId + "&productName=" + productName + "&price=" + price + "&qty=" + productqty;
			fetch(url, {
				method: "POST",
			})
				.then((response) => response.json())
				.then((data) => {
					console.log("商品數量已更新");
				})
				.catch((error) => {
					console.error("更新商品數量時發生錯誤:", error);
				});
		});

		function updatePrice(input) {
			const qty = parseInt(input.value);
			const prod = input.closest(".prod");
			const priceElement = prod.querySelector(".price");
			const unitPrice = parseInt(prod.querySelector('[data-product-price]').dataset.productPrice.toString().replace(/,/g, ""));
			const updatedPrice = unitPrice * qty;
			priceElement.textContent = "NT $" + updatedPrice.toLocaleString();
			updateSidebar();
		}
	});
	// === count_minus ===
	const countMinusButtons = document.querySelectorAll(".count_minus");
	countMinusButtons.forEach((button) => {
		button.addEventListener("click", function count_minus() {
			const prod = button.closest(".prod");
			const countInput = button.nextElementSibling;
			let qty = parseInt(countInput.value);
			if (qty > 1) {
				qty -= 1;
				countInput.value = qty;
				const priceElement = button.closest(".prod").querySelector(".price");
				const unitPrice = parseInt(prod.querySelector('[data-product-price]').dataset.productPrice.toString().replace(/,/g, ""));
				const updatedPrice = unitPrice * qty;
				priceElement.textContent = "NT $" + updatedPrice.toLocaleString();
				updateSidebar();
				// fetch
				const productId = prod.dataset.productId; 
				const operation = "updateQuantity";
				const productName = prod.querySelector('[data-product-name]').dataset.productName.toLowerCase();
				const price = parseInt(prod.querySelector('[data-product-price]').dataset.productPrice.toString().replace(/,/g, ""));
				const productqty = qty;
				const url = "get-cart?operation=" + operation + "&productId=" + productId + "&productName=" + productName + "&price=" + price + "&qty=" + productqty;
				fetch(url, {
					method: "POST",
				})
					.then((response) => response.json())
					.then((data) => {
						console.log("商品數量已更新");
					})
					.catch((error) => {
						console.error("更新商品數量時發生錯誤:", error);
					});

			}
		});
	});
	// === count_plus ===
	const countPlusButtons = document.querySelectorAll(".count_plus");
	countPlusButtons.forEach((button) => {
		button.addEventListener("click", function() {
			const prod = button.closest(".prod");
			const countInput = button.previousElementSibling;
			let qty = parseInt(countInput.value);
			qty += 1;
			countInput.value = qty;
			const priceElement = button.closest(".prod").querySelector(".price");
			const unitPrice = parseInt(prod.querySelector('[data-product-price]').dataset.productPrice.toString().replace(/,/g, ""));
			const updatedPrice = unitPrice * qty;
			priceElement.textContent = "NT $" + updatedPrice.toLocaleString();
			updateSidebar();
			// fetch
			const productId = prod.dataset.productId;
			const operation = "updateQuantity";
			const productName = prod.querySelector('[data-product-name]').dataset.productName.toLowerCase();
			const price = parseInt(prod.querySelector('[data-product-price]').dataset.productPrice.toString().replace(/,/g, ""));
			const productqty = qty;
			const url = "get-cart?operation=" + operation + "&productId=" + productId + "&productName=" + productName + "&price=" + price + "&qty=" + productqty;
			fetch(url, {
				method: "POST",
			})
				.then((response) => response.json())
				.then((data) => {
					console.log("商品數量已更新");
				})
				.catch((error) => {
					console.error("更新商品數量時發生錯誤:", error);
				});

		});
	});

	// === 加到側邊欄 ===
	const checkboxes = document.querySelectorAll(".prod_checkbox");
	const countInputs = document.querySelectorAll(".qty");
	const priceElements = document.querySelectorAll(".price");
	const totalQtyElement = document.querySelector(".qty_number");
	const totalPriceElement = document.querySelector(".amount");

	let totalQty = 0;
	let totalPrice = 0;

	checkboxes.forEach((checkbox, index) => {
		checkbox.addEventListener("click", () => {
			if (checkbox.checked) {
				const qty = parseInt(countInputs[index].value);
				const priceElement = priceElements[index];
				const priceText = priceElement.textContent.trim();
				const priceData = priceElement.dataset.price;

				const priceMatch = priceText.match(/[\d,]+/);
				const price = parseInt(priceMatch[0].replace(/,/g, ""));

				if (isNaN(price)) {
					price = parseInt(priceData);
				}

				totalQty += qty;
				totalPrice += price;
				totalQtyElement.textContent = totalQty;
				totalPriceElement.textContent = "NT $" + totalPrice;
			} else {
				const qty = parseInt(countInputs[index].value);
				const priceElement = priceElements[index];
				const priceText = priceElement.textContent.trim();
				const priceMatch = priceText.match(/[\d,]+/);
				const price = parseInt(priceMatch[0].replace(/,/g, ""));
				totalQty -= qty;
				totalPrice -= price;
				totalQtyElement.textContent = totalQty;
				totalPriceElement.textContent = "NT $" + totalPrice.toLocaleString();
			}
		});
	});
	//=== 更新側邊攔 ===
	function updateSidebar() {
		let totalQty = 0;
		let totalPrice = 0;

		checkboxes.forEach((checkbox, index) => {
			if (checkbox.checked) {
				const qty = parseInt(countInputs[index].value);
				const priceElement = priceElements[index];
				const priceText = priceElement.textContent.trim();

				const priceMatch = priceText.match(/[\d,]+/);
				const price = parseInt(priceMatch[0].replace(/,/g, ""));
				totalQty += qty;
				totalPrice += price;
			}
		});

		totalQtyElement.textContent = totalQty;
		totalPriceElement.textContent = "NT $" + totalPrice.toLocaleString();
	}

	checkboxes.forEach((checkbox) => {
		checkbox.addEventListener("click", updateSidebar);
	});
	// === all_checkbox ===
	const selectAllCheckbox = document.querySelector(".select_all_checkbox");

	selectAllCheckbox.addEventListener("click", function() {
		const isChecked = selectAllCheckbox.checked;
		checkboxes.forEach((checkbox) => {
			checkbox.checked = isChecked;
		});
		updateSidebar();
	});

	// === delete_btn ===
	const deleteButtons = document.querySelectorAll(".delete_btn");
	const overlay = document.querySelector(".confirmation-overlay");
	deleteButtons.forEach((button) => {
		button.addEventListener("click", function() {
			const prod = button.closest(".prod");
			overlay.style.display = "flex";
			const confirmBtn = document.querySelector(".confirm-btn");
			const cancelBtn = document.querySelector(".cancel-btn");
			confirmBtn.addEventListener("click", function() {
				prod.remove();
				overlay.style.display = "none";
				updateSidebar();

				// === fetch ===
				const productId = prod.dataset.productId; 
				const operation = "deleteSelected"; 
				const productName = prod.querySelector('[data-product-name]').dataset.productName.toLowerCase();
				const price = parseInt(prod.querySelector('[data-product-price]').dataset.productPrice.toString().replace(/,/g, ""));
				const qty = prod.querySelector('[data-product-qty]').dataset.productQty.toLowerCase();
				const url = "get-cart?operation=" + operation + "&productId=" + productId + "&productName=" + productName + "&price=" + price + "&qty=" + qty;;
				fetch(url, {
					method: "POST",
				})
					.then((response) => response.json())
					.then((data) => {
						console.log("商品已刪除");
					})
					.catch((error) => {
						console.error("刪除商品時發生錯誤:", error);
					});

			});
			cancelBtn.addEventListener("click", function() {
				overlay.style.display = "none";
			});

		});
	});

	// === delete_selected_btn ===
	const deleteSelectedButton = document.querySelector(".delete_selected_btn");
	deleteSelectedButton.addEventListener("click", function() {
		const checkboxes = document.querySelectorAll(".prod_checkbox:checked");
		if (checkboxes.length === 0) {
			return;
		}
		const overlay = document.querySelector(".confirmation-overlay");
		overlay.style.display = "flex";
		const confirmBtn = document.querySelector(".confirm-btn");
		const cancelBtn = document.querySelector(".cancel-btn");
		confirmBtn.addEventListener("click", function() {
			checkboxes.forEach(function(checkbox) {
				const prod = checkbox.closest(".prod");
				const productId = prod.dataset.productId;
				const operation = "deleteSelected"; 
				const productName = prod.querySelector('[data-product-name]').dataset.productName.toLowerCase();
				const price = parseInt(prod.querySelector('[data-product-price]').dataset.productPrice.toString().replace(/,/g, ""));
				const qty = prod.querySelector('[data-product-qty]').dataset.productQty.toLowerCase();
				const url = "get-cart?operation=" + operation + "&productId=" + productId + "&productName=" + productName + "&price=" + price + "&qty=" + qty;
				fetch(url, {
					method: "POST",
				})
					.then((response) => response.json())
					.then((data) => {
						console.log("商品已删除");
						prod.remove();
					})
					.catch((error) => {
						console.error("刪除商品時發生錯誤:", error);
					});

			});
			overlay.style.display = "none";
			updateSidebar();
		});
		cancelBtn.addEventListener("click", function() {
			overlay.style.display = "none";
		});
	});

	const confirmationBox = document.querySelector(".confirmation-box");
	overlay.addEventListener("click", function(event) {
		if (event.target === overlay) {
			overlay.style.display = "none";
		}
	});
	confirmationBox.addEventListener("click", function(event) {
		event.stopPropagation();
	});

	selectAllCheckbox.checked = false;

	selectAllCheckbox.addEventListener("click", function() {
		const isChecked = selectAllCheckbox.checked;
		checkboxes.forEach((checkbox) => {
			checkbox.checked = isChecked;
		});
	});

	checkboxes.forEach((checkbox) => {
		checkbox.addEventListener("click", function() {
			const allChecked = Array.from(checkboxes).every((c) => c.checked);
			selectAllCheckbox.checked = allChecked;
		});
	});

}
