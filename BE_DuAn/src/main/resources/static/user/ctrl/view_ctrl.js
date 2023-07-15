app.controller('UserController', function ($rootScope, $scope, $http, $window, $timeout) {
    var apiUrlProduct = "http://localhost:8080/api/product";

    var apiUrlAccout = "http://localhost:8080/rest/user";

    var urlOrder = "http://localhost:8080/rest/guest/order";

    var urlOrderDetail = "http://localhost:8080/rest/guest/order/detail";

    var urlShippingOder = "http://localhost:8080/rest/user/address/getShipping-order";
    $scope.products = [];
    $scope.formProduct = {};
    $scope.sizes = [];
    $scope.formSize = {};
    $scope.colors = [];
    $scope.formColor = {};
    $scope.materials = [];
    $scope.formMaterial = {};
    $scope.designs = [];
    $scope.formDesign = {};
    $scope.categories = [];
    $scope.accountActive = {};
    $rootScope.cartItems = [];
    $rootScope.qtyCart = 0;
    $scope.index = 0;
    $scope.addressAccount = {};
    $scope.to_district_id = "";
    $scope.to_ward_code = ""
    $scope.ship = "";
    $scope.checkBuy = null;
    $scope.bills = {};



    $scope.getAcountActive = function () {
        $http.get(apiUrlAccout + `/getAccountActive`).then(function (respon) {
            $scope.accountActive = respon.data;
            $rootScope.name = $scope.accountActive.username;
            console.log($scope.accountActive.username)
        }).catch(err => {
            $scope.accountActive = null;
            $rootScope.account = null;
        })

    };
    $scope.checkBuyPaypal = function () {
        $scope.checkBuy = true;
    }
    $scope.checkBuyCOD = function () {
        $scope.checkBuy = false;
    }
    $scope.buyCart = function () {
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
                alert("1")
                if ($scope.checkBuy) {
                    let price =  ($scope.calculateTotal() / 1).toFixed(2)
                    alert(price + "tổng tiền")
                    $http({
                        url: `http://localhost:8080/pay`,
                        method: 'POST',
                        data: price,
                        transformResponse: [
                            function (data) {
                                return data;
                            }
                        ]
                    }).then(res => {
                        $scope.linkPaypal = res.data;
                        $scope.bills.personTake = $scope.addressAccount.personTake;
                        $scope.bills.phoneTake = $scope.addressAccount.phoneTake;
                        $scope.bills.address = $scope.addressAccount.addressDetail + ", " + $scope.addressAccount.addressTake;
                        $scope.bills.totalMoney = $scope.calculateTotalAmount();
                        $scope.bills.status = 0;
                        $scope.bills.statusBuy = 1;
                        $scope.bills.moneyShip = $scope.ship;
                        $scope.bills.typePayment = false;
                        $http.post(urlOrder + '/add', $scope.bills).then(res => {
                            if (res.data) {
                                alert("vào bill detail chưa")
                                $http.post(urlOrderDetail + '/add', $scope.cartItems).then(res => {
                                    console.log("orderDetail", res.data)
                                }).catch(err => {
                                    swal.fire({
                                        icon: 'error',
                                        showConfirmButton: false,
                                        title: err.data.message,
                                        timer: 5000
                                    });
                                })
                                $window.location.href = $scope.linkPaypal;
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
                    })

                } else {
                    $scope.bills.personTake = $scope.addressAccount.personTake;
                    $scope.bills.phoneTake = $scope.addressAccount.phoneTake;
                    $scope.bills.address = $scope.addressAccount.addressDetail + ", " + $scope.addressAccount.addressTake;
                    $scope.bills.totalMoney = $scope.calculateTotalAmount();
                    $scope.bills.status = 0;
                    $scope.bills.statusBuy = 1;
                    $scope.bills.moneyShip = $scope.ship;
                    $scope.bills.typePayment = false;
                    $http.post(urlOrder + '/add', $scope.bills).then(res => {
                        alert("add vào bill chưa")
                        if (res.data) {
                            alert("add vào bill_detail chưa chưa" +  res.data)
                            $http.post(urlOrderDetail + '/add', $scope.cartItems).then(res => {
                                alert($scope.cartItems.id + "số lượng trong cảt");
                                alert(urlOrderDetail)
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
                        console.log("err order", err)
                        alert(err+  "lỗi")
                    })
                }
            }

        })
    }
    $scope.addCart = function (product) {
        alert("vào ")
        // Get the selected options (design, size, color, material)
        var selectedDesign = $scope.checkDesign.name;
        var selectedSize = $scope.checkSize.name;
        var selectedColor = $scope.checkColor.name;
        var selectedMaterial = $scope.checkMaterial.name;

        // Create a cart item object
        var cartItem = {
            product: product,
            design: selectedDesign,
            size: selectedSize,
            color: selectedColor,
            material: selectedMaterial,
            quantity: 1
        };

        // Retrieve existing cart items from local storage
        var cartItems = JSON.parse(localStorage.getItem('cartItems')) || [];

        // Check if the same product with the same options already exists in the cart
        var existingItem = cartItems.find(function (item) {
            return (
                item.product.id === cartItem.product.id &&
                item.design === cartItem.design &&
                item.size === cartItem.size &&
                item.color === cartItem.color &&
                item.material === cartItem.material
            );
        });

        if (existingItem) {
            // If the same item exists, increase its quantity
            existingItem.quantity++;
        } else {
            // Otherwise, add the new item to the cart
            cartItems.push(cartItem);
            $rootScope.qtyCart++;
            $rootScope.loadQtyCart();
            $scope.messageSuccess("Thêm vào giỏ hàng thành công!");
        }

        // Update the cart items in local storage
        localStorage.setItem('cartItems', JSON.stringify(cartItems));
        // $window.location.href = 'http://localhost:8080/user/index.html#!/cart';
    };
    $scope.cartItems = JSON.parse(localStorage.getItem('cartItems')) || [];
    $rootScope.loadQtyCart=function(){
        $rootScope.qtyCart=0;
        if($rootScope.cartItems){
            $rootScope.cartItems.forEach(item=>{
                $rootScope.qtyCart+=item.qty;
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
    $scope.calculateTotal = function(item) {
        return item.product.price * item.quantity;
    };
    $scope.increaseQuantity = function(item) {
        item.quantity++;
    };

    $scope.decreaseQuantity = function(item) {
        if (item.quantity > 1) {
            item.quantity--;
        }
    };
    // Lấy danh sách sản phẩm
    $scope.getProducts = function () {
        $http.get(apiUrlProduct)
            .then(function (response) {
                $scope.products = response.data;
                console.log(response);
                console.log($scope.products.images.urlimage);
            })
            .catch(function (error) {
                console.log(error);
            });
    };

    $scope.addProduct = function () {
        // Lấy tên tệp tin từ đường dẫn
        var formData = new FormData();
        formData.append('name', $scope.formProduct.name);
        formData.append('size', $scope.formProduct.size);
        formData.append('price', $scope.formProduct.price);
        formData.append('status', $scope.formProduct.status = 1)
        formData.append('category', $scope.formProduct.category)
        formData.append('material', $scope.formProduct.material)
        formData.append('design', $scope.formProduct.design)
        formData.append('color', $scope.formProduct.color)
        console.log($scope.formProduct.category)
        var req = {
            method: 'POST',
            url: '/api/product/saveProduct',
            data: formData,
            headers: {'Content-Type': undefined}
        }
        let timerInterval
        Swal.fire({
            title: 'Đang thêm  mới vui lòng chờ!',
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
        $http(req).then(response => {
            console.log("ddd " + response);
            $scope.message("thêm mới sản phẩm thành công");
            $scope.resetProducts();
            $scope.getProducts();
        }).catch(error => {
            $scope.error('thêm mới thất bại');
        });
    };
    $scope.getAddressAcountActive = function () {
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
            console.log(err)
            $window.location.href = '#!address';
        })
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
    $scope.doSubmit = function () {
        if ($scope.formProduct.idProduct) {
            let timerInterval
            Swal.fire({
                title: 'Đang cập nhật!',
                html: 'Vui lòng chờ <b></b> milliseconds.',
                timer: 1500,
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
            }).then((result) => {
                /* Read more about handling dismissals below */
                if (result.dismiss === Swal.DismissReason.timer) {
                    $scope.onUpdate();
                    console.log('I was closed by the timer')
                }
            })
        } else {
            let timerInterval
            Swal.fire({
                title: 'Đang lưu mới!',
                html: 'Vui lòng chờ <b></b> milliseconds.',
                timer: 2500,
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
            }).then((result) => {
                /* Read more about handling dismissals below */
                if (result.dismiss === Swal.DismissReason.timer) {
                    $scope.onSave();
                    console.log('I was closed by the timer')
                }
            })
        }
    };
    $scope.editProduct = function (product) {
        $scope.formProduct = angular.copy(product);
    }
    // Cập nhật thông tin sản phẩm
    $scope.updateProduct = function (product) {
        $http.put(apiUrlProduct + '/' + product.id, product)
            .then(function (response) {
                // Cập nhật thông tin sản phẩm trong danh sách
                var index = $scope.products.findIndex(p => p.id === product.id);
                if (index !== -1) {
                    $scope.products[index] = response.data;
                }
            })
            .catch(function (error) {
                console.log(error);
            });
    };
    // Xóa sản phẩm
    $scope.deleteProduct = function (formProduct) {
        Swal.fire({
            title: 'Bạn có chắc muốn xóa: ' + formProduct.name + '?',
            text: "Xóa không thể khôi phục lại!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, delete it!'
        }).then((result) => {
            if (result.isConfirmed) {
                let timerInterval
                Swal.fire({
                    title: 'Đang xóa: ' + formProduct.name + '!',
                    html: 'Vui lòng chờ <b></b> milliseconds.',
                    timer: 1500,
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
                }).then((result) => {
                    if (result.dismiss === Swal.DismissReason.timer) {
                        $http.post(apiUrlProduct + `/delete?id=${formProduct.id}`, formProduct.id).then(response => {
                            $scope.message('Đã cập nhật trạng thái  sản phẩm thành hết hàng');
                            $scope.getProducts();
                        }).catch(error => {
                            $scope.error('xóa thất bại');
                        });
                        console.log('I was closed by the timer')
                    }
                })

            }
        })

    };
    $scope.resetProducts = function () {
        $scope.formProduct = {
            status: 1
        }
    }


    $scope.previewImage = function () {
        var input = document.getElementById('image');
        if (input.files && input.files.length > 0) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $scope.imagePreview = e.target.result;
                $scope.$apply(); // Cập nhật lại scope để hiển thị hình ảnh
            };
            $scope.formProduct.url_images = []; // Xóa các hình ảnh hiện có trong danh sách
            for (var i = 0; i < input.files.length; i++) {
                var file = input.files[i];
                var reader1 = new FileReader();
                reader.onload = (function (file) {
                    return function (e) {
                        $scope.formProduct.url_image.push(e.target.result);
                        $scope.$apply(); // Cập nhật lại scope để hiển thị hình ảnh
                    };
                })(file);
                reader1.readAsDataURL(file);
                console.log("Tên tệp tin:", file.name);
            }
        } else {
            $scope.imagePreview = ""; // Đặt giá trị rỗng nếu không có tệp tin được chọn
            $scope.formProduct.url_image = []; // Đặt danh sách hình ảnh thành rỗng
        }
    };
    // Thêm sản phẩm mới


    $scope.getColors = function () {
        $http.get(`${apiUrlProduct}/getAllColor`)
            .then(function (response) {
                $scope.colors = response.data;
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
    };
    $scope.getMaterials = function () {
        $http.get(`${apiUrlProduct}/getAllMaterial`)
            .then(function (response) {
                $scope.materials = response.data;
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
    };
    $scope.getDesign = function () {
        $http.get(`${apiUrlProduct}/getAllDesign`)
            .then(function (response) {
                $scope.designs = response.data;
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
    };
    $scope.getSize = function () {
        $http.get(`${apiUrlProduct}/getAllSize`)
            .then(function (response) {
                $scope.sizes = response.data;
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
    };
    $scope.getCategory = function () {
        $http.get(`${apiUrlProduct}/getAllCategory`)
            .then(function (response) {
                $scope.categories = response.data;
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
    };
    $scope.generationName = function () {
        if ($scope.formProduct.name != undefined || $scope.formProduct.name != null || $scope.formProduct.name != '') {
            $scope.formProduct.name = '';
        }
        if ($scope.formProduct.category != undefined || $scope.formProduct.category != null || $scope.formProduct.category != '') {
            for (let i = 0; i < $scope.categories.length; i++) {
                if ($scope.formProduct.category == $scope.categories[i].idCategory) {
                    $scope.formProduct.name = $scope.categories[i].name;
                }
            }
        }
        if ($scope.formProduct.color != undefined || $scope.formProduct.color != null || $scope.formProduct.color != '') {
            for (let i = 0; i < $scope.colors.length; i++) {
                if ($scope.formProduct.color == $scope.colors[i].id) {
                    $scope.formProduct.name += ' Màu ' + $scope.colors[i].name;
                }
            }
        }
        if ($scope.formProduct.design != undefined || $scope.formProduct.design != null || $scope.formProduct.design != '') {
            for (let i = 0; i < $scope.designs.length; i++) {
                if ($scope.formProduct.design == $scope.designs[i].id) {
                    $scope.formProduct.name += ' Thiết kế ' + $scope.designs[i].name;
                }
            }
        }
        if ($scope.formProduct.material != undefined || $scope.formProduct.material != null || $scope.formProduct.material != '') {
            for (let i = 0; i < $scope.colors.length; i++) {
                if ($scope.formProduct.material == $scope.materials[i].id) {
                    $scope.formProduct.name += ' Chất Liệu ' + $scope.materials[i].name;
                }
            }
        }
        if ($scope.formProduct.size != undefined || $scope.formProduct.size != null || $scope.formProduct.size != '') {
            for (let i = 0; i < $scope.sizes.length; i++) {
                if ($scope.formProduct.size == $scope.sizes[i].id) {
                    $scope.formProduct.name += ' Size ' + $scope.sizes[i].name;
                }
            }
        }

    }
    $scope.searchProduct = function () {
        if ($scope.searchPriceMin === "") {
            $scope.searchPriceMin = "Min"

        }
        if ($scope.searchProducts === "") {
            $scope.searchProducts = " "

        }
        if ($scope.searchPriceMax === "") {
            $scope.searchPriceMax = "Max"
        }
        if ($scope.searchColor === 'undefined' && $scope.searchDesign === 'undefined' && $scope.searchMaterial === 'undefined'
            && $scope.searchSize === 'undefined' && $scope.searchPriceMin === "" && $scope.searchPriceMax === "" && $scope.searchProducts === 'undefined'
        ) {
            $scope.getProducts();
        } else {
            $http.get(apiUrlProduct + '/search' + '/' + $scope.searchProducts + '/' + $scope.searchColor + '/' + $scope.searchMaterial + '/' + $scope.searchSize + '/' + $scope.searchDesign + '/' + $scope.searchPriceMin + '/' + $scope.searchPriceMax + '/' + $scope.searchStatus)
                .then(function (response) {
                    $scope.products = response.data;
                    console.log(response);
                })
                .catch(function (error) {
                    console.log(error);
                })
        }
    };
    $scope.GetresetForm = function () {
        $http.get(apiUrlProduct + '/search' + '/' + "undefined" + '/' + "undefined" + '/' + "undefined" + '/' + "undefined" + '/' + "undefined" + '/' + "undefined" + '/' + "undefined" + '/' + "1")
            .then(function (response) {
                $scope.products = response.data;
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            })
    };
    $scope.pagerProducts = {
        page: 0,
        size: 9,
        get products() {
            var start = this.page * this.size;
            return $scope.products.slice(start, start + this.size);

        },
        get count() {
            return Math.ceil(1.0 * $scope.products.length / this.size);
            return $scope.products.slice(start, start + this.size);

        },
        get count() {
            return Math.ceil(1.0 * $scope.products.length / this.size);

        },
        first() {
            this.page = 0;
        },
        prev() {
            this.page--;
            if (this.page < 0) {
                this.first();
                alert("Bạn đang ở trang đầu")
            }
        },
        next() {
            this.page++;
            if (this.page >= this.count) {
                this.last();
                alert("Bạn đang ở trang cuối")
            }
        },
        last() {
            this.page = this.count - 1;
        }
    }
    $scope.resetSearch = function () {
        $('#matesearch').prop('selectedIndex', 0);
        $('#colorSearch').prop('selectedIndex', 0);
        $('#designSearch').prop('selectedIndex', 0);
        $('#statusSearch').prop('selectedIndex', 0);
        $scope.searchColor = "undefined";
        $scope.searchDesign = "undefined";
        $scope.searchMaterial = "undefined";
        $scope.searchSize = "undefined";
        $scope.searchStatus = "1";
        $scope.searchPriceMin = "";
        $scope.searchPriceMax = "";
        $scope.searchProducts = " ";
        $('#sizeSearch').prop('selectedIndex', 0);
        $scope.GetresetForm();
    }
    $scope.resetSearch();
    $scope.getSize()
    $scope.getDesign();
    $scope.GetresetForm();
    $scope.getMaterials();
    $scope.getColors();
    $scope.getCategory();

    $scope.detailProduct = {}
    $scope.idCheck = undefined;
    $scope.getDetailProduct = function (id) {
        console.log(id)
        if (id == 0) {
            id = localStorage.getItem('idDetail');
            $http.post(`/rest/guest/product/product_detail/` + id).then(function (response) {
                $scope.detailProduct = response.data;
                alert($scope.detailProduct.images[0])
            }).catch(error => {
                console.log(error, "lỗi")
            })
        } else {
            localStorage.removeItem('idDetail');
            localStorage.setItem('idDetail', id);
            $window.location.href = '#!product_detail';
        }
    }
    $scope.checkDesign = 0;
    $scope.checkMaterial = 0;
    $scope.checkSize = 0;
    $scope.checkColor = 0;
    $scope.PrD = {};
    $scope.checkProduct = function (id, check) {
        if (check == 0) {
            $scope.checkDesign = id;
        } else if (check == 1) {
            $scope.checkSize = id;
        } else if (check == 2) {
            $scope.checkColor = id;
        } else if (check == 3) {
            $scope.checkMaterial = id;
        }
        if ($scope.checkDesign != 0 && $scope.checkSize != 0 && $scope.checkColor != 0 && $scope.checkMaterial != 0) {
//            let url = 'rest/guest/product/get_detail_product' +'/' +$scope.checkDesign +'/' +$scope.checkSize +'/'+$scope.checkColor +'/'+$scope/checkMaterial
            $http.get(`/rest/guest/product/get_detail_product/` + $scope.checkDesign + `/` + $scope.checkSize + `/` + $scope.checkColor + `/` + $scope.checkMaterial).then(function (response) {
                $scope.PrD = response.data;
                if ($scope.PrD != '') {
                    $scope.checkQuantity = false;
                } else if ($scope.PrD == '') {
                    $scope.checkQuantity = true;
                }
            }).catch(error => {
                console.log(error, 'lỗi check product')
                alert(error);
            })
        }
    }

});