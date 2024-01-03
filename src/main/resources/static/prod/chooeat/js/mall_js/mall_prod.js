//===============================================================================
var productName;
var price;
var resName;
var btn_add_cart_el;
var productId;
var prodPic;
let account = JSON.parse(sessionStorage.getItem("loginReq"));
let accId;
const overlay = document.querySelector(".confirmation-overlay");
const confirmationBox = document.querySelector(".confirmation-box");
const cancelBtn = document.querySelector(".cancel-btn");
var star;
//===========
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
// ========================從URL獲取商品ID並獲取詳細資料====================================
document.addEventListener("DOMContentLoaded", function() {
	productId = getProductIdFromURL();
	getProductDetails(productId);
});
//======================================== 時間轉換 ========================================
function formatTimestamp(timestampString) {
	var timestamp = new Date(timestampString);
	var year = timestamp.getFullYear();
	var month = timestamp.getMonth() + 1;
	var day = timestamp.getDate();
	var hours = timestamp.getHours();
	var minutes = timestamp.getMinutes();
	if (minutes < 10) {
		minutes = "0" + minutes;
	}

	var formattedTimestamp = year + "年" + month + "月" + day + "日 " + hours + ":" + minutes;

	return formattedTimestamp;
}
//===================================送到餐券詳細頁面=================================
function initMap() {
	const uluru = { lat: 25.105, lng: 121.597 };
	const map = new google.maps.Map(document.getElementById("map"), {
		zoom: 10,
		center: uluru,
	});
	const marker = new google.maps.Marker({
		position: uluru,
		map: map,
	});
}
//============使用fetch函數從後端獲取商品詳細資料並動態生成商品詳細頁面=============================
function getProductDetails(productId) {
	fetchController = new AbortController(); // 創建請求的控制器
	var fetchSignal = fetchController.signal;
	const url = "prod/details?id=" + productId;
	fetch(url, { signal: fetchSignal })
		.then(response => response.json())
		.then(data => {
			//			console.log(data);
			const prod = data.prod;
			const orderDetails = data.orderDetails;
			const address = prod.resAdd;
			resName = prod.resName;
			productName = prod.resName + "｜" + prod.prodName;
			price = prod.prodPrice.toLocaleString();
			star = Math.floor(prod.prodCommentScore);
			prodPic = prod.prodPic;
			const uint8Array = new Uint8Array(prod.prodPic);
			let blob = new Blob([uint8Array], { type: "image/*" });
			let imageUrl = URL.createObjectURL(blob);

			document.getElementById("prodpic").src = `${imageUrl}`;

			document.getElementById("breadcrumb-page").innerHTML = `
			${prod.resName} | ${prod.prodName}
			`;
			document.getElementById("prodInfor").innerHTML = `
			<span id="productName" class="prodTitle">${prod.resName} | ${prod.prodName}</span>
                <br />
              <div class="type">${prod.resType}</div>
              <div class="add">${prod.resAdd}</div>`;
			document.getElementById("prodText").innerHTML = `<span class="prodTitle">餐券詳情</span>
                <br />
                <ul>
                  <li>${prod.prodText}</li>
                </ul>`;
			document.getElementById("prodUserguide").innerHTML = `
			<span class="prodTitle">如何使用</span>
			                <br />
			                <ul>
			                 <li>${prod.prodUserguide}</li>
			                </ul>`;
			document.getElementById("star_score").innerHTML = `
			${prod.prodCommentScore} <span style="font-size: 18px;">/ 5</span>
			`;

			if (orderDetails.length === 0) {
				document.getElementById("comment").innerHTML = `<h2 style="margin-top:30px; text-align: center;">尚未有任何評論。</h2>`;
			} else {
				for (let orderDetail of orderDetails) {
					console.log(orderDetail);
					const star = Math.floor(orderDetail.prodCommentScore);
					let starHtml = '';
					for (let i = 1; i <= 5; i++) {
						if (i <= star) {
							starHtml += `<span class="star -on" data-star="${i}" style="padding-right:3px;"><i class="fas fa-star"></i></span>`;
						} else {
							starHtml += `<span class="star" data-star="${i}" style="padding-right:3px;"><i class="fas fa-star"></i></span>`;
						}
					}
					if (orderDetail.prodCommentScore !== 0) {
						document.getElementById("comment").innerHTML += `
        <div class="acc_profiles">
          <div class="acc_photo" style="background-image: url('./chooeat/images/header/logo2.png');"></div>
          <div class="nameandStar">
            <div class="acc_name">${orderDetail.accName}</div>
            <div class="dot3">
              <input type="submit" id="more_button" class="more" value="" data-order-detail-id="${orderDetail.orderDetailId}"
                style="background-image: url(./chooeat/images/mall_image/more.png);">
            </div>
            <br />
            <div class="commemt_star_block">
              ${starHtml}
            </div>
          </div>
        </div>
        <div class="comment_area">
          <div>${formatTimestamp(orderDetail.prodCommentTimestamp)} 單人｜平日晚餐</div>
          <div class="comment_text">
            ${orderDetail.prodCommentText}
          </div>
        </div>
        <hr class="comment_hr" style="border: 1.3px solid; margin-bottom:15px;" />
      `;
					}
				}
			}


			document.getElementById("price").innerHTML = ` NT $${price}`;
			googleMap(address);
			bindEventsToElements();
			prodStar();
		})
		.catch(error => {
			console.error("無法獲取商品詳細資料:", error);
		});
}

