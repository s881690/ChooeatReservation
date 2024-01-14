window.addEventListener('DOMContentLoaded', function() {
  // 檢查 sessionStorage 中是否存在 loginReq 鍵
  if (!sessionStorage.getItem('loginReq')) {
    alert("請登入會員");
    window.location.href = "account/login.html";
  } else {
    // 檢查 sessionStorage 中是否存在 searchResult 鍵
    if (!sessionStorage.getItem('searchResult')) {
      alert("請先搜尋想吃的餐廳~");
      window.location.href = "restaurant/Homepage.html";
    }
  }
});
