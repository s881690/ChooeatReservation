$(function () {
  $("#datepicker-widget").datepicker({
    onSelect: function (dateText) {
      console.log(dateText);
      $.ajax({
        url: "../getBusinessDay?date="+dateText,
        type: "GET",
        success: function (response) {
          console.log(response);
          
          if (response.status === "dayoff") {
            // 遍歷每個按鈕，添加 'stop' 類別
            $('.meal_time').each(function () {
              $(this).addClass('stop');
              
            });
          } else {
            // 刪除所有 .stop 的 class
            $('.meal_time').removeClass('stop');
            mealData.date_time = serializeDate(dateText);
            updateMealTimeAvailability(response.resStartTime, response.resEndTime);
            updateButtonStatus(response.HourlySeatlist); 
            markReservedTimeSlots(response.reservedList);
          }
        }
      });
    }
  });
  
});

function updateMealTimeAvailability(resStartTime, resEndTime) {
  // 將營業時間轉換為時間物件
  var startTimeArray = resStartTime.split(':');
  var endTimeArray = resEndTime.split(':');

  var resStartHour = parseInt(startTimeArray[0]);
  var resEndHour = parseInt(endTimeArray[0]);

  // 迭代每個時間按鈕
  $('.meal_time').each(function () {
    console.log(this)
    var button = $(this);
    var buttonHour = parseInt(button.text().split(':')[0]);
    
    // 檢查按鈕時間是否在營業時間範圍內
    if (buttonHour < resStartHour || buttonHour >= resEndHour) {
      // 按鈕不在營業時間範圍內，添加stop類別
      button.addClass('stop');
    } else {
      // 按鈕在營業時間範圍內，移除stop類別
      button.removeClass('stop');
    }
  });
  
}

function updateButtonStatus(HourlySeatlist) {
  var selectedOption = $("#meal_select").val(); 
  console.log("人數" + selectedOption);
  const buttons = document.getElementsByClassName("meal_time");
  
  for (let i = 0; i < buttons.length; i++) {
    const button = buttons[i];
    const time = button.getAttribute("name");
    
    // 构建完整的时间字符串（例如：18:00:00）
    const fullTime = time + ":00";

     // 在 HourlySeatlist 陣列中查找對應時間的物件
     const seatObj = HourlySeatlist.find(item => item.hour === fullTime);

    if (seatObj && seatObj.remainSeat === 0) {
      button.classList.add("full");
      button.classList.remove("notEnough"); // 移除 notenough 类
      button.textContent = "訂位已滿";
    } else if (selectedOption > HourlySeatlist[fullTime]) {
      button.classList.remove("full");
      button.classList.add("notEnough"); 
      button.textContent = "剩餘座位不足"; 
      
      
    } else {
      button.classList.remove("full");
      button.classList.remove("notEnough"); 
      button.textContent = time;
    }
  }
}


// 這個function判斷該會員是否在該時段有預約
function markReservedTimeSlots(reservedList) {
 
  const timeButtons = document.getElementsByClassName('meal_time');
  
  //把timeButtons轉換成陣列並進行迭代操作
  Array.from(timeButtons).forEach(function(button) {
    
    // 抓name屬性，獲得按鈕的時間，例如 "17:00"
    const buttonName = button.getAttribute('name') 
    // 提取小時部分並轉換為整數
    const buttonTime = parseInt(buttonName.split(':')[0]); 
   
    //下判斷，如果後端回傳的list包含按鈕的時間，就認為已預約，並改變button的文字及+class去改變css
    //備註：後端回傳的是一個陣列，上面有該會員在該日所有的預約時段
    if (reservedList.includes(buttonTime)) {
      button.classList.add('reserved'); 
      button.classList.remove("full");
      button.textContent = "已預約"; 
     
    }else{
      button.classList.remove("reserved");
      button.textContent = buttonName;
     
    }
  });
}

 




