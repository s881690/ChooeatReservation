var form_data = JSON.parse(sessionStorage.getItem("form_data"));
var productId = form_data.productId;
var productName = form_data.productName;
var price = removeThousandsSeparator(form_data.price);
var qty = form_data.qty;
function removeThousandsSeparator(number) {
	return number.replace(/,/g, '');
}
// === 載入 ===
window.addEventListener("load", function() {
	// 將資料於頁面上呈現
	var preview_func = function() {
		var product_name_el = document.getElementById("prodTitle");
		var product_price_el = document.getElementById("price");
		var product_qty_el = document.getElementById("qty");
		product_name_el.innerHTML = productName;
		product_price_el.innerHTML = "總價：NT $" + price.toLocaleString();
		product_qty_el.innerHTML = qty;
	};
	preview_func();
	addToCart();
});
// === 傳進redis ===
function addToCart() {
	// 構建請求URL
	const url = "get-cart?operation=addcart&productId=" + productId +"&productName="+productName+"&price="+price+"&qty="+qty;
	// 發送POST請求到後端
	fetch(url, {
		method: "POST",
	})
		.then(function(response) {
			return response.json();
		})
		.then(function(data) {
			console.log(data);
		})
		.catch(function(error) {
			console.log(error);
			// 處理錯誤情況
		});
}
