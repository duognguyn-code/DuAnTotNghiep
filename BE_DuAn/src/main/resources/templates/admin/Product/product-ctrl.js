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
    }).controller('MaterialController',function($scope,$http){
      const apiUrl = "http://localhost:8080/api/material";
              console.log(apiUrl);

              $scope.materials = [];
              $scope.formMaterial = {};

              $scope.getMaterials = function() {
                          $http.get(apiUrl)
                              .then(function(response) {
                                  $scope.materials = response.data;
                                  console.log(response);
                              })
                              .catch(function(error) {
                                  console.log(error);
                              });
              };
              $scope.addMaterial = function() {
                          $http.post(apiUrl, $scope.formMaterial)
                              .then(function(response) {
                                  $scope.materials.push(response.data);
                                  $scope.formMaterial = {};
                                  $scope.reset();
                              })
                              .catch(function(error) {
                                  console.log(error);
                              });
              };
              $scope.edit=function(material){
                          $scope.formMaterial=angular.copy(material);
              }
              $scope.update = function(){
                          var item = angular.copy($scope.formMaterial);
                          $http.put(apiUrl+'/'+item.id , item).then(resp =>{
                              var index = $scope.materials.findIndex( p => p.id == item.id);
                              $scope.materials[index] =item;
                              alert("Cập nhật thành công");
                              $scope.reset();
                          }).xath(error =>{
                              alert("Cập nhật thất bại");
                              console.log("Error",error);
                          });
              }
              $scope.deleteMaterial = function(material) {
                          $http.delete(apiUrl + '/' + material.id)
                              .then(function(response) {
                                  var index = $scope.materials.findIndex(p => p.id === material.id);
                                  if (index !== -1) {
                                      $scope.materials.splice(index, 1);
                                  }
                              })
                              .catch(function(error) {
                                  console.log(error);
                              });
              };
              $scope.reset=function(){
                          $scope.formMaterial={
                              status: 1
                          }
              }
              $scope.getMaterials();
    });