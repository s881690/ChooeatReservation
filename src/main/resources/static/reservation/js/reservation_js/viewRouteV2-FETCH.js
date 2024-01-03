document.addEventListener("DOMContentLoaded", () => {
  const viewButton = document.getElementById("viewButton");

  viewButton.addEventListener("click", () => {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        (position) => {
          const userLocation = `${position.coords.latitude},${position.coords.longitude}`;
          const sessionStorageData = JSON.parse(sessionStorage.getItem('searchResult'));
          const restaurantId = sessionStorageData.myself[0].restaurantId;

          const encodedStartAddress = encodeURIComponent(userLocation);
          const url = `../restaurantAddress?restaurantId=${restaurantId}`;

          fetch(url)
            .then((response) => response.text())
            .then((address) => {
              const encodedEndAddress = encodeURIComponent(address);
              const mapUrl = `https://www.google.com/maps?saddr=${encodedStartAddress}&daddr=${encodedEndAddress}`;
              window.open(mapUrl, "_blank"); // 在新的选项卡或窗口中打开地图
            })
            .catch((error) => {
              console.error("Failed to fetch restaurant address:", error);
              // 处理获取餐厅地址失败的情况
              // 或者在这里添加其他错误处理逻辑
            });
        },
        (error) => {
          console.error("Failed to get user location:", error);
          // 处理获取用户位置失败的情况
          // 或者在这里添加其他错误处理逻辑
        }
      );
    } else {
      console.error("Geolocation is not supported by this browser.");
      // 处理浏览器不支持地理定位的情况
      // 或者在这里添加其他错误处理逻辑
    }
  });
});
