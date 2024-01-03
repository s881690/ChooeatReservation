let accId;
var selectedProducts;
var jsonselectedProducts;

//===========
let account = JSON.parse(sessionStorage.getItem("loginReq"));
if (sessionStorage.getItem("loginReq") != null) {
	document.getElementById("sname").innerHTML = account.acc_name;
	accId = account.acc_id;
} else {
	accId = '0';
}

//=================================================================
// 拿到會員icon
let accountIcon = $("a.accountIcon");
// console.log(accountIcon);
// 會員中心的判斷
if (account != null) {
	accountIcon.attr("href", "../account/usercenter.html");
} else {
	accountIcon.attr("href", "../account/login.html");
}
// ================================== 後端 ===================================
// 獲取購物車內容並顯示在畫面上
function displayCart() {
	var cartContainer = document.getElementById("cart_container");
	cartContainer.innerHTML = "";
	const operation = "mergeCart";
	const url = "get-cart?accId=" + accId + "&operation=" + operation;
	fetch(url)
		.then(function(response) { return response.json(); })
		.then(data => {
			// console.log(data);
			const productsMap = data;
			if (Object.keys(productsMap).length === 0) {
				cartContainer.innerHTML = `<a href="mall.html"><h2 style="margin-top:30px; text-align: center;">購物車尚未有任何商品，快前往選購吧！</h2></a>`;
			} else {
				for (let [productId, value] of Object.entries(productsMap)) {
					const key = productId;
					const productName = value.productName;
					const productPrice = value.price.toLocaleString();
					const productqty = value.qty;
					const uint8Array = new Uint8Array(value.prodPic);
					let blob = new Blob([uint8Array], { type: "image/*" });
					let imageUrl = URL.createObjectURL(blob);
					cartContainer.innerHTML += `
				<div data-product-id="${key}" class="prod">
						<table>
							<tr>
								<td><input type="checkbox" class="prod_checkbox" /></td>
								<td width="120"><img src="${imageUrl}" class="prod_img" /></td>
								<td>
									<h5 data-product-name="${productName}">${productName}</h5>
									<span style="color: gray;">單人｜平日晚餐</span>
								</td>
								<td>
									<div class="deleteandCount">
										<div class="delete">
											<button class="delete_btn">刪除</button>
										</div>
										<div class="count">
											<button class="count_minus">-</button>
											<input type="number" data-product-qty="${productqty}" class="qty" value="${productqty}">
											<button class="count_plus">+</button>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="4" style="padding: 0;"><hr></td>
							</tr>
							<tr>
								<td colspan="3" style="padding: 0;"></td>
								<td>
									<div class="price" data-product-price="${productPrice}">NT $${productPrice}</div>
								</td>
							</tr>
						</table>
					</div>
				`;
				}

				bindEventsToElements();
			}
			// Step 3: 勾選所有checkbox
			const checkboxes = document.querySelectorAll('input[type="checkbox"]');
			const totalQtyElement = document.querySelector(".qty_number");
			const totalPriceElement = document.querySelector(".amount");
			const countInputs = document.querySelectorAll(".qty");
			const priceElements = document.querySelectorAll(".price");

			let totalQty = 0;
			let totalPrice = 0;

			checkboxes.forEach((checkbox, index) => {
				checkbox.checked = true;
			
				if (index === checkboxes.length - 1) {
					//					const prodElement = checkbox.closest('.prod');
					//					console.log(prodElement);
					//					const productId = prodElement.getAttribute('data-product-id');
					//					console.log(productId);
					//					selectedProducts.push(productId);
//					console.log(checkbox);
					return;
				} else {
					console.log(checkbox);
					const qty = parseInt(countInputs[index].value);
					const priceElement = priceElements[index];
					const priceText = priceElement.textContent.trim();
					const priceMatch = priceText.match(/[\d,]+/);
					const price = parseInt(priceMatch[0].replace(/,/g, ""));
					totalQty += qty;
					totalPrice += price * qty;
				}
			});
			totalQtyElement.textContent = totalQty;
			totalPriceElement.textContent = "NT $" + totalPrice.toLocaleString();



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
			const prod = input.closest(".prod");
			const qty = parseInt(input.value);
			if (qty < 1 || isNaN(qty)) {
				input.value = "1";
			} else if (qty > 10) {
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
			const url = "get-cart?operation=" + operation + "&productId=" + productId + "&productName=" + productName + "&price=" + price + "&qty=" + productqty + "&accId=" + accId;
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
				const url = "get-cart?operation=" + operation + "&productId=" + productId + "&productName=" + productName + "&price=" + price + "&qty=" + productqty + "&accId=" + accId;
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
			const url = "get-cart?operation=" + operation + "&productId=" + productId + "&productName=" + productName + "&price=" + price + "&qty=" + productqty + "&accId=" + accId;
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
				selectedProducts = [];
				const qty = parseInt(countInputs[index].value);
				const priceElement = priceElements[index];
				const priceText = priceElement.textContent.trim();
				const priceData = priceElement.dataset.price;

				const priceMatch = priceText.match(/[\d,]+/);
				const price = parseInt(priceMatch[0].replace(/,/g, ""));
				const productId = checkbox.closest('.prod').dataset.productId;
				selectedProducts.push(productId);

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
			handleCheckboxChange();
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
				totalPrice += price * qty;
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
		handleCheckboxChange();
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
				const checkbox = prod.querySelector(".prod_checkbox");
				checkbox.checked = false;
				// === fetch ===
				const productId = prod.dataset.productId;
				const operation = "deleteSelected";
				const productName = prod.querySelector('[data-product-name]').dataset.productName.toLowerCase();
				const price = parseInt(prod.querySelector('[data-product-price]').dataset.productPrice.toString().replace(/,/g, ""));
				const qty = prod.querySelector('[data-product-qty]').dataset.productQty.toLowerCase();
				const url = "get-cart?operation=" + operation + "&productId=" + productId + "&productName=" + productName + "&price=" + price + "&qty=" + qty + "&accId=" + accId;
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
				prod.remove();
				overlay.style.display = "none";
				updateSidebar();
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
				checkbox.checked = false;
				const prod = checkbox.closest(".prod");
				const productId = prod.dataset.productId;
				const operation = "deleteSelected";
				const productName = prod.querySelector('[data-product-name]').dataset.productName.toLowerCase();
				const price = parseInt(prod.querySelector('[data-product-price]').dataset.productPrice.toString().replace(/,/g, ""));
				const qty = prod.querySelector('[data-product-qty]').dataset.productQty.toLowerCase();
				const url = "get-cart?operation=" + operation + "&productId=" + productId + "&productName=" + productName + "&price=" + price + "&qty=" + qty + "&accId=" + accId;
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
			totalQty -= qty;
			totalPrice -= price * qty;
			totalPriceElement.textContent = "NT $" + totalPrice.toLocaleString();
		});
		const confirmationBox = document.querySelector(".confirmation-box");
		overlay.addEventListener("click", function(event) {
			if (event.target === overlay) {
				overlay.style.display = "none";
			}
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
	const isDirectBuy = false;
	function handleCheckboxChange(event) {
		selectedProducts = [];
		checkboxes.forEach(checkbox => {
			if (checkbox.checked) {
				const productId = checkbox.closest('.prod').dataset.productId;
				selectedProducts.push(productId);
			}
		});
		console.log(selectedProducts);
		jsonselectedProducts = JSON.stringify(selectedProducts);
		//		console.log(jsonselectedProducts);
	}
	var btn_pay_el = document.getElementById("pay");

	btn_pay_el.addEventListener("click", function() {
		// 判斷是否已登入
		let account = JSON.parse(sessionStorage.getItem("loginReq"));
		console.log(account);
		if (sessionStorage.getItem("loginReq") == null) {
			alert("請先進行登入");
			return;
		} else {
			console.log("T");
			console.log(jsonselectedProducts);
			fetch('checkout', {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json'
				},
				body: JSON.stringify({ selectedProducts: selectedProducts, isDirectBuy: isDirectBuy })
			})
				.then(data => {
					console.log("d");
					window.location.href = 'mall_checkout.html';
				})
				.catch(error => {
					console.error('Error:', error);
				});
		}
	});
}



