// 在你的JavaScript檔案中
// 取得要放置table的區域
const tableContainer = document.getElementById('table');

// 發出fetch請求
fetch('../getAllreservation')
  .then(response => response.json()) // 將回傳的資料轉換為JSON格式
  .then(data => {
    // 生成table的HTML
    const tableHTML = generateTableHTML(data);

    // 將table的HTML插入到tableContainer區域中
    tableContainer.innerHTML = tableHTML;
  })
  .catch(error => {
    console.error('發生錯誤:', error);
  });

// 生成table的HTML
function generateTableHTML(data) {
  // 建立table的開頭標籤和標題列
  let tableHTML = '<table>';
  tableHTML += '<thead><tr><th>訂位編號</th><th>訂位餐廳</th><th>訂位人數</th><th>訂位時間</th></tr></thead>';

  // 建立table的內容列
  tableHTML += '<tbody>';
  data.forEach(item => {
    tableHTML += '<tr>';
    tableHTML += `<td>${item.reservationId}</td>`;
    tableHTML += `<td>${item.restaurantName}</td>`;
    tableHTML += `<td>${item.reservationNumber}</td>`;
    tableHTML += `<td>${item.reservationTime}</td>`;
    tableHTML += '</tr>';
  });
  tableHTML += '</tbody>';

  // 建立table的結尾標籤
  tableHTML += '</table>';

  return tableHTML;
}
