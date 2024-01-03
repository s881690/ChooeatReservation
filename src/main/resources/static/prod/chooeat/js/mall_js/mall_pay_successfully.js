
//===========
let account = JSON.parse(sessionStorage.getItem("loginReq"));
if (sessionStorage.getItem("loginReq") != null) {
	document.getElementById("sname").innerHTML = account.acc_name;

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
//	const url = "order";
//	fetch(url)
//		.then(function(response) { return response.json(); })
//		.then(data => {
//			const totalPrice = total.toLocaleString();
//			const productsMap = data;
//			for (let [productId, value] of Object.entries(productsMap)) {
//				const key = productId;
//				const productName = value.productName;
//				const productPrice = value.price.toLocaleString();
//				const productqty = value.qty;
//				const producttotalPrice = (value.price * productqty).toLocaleString();
//				tbodyContainer.innerHTML += `
//									<tr>
//										<td style="width: 120px;"><img src="./chooeat/images/mall_image/mall3.jpg"
//											class="pord_img" /></td>
//										<td><h5>${productName}</h5>
//											<span style="color: gray;">單人｜平日晚餐</span></td>
//										<td>NT $ ${productPrice}</td>
//										<td>${productqty}</td>
//										<td>NT ${producttotalPrice}</td>
//									</tr>
//									<tr>
//										<td colspan="5" style="padding: 0;"><hr></td>
//									</tr>
//						`;
//				tfootContainer.innerHTML = `
//						<tfoot>
//							<tr>
//								<td></td>
//								<td></td>
//								<td></td>
//								<td></td>
//								<td>NT $${totalPrice}</td>
//							</tr>
//						</tfoot>
//								`;
//
//			};
//		})
//		.catch(function(error) {
//			console.log(error);
//		});