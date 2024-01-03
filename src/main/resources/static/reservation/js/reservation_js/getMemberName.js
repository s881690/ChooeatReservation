window.addEventListener('DOMContentLoaded', function() {
  var memberNameSpan = document.getElementById('memberName');

  // 發送HTTP請求
  var request = new XMLHttpRequest();
  request.open('GET', 'http://localhost:8080/getMemberName', true);

  request.onload = function() {
    if (request.status >= 200 && request.status < 400) {
      // 回傳的字串取代<span>的內容文字
      memberNameSpan.textContent = request.responseText;
    } else {
      console.error('無法取得會員名稱');
    }
  };

  request.onerror = function() {
    console.error('發生錯誤');
  };

  request.send();
});
 