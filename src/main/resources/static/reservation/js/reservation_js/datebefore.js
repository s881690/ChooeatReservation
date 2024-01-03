$("#meal_select").change(function () {
  var selectedDate = $("#datepicker-widget").datepicker("getDate");
  if (selectedDate !== null) {
    var dateText = $.datepicker.formatDate("yy-mm-dd", selectedDate);
    handleDateSelect(dateText);
  }
});

$(function () {
  $("#datepicker-widget").datepicker({
    minDate: new Date(), // 添加minDate选项
    onSelect: handleDateSelect
  });
});

//把日期判斷的方法拉出來單獨成一個function，人數選單也可以套
function handleDateSelect(dateText) {
  console.log(dateText);
  var acc_id = JSON.parse(sessionStorage.getItem('loginReq')).acc_id;
  var restaurantId = JSON.parse(sessionStorage.getItem('searchResult')).myself[0].restaurantId;

  $.ajax({
    url: "../getBusinessDay",
    type: "GET",
    data: {
      acc_id: acc_id,
      restaurantId: restaurantId,
      date: dateText
    },
    success: function (response) {
      console.log(response);

      if (response.status === "dayoff") {
        // 遍历每个按钮，移除内部的 <div> 标签
        $('.meal_time').each(function () {
          $(this).addClass('stop');
          $(this).removeClass('full');
          $(this).removeClass('notEnough');
          $(this).removeClass('reserved');

          $(this).find('.remainSeat').addClass('hidden-div');
        });

      } else {
        // 刪除所有 .stop 的 class
        $('.meal_time').removeClass('stop');
        $('.meal_time').removeClass('full');
        $('.meal_time').removeClass('notEnough');
        $('.meal_time').removeClass('reserved');
        $('.meal_time').find('.remainSeat').removeClass('hidden-div');
        $('.meal_time').find('.remainSeat').removeClass('hasFull');
        $('.meal_time').find('.remainSeat').removeClass('hasReserved');

        mealData.date_time = serializeDate(dateText);

        updateRemainSeat(response.remainSeat);
        updateMealTimeAvailability(response.resStartTime, response.resEndTime);
        markReservedTimeSlots(response.reservedList);
        updateButtonStatus(response.HourlySeatlist);
      }
    }
  });
}


//印出每個按鈕剩餘座位數的function
function updateRemainSeat(remainSeat) {
  $('.remainSeatNumber').each(function () {
    $(this).text(remainSeat);
  });
}

//這個function是檢查按鈕時間，非營業時間不可選取
function updateMealTimeAvailability(resStartTime, resEndTime) {
  var startTimeArray = resStartTime.split(':');
  var endTimeArray = resEndTime.split(':');

  var resStartHour = parseInt(startTimeArray[0]);
  var resEndHour = parseInt(endTimeArray[0]);

  var currentDate = new Date(); // 獲取當前時間
  var currentHour = currentDate.getHours(); // 獲取當前小時

  $('.meal_time').each(function () {
    var button = $(this);
    var buttonHour = parseInt(button.text().split(':')[0]);

    if (buttonHour < resStartHour || buttonHour >= resEndHour) {
      button.addClass('stop');
      button.find('.remainSeat').addClass('not-business');
    } else {
      button.removeClass('stop');
      button.find('.remainSeat').removeClass('not-business');
    }

    if (buttonHour < currentHour) {
      button.addClass('before');
    } else {
      button.removeClass('before');
    }
  });
}

// 這個function判斷該會員是否在該時段有預約
function markReservedTimeSlots(reservedList) {
  const timeButtons = document.getElementsByClassName('meal_time');

  Array.from(timeButtons).forEach(function (button) {
    const buttonName = button.getAttribute('name');
    const buttonTime = parseInt(buttonName.split(':')[0]);
    if (reservedList.includes(buttonTime)) {
      button.classList.add('reserved');
      button.classList.remove('full');
    
      const divElement = button.querySelector('.remainSeat');
      divElement.classList.add('hasReserved');
    
      const buttonText = button.firstChild;
      buttonText.textContent = '已預約';
    } else {
      button.classList.remove('reserved');
      button.classList.remove('full');
    
      const divElement = button.querySelector('.remainSeat');
      divElement.classList.remove('hasReserved');
    
      const buttonText = button.firstChild;
      buttonText.textContent = buttonName;
    }
    
  });
}





//這個function存取各時段剩餘座位數，並判斷是否客滿/剩餘座位不足
function updateButtonStatus(HourlySeatlist) {
  const buttons = document.getElementsByClassName('meal_time');
  const remainSeatDivs = document.getElementsByClassName('remainSeatNumber');

  for (let i = 0; i < buttons.length; i++) {
    const button = buttons[i];
    const h = parseInt(button.getAttribute('name'));

    for (let j = 0; j < HourlySeatlist.length; j++) {
      const { hour, remainSeat } = HourlySeatlist[j];

      if (hour === h) {
        const remainSeatDiv = remainSeatDivs[i];
        remainSeatDiv.innerText = remainSeat;

        if (remainSeat === 0) {
          button.classList.add('full');
          button.classList.remove('notEnough');

          const divElement = button.querySelector('.remainSeat');
          divElement.classList.add('hasFull');

          const buttonText = button.firstChild;
          buttonText.textContent = '該時段已客滿';
        } else if (remainSeat < parseInt(document.getElementById('meal_select').value)) {
          button.classList.add('notEnough');
          button.classList.remove('full');

          const divElement = button.querySelector('.remainSeat');
          divElement.classList.remove('hasFull');
        }
      }
    }
  }
}





 




