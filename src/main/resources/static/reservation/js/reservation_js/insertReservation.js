function serializeDate(dateString) {
  var dateArray = dateString.split("-");
  var month = dateArray[0];
  var day = dateArray[1];
  var year = dateArray[2];

  var dateObj = new Date(year, month - 1, day);

  var yyyy = dateObj.getFullYear();
  var mm = (dateObj.getMonth() + 1).toString().padStart(2, "0");
  var dd = dateObj.getDate().toString().padStart(2, "0");

  var formattedDate = yyyy + "-" + mm + "-" + dd + " ";
  return formattedDate;
}


const mealData = {
  ppl: "",
  date_time: null, // 将初始值设为 null
  text: "",
  acc_id:"",
  restaurantId :""
};

const loginReq = JSON.parse(sessionStorage.getItem('loginReq'));
const acc_id = loginReq.acc_id;

const searchResult = JSON.parse(sessionStorage.getItem('searchResult'));
const restaurantId2 = searchResult.myself[0].restaurantId;

mealData.acc_id = acc_id;
mealData.restaurantId = restaurantId2;

console.log(typeof mealData.acc_id); // 打印acc_id属性的类型
console.log(typeof mealData.restaurantId); // 打印restaurantId属性的类型

const time = document.querySelectorAll(".meal_time");
const meal_select = document.querySelector("#meal_select");
const picker = document.querySelector("#datepicker-widget");
const btn = document.querySelector("#btn");
const textarea = document.querySelector(".form-control");

textarea.onblur = function (event) {
  console.log(event.target.value);
  mealData.text = event.target.value;
};

picker.onchange = () => {
  mealData.date_time = serializeDate(picker.value);
};

let selectedButton = null; // 儲存目前被選擇的按鈕

time.forEach((e) => {
  // for 取得全部的時間按鈕

  e.onclick = () => {
    // 給每個時間按鈕加上事件
    if (!mealData.date_time) {
      alert("請選擇日期");
      return false;
    }

    if (selectedButton) {
      selectedButton.classList.remove("meal-time-style");
    }
    // 先把每個時間按鈕的 class 初始化
    // time.forEach((j) => (j.className = "meal_time"));
    // 把時間存在物件裡面
    
    mealData.date_time = `${mealData.date_time.slice(0, 10)} ${e.getAttribute('name')}:00`;
    console.log(mealData.date_time);
    // 改變樣式
    e.className = "meal-time-style meal_time";
    selectedButton = e;
  };
});




// 監聽選擇框變化的時候執行事件
meal_select.onchange = () => {
  // 把選擇的內容存在全局變數
  mealData.ppl = meal_select.value;
  // new mealData().setPpl();
};

btn.onclick = () => {
  const selectedValue = document.getElementById("meal_select").value;

  // 如果沒有選擇人數，則跳出 alert 警告並中斷送出行為
  if (!selectedValue) {
    alert("請選擇人數");
    return;
  }
  if (!mealData.date_time || mealData.date_time.length <= 10) {
    alert("請選擇日期和時間");
    return;
  }

  console.log(mealData);
  fetch("../reservationRedis", {
    method: "POST",
    headers: {
      "Content-type": "application/json",
    },
    body: JSON.stringify(mealData),
  })
    .then((r) => r.json())
    .then((data) => {
      if (data.status === "success") {
        sessionStorage.setItem("index", data.index);
        window.location.href = "reservationCheckout.html";
 
        console.log("success");
      } else if(data.status === "wait") {
        alert("該時段有人訂位中，請稍後！");
      }else{
        alert("訂位失敗，請重新選擇");
      }
    });


  
};
