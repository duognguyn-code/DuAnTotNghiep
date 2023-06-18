app.controller('productController', function ($rootScope,$scope, $http) {
    const apiUrlProduct = "http://localhost:8080/api/product";

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
    $scope.index = 0;


    // Lấy danh sách sản phẩm
    $scope.getProducts = function () {
        $http.get(apiUrlProduct)
            .then(function (response) {
                $scope.products = response.data;
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
    };
    // $scope.addProduct = function () {
    //     $http.post(apiUrlProduct, $scope.formProduct)
    //         .then(function (response) {
    //             $scope.products.push(response.data);
    //             $scope.formProduct = {};
    //             $scope.resetProducts();
    //         })
    //         .catch(function (error) {
    //             console.log(error);
    //         });
    // };
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
            headers: { 'Content-Type': undefined }
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
    $scope.deleteProduct = function (product) {
        $http.delete(apiUrlProduct + '/' + product.id)
            .then(function (response) {
                // Xóa sản phẩm khỏi danh sách
                var index = $scope.products.findIndex(p => p.id === product.id);
                if (index !== -1) {
                    $scope.products.splice(index, 1);
                }
            })
            .catch(function (error) {
                console.log(error);
            });
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
    $scope.getSize()
    $scope.getDesign();
    $scope.getProducts();
    $scope.getMaterials();
    $scope.getColors();
    $scope.getCategory();

});