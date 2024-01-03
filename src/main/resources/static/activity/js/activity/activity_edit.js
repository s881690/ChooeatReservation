// 取得活動照片區塊
let show_photo = document.querySelector("div.show_photo");
// 取得上傳照片按鈕
let photo_btn = document.querySelector("#activity_photo");
// 取得form表單
let form = document.querySelector("form.activity_establish_form");
// 取得彈窗內的「送出」
let check_submit = document.querySelector("button.check_submit");

// 日期格式化 YYYY-MM-DDTHH:MM:SS
function YYYYMMDDTHHMM(dateTime) {
  let date = new Date(dateTime);
  // 獲取各個時間元素
  let year = date.getFullYear();
  let month = ("0" + (date.getMonth() + 1)).slice(-2); // 月份需要補零
  let day = ("0" + date.getDate()).slice(-2); // 日期需要補零
  let hours = ("0" + date.getHours()).slice(-2); // 小時需要補零
  let minutes = ("0" + date.getMinutes()).slice(-2); // 分鐘需要補零
  let seconds = ("0" + date.getSeconds()).slice(-2); // 秒數需要補零

  // 拼接成 YYYY-MM-DDTHH:MM:SS 格式
  let formattedDateTime = `${year}-${month}-${day}T${hours}:${minutes}:${seconds}`;
  return formattedDateTime;
}

// 編輯畫面，先帶入所有的資訊
function activity_edit() {
  let activityId = sessionStorage.getItem("activityId");
  // console.log(activityId);
  fetch(`findEdit/${activityId}`)
    .then((res) => {
      return res.json();
    })
    .then((result) => {
      console.log(result);

      // 將資訊塞入到欄位中
      $("input#activity_name").val(result.activityName); // 活動名稱

      // 如果option標籤的餐廳名稱與result中的一樣，那就設置為selected(塞不進去)
      let options = document.querySelectorAll("option");
      // console.log(options);
      for (let option of options) {
        // console.log(option.innerText);
        if (option.innerText == result.activityrestaurantVO.resName) {
          option.selected == true;
        }
      }
      console.log(result.activityrestaurantVO.resName);
      let am = result.activityDate.split(" ")[0].replace("月", "");
      let ad = result.activityDate.split(" ")[1].split(",")[0];
      if (am < 10) {
        am = "0" + am;
      }
      if (ad < 10) {
        ad = "0" + ad;
      }
      let activityDate = `${result.activityDate.split(" ")[2]}-${am}-${ad}`;
      $("input#activity_date").val(activityDate); // 活動日期
      // 活動報名開始時間
      $("input#regesteration_starting_time").val(
        YYYYMMDDTHHMM(result.regesterationStartingTime)
      );
      // 活動報名結束時間
      $("input#regesteration_ending_time").val(
        YYYYMMDDTHHMM(result.regesterationEndingTime)
      );
      // 活動開始時間
      $("input#activity_starting_time").val(
        result.activityStartingTime.slice(0, 5)
      );
      // 活動結束時間
      $("input#activity_ending_time").val(
        result.activityEndingTime.slice(0, 5)
      );
      $("input#min_number").val(result.minNumber); //最少參加人數
      $("input#max_number").val(result.maxNumber); //最多參加人數
      $("textarea#activity_text").val(result.activityText); // 聚會活動內容簡介

      // 將圖片byte[] array解碼成base64
      let uint8Array = new Uint8Array(result.activityPhoto);
      let binaryString = String.fromCharCode.apply(null, uint8Array);
      var base64Encoded = btoa(binaryString);
      let image = new Image();
      image.src = `data:image/*;base64,${base64Encoded}`;
      image.className = "preview";
      show_photo.innerHTML = "";
      show_photo.appendChild(image);
    });
}

// 取得 餐廳名稱
function getResName() {
  let restaurant_list = document.querySelector("select#activity_restaruant");
  fetch("restaurantList")
    .then((res) => {
      return res.json();
    })
    .then((resList) => {
      // console.log(resList);
      for (let reser of resList) {
        // console.log(reser.restaurantId);
        restaurant_list.innerHTML += `
        <option value="${reser.restaurantId}">${reser.resName}</option>
      `;
      }
    });
}

