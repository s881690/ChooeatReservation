// 从sessionStorage获取restaurantId的值
var sessionStorageData = JSON.parse(sessionStorage.getItem('searchResult'));
var restaurantId = sessionStorageData.myself[0].restaurantId;

// 设置请求的URL
var url = '../restaurantName?restaurantId=' + restaurantId; // 请替换为您的后端URL，将restaurantId作为查询参数

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
