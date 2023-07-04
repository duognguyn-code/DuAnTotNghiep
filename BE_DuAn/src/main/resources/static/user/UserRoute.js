var app = angular.module("Myapp",["ngRoute"]);
app.config(function($routeProvider){
    $routeProvider
        .when("/",{
            templateUrl:"/user/product/viewProduct.html",
            controller:"UserController"
        })
        .when("/product",{
            templateUrl:"/user/product/Product.html",
            controller:"UserController"
        })
        .when("/cart",{
            templateUrl:"/user/Cart.html",
        })
        .when("/product_detail",{
            templateUrl:"/user/product/ProductDetail.html",
            controller:"UserController"
        })
        .when("/address", {
            templateUrl:"/user/address/addressTable.html",
            controller:"address-form-ctrl"
        })
})