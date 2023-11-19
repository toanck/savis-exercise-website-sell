var myApp = angular.module("myApp", ["ngRoute"]);

myApp.config(function ($routeProvider, $locationProvider, $httpProvider) {
  $httpProvider.defaults.headers.common["Access-Control-Allow-Origin"] = "*";

  $locationProvider.hashPrefix("");

  $routeProvider
    .when("/san-pham/hien-thi", {
      templateUrl: "san-pham/hien-thi-san-pham.html",
      controller: hienThiSanPhamController,
    })
    .when("/san-pham/add", {
      templateUrl: "san-pham/add-san-pham.html",
      controller: addSanPhamController,
    })
    .when("/san-pham/update/:id", {
      templateUrl: "san-pham/update-san-pham.html",
      controller: updateSanPhamController,
    })
    .when("/san-pham-chi-tiet/hien-thi/:id", {
      templateUrl: "san-pham-chi-tiet/hien-thi-san-pham-chi-tiet.html",
      controller: hienThiSanPhamChiTietController,
    })
    .when("/mau-sac/hien-thi", {
      templateUrl: "mau-sac/hien-thi-mau-sac.html",
      controller: hienThiMauSacController,
    })
    .when("/mau-sac/add", {
      templateUrl: "mau-sac/add-mau-sac.html",
      controller: addMauSacController,
    })
    .when("/mau-sac/update/:id", {
      templateUrl: "mau-sac/update-mau-sac.html",
      controller: updateMauSacController,
    })
    .when("/kich-thuoc/hien-thi", {
      templateUrl: "kich-thuoc/hien-thi-kich-thuoc.html",
      controller: hienThiKichThuocController,
    })

    .when("/kich-thuoc/add", {
      templateUrl: "kich-thuoc/add-kich-thuoc.html",
      controller: addKichThuocController,
    })
    .when("/kich-thuoc/update/:id", {
      templateUrl: "kich-thuoc/update-kich-thuoc.html",
      controller: updateKichThuocController,
    })
    .when("/chat-lieu/hien-thi", {
      templateUrl: "chat-lieu/hien-thi-chat-lieu.html",
      controller: hienThiChatLieuController,
    })
    .when("/chat-lieu/add", {
      templateUrl: "chat-lieu/add-chat-lieu.html",
      controller: addChatLieuController,
    })
    .when("/chat-lieu/update/:id", {
      templateUrl: "chat-lieu/update-chat-lieu.html",
      controller: updateChatLieuController,
    })
    .when("/hoa-tiet/hien-thi", {
      templateUrl: "hoa-tiet/hien-thi-hoa-tiet.html",
      controller: hienThiHoaTietController,
    })
    .when("/hoa-tiet/add", {
      templateUrl: "hoa-tiet/add-hoa-tiet.html",
      controller: addHoaTietController,
    })
    .when("/hoa-tiet/update/:id", {
      templateUrl: "hoa-tiet/update-hoa-tiet.html",
      controller: updateHoaTietController,
    })
    .when("/nhan-vien/hien-thi", {
      templateUrl: "nhan-vien/hien-thi-nhan-vien.html",
      controller: hienThiNhanVienController,
    })
    .when("/phong-cach/hien-thi", {
      templateUrl: "phong-cach/hien-thi-phong-cach.html",
      controller: hienThiPhongCachController,
    })
    .when("/phong-cach/add", {
      templateUrl: "phong-cach/add-phong-cach.html",
      controller: addPhongCachController,
    })
    .when("/phong-cach/update/:id", {
      templateUrl: "phong-cach/update-phong-cach.html",
      controller: updatePhongCachController,
    })
    .when("/co-ao/hien-thi", {
      templateUrl: "co-ao/hien-thi-co-ao.html",
      controller: hienThiCoAoController,
    })
    .when("/co-ao/add", {
      templateUrl: "co-ao/add-co-ao.html",
      controller: addCoAoController,
    })
    .when("/co-ao/update/:id", {
      templateUrl: "co-ao/update-co-ao.html",
      controller: updateCoAoController,
    })
    .when("/tay-ao/hien-thi", {
      templateUrl: "tay-ao/hien-thi-tay-ao.html",
      controller: hienThiTayAoController,
    })
    .when("/tay-ao/add", {
      templateUrl: "tay-ao/add-tay-ao.html",
      controller: addTayAoController,
    })
    .when("/tay-ao/update/:id", {
      templateUrl: "tay-ao/update-tay-ao.html",
      controller: updateTayAoController,
    })
    .when("/nhan-vien/add", {
      templateUrl: "nhan-vien/add-nhan-vien.html",
      controller: addNhanVienController,
    })
    .when("/nhan-vien/update/:id", {
      templateUrl: "nhan-vien/update-nhan-vien.html",
      controller: updateNhanVienController,
    })
    .when("/khach-hang/hien-thi", {
      templateUrl: "khach-hang/hien-thi-khach-hang.html",
      controller: hienThiKhachHangController,
    })
    .when("/khach-hang/add", {
      templateUrl: "khach-hang/add-khach-hang.html",
      controller: addKhachHangController,
    })

    .when("/hoa-don/hien-thi", {
      templateUrl: "hoa-don/hien-thi-hoa-don.html",
      controller: HoaDonController,
    })
    .when("/hoa-don/update/:id", {
      templateUrl: "hoa-don/hien-thi-don-hang.html",
      controller: DonHangController,
    })
    .when("/thu-chi/hien-thi", {
      templateUrl: "quan-ly-thu-chi.html",
      controller: ThuChiController,
    })

    .when("/ma-giam-gia/hienthi", {
      templateUrl: "ma-giam-gia/hien-thi-ma-giam-gia.html",
      controller: MaGiamGiaController,
    })

    .when("/ma-giam-gia/add", {
      templateUrl: "ma-giam-gia/add-ma-giam-gia.html",
      controller: addMaGiamGiaController,
    })
    .when("/ma-giam-gia/update/:id", {
      templateUrl: "ma-giam-gia/update-ma-giam-gia.html",
      controller: updateMaGiamGiaController,
    })
    .when("/ban-hang/tai-quay", {
      templateUrl: "ban-hang-tai-quay/hien-thi.html",
      controller: addHoaDonController,
    })
    .when("/khach-hang/update/:id", {
      templateUrl: "khach-hang/update-khach-hang.html",
      controller: updateKhachHangController,
    })

    .otherwise({
      redirectTo: "/admin",
    });
});
