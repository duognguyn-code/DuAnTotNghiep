var app = angular.module("Myapp",["ngRoute"]);
app.config(function($routeProvider){
    $routeProvider
        .when("/",{
            templateUrl:"/user/viewProduct.html",
            controller:"UserController"
        })
        .when("/product",{
            templateUrl:"/user/Product.html",
        })
        .when("/cart",{
            templateUrl:"/user/Cart.html",
        })
        .when("/product_detail",{
            templateUrl:"/user/ProductDetail.html",
        })
})