// 判斷表單內容相關
function validateInput() {
  // 創建 formdata物件，接收表單的值
  let formData = new FormData();

  // 判斷活動名稱
  $("input#activity_name").blur(function (e) {
    let activityName = $("input#activity_name").val();
    let err_span = $("span#activity_name_error");
    if (activityName == "") {
      err_span.show();
      // $("button.submit").attr("disabled", true);
    } else {
      err_span.hide();
      formData.append("activityName", activityName);
      // $("button.submit").attr("disabled", false);
    }
  });

  // 判斷活動餐廳
  $("select#activity_restaruant").blur(function (e) {
    let restaurantName = $("select#activity_restaruant").val();
    let err_span = $("span#activity_restaruant_error");
    if (restaurantName == "recommand_restaurant") {
      err_span.show();
      // $("button.submit").attr("disabled", true);
    } else {
      err_span.hide();
      // $("button.submit").attr("disabled", false);
    }
  });

  // 判斷活動日期
  $("input#activity_date").on("blur input", function (e) {
    // 將活動申請日期清空
    $("input#regesteration_starting_time").val("");
    $("input#regesteration_ending_time").val("");
    let activityDate = $("input#activity_date").val();

    let err_span = $("span#activity_date_error");
    if (activityDate == "") {
      err_span.show();
      // $("button.submit").attr("disabled", true);
    } else {
      err_span.hide();
      // $("button.submit").attr("disabled", false);
    }
  });

  // 判斷活動報名開始時間
  $("input#regesteration_starting_time").blur(function (e) {
    let regesterationStartingTime = $(
      "input#regesteration_starting_time"
    ).val();
    let err_span = $("span#regesteration_starting_time_error");
    if (regesterationStartingTime == "") {
      err_span.show();
      // $("button.submit").attr("disabled", true);
    } else {
      err_span.hide();
      // $("button.submit").attr("disabled", false);
    }
  });

  // 判斷活動報名截止時間
  $("input#regesteration_ending_time").blur(function (e) {
    let regesterationEndingTime = $("input#regesteration_ending_time").val();
    let err_span = $("span#regesteration_ending_time_error");
    if (regesterationEndingTime == "") {
      err_span.show();
      // $("button.submit").attr("disabled", true);
    } else {
      err_span.hide();
      // $("button.submit").attr("disabled", false);
    }
  });

  // 判斷活動開始時間
  $("input#activity_starting_time").blur(function (e) {
    let activityStartingTime = $("input#activity_starting_time").val();
    let err_span = $("span#activity_starting_time_error");
    if (activityStartingTime == "") {
      err_span.show();
      // $("button.submit").attr("disabled", true);
    } else {
      err_span.hide();
      // $("button.submit").attr("disabled", false);
    }
  });

  // 判斷活動結束時間
  $("input#activity_ending_time").blur(function (e) {
    let activityEndingTime = $("input#activity_ending_time").val();
    let err_span = $("span#activity_ending_time_error");
    if (activityEndingTime == "") {
      err_span.show();
      // $("button.submit").attr("disabled", true);
    } else {
      err_span.hide();
      // $("button.submit").attr("disabled", false);
    }
  });

  // 判斷最少參加人數
  $("input#min_number").on("blur input", function (e) {
    let minNumber = $("input#min_number").val();
    let err_span = $("span#min_number_error");
    if (minNumber == "") {
      err_span.show();
      // $("button.submit").attr("disabled", true);
    } else if (minNumber <= 0) {
      err_span.show();
      // $("button.submit").attr("disabled", true);
    } else {
      err_span.hide();
      // $("button.submit").attr("disabled", false);
    }
  });

  // 判斷最多參加人數
  $("input#max_number").on("blur input", function (e) {
    let maxNumber = $("input#max_number").val();
    console.log(maxNumber);
    let err_span = $("span#max_number_error");

    if (maxNumber == "") {
      err_span.show();
      // $("button.submit").attr("disabled", true);
    } else {
      err_span.hide();
      // $("button.submit").attr("disabled", false);
    }

    // else if (maxNumber < $("input#min_number").val()) {
    //   console.log($("input#min_number").val());
    //   err_span.show();
    // }
  });

  // 判斷圖片
  $("input#activity_photo").on("change input blur", function (e) {
    let file = $("input#activity_photo")[0].files[0];
    let err_span = $("span#activity_photo_error");
    if (!file) {
      err_span.show();
      $("button.submit").attr("disabled", true);
    } else if (
      file.type != "image/png" &&
      file.type != "image/jpg" &&
      file.type != "image/jpeg"
    ) {
      err_span.html("*僅能上傳png、jpeg、jpg圖檔");
      err_span.show();
      // $("button.submit").attr("disabled", false);
    } else {
      err_span.hide();
      // $("button.submit").attr("disabled", false);
    }
  });
}

