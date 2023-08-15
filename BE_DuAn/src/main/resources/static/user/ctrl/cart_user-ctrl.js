app.controller('cart_user-ctrl', function ($rootScope, $scope, $http, $window, $timeout) {
    var apiUrlProduct = "http://localhost:8080/api/product";

    var apiUrlAccout = "http://localhost:8080/rest/user";

    var urlOrder = "http://localhost:8080/rest/guest/order";

    var urlOrderDetail = "http://localhost:8080/rest/guest/order/detail";

    var urlShippingOder = "http://localhost:8080/rest/user/address/getShipping-order";

    $scope.accountActive = {};
    $scope.item = {};
    $rootScope.qtyCart = 0;
    $rootScope.name = "";
    $scope.checkDesign = 0;
    $scope.checkMaterial = 0;
    $scope.checkSize = 0;
    $scope.checkColor = 0;
    $scope.index = 0;
    $scope.addressAccount = {};
    $scope.to_district_id = "";
    $scope.to_ward_code = ""
    $scope.ship = "";
    $scope.checkBuy = null;
    $scope.bills = {};
    $scope.PrD = {};


    $scope.getAcountActive = function () {
        $http.get(apiUrlAccout + `/getAccountActive`).then(function (respon) {
            $scope.accountActive = respon.data;
            $rootScope.name = $scope.accountActive.username;
            console.log($scope.accountActive.username)
        }).catch(err => {
            $scope.accountActive = null;
            $rootScope.account = null;
        })

    }
    $scope.checkBuyPaypal = function () {
        $scope.checkBuy = true;
    }
    $scope.checkBuyCOD = function () {
        $scope.checkBuy = false;
    }
    $scope.buyCart = function () {
        $scope.messageQuantity = '';
        if (!$rootScope.account) {
            Swal.fire({
                title: 'Bạn chưa đăng nhập',
                text: "quay lại đăng nhập !",
                icon: 'info',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Xác nhận!'
            }).then((result) => {
                $window.location.href = '/login';
            })
        } else {
            Swal.fire({
                title: 'Xác nhận thanh toán?',
                text: "Xác nhận thanh toán để mua hàng!",
                icon: 'info',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Xác nhận!'
            }).then((result) => {
                if (result.isConfirmed) {
                    if ($scope.checkBuy) {
                        var vnp_OrderInfo = 'thanh toan hoa don';
                        var orderType = 'other';
                        var amount = $scope.calculateTotalAmount();
                        var bankcode = ''; // Optional
                        var language = 'vn'; // Optional
                        $http.post(`${urlPaymentVNP}?vnp_OrderInfo=${vnp_OrderInfo}&ordertype=${orderType}&amount=${amount}&bankcode=&language=${language}`).then(res => {
window.location.href = res.data.value;
                            $scope.bills.personTake = $scope.addressAccount.personTake;
                            $scope.bills.phoneTake = $scope.addressAccount.phoneTake;
                            $scope.bills.address = $scope.addressAccount.addressDetail + ", " + $scope.addressAccount.addressTake;
                            $scope.bills.totalMoney = $scope.calculateTotalAmount();
                            $scope.bills.status = 1;
                            $scope.bills.statusBuy = 1;
                            $scope.bills.moneyShip = $scope.ship;
                            $scope.bills.typePayment = false;
                            $http.post(urlOrder + '/add', $scope.bills).then(res => {
                                if (res.data) {
                                    $http.post(urlOrderDetail + '/add', $scope.cartItems).then(res => {
                                        $scope.clearCart();
                                        console.log("orderDetail", res.data)
                                    }).catch(err => {
                                        swal.fire({
                                            icon: 'error',
                                            showConfirmButton: false,
                                            title: err.data.message,
                                            timer: 5000
                                        });
                                    })

                                } else {
                                    Swal.fire(
                                        'Thanh toán thất bại!',
                                        '',
                                        'error'
                                    )
                                }
                            })
                        }).catch(err => {
                            Swal.fire(
                                'Thanh toán thất bại!',
                                '',
                                'error'
                            )
                            console.log("error buy cart", err)
                            alert(err + "lỗi 1");
                        })

                    } else {
                        $scope.bills.personTake = $scope.addressAccount.personTake;
                        $scope.bills.phoneTake = $scope.addressAccount.phoneTake;
                        $scope.bills.address = $scope.addressAccount.addressDetail + ", " + $scope.addressAccount.addressTake;
                        $scope.bills.totalMoney = $scope.calculateTotalAmount();
                        $scope.bills.status = 1;
                        $scope.bills.statusBuy = 0;
                        $scope.bills.moneyShip = $scope.ship;
                        $scope.bills.typePayment = false;
                        let timerInterval
                        Swal.fire({
title: 'Đang thanh toán  vui lòng chờ!',
                            html: 'Vui lòng chờ <b></b> milliseconds.',
                            timer: 5500,
                            timerProgressBar: true,
                            didOpen: () => {
                                Swal.showLoading()
                                const b = Swal.getHtmlContainer().querySelector('b')
                                timerInterval = setInterval(() => {
                                    b.textContent = Swal.getTimerLeft()
                                }, 100)
                            },
                            willClose: () => {
                                clearInterval(timerInterval)
                            }
                        });
                        $http.post(urlOrder + '/add', $scope.bills).then(res => {
                            if (res.data) {
                                $http.post(urlOrderDetail + '/add', $scope.cartItems).then(res => {
                                    $scope.clearCart();
                                    $window.location.href = '/user/cart/buy-cod-success.html';
                                }).catch(err => {
                                    swal.fire({
                                        icon: 'error',
                                        showConfirmButton: false,
                                        title: err.data.message,
                                        timer: 5000
                                    });
                                })
                            }

                        }).catch(err => {
                            Swal.fire(
                                'Thanh toán thất bại!',
                                '',
                                'error'
                            )
                            if (error.status == 401) {
                                $scope.isLoading = false;
                                setTimeout(() => {
                                    document.location = '/admin#!/login';
                                }, 2000);
                                sweetError('Mời bạn đăng nhập !');
                                return;
                            }
                            console.log("err order", err)
                            alert(err + "lỗi")
                        })
                    }
                }

            })
        }

    }
    $scope.clearCart = function() {
        localStorage.removeItem('cartItems');
        $rootScope.qtyCart = 0;
    };

    $scope.cartItems = JSON.parse(localStorage.getItem('cartItems')) || [];
    $rootScope.loadQtyCart = function () {
        $rootScope.qtyCart = 0;
        if ($rootScope.cartItems) {
            $rootScope.cartItems.forEach(item => {
                $rootScope.qtyCart += item.quantity;
            });
        }
    }
$scope.calculateSubtotal = function () {
        var subtotal = 0;
        for (var i = 0; i < $scope.cartItems.length; i++) {
            var item = $scope.cartItems[i];
            subtotal += item.product.price * item.quantity;
        }
        return subtotal;
    };
    $scope.calculateTotalAmount = function () {
        var subtotal = $scope.calculateSubtotal();
        return subtotal + $scope.ship;
    };
    // Hàm để xóa một mục khỏi giỏ hàng
    $scope.removeItem = function (index) {
        Swal.fire({
            title: 'Bạn có chắc muốn xóa Sản phẩm này khỏi giỏ hàng?',
            text: "Xóa không thể khôi phục lại!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, delete it!'
        }).then((result) => {
            if (result.isConfirmed) {
                let timerInterval;
                Swal.fire({
                    title: 'Đang xóa!',
                    html: 'Vui lòng chờ <b></b> milliseconds.',
                    timer: 1500,
                    timerProgressBar: true,
                    didOpen: () => {
                        Swal.showLoading();
                        const b = Swal.getHtmlContainer().querySelector('b');
                    },
                    willClose: () => {
                        $scope.cartItems.splice(index, 1);
                        localStorage.setItem('cartItems', JSON.stringify($scope.cartItems));
                        location.reload("http://localhost:8080/user/index.html#!/cart");
                        $scope.message('Đã xóa sản phẩm thành công');
                    }
                });
            }
        });
    };

    $scope.loadFromLocalStorage = function () {
        var json = localStorage.getItem("cartItems");
        this.cartItems = json ? JSON.parse(json) : [];
    }
    $scope.calculateTotal = function (item) {
        if (!item || !item.product || !item.quantity) {
            return 0;
        }
        return item.product.price * item.quantity;
    };
    $scope.checkCartItemQuantity = function (item) {
        var cartItems = JSON.parse(localStorage.getItem('cartItems')) || [];

        var itemsWithSameProduct = cartItems.filter(function (cartItem) {
            return (
                cartItem.product.id === item.product.id &&
                cartItem.design === item.design &&
                cartItem.size === item.size &&
                cartItem.color === item.color &&
                cartItem.material === item.material
            );
        });

        var totalQuantityInCart = itemsWithSameProduct.reduce(function (total, cartItem) {
            return total + cartItem.quantity;
        }, 0);
        item.totalQuantityInCart = totalQuantityInCart;
var apiUrlProduct = `http://localhost:8080/api/product/${item.product.id}`;
        $http.get(apiUrlProduct).then(function (response) {
            var dbProductQuantity = response.data.quantity;
            item.messageQuantity = ""; // Reset thông báo lỗi
            if (item.quantity == 0) {
                item.messageQuantity = "Số lượng không trống";
            } else if (item.quantity > dbProductQuantity) {
                item.messageQuantity = "Số lượng này vượt quá số lượng hiện có.";
                console.log("Số lượng trong giỏ hàng vượt quá số lượng của sản phẩm trong db.")
            } else if (item.quantity + item.totalQuantityInCart > dbProductQuantity) {
                item.messageQuantity = "Số lượng này vượt quá số lượng hiện có .";
                console.log("Số lượng trong giỏ hàng vượt quá số lượng của sản phẩm trong db.")
            }
        }).catch(function (error) {
            console.log("Lỗi khi truy vấn số lượng sản phẩm từ cơ sở dữ liệu: ", error);
        });
    };

    $scope.getAddressAcountActive = function () {
        if ($rootScope.account != null) {
            $http.get(apiUrlAccout + "/getAddress").then(function (respon) {
                $scope.addressAccount = respon.data;
                $scope.to_district_id = $scope.addressAccount.districtId;
                $scope.getShippingOder();
                $scope.to_ward_code = $scope.addressAccount.wardId;
                console.log($scope.to_district_id, $scope.to_ward_code)
                console.log($scope.addressDefault)

            }).catch(err => {
                Swal.fire({
                    icon: 'error',
                    text: 'Vui lòng thêm địa chỉ!!!',
                })
                alert("bên cart")
                console.log(err)
                $window.location.href = '#!address';
            })
        }
    }
    $scope.getShippingOder = function () {
        $http.get(urlShippingOder + "?from_district_id=1542&service_id=53320&to_district_id="
            + $scope.to_district_id + "&to_ward_code=" + $scope.to_ward_code
            + "&weight=200&insurance_value=" + $scope.calculateTotalAmount()).then(function (respon) {
            $scope.ship = respon.data.body.data.total;
            console.log(respon.data.body.data.total)
        })
        $http.get(urlShippingOder + "?from_district_id=1542&service_id=53321&to_district_id="
            + $scope.to_district_id + "&to_ward_code=360204"
            + "&weight=200&insurance_value=" + $scope.calculateTotalAmount()).then(function (respon) {
            $scope.ship = respon.data.body.data.total;
            console.log(respon.data.body.data.total)
        })
        $http.get(urlShippingOder + "?from_district_id=1542&service_id=53322&to_district_id="
            + $scope.to_district_id + "&to_ward_code=360204"
+ "&weight=200&insurance_value=" + $scope.calculateTotalAmount()).then(function (respon) {
            $scope.ship = respon.data.body.data.total;
            console.log(respon.data.body.data.total)
        })
    }
    $scope.loadMoneyShip = function () {
        $timeout(function () {
            $scope.getShippingOder();
        }, 2000);
    }

    $scope.getAddressAcountActive();

});