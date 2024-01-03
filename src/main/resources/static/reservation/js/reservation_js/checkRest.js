// 檢查頁面載入時的sessionStorage
window.addEventListener('DOMContentLoaded', function() {
  // 檢查sessionStorage中是否存在loginReq鍵
  if (!sessionStorage.getItem('searchResult')) {
    alert("請先搜尋想吃的餐廳~");
    window.location.href = "http://localhost:8080/restaurant/Homepage.html";
  }
});