// 若有欄位為空，則禁用送出按鈕
function disabledBtn() {
  let form = document.querySelector("form.activity_establish_form");
  let submit_btn = document.querySelector("button.form_submit");

  //監聽form輸入事件
  form.addEventListener("input", function () {
    // 檢查每個input中有required屬性的值
    let fields = form.querySelectorAll("input[required]");
    let isAnyFieldEmpty = false;

    for (let i = 0; i < fields.length; i++) {
      if (fields[i].value.trim() === "") {
        isAnyFieldEmpty = true;
        break;
      }
    }

    // 禁用或啟用提交按鈕
    submit_btn.disabled = isAnyFieldEmpty;
  });
}

// 顯示圖片功能
function showImg() {
  photo_btn.addEventListener("change", function () {
    show_photo.innerHTML = ""; // 清空
    let file = this.files[0];
    if (!file) {
      return;
    } else if (
      // 不能上傳其他類型圖檔
      file.type != "image/png" &&
      file.type != "image/jpg" &&
      file.type != "image/jpeg"
    ) {
      return;
    }

    let reader = new FileReader();
    reader.readAsDataURL(file);
    reader.addEventListener("load", function () {
      let img_str = `
          <img src = "${this.result}" class="preview"  >
      `;

      show_photo.innerHTML = img_str;
    });
  });
}

// 表單相關預設設定
function formDefault() {
  // 取得申請開始日期的最小日期為今天
  let date = new Date().toISOString().split(".")[0].slice(0, 16);
  // console.log(date);
  document.querySelector("input#regesteration_starting_time").min = date;
}

// 1.活動報名結束日期
//    (1)最小值：活動報名開始日期
//    (2)最大值：活動日期
// 2. 活動報名開始日期
//    (1)最小值：今天
//    (2)最大值：活動日期
// 3. 活動日期
//    (1)最小值：今天
function minAndMaxDate() {
  let regesterationStartingTime = document.querySelector(
    "input#regesteration_starting_time"
  );
  let regesterationEndingTime = document.querySelector(
    "input#regesteration_ending_time"
  );
  let activityDate = document.querySelector("input#activity_date");
  let activityStartingTime = document.querySelector(
    "input#activity_starting_time"
  );
  let activityEndingTime = document.querySelector("input#activity_ending_time");

  // 更改活動日期，設置活動報名開始時間、活動報名結束日期最大值
  $("input#activity_date").on("change input", (e) => {
    // 清空活動報名開始時間、活動報名結束日期中的值
    regesterationStartingTime.value = "";
    regesterationEndingTime.value = "";
    let max_val = YYYYMMDDTHHMM(new Date(activityDate.value));

    regesterationStartingTime.max = max_val;
    regesterationEndingTime.max = max_val;
  });

  // 點擊活動報名開始日期，設置活動報名結束日期最大小值
  $("input#regesteration_starting_time").on("change input", (e) => {
    regesterationEndingTime.value = "";
    let min_val = new Date(regesterationStartingTime.value)
      .toISOString()
      .split(".")[0]
      .slice(0, 16);
    let max_val = YYYYMMDDTHHMM(new Date(activityDate.value));

    // 設置活動報名結束日期最大值與最小值
    regesterationEndingTime.min = min_val;
    regesterationEndingTime.max = max_val;
  });
}

// 活動結束時間不能比開始時間大(目前有問題)
function minactivityEndingTime() {
  let activityStartingTime = document.querySelector(
    "input#activity_starting_time"
  );
  let activityEndingTime = document.querySelector("input#activity_ending_time");
  activityStartingTime.addEventListener("change", (e) => {
    activityEndingTime.value = "";
    let min_val = activityStartingTime.value;
    activityEndingTime.min = min_val;
  });
}

// 點擊圖片換圖
function clickImg() {
  show_photo.addEventListener("click", () => {
    // 裡面觸發 photo_btn 的點擊
    photo_btn.click();
  });
}

