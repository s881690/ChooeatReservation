// 取得按鈕元素
var button = document.querySelector('.pay_check_btn');

// 綁定點擊事件處理函式
button.addEventListener('click', function() {
  // 檢查信用卡選項是否被選取
  var creditCardSelected = document.querySelector('.credit_area input[type="radio"]').checked;
  if (!creditCardSelected) {
    // 未選取信用卡，處理錯誤
    alert('請選擇付款方式');
    return;
  }

  // 檢查信用卡卡號格式
  var creditNumberInput = document.querySelector('.credit_number');
  var creditNumber = creditNumberInput.value.trim();
  if (!isValidCreditCardNumber(creditNumber)) {
    // 信用卡卡號格式不符，處理錯誤
    alert('請輸入有效的信用卡卡號');
    return;
  }

  // 檢查信用卡到期日格式
  var creditDateInput = document.querySelector('.credit_date');
  var creditDate = creditDateInput.value.trim();
  if (!isValidCreditCardDate(creditDate)) {
    // 信用卡到期日格式不符，處理錯誤
    alert('請輸入有效的信用卡到期日');
    return;
  }

  // 檢查安全碼格式
  var creditSecurityInput = document.querySelector('.credit_security');
  var creditSecurity = creditSecurityInput.value.trim();
  if (!isValidCreditCardSecurity(creditSecurity)) {
    // 安全碼格式不符，處理錯誤
    alert('請輸入有效的信用卡安全碼');
    return;
  }

  const index = sessionStorage.getItem('index');
  // 發送請求給後端
  fetch('/reservation?index=' + index)
    .then(response => response.json())
    .then(data => {
      // 檢查回傳的物件中的status屬性
      if (data.status === 'success') {
        sessionStorage.setItem('reservationId', data.reservationId);
        // 删除名为 "index" 的项
        sessionStorage.removeItem("index");

        // 跳轉至下一個頁面
        window.location.href = 'reservationSucess.html';
      } else {
        // 處理其他情況
        // ...
      }
    })
    .catch(error => {
      // 處理錯誤
      // ...
    });
});

// 函式：檢查信用卡卡號格式
function isValidCreditCardNumber(number) {
  // 檢查信用卡卡號是否為 16 位數字
  var regex = /^\d{16}$/;
  return regex.test(number);
}

// 函式：檢查信用卡到期日格式
// 函式：檢查信用卡到期日格式
function isValidCreditCardDate(date) {
  // 檢查信用卡到期日是否符合 mm/yy 的格式
  var regex = /^(0[1-9]|1[0-2])\/\d{2}$/;
  return regex.test(date);
}

// 函式：檢查信用卡安全碼格式
function isValidCreditCardSecurity(security) {
  // 檢查安全碼是否為 3 位數字
  var regex = /^\d{3}$/;
  return regex.test(security);
}
