// 日期格式化 YYYY-MM-DD
function YYYYMMDD(dateTime) {
  let date = new Date(dateTime);
  // 獲取各個時間元素
  let year = date.getFullYear();
  let month = ("0" + (date.getMonth() + 1)).slice(-2); // 月份需要補零
  let day = ("0" + date.getDate()).slice(-2); // 日期需要補零
  let hours = ("0" + date.getHours()).slice(-2); // 小時需要補零
  let minutes = ("0" + date.getMinutes()).slice(-2); // 分鐘需要補零
  let seconds = ("0" + date.getSeconds()).slice(-2); // 秒數需要補零

  // 拼接成 YYYY-MM-DDTHH:MM:SS 格式
  let formattedDateTime = `${year}-${month}-${day}`;
  return formattedDateTime;
}

// ======= 搜尋  =========
function search() {
  document.querySelector("button.submit").addEventListener("click", (e) => {
    // 阻止表單提交
    e.preventDefault();
    let val = document.querySelector(".search_value").value.trim();
    let search_result = document.querySelector(".search_result");
    search_result.innerHTML = "搜尋結果 ：" + val;
    let card_list = document.querySelector(".card_list");

    let url = "search?search_value=" + val;
    // ======= 用fetch 發送請求  =========
    fetch(url)
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        // console.log(data);
        // 在開始顯示資料前隱藏loading
        hideLoading();
        // 重置列表的內容
        card_list.innerHTML = "";
        for (let reser of data) {
          cardList(reser);
        }
      });
    sessionStorage.setItem("search_value", val);
    like();
    getlikes();
    signup();
  });
}

// 寫入搜尋列表
function cardList(data) {
  let card_list = document.querySelector(".card_list");
  // console.log(reser);
  let activityDate = data.activityDate.split(" ");
  // console.log(activityDate);
  let month = activityDate[0];
  let date = activityDate[1].split(",")[0];
  let year = activityDate[2];
  let base64Photo = data.activityPhotoBase64;
  let image = new Image();
  image.src = `data:image/*;base64,${base64Photo}`;

  card_list.innerHTML += `
      <div class="col-4 mb-5">
      <div class="card">
        <img
          src="${image.src}"
          class="card-img-top"
          alt="..."
        />
        <div class="card-body" data-activityId=${data.activityId}>
          <h5 class="card-title">${data.activityName}</h5>
          <p class="restaurant-name">地點：${
            data.activityrestaurantVO.resName
          }</p>
          <p class="card-text address">地址：
            ${data.activityrestaurantVO.resAdd}
          </p>
          <p class="card-text regesteration">報名時間：<span class="regesteration_starting_time">${YYYYMMDD(
            data.regesterationStartingTime
          )} </span>00:00 ~ <span class="regesteration_ending_time">${YYYYMMDD(
    data.regesterationEndingTime
  )}</span> 23:59 止
          </p>
          <p class="card-text date_time">活動時間：${year}年${month}${date}日 ${
    data.activityStartingTime.slice(9) +
    " " +
    data.activityStartingTime.slice(0, 5)
  }</p>
          <p class="card-text expected">
           預計參加人數：${data.minNumber}-${data.maxNumber}人
          </p>
          <p class="total">${data.activityNumber}人已報名參加</p>
          <div class="btns row align-items-center">
            <div class="col-10">
              <a
                href="./activity_detail.html"
                class="btn btn-outline-warning signup"
                >立刻報名</a
              >
            </div>
            <div class="col-2">
              <svg class="like" data-like="false" xmlns="http://www.w3.org/2000/svg" width="25" height="25" viewBox="0 0 24 24" fill="none">
                <path d="M12 21.35L10.55 20.03C5.4 15.36 2 12.27 2 8.5C2 5.41 4.42 3 7.5 3C9.24 3 10.91 3.81 12 5.08C13.09 3.81 14.76 3 16.5 3C19.58 3 22 5.41 22 8.5C22 12.27 18.6 15.36 13.45 20.03L12 21.35Z"  stroke="red" stroke-width="2" />
              </svg>
            </div>
          </div>
        </div>
      </div>
    </div>
        `;
}

