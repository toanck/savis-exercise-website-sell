window.hienThiKhachHangController = function (
  $http,
  $scope,
  $location,
  $rootScope
) {
  $scope.list_kh = [];
  $scope.searchKeyword = "";
  $scope.selectedOption = "";
  $scope.currentPage = 0;
  $scope.totalPages = [];
  $scope.maxVisiblePages = 3;
  $scope.getKhachHang = function () {
    $http
      .get(khachHangAPI + "/hien-thi?pageNo=" + $scope.currentPage)
      .then(function (response) {
        $scope.list_kh = response.data;
        $scope.totalPages = new Array(response.data.totalPages);
      });
  };
  $scope.getKhachHang();
  $scope.getVisiblePages = function () {
    var totalPages = $scope.totalPages.length;

    var range = $scope.maxVisiblePages; // Số trang tối đa để hiển thị
    var curPage = $scope.currentPage;

    var numberTruncateLeft = curPage - Math.floor(range / 2);
    var numberTruncateRight = curPage + Math.floor(range / 2);

    // Tạo danh sách trang hiển thị
    var visiblePages = [];

    for (var pos = 1; pos <= totalPages; pos++) {
      var active = pos === curPage ? "active" : "";

      if (totalPages >= 2 * range - 1) {
        if (pos >= numberTruncateLeft && pos <= numberTruncateRight) {
          visiblePages.push({
            page: pos,
            active: active,
          });
        }
      } else {
        visiblePages.push({
          page: pos,
          active: active,
        });
      }
    }

    return visiblePages;
  };

  $scope.changePage = function (index) {
    if (index >= 0 && index < $scope.totalPages.length) {
      $scope.currentPage = index;
      $scope.getKhachHang();
    }
  };

  $scope.nextPage = function () {
    if ($scope.currentPage < $scope.totalPages.length - 1) {
      $scope.currentPage++;
      $scope.getKhachHang();
    }
  };

  $scope.previousPage = function () {
    if ($scope.currentPage > 0) {
      $scope.currentPage--;
      $scope.getKhachHang();
    }
  };
  $scope.$watch("searchKeyword", function (newVal, oldVal) {
    if (newVal !== oldVal) {
      $http
        .get(khachHangAPI + "/search?search=" + $scope.searchKeyword)
        .then(function (response) {
          $scope.list_kh = response.data;
          console.log("thanh cong", response.data);
        });
    }
  });
  $scope.searchTT = function () {
    $http
      .get(khachHangAPI + "/hien-thiTT?search=" + $scope.selectedOption)
      .then(function (response) {
        $scope.list_kh = response.data;
      });
  };

  $scope.import = function (files) {
    var reader = new FileReader();
    reader.onload = async () => {
      var workbook = new ExcelJS.Workbook();
      await workbook.xlsx.load(reader.result);
      const worksheet = workbook.getWorksheet("Sheet1");
      var date = new Date();
      worksheet.eachRow((row, index) => {
        if (index > 1) {
          let nhanvien = {
            ma: row.getCell(1).value,
            hoten: row.getCell(2).value,
            gioitinh: true && row.getCell(3).value,
            email: row.getCell(5).value,
            ngaysinh: row.getCell(6).value,
            sodienthoai: row.getCell(7).value,
            matkhau: "123",
            ngaytao: date,
            tinhthanhpho: row.getCell(8).value,
            quanhuyen: row.getCell(9).value,
            phuongxa: row.getCell(10).value,
            mota: row.getCell(11).value,
            trangthai: row.getCell(12).value,
          };
          $http.post(khachHangAPI + "/add", nhanvien).then(function () {
            $location.path("/nhan-vien/hien-thi");
          });
        }
      });
    };
    reader.readAsArrayBuffer(files[0]);
  };
  $scope.exportToExcel = function () {
    // Lấy dữ liệu từ bảng (sử dụng jQuery, hoặc nguyên bản AngularJS)
    var tableData = [];
    $("table tr").each(function (rowIndex, row) {
      var rowData = [];
      $(row)
        .find("td")
        .each(function (colIndex, cell) {
          rowData.push(angular.element(cell).text());
        });
      tableData.push(rowData);
    });

    // Tạo tệp Excel sử dụng SheetJS
    var ws = XLSX.utils.aoa_to_sheet(tableData);
    var wb = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(wb, ws, "Danh sách");

    // Lưu tệp Excel
    XLSX.writeFile(wb, "danh-sach.xlsx");
  };
};
