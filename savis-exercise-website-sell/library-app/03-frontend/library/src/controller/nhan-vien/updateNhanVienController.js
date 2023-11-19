window.updateNhanVienController = function (
  $http,
  $scope,
  $routeParams,
  $location
) {
  $http
    .get(nhanVienAPI + "/detail/" + $routeParams.id)
    .then(function (response) {
      $scope.detailNhanVien = response.data;
      $scope.showTen = true;
      console.log($scope.detailNhanVien);
    });

  $scope.update = function (id, event) {
    let check = true;
    $scope.showTen = true;
    $scope.showEmail = true;
    $scope.showSdt = true;
    $scope.showMota = true;
    $scope.showT = true;
    $scope.showP = true;
    $scope.showQ = true;
    let ht = $scope.detailNhanVien.hoten;

    var date = new Date();
    const hinhanh = document.getElementById("product-image");
    console.log(hinhanh);
    for (const image of hinhanh.files) {
      $scope.detailNhanVien.anhdaidien = image.name;
    }

    $scope.updateNhanVien = {
      ma: $scope.detailNhanVien.ma,
      hoten: $scope.detailNhanVien.hoten,
      chucVu: $scope.detailNhanVien.chucVu.ten,
      email: $scope.detailNhanVien.email,
      gioitinh: $scope.detailNhanVien.gioitinh,
      ngaytao: $scope.detailNhanVien.ngaytao,
      sodienthoai: $scope.detailNhanVien.sodienthoai,
      ngaysinh: $scope.detailNhanVien.ngaysinh,
      trangthai: $scope.detailNhanVien.trangthai,
      anhdaidien: $scope.detailNhanVien.anhdaidien,
      ngaysua: ($scope.detailNhanVien.ngaysua = date),
      tinhthanhpho: $scope.detailNhanVien.tinhthanhpho,
      quanhuyen: $scope.detailNhanVien.quanhuyen,
      mota: $scope.detailNhanVien.mota,
      phuongxa: $scope.detailNhanVien.phuongxa,
    };
    const emailRegex = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
    const specialChars = /[`!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?~]/;
    const vietnamPhoneRegex =
      /^(?:\+84|0)(3[2-9]|5[689]|7[06-9]|8[1-9]|9\d)\d{7}$/;

    if (ht.length == 0 || ht.length > 50 || specialChars.test(ht)) {
      $scope.showTen = false;
      check = false;
    }
    // if (!emailRegex.test(email)) {
    //   console.log(email);
    //   $scope.showEmail = false;

    //   check = false;
    // }
    // if (
    //   sodienthoai.length == 0 ||
    //   specialChars.test(sodienthoai) ||
    //   !vietnamPhoneRegex.test(sodienthoai)
    // ) {
    //   $scope.showSdt = false;

    //   check = false;
    // }
    // if (mota.length == 0 || mota.length > 51 || specialChars.test(mota)) {
    //   $scope.showMota = false;

    //   check = false;
    // }
    // if (tinhthanhpho == "" || specialChars.test(tinhthanhpho)) {
    //   $scope.showT = false;

    //   check = false;
    // }
    // if (quanhuyen == "" || specialChars.test(quanhuyen)) {
    //   $scope.showQ = false;

    //   check = false;
    // }
    // if (phuongxa == "" || specialChars.test(phuongxa)) {
    //   $scope.showP = false;

    //   check = false;
    // }
    if (check) {
      $http
        .put(nhanVienAPI + "/update/" + id, $scope.updateNhanVien)
        .then(function () {
          alert("Cập nhật thành công");
          $location.path("/nhan-vien/hien-thi");
          $scope.show = true;
          return true;
        })
        .catch(function (errorResponse) {
          if (errorResponse && errorResponse.preventDefault) {
            errorResponse.preventDefault();
          }
          alert("Email hoặc số điện thoại đã tồn tại");
          $scope.show = true;
        });
    } else {
      event.preventDefault();
    }
  };
};