// 接收前一頁帶來的搜尋詞，並進行搜尋呈現結果
function firstSearch() {
  let card_list = document.querySelector(".card_list");
  let search_value = sessionStorage.getItem("search_value");
  let search_result = document.querySelector(".search_result");
  search_result.innerHTML = "搜尋結果 ：" + search_value;
  let url = "search?search_value=" + search_value;
  // ======= 用fetch 發送請求  =========
  fetch(url)
    .then((res) => {
      // 在接收到回應後隱藏loading示意
      hideLoading();
      // 重置列表的內容
      card_list.innerHTML = "";
      return res.json();
    })
    .then((data) => {
      for (let reser of data) {
        cardList(reser);
      }
      like();
      getlikes();
      signup();
    });
}

// ======== 活動收藏相關函式要放在fetch資料後的函式下，這樣才抓的到資料 ========
// like按鈕點擊事件
function like() {
  // 取得like按鈕的內層path標籤
  let likebtn = $("svg.like");

  likebtn.click(function (e) {
    // 判斷會員登入
    if (sessionStorage.getItem("loginReq") == null) {
      alert("請先登入");
      return;
    }
    // 解析會員資訊
    let account = JSON.parse(sessionStorage.getItem("loginReq"));
    let accId = account.acc_id;
    let activityId = $(e.target).closest(".card-body").attr("data-activityId");

    // 判斷是否已登入
    if (sessionStorage.getItem("loginReq") == null) {
      alert("請先進行登入");
      return;
    }

    if ($(e.target).attr("data-like") == "false") {
      // 將收藏的資訊發送給後端
      let likeURL = "like";
      fetch(likeURL, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          accId: accId,
          activityId: activityId,
        }),
      }).then((res) => {
        console.log(res);
      });
      //更改顏色與data-like屬性
      $(e.target).attr("data-like", "true");
      $(e.target).css("fill", "#FF0000");
    } else if ($(e.target).attr("data-like") == "true") {
      // 將取消收藏的資訊發送給後端
      let jsonobj = { accId: accId, activityId: activityId };
      console.log(jsonobj);
      fetch(`dislike`, {
        method: "DELETE",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(jsonobj),
      }).then((res) => {
        console.log(res);
      });
      //更改顏色與data-like屬性(目前換色只能一次..)
      $(e.target).attr("data-like", "false");
      $(e.target).css("fill", "none");
    }
  });
}

// 取得收藏活動
function getlikes() {
  // 判斷會員是否已登入
  if (JSON.parse(sessionStorage.getItem("loginReq")) == null) {
    // console.log("請先會員登入");
    return;
  }

  // 解析會員資訊
  let account = JSON.parse(sessionStorage.getItem("loginReq"));
  let accId = account.acc_id;

  if (accId != null) {
    // 取得該會員已收藏的活動，若已收藏，愛心就會是實心
    // let accId = sessionStorage.getItem("accId");
    let getLikeURL = "getlike?accId=" + accId;
    // 準備好array，用來接活動id
    let activityId_arr = [];
    fetch(getLikeURL)
      .then((res) => {
        return res.json();
      })
      .then((resJSON) => {
        // 將該會員收藏的活動id放進activityId_arr中
        for (let i = 0; i < resJSON.length; i++) {
          activityId_arr.push(resJSON[i].activityId);
        }
        // console.log(activityId_arr);

        // 跑迴圈，判斷活動編號是否符合
        let cardBodys = document.querySelectorAll("div.card-body");
        cardBodys.forEach((ele) => {
          for (let i = 0; i < activityId_arr.length; i++) {
            if (ele.dataset.activityid == activityId_arr[i]) {
              //更改顏色與data-like屬性
              $(ele).find("svg.like").attr("data-like", "true");
              $(ele).find("svg.like").css("fill", "#FF0000");
            }
          }
        });
      });
  }
}

// 點擊「立刻報名」按鈕
function signup() {
  //點擊報名按鈕，會將活動id存入session，然後傳給activity/detail畫面
  let signup_btn = $("a.signup");
  signup_btn.click((e) => {
    e.preventDefault();
    // 判斷是否登入，未登入就不給報名
    if (sessionStorage.getItem("loginReq") == null) {
      alert("請先登入");
      return;
    }

    let activityid = $(e.target)
      .closest("div.card-body")
      .attr("data-activityid");
    // 將值存在session中，傳給下個頁面
    sessionStorage.setItem("activityId", activityid);
    //進行重導向
    document.location.href = "activity_detail.html";
  });
}

// ========= loading =========
function showLoading() {
  document.getElementById("loadingContainer").style.display = "block";
}

function hideLoading() {
  document.getElementById("loadingContainer").style.display = "none";
}

$(function () {
  // 進行第一次的搜尋
  firstSearch();
  // 接收後端 search資訊
  search();

  showLoading();
});