// =====================從URL中獲取商品ID的函數=====================
function getProductIdFromURL() {
	var urlParams = new URLSearchParams(window.location.search);
	return urlParams.get("id");
}
//=========================================================
function googleMap(address) {
	const geocoder = new google.maps.Geocoder();
	geocoder.geocode({ address: address }, (results, status) => {
		if (status === 'OK' && results[0]) {
			const map = new google.maps.Map(document.getElementById('map'), {
				center: results[0].geometry.location,
				zoom: 14
			});

			new google.maps.Marker({
				map: map,
				position: results[0].geometry.location
			});
		} else {
			console.error('無法解析地址:', status);
		}
	});
}
// ================================================================
function bindEventsToElements() {
	// === shopping cart ===
	btn_add_cart_el = document.getElementById("add_cart");
	if (btn_add_cart_el) {
		btn_add_cart_el.addEventListener("click", function() {
			var productId = new URLSearchParams(window.location.search).get("id");
			var cart_data = {};

			if (sessionStorage.getItem("loginReq")) {
				cart_data.accId = accId;
			} else {
				cart_data.accId = "0";
			}

			cart_data.productId = productId;
			cart_data.resName = resName;
			cart_data.accId = accId;
			cart_data.productName = productName;
			cart_data.price = price;
			cart_data.qty = 1;
			cart_data.prodPic = prodPic;
			console.log(cart_data);

			sessionStorage.setItem("form_data", JSON.stringify(cart_data));
			fetchController.abort();
			location.href = "mall_add_cart.html";
		});
	}
	// ==========
	const isDirectBuy = true;
	var selectedProducts = new Array();
	selectedProducts.push(productId);
	var btn_pay_el = document.getElementById("pay_immediately");
	btn_pay_el.addEventListener("click", function() {



		if (sessionStorage.getItem("loginReq") == null) {
			alert("請先進行登入");
			return;
		} else {

			console.log("T");
			console.log(selectedProducts);
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
	// === more_btn ===
	const confirmationBox = $(".confirmation-box");
	const overlay = $(".confirmation-overlay");
	
	$(document).on("click", ".more", function(){
		const moreBtn = $(this);
		const orderDetailId = moreBtn.attr("data-order-detail-id");
		console.log(orderDetailId);
		if (moreBtn.hasClass("reported")) {
			overlay.css("display", "flex");
			confirmationBox.css({
				width: "300px",
				height: "180px"
			});
			confirmationBox.html("<h4 class='centered-text'>您已檢舉過這則評論。</h4>");
			return;
		}
		console.log("檢舉按鈕");
		confirmationBox.css({
			height: "350px",
			width: "350px"
		});

		confirmationBox.html(
			`<h5 style="font-weight: bold;">檢舉評論</h5>
				<div style="margin:5px;">檢舉這則評論的原因？</div>
				<textarea placeholder="請輸入檢舉原因..."></textarea>
				<div>
					<button class="confirm-btn">完成</button>
					<button class="cancel-btn">取消</button>
				</div>
				`);
		overlay.css("display", "flex");

		const confirmBtn = confirmationBox.find(".confirm-btn");
		confirmBtn.on("click", function() {
			const reason = confirmationBox.find("textarea").val();
			if (reason.trim() === "") {
				alert("請輸入檢舉原因");
				return;
			}
			console.log(reason);
			confirmationBox.css({
				width: "300px",
				height: "180px"
			});
			confirmationBox.html("<h4 class='centered-text'>檢舉成功！</h4>");
			moreBtn.addClass("reported");
			const url = "prod/details";
			fetch(url, {
				method: "POST",
				body: JSON.stringify({ reason: reason, orderDetailId: orderDetailId })
			})
				.then(function(response) { return response.json(); })
				.then((data) => {
					console.log(data);
				})
				.catch((error) => {
					console.log("哀哀哀：" + error);
				});

		})
		const cancelBtn = confirmationBox.find(".cancel-btn");
		cancelBtn.on("click", function() {
			overlay.css("display", "none");
		});
	});

	$(".confirmation-overlay").on("click", function(event) {
		if ($(event.target).is(".confirmation-overlay")) {
			$(this).css("display", "none");
		}
	});

	$(".confirmationBox").on("click", function(event) {
		event.stopPropagation();
	
	})
	
	
	
//	$(".more").on("click", function() {
//		const moreBtn = $(this);
//		const orderDetailId = moreBtn.attr("data-order-detail-id");
//		console.log(orderDetailId);
//		if (moreBtn.hasClass("reported")) {
//			overlay.css("display", "flex");
//			confirmationBox.css({
//				width: "300px",
//				height: "180px"
//			});
//			confirmationBox.html("<h4 class='centered-text'>您已檢舉過這則評論。</h4>");
//			return;
//		}
//		console.log("檢舉按鈕");
//		confirmationBox.css({
//			height: "350px",
//			width: "350px"
//		});
//
//		confirmationBox.html(
//			`<h5 style="font-weight: bold;">檢舉評論</h5>
//				<div style="margin:5px;">檢舉這則評論的原因？</div>
//				<textarea placeholder="請輸入檢舉原因..."></textarea>
//				<div>
//					<button class="confirm-btn">完成</button>
//					<button class="cancel-btn">取消</button>
//				</div>
//				`);
//		overlay.css("display", "flex");
//
//		const confirmBtn = confirmationBox.find(".confirm-btn");
//		confirmBtn.on("click", function() {
//			const reason = confirmationBox.find("textarea").val();
//			if (reason.trim() === "") {
//				alert("請輸入檢舉原因");
//				return;
//			}
//			console.log(reason);
//			confirmationBox.css({
//				width: "300px",
//				height: "180px"
//			});
//			confirmationBox.html("<h4 class='centered-text'>檢舉成功！</h4>");
//			moreBtn.addClass("reported");
//			const url = "prod/details";
//			fetch(url, {
//				method: "POST",
//				body: JSON.stringify({ reason: reason, orderDetailId: orderDetailId })
//			})
//				.then(function(response) { return response.json(); })
//				.then((data) => {
//					console.log(data);
//				})
//				.catch((error) => {
//					console.log("哀哀哀：" + error);
//				});
//
//		})
//		const cancelBtn = confirmationBox.find(".cancel-btn");
//		cancelBtn.on("click", function() {
//			overlay.css("display", "none");
//		});
//	});
//
//	$(".confirmation-overlay").on("click", function(event) {
//		if ($(event.target).is(".confirmation-overlay")) {
//			$(this).css("display", "none");
//		}
//	});
//
//	$(".confirmationBox").on("click", function(event) {
//		event.stopPropagation();
//	});
	//=====================搜尋欄位可以按enter=========================
	$("#search_text").on("keyup", function(e) {
		if (e.which == 13) {
			$("#search_button").click();
		}
	});
	//=====================搜尋按鈕點擊事件============================
	document.getElementById('search_button').addEventListener('click', function(event) {
		const url = "Prod/getall?search=" + search_text + "&action=getByCompositeQuery";
		fetch(url)
			.then(function(response) {
				return response.text();
			})
			.then(function(data) {
				window.location.href = "mall.html";
			})
			.catch(function(error) {
			});
	})
};
// ==========================================
function prodStar() {
	$("span.star_block").children("span.star").each(function(index, item) {
		if (parseInt($(item).attr("data-star")) <= star) {
			$(this).addClass("-on");
		} else {
			$(this).removeClass("-on");
		}
	});
}
// ===================== 評論星等 =====================
var buttons = document.getElementsByClassName("star_filter_all");
for (var i = 0; i < buttons.length; i++) {
	buttons[i].addEventListener("click", filterComments(rating));
}
function filterComments(rating) {
	var starRating = rating;
	function convertToChineseNumber(number) {
		const chineseNumbers = ["零", "一", "二", "三", "四", "五", "六", "七", "八", "九"];
		if (number >= 0 && number <= 9) {
			return chineseNumbers[number];
		} else {
			return String(number);
		}
	} let chineseRating = convertToChineseNumber(starRating);
	const url = "prod/commentSort?sort=" + starRating + "&id=" + productId;
	const requestOptions = {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({})
	};
	fetch(url, requestOptions)
		.then(function(response) {
			return response.json();
		})
		.then(orderDetails => {
			if (orderDetails.length === 0) {
				document.getElementById("comment").innerHTML = `<h2 style="margin-top:30px; text-align: center;">尚未有任何${chineseRating}星評論。</h2>`;
			} else {
				document.getElementById("comment").innerHTML = "";
				for (let orderDetail of orderDetails) {
					console.log(orderDetail);

					const star = Math.floor(orderDetail.prodCommentScore);
					let starHtml = '';
					for (let i = 1; i <= 5; i++) {
						if (i <= star) {
							starHtml += `<span class="star -on" data-star="${i}" style="padding-right:3px;"><i class="fas fa-star"></i></span>`;
						} else {
							starHtml += `<span class="star" data-star="${i}" style="padding-right:3px;"><i class="fas fa-star"></i></span>`;
						}
					}
					if (orderDetail.prodCommentScore !== 0) {
						document.getElementById("comment").innerHTML += `
					        <div class="acc_profiles">
					          <div class="acc_photo" style="background-image: url('./chooeat/images/header/logo2.png');"></div>
					          <div class="nameandStar">
					            <div class="acc_name">${orderDetail.accName}</div>
					            <div class="dot3">
					              <input type="submit" id="more_button" class="more" value="" data-order-detail-id="${orderDetail.orderDetailId}"
					                style="background-image: url(./chooeat/images/mall_image/more.png);">
					            </div>
					            <br />
					            <div class="commemt_star_block">
					              ${starHtml}
					            </div>
					          </div>
					        </div>
					        <div class="comment_area">
					          <div>${formatTimestamp(orderDetail.prodCommentTimestamp)} 單人｜平日晚餐</div>
					          <div class="comment_text">
					            ${orderDetail.prodCommentText}
					          </div>
					        </div>
					        <hr class="comment_hr" style="border: 1.3px solid; margin-bottom:15px;" />
					      `;
					}
				}
			}
		})
		.catch(error => {
			console.error('發生錯誤:', error);
		});
	console.log("篩選評論：" + starRating);
	const confirmationBox = $(".confirmation-box");
	const overlay = $(".confirmation-overlay");
	$(".more").on("click", function() {
		const moreBtn = $(this);
		const orderDetailId = moreBtn.attr("data-order-detail-id");
		console.log(orderDetailId);
		if (moreBtn.hasClass("reported")) {
			overlay.css("display", "flex");
			confirmationBox.css({
				width: "300px",
				height: "180px"
			});
			confirmationBox.html("<h4 class='centered-text'>您已檢舉過這則評論。</h4>");
			return;
		}
		console.log("檢舉按鈕");
		confirmationBox.css({
			height: "350px",
			width: "350px"
		});

		confirmationBox.html(
			`<h5 style="font-weight: bold;">檢舉評論</h5>
				<div style="margin:5px;">檢舉這則評論的原因？</div>
				<textarea placeholder="請輸入檢舉原因..."></textarea>
				<div>
					<button class="confirm-btn">完成</button>
					<button class="cancel-btn">取消</button>
				</div>
				`);
		overlay.css("display", "flex");

		const confirmBtn = confirmationBox.find(".confirm-btn");
		confirmBtn.on("click", function() {
			const reason = confirmationBox.find("textarea").val();
			if (reason.trim() === "") {
				alert("請輸入檢舉原因");
				return;
			}
			console.log(reason);
			confirmationBox.css({
				width: "300px",
				height: "180px"
			});
			confirmationBox.html("<h4 class='centered-text'>檢舉成功！</h4>");
			moreBtn.addClass("reported");
			const url = "prod/details";
			fetch(url, {
				method: "POST",
				body: JSON.stringify({ reason: reason, orderDetailId: orderDetailId })
			})
				.then(function(response) { return response.json(); })
				.then((data) => {
					console.log(data);
				})
				.catch((error) => {
					console.log("哀哀哀：" + error);
				});

		})
		const cancelBtn = confirmationBox.find(".cancel-btn");
		cancelBtn.on("click", function() {
			overlay.css("display", "none");
		});
	});

	$(".confirmation-overlay").on("click", function(event) {
		if ($(event.target).is(".confirmation-overlay")) {
			$(this).css("display", "none");
		}
	});

	$(".confirmationBox").on("click", function(event) {
		event.stopPropagation();
	});
}