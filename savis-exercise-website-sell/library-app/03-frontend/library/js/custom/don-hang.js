// // var completes = document.querySelectorAll(".complete");
// // var toggleButton = document.getElementById("toggleButton");

// // function toggleComplete() {
// //   var lastComplete = completes[completes.length - 1];
// //   lastComplete.classList.toggle("complete");
// // }

// // toggleButton.onclick = toggleComplete;

// var myApp = angular.module("myApp", []);

// myApp.controller("myController", function ($scope) {
//   $scope.tabs = [
//     { title: "Tab 1", content: "Nội dung Tab 1", isActive: true },
//     { title: "Tab 2", content: "Nội dung Tab 2", isActive: false },
//     { title: "Tab 3", content: "Nội dung Tab 3", isActive: false },
//     // Thêm các tab khác nếu cần, nhưng không quá 5 tab
//   ];

//   $scope.selectTab = function (selectedTab) {
//     angular.forEach($scope.tabs, function (tab) {
//       tab.isActive = tab === selectedTab;
//     });
//   };
// });
