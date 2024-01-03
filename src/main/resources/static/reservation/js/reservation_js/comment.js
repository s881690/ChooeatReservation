document.addEventListener("DOMContentLoaded", function () {
  var calendarEl = document.getElementById("calendar");
  var calendar = new FullCalendar.Calendar(calendarEl, {
    // 行事曆的設定...

    eventContent: function (arg) {
      var eventTitle = arg.event.title;
      var commentButton = document.createElement("button");
      commentButton.className = "comment-button";
      commentButton.innerText = "評論";
      commentButton.addEventListener("click", function () {
        var comment = prompt("請輸入評論：");
        if (comment) {
          saveComment(eventTitle, comment);
        }
      });

      var eventContent = document.createElement("div");
      eventContent.className = "fc-content";
      eventContent.innerText = eventTitle;
      eventContent.appendChild(commentButton);

      return { domNodes: [eventContent] };
    },
    events: [
      {
        title: "預約紀錄",
        start: "2023-05-23T12:00:00",
        end: "2023-05-23T13:00:00",
      },
    ],
  });
  calendar.render();
});

function saveComment(eventTitle, comment) {
  // 發送 POST 請求
  fetch("/api/save-comment", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      eventTitle: eventTitle,
      comment: comment,
    }),
  })
    .then(function (response) {
      if (response.ok) {
        console.log("評論已成功儲存至資料庫");
      } else {
        console.error("發生錯誤，評論儲存失敗");
      }
    })
    .catch(function (error) {
      console.error("發生錯誤，評論儲存失敗：" + error);
    });
}
 