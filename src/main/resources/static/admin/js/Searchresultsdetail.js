var result = JSON.parse(sessionStorage.getItem("searchResult")); // 將字串轉換為物件
var resTypeNames = result.restype.map(function (item) {
  return item.resTypeName;
});

var resName = result["myself"][0].resName;
var resAdd = result["myself"][0].resAdd;
var resStartTime = result["myself"][0].resStartTime;
var resEndTime = result["myself"][0].resEndTime;
var resTotalScore = result["myself"][0].resTotalScore;
var resIntro = result["myself"][0].resIntro;

// 餐廳詳細資料
var newDiv = document.createElement("div");
newDiv.innerHTML = `
  <div>
  <h1>
	${resName}<span style="float: right"><i class="far fa-bookmark"></i></span>
  </h1>
  ${resTotalScore}
  <i class="fa fa-star" style="color: yellow"></i>
  <p>${resTypeNames}</p>
  <p>${resAdd}</p>
  <p>營業時間${resStartTime}-${resEndTime}</p>
  <div>    
	<h1>餐廳簡介</h1>
	<p>
	${resIntro}
	</p>
  </div>
  </div>
  `;
var parentElement = document.getElementById("parentElement");
parentElement.appendChild(newDiv);

//廣告
for (let i = 0; i < result["comment"].length; i++) {

  result["account"][i].accName;
  result["comment"][i].restaurantCommentDatetime;
  result["comment"][i].restaurantCommentReplyDatetime;
  result["comment"][i].restaurantCommentReplyText;
  result["comment"][i].restaurantCommentScore;
  result["comment"][i].restaurantCommentText;

  var ccc = document.createElement("div");
  ccc.innerHTML = `   
   <div class="row">
  <div class="col-sm-2">
  <img decoding="async" src="./images/餐廳圖片測試.jpg" height="50px"/>
  </div>
  <div class="col-sm-10">
  <p>用戶 : ${result["account"][i].accName}</p>
  <p>評價 : ${result["comment"][i].restaurantCommentScore}<i class="fa fa-star"style="color: yellow"></i></p>
  <p>日期 : ${result["comment"][i].restaurantCommentDatetime}</p>
  <p>留言 : ${result["comment"][i].restaurantCommentText}</p>
  <p style="text-align: right">餐廳回復日期 : ${result["comment"][i].restaurantCommentReplyDatetime}</p>
  <p style="text-align: right">餐廳留言 : ${result["comment"][i].restaurantCommentReplyText}</p>
  <hr />
  </div>	
  </div>
  </div>
   `;

  var aaaaa = document.getElementById("abcde");
  aaaaa.appendChild(ccc);
}

for (let i = 0; i < result.prod.length; i++) {
  var prodName = result.prod[i].prodName;
  var prodPrice = result.prod[i].prodPrice;

  var ccc = document.createElement("li");
  ccc.innerHTML = ` 
   ${prodName} NT$${prodPrice}</li>
	
	`;

  var tt = document.getElementById("zzz");
  tt.appendChild(ccc);
}

// GOOGLE 地圖 API 相關功能
function initMap() {
  // 在網頁上創建一個地圖
  var map = new google.maps.Map(document.getElementById("map"), {
	center: { lat: 25.033964, lng: 121.564472 }, // 預設中心點經緯度
	zoom: 12, // 預設縮放級別
  });

  var address = resAdd;

  if (address) {
	// 建立新的地圖標記
	var geocoder = new google.maps.Geocoder();
	geocoder.geocode({ address: address }, function (results, status) {
	  if (status === "OK") {
		// 移動地圖至標記位置
		map.setCenter(results[0].geometry.location);
		var marker = new google.maps.Marker({
		  map: map,
		  position: results[0].geometry.location,
		});
	  } else {
		console.log("地理編碼失敗，原因：" + status);
	  }
	});
  }
}