// // 一進到畫面時，判斷表單內容是否有value
function validateInputAtFirst() {
  if ($("input").html() == "") {
    $("input").next().show();
    // $("button.submit").attr("disabled", true);
  } else {
    $("input").next().hide();
    // $("button.submit").attr("disabled", false);
  }

  if ($("select").selectedIndex() == -1 || $("select").val() == "") {
    $("select").next().show();
    // $("button.submit").attr("disabled", true);
  } else {
    $("select").next().hide();
    // $("button.submit").attr("disabled", false);
  }
}

// 判斷表單內容是否已經填寫
function validateInput() {
  // 判斷活動名稱
  $("input#activity_name").blur(function (e) {
    let activityName = $("input#activity_name").val();
    let err_span = $("span#activity_name_error");
    if (activityName == "") {
      err_span.show();
      $("button.submit").attr("disabled", true);
    } else {
      err_span.hide();
      $("button.submit").attr("disabled", false);
    }
  });

  // 判斷活動餐廳
  $("select#activity_restaruant").on("blur input", function (e) {
    let restaurantName = $("select#activity_restaruant").val();
    let err_span = $("span#activity_restaruant_error");
    if (restaurantName == "recommand_restaurant") {
      err_span.show();
      $("button.submit").attr("disabled", true);
    } else {
      err_span.hide();
      $("button.submit").attr("disabled", false);
    }
  });

  // 判斷活動日期
  $("input#activity_date").blur(function (e) {
    let activityDate = $("input#activity_date").val();
    let err_span = $("span#activity_date_error");
    if (activityDate == "") {
      err_span.show();
      $("button.submit").attr("disabled", true);
    } else {
      err_span.hide();
      $("button.submit").attr("disabled", false);
    }
  });

  // 判斷活動報名開始時間
  $("input#regesteration_starting_time").blur(function (e) {
    let regesterationStartingTime = $(
      "input#regesteration_starting_time"
    ).val();
    let err_span = $("span#regesteration_starting_time_error");
    if (regesterationStartingTime == "") {
      err_span.show();
      $("button.submit").attr("disabled", true);
    } else {
      err_span.hide();
      $("button.submit").attr("disabled", false);
    }
  });

  // 判斷活動報名截止時間
  $("input#regesteration_ending_time").blur(function (e) {
    let regesterationEndingTime = $("input#regesteration_ending_time").val();
    let err_span = $("span#regesteration_ending_time_error");
    if (regesterationEndingTime == "") {
      err_span.show();
      $("button.submit").attr("disabled", true);
    } else {
      err_span.hide();
      $("button.submit").attr("disabled", false);
    }
  });

  // 判斷活動開始時間
  $("input#activity_starting_time").blur(function (e) {
    let activityStartingTime = $("input#activity_starting_time").val();
    let err_span = $("span#activity_starting_time_error");
    if (activityStartingTime == "") {
      err_span.show();
      $("button.submit").attr("disabled", true);
    } else {
      err_span.hide();
      $("button.submit").attr("disabled", false);
    }
  });

  // 判斷活動結束時間
  $("input#activity_ending_time").blur(function (e) {
    let activityEndingTime = $("input#activity_ending_time").val();
    let err_span = $("span#activity_ending_time_error");
    if (activityEndingTime == "") {
      err_span.show();
      $("button.submit").attr("disabled", true);
    } else {
      err_span.hide();
      $("button.submit").attr("disabled", false);
    }
  });

  // 判斷最少參加人數
  $("input#min_number").blur(function (e) {
    let minNumber = $("input#min_number").val();
    let err_span = $("span#min_number_error");
    if (minNumber == "") {
      err_span.show();
      $("button.submit").attr("disabled", true);
    } else {
      err_span.hide();
      $("button.submit").attr("disabled", false);
    }
  });

  // 判斷最多參加人數
  $("input#max_number").blur(function (e) {
    let maxNumber = $("input#max_number").val();
    let err_span = $("span#max_number_error");

    if (maxNumber == "") {
      err_span.innerHTML = "*請填入最多人數";
      err_span.show();
      $("button.submit").attr("disabled", true);
    } else if (Number(maxNumber) < Number($("input#min_number").val())) {
      console.log("安安安");
      err_span.innerHTML = "*人數須大於最小參加人數";
      err_span.show();
    } else {
      err_span.hide();
      $("button.submit").attr("disabled", false);
    }
  });

  // 判斷圖片
  $("input#activity_photo").on("change input blur", function (e) {
    let file = $("input#activity_photo")[0].files[0];
    let err_span = $("span#activity_photo_error");
    if (!file) {
      err_span.show();
      // $("button.submit").attr("disabled", true);
    } else if (
      file.type != "image/png" &&
      file.type != "image/jpg" &&
      file.type != "image/jpeg"
    ) {
      err_span.html("*僅能上傳png、jpeg、jpg圖檔");
      err_span.show();
      $("button.submit").attr("disabled", false);
    } else {
      err_span.hide();
      $("button.submit").attr("disabled", false);
    }
  });
}

