// $("header.headerpage").load("../../header.html .navbar");
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

// 接收list資訊
function fetch3ActivityList() {
  const card_list_3 = document.querySelector(".card_list_3");
  const card_list_collapse = document.querySelector(".card_list_collapse");
  // 因Java有將靜態檔案設定映射名稱與前綴字，因此不用加"activity/"
  const url = "activitys";
  fetch(url)
    .then((res) => {
      return res.json();
    })
    .then((resList) => {
      console.log(resList);
      let resList3 = resList.slice(0, 3);
      // console.log(resList3);
      let resListCollapse = resList.slice(3, 9);

      // 在開始顯示資料前隱藏loading
      hideLoading();
      // 重置列表的內容
      card_list_3.innerHTML = "";

      //塞進前三個
      for (let reser of resList3) {
        // console.log(reser);
        let activityDate = reser.activityDate.split(" ");
        // console.log(activityDate);
        let month = activityDate[0];
        let date = activityDate[1].split(",")[0];
        let year = activityDate[2];
        let base64Photo = reser.activityPhotoBase64;
        let image = new Image();
        image.src = `data:image/*;base64,${base64Photo}`;
        card_list_3.innerHTML += `
        <div class="col-4 mt-5">
          <div class="card">
            <img src="${image.src}" class="card-img-top" alt="..." />
            <div class="card-body" data-activityid=${reser.activityId}>
              <h5 class="card-title">${reser.activityName}</h5>
              <p class="restaurant-name">地點：${
                reser.activityrestaurantVO.resName
              }</p>
              <p class="card-text address">地址：
                ${reser.activityrestaurantVO.resAdd}
              </p>
              <p class="card-text regesteration">報名時間：<span class="regesteration_starting_time">${YYYYMMDD(
                reser.regesterationStartingTime
              )} </span>00:00 ~ <span class="regesteration_ending_time">${YYYYMMDD(
          reser.regesterationEndingTime
        )}</span> 23:59 止
              </p>
              <p class="card-text date_time">活動時間：${year}年${month}${date}日 ${
          reser.activityStartingTime.slice(9) +
          " " +
          reser.activityStartingTime.slice(0, 5)
        }</p>
              <p class="card-text expected">預計參加人數：${reser.minNumber}-${
          reser.maxNumber
        }人</p>
              <p class="total">
              ${reser.activityNumber}人已報名參加
              </p>
              <div class="btns row align-items-center">
                <div class="col-10">
                  <a href="./activity_detail.html" class="btn btn-outline-warning signup">立刻報名</a>
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

      // 塞進摺疊區塊內
      for (let reser of resListCollapse) {
        // console.log(reser);
        let activityDate = reser.activityDate.split(" ");
        // console.log(activityDate);
        let month = activityDate[0];
        let date = activityDate[1].split(",")[0];
        let year = activityDate[2];
        let base64Photo = reser.activityPhotoBase64;
        let image = new Image();
        image.src = `data:image/jpeg;base64,${base64Photo}`;
        card_list_collapse.innerHTML += `
            <div class="col-4 mt-5">
            <div class="card">
              <img
              src="${image.src}"
                class="card-img-top"
                alt="..."
              />
              <div class="card-body" data-activityid=${reser.activityId}>
                <h5 class="card-title">${reser.activityName}</h5>
                <p class="restaurant-name">地點：${
                  reser.activityrestaurantVO.resName
                }</p>
                <p class="card-text address">地址：
                  ${reser.activityrestaurantVO.resAdd}
                </p>
                <p class="card-text regesteration">報名時間：<span class="regesteration_starting_time">${YYYYMMDD(
                  reser.regesterationStartingTime
                )} </span>00:00 ~ <span class="regesteration_ending_time">${YYYYMMDD(
          reser.regesterationEndingTime
        )}</span> 23:59 止
                </p>
                <p class="card-text date_time">活動時間：${year}年${month}${date}日 ${
          reser.activityStartingTime.slice(9) +
          " " +
          reser.activityStartingTime.slice(0, 5)
        }</p>
                <p class="card-text expected">
                    預計參加人數：${reser.minNumber}-${reser.maxNumber}人
                    </p>
                <p class="total">
                  ${reser.activityNumber}人已報名參加
                  </p>
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
                      <path d="M12 21.35L10.55 20.03C5.4 15.36 2 12.27 2 8.5C2 5.41 4.42 3 7.5 3C9.24 3 10.91 3.81 12 5.08C13.09 3.81 14.76 3 16.5 3C19.58 3 22 5.41 22 8.5C22 12.27 18.6 15.36 13.45 20.03L12 21.35Z" stroke="red" stroke-width="2" />
                    </svg>
                  </div>
                </div>
              </div>
            </div>
          </div>
              `;
      }

      like();
      // 依照session的會員id取得收藏活動
      getlikes();

      signup();
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

// 查看更多按鈕
function viewmore() {
  let viewmore = document.querySelector("a.viewmore");
  viewmore.addEventListener("click", (e) => {
    if (viewmore.innerHTML == "查看更多⌄") {
      viewmore.innerHTML = "收起⌃";
    } else if (viewmore.innerHTML == "收起⌃") {
      viewmore.innerHTML = "查看更多⌄";
    }
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
        // console.log(res);
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
  if (sessionStorage.getItem("loginReq") == null) {
    // console.log("請先登入");
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
  let signup_btn = $("a.signup");
  //點擊報名按鈕，會將活動id存入session，然後傳給activity/detail畫面
  signup_btn.click((e) => {
    e.preventDefault();
    // 判斷是否登入，未登入就不給報名
    if (sessionStorage.getItem("loginReq") == null) {
      alert("請先登入");
      return;
    }

    // 判斷報名時間，如果大於今天就不給報名
    // let today = new Date();
    // console.log(today);
    // console.log($(this).closest("p.regesteration"));
    // if (signup_btn.closest("span.regesteration_starting_time").val() > today) {
    //   alert("報名時間還沒到喔!");
    //   return;
    // }
    // if(signup_btn.closest())
    let activityid = $(e.target)
      .closest("div.card-body")
      .attr("data-activityid");
    // console.log(activityid);
    // 將值存在session中，傳給下個頁面
    sessionStorage.setItem("activityId", activityid);
    //進行重導向
    document.location.href = "activity_detail.html";
  });
}

// 點擊「建立活動」按鈕
function establish() {
  $("a.establish").click((e) => {
    e.preventDefault();
    // 判斷是否登入
    if (sessionStorage.getItem("loginReq") == null) {
      alert("請先登入");
      return;
    }

    document.location.href = "activity_establish.html";
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
  // 接收activityList資訊
  fetch3ActivityList();

  //查看更多按鈕
  viewmore();

  //搜尋功能
  search();

  establish();

  showLoading();
});
