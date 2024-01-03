window.addEventListener('DOMContentLoaded', function() {
  // 發送請求獲取後端數據
  fetch('/getRedisResPpl')
      .then(function(response) {
          return response.json();
      })
      .then(function(resultObj) {
          // 使用resultObj中的ReservedPeople的值來更新HTML元素
          var reservedPeople = resultObj.reservedPeople;
          var pplElement = document.getElementById('ppl');
          var totalElement = document.getElementById('total');

          // 更新td標籤的內容
          pplElement.textContent = reservedPeople;

          // 更新span標籤的內容
          var totalValue = 1000 * reservedPeople;
          totalElement.textContent = totalValue;
      })
      .catch(function(error) {
          console.error('發生錯誤:', error);
      });
});