//將表單數據轉為JSON，並且提交給後端
function form2JSON() {
  check_submit.addEventListener("click", (e) => {
    // 阻止表單送出
    e.preventDefault();

    // 取得form裡面的數據
    let activityName = $("input#activity_name").val();
    let restaurantId = $("select#activity_restaruant").val();
    // 轉換日期格式，使其符合後端資料
    let activityDate = $("input#activity_date").val();
    let activityDateObj = new Date(activityDate);
    let activityStartingTime = $("input#activity_starting_time").val();
    let activityEndingTime = $("input#activity_ending_time").val();
    let regesterationStartingTime = `${$("input#regesteration_starting_time")
      .val()
      .substring(0, 10)} ${$("input#regesteration_starting_time")
      .val()
      .substring(11)}:00`;
    let regesterationEndingTime = `${$("input#regesteration_ending_time")
      .val()
      .substring(0, 10)} ${$("input#regesteration_ending_time")
      .val()
      .substring(11)}:00`;
    let minNumber = $("input#min_number").val();
    let maxNumber = $("input#max_number").val();
    let activityText = $("textarea#activity_text").val();
    let activityStatus = 0; // 0：報名中 1：成團 2：流團，預設為0
    let accId = JSON.parse(sessionStorage.getItem("loginReq")).acc_id;
    let activityNumber = 1; //預設會有1人
    let activityPhotoBase64;

    let activityId;
    // 若有activityId存在，就放進來
    if (sessionStorage.getItem("activityId") != "") {
      activityId = sessionStorage.getItem("activityId");
    }

    //取得上傳的圖片
    // type="file"的input標籤會回傳filesList物件
    let file = document.querySelector("input#activity_photo").files[0];
    let reader = new FileReader();
    reader.readAsDataURL(file);

    // 讀取好後，嘗試將資料送往後端
    reader.addEventListener("load", () => {
      // 這是一個Base64型態的物件，並將其他不相關的字串去除
      activityPhotoBase64 = reader.result.split(",")[1];
      // console.log(reader.result.split(",")[1]);

      // 準備好空物件，拿來裝form資料的key-value
      let jsonObject = {
        activityId: activityId,
        activityName: activityName,
        activityNumber: activityNumber,
        minNumber: minNumber,
        maxNumber: maxNumber,
        activityDate: activityDate,
        accId: accId,
        restaurantId: restaurantId,
        regesterationStartingTime: regesterationStartingTime,
        regesterationEndingTime: regesterationEndingTime,
        activityStartingTime: activityStartingTime,
        activityEndingTime: activityEndingTime,
        activityText: activityText,
        activityStatus: activityStatus,
        // activityPhoto: activityPhoto,
        activityPhotoBase64: activityPhotoBase64,
      };
      console.log(jsonObject);

      fetch("establish", {
        method: "POST",
        headers: {
          "Content-Type": "application/json;charset=UTF-8",
        },
        body: JSON.stringify(jsonObject), // 欲傳送的資料
      })
        .then((res) => {
          console.log(res);
          return res.json();
        })
        .then((json) => {
          console.log(json);
          // 建立成功後直接導向該活動詳細頁
          let activityId = json.activityId;
          console.log(activityId);
          sessionStorage.setItem("activityId", activityId);
          document.location.href = `activity_detail.html`;
        })
        .catch((e) => {
          console.log(e);
        });
    });
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

$(function () {
  search();

  //帶入所有資訊
  activity_edit();

  // 顯示圖片功能
  showImg();

  //點擊圖片也可以換圖
  clickImg();

  // 取得 餐廳名稱
  getResName();

  //form表單相關設定
  validateInput();
  formDefault();
  minAndMaxDate();
  minactivityEndingTime();
  // validateInputAtFirst();

  // //將表單數據轉為JSON，並且提交給後端
  form2JSON();
});
