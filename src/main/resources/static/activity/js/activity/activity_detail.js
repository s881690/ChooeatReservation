// 地圖
function initMap() {
  const uluru = { lat: -25.344, lng: 131.031 };
  const map = new google.maps.Map(document.getElementById("map"), {
    zoom: 14,
    center: uluru,
  });
  const marker = new google.maps.Marker({
    position: uluru,
    map: map,
  });
}

// google地圖地址動態載入，放在fetch之後
function googleMap(address) {
  const geocoder = new google.maps.Geocoder();
  geocoder.geocode({ address: address }, (results, status) => {
    if (status === "OK" && results[0]) {
      const map = new google.maps.Map(document.getElementById("map"), {
        center: results[0].geometry.location,
        zoom: 14,
      });

      new google.maps.Marker({
        map: map,
        position: results[0].geometry.location,
      });
    } else {
      console.error("無法解析地址:", status);
    }
  });
}

// 接收活動id，顯示該活動
function showDetail() {
  // 若存在會員資訊，就解析
  if (sessionStorage.getItem("loginReq") != null) {
    let account = JSON.parse(sessionStorage.getItem("loginReq"));
    let accId = account.acc_id;
  }

  let activityId = sessionStorage.getItem("activityId");
  //活動圖片區塊
  let img = document.querySelector("div.activity_img");
  // 活動標題區塊
  let title = document.querySelector("h1.activity_title");
  // 活動內容區塊
  let activity_info = document.querySelector("div.activity_info");
  // 報名區塊
  let outter_signup = document.querySelector("div.outter_signup");
  // 活動舉辦人區塊
  let activity_host = document.querySelector("div.activity_host");
  let url = `detail/${activityId}`;
  fetch(url)
    .then((res) => {
      return res.json();
    })
    .then((result) => {
      console.log(result.activityDate);
      let activityDate = result.activityDate.split(" ");
      // console.log(activityDate);
      let month = activityDate[0];
      let date = activityDate[1].split(",")[0];
      let year = activityDate[2];
      let address = result.activityrestaurantVO.resAdd;
      let base64Photo = result.activityPhotoBase64;
      let image = new Image();
      image.src = `data:image/*;base64,${base64Photo}`;
      img.innerHTML = `
        <img
          src="${image.src}"
          alt="活動上傳照片"
          class="activity_img img_fluid"
        />
      `;
      title.innerHTML = `${result.activityName}`;
      activity_info.innerHTML = `
      
      <div class="location mb-5">
      <h3 class="mb-5 fw-bolder" style="padding-left:32%">活動資訊</h3>
        <p>餐廳名稱：${result.activityrestaurantVO.resName}</p>
        <p>地點：${address}</p>
        <p>${year}年${month}月${date}日 ${result.activityStartingTime.slice(
        9
      )} ${result.activityStartingTime.slice(0, 5)}</p>
      <p>${result.activityNumber}人已報名參加</p>
      </div>

      <!-- 活動簡介 -->
      <div class="activity_introtext mb-5">
        <!-- 帶後端資料 -->
        <h3 class="mb-5 text-center fw-bolder">活動簡介</h3>
          ${result.activityText}
      </div>
      `;

      activity_host.innerHTML = `
      <!-- 認識主辦人 -->
      <div class="activity_host row align-items-center">
        <div class="col">
          <h4 class="fw-bolder">認識主辦人</h4>
        </div>
        <div class="col">
          <img
            src="https://picsum.photos/250/250"
            alt="大頭照"
            class="border rounded-circle img-fluid"
          />
        </div>
      </div>
      `;
      signup.innerHTML = googleMap(address);
      isactivityHost(result.accId);
    });
}

// 搜尋
function search() {
  const search_btn = document.querySelector("button.submit");
  search_btn.addEventListener("click", (e) => {
    e.preventDefault();
    // 取得搜尋的值
    search_value = $("input.search_value").val().trim();
    // 將值存在session中，傳給下個頁面
    sessionStorage.setItem("search_value", search_value);
    //進行重導向
    document.location.href = "activity_search.html";
  });
}

//活動報名(彈窗)
function innerSignup() {
  let confirm = document.querySelector("button.confirm");
  let activityId = sessionStorage.getItem("activityId");

  confirm.addEventListener("click", (e) => {
    e.preventDefault();
    //檢查是否登入
    if (JSON.parse(sessionStorage.getItem("loginReq")) == null) {
      alert("請先登入!");
      return;
    }
    let account = JSON.parse(sessionStorage.getItem("loginReq"));
    let accId = account.acc_id;
    // 檢查是否已經報名ㄌ(用{accId,activityId}去找是否存有這筆資料)
    fetch(`isSignup?accId=${accId}&activityId=${activityId}`)
      .then((res) => {
        return res.json();
      })
      .then((result) => {
        // 阻止重複報名
        if (result) {
          alert("已報名活動囉~");
          return;
        }

        // 確認尚未報名，將資料送往後端
        let groupNote = $("textarea#toHost").val().trim();
        // console.log(groupNote);
        fetch("signup", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            accId: accId,
            activityId: activityId,
            groupNote: groupNote,
          }),
        })
          .then((res) => {
            return res.json();
          })
          .then((result) => {
            if (result) {
              console.log(result);
              alert("報名成功");
            } else {
              console.log(result);
            }
          });
      });
  });
}

// 判斷是否為活動建立者，是的話報名按鈕就要變成編輯鈕，並多一個審核頁按鈕
function isactivityHost(result_accId) {
  // 判斷是否有會員資訊
  if (sessionStorage.getItem("loginReq") == null) {
    return;
  }
  let accId = JSON.parse(sessionStorage.getItem("loginReq")).acc_id;

  if (accId == result_accId) {
    // 刪除彈窗
    $("div#signup").remove();
    // 更改文字
    $("div.signup_and_edit").html("編輯");
    // 進入編輯畫面
    $("div.signup_and_edit").click((e) => {
      e.preventDefault();
      //進行重導向
      document.location.href = "activity_edit.html";
    });

    // 增加「審核參加人員」按鈕
    let organizer_btn = `<div class="btn btn-outline-dark pe-2 col organizer">審核成員</div>`;
    document.querySelector("div.other_btns").innerHTML += organizer_btn;
    $("div.organizer").click((e) => {
      //進行重導向
      document.location.href = "activity_organizer.html";
    });
  }
}

$(function () {
  showDetail();
  innerSignup();
  search();
});
