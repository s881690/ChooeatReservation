// 获取当前URL中的reservationId
var reservationId = new URLSearchParams(window.location.search).get('reservationId');

// 设置请求的URL
var url = '../getRestaurantNameByReservation?reservationId=' + reservationId; // 请替换为您的后端URL，将reservationId作为查询参数

// 发送GET请求到后端
fetch(url)
  .then(response => response.text())
  .then(data => {
    // 修改h1标签的内容
    var h1Element = document.getElementById('restaurantName'); // 请替换为您h1标签的ID
    h1Element.innerHTML = data; // 根据返回的结果修改标签内容
  })
  .catch(error => {
    console.log("Error:", error);
  });
