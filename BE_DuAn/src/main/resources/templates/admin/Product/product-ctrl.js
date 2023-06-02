angular.module('myApp', [])
    .controller('ProductController', function($scope, $http) {
        const apiUrl = "http://localhost:8080/api/product";
        console.log(apiUrl);

        $scope.products = [];
        $scope.formProduct = {};

        // Lấy danh sách sản phẩm
        $scope.getProducts = function() {
            $http.get(apiUrl)
                .then(function(response) {
                    $scope.products = response.data;
                    console.log(response);
                })
                .catch(function(error) {
                    console.log(error);
                });
        };

        // Thêm sản phẩm mới
        $scope.addProduct = function() {
            $http.post(apiUrl, $scope.formProduct)
                .then(function(response) {
                    $scope.products.push(response.data);
                    $scope.formProduct = {};
                })
                .catch(function(error) {
                    console.log(error);
                });
        };

        // Cập nhật thông tin sản phẩm
        $scope.updateProduct = function(product) {
            $http.put(apiUrl + '/' + product.id, product)
                .then(function(response) {
                    // Cập nhật thông tin sản phẩm trong danh sách
                    var index = $scope.products.findIndex(p => p.id === product.id);
                    if (index !== -1) {
                        $scope.products[index] = response.data;
                    }
                })
                .catch(function(error) {
                    console.log(error);
                });
        };

        // Xóa sản phẩm
        $scope.deleteProduct = function(product) {
            $http.delete(apiUrl + '/' + product.id)
                .then(function(response) {
                    // Xóa sản phẩm khỏi danh sách
                    var index = $scope.products.findIndex(p => p.id === product.id);
                    if (index !== -1) {
                        $scope.products.splice(index, 1);
                    }
                })
                .catch(function(error) {
                    console.log(error);
                });
        };

        // Gọi hàm để lấy danh sách sản phẩm khi trang được tải
        $scope.getProducts();
    });