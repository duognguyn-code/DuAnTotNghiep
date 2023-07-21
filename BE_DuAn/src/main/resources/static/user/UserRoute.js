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
            templateUrl:"/user/cart/Cart.html",
            controller:"UserController"
        })
        .when("/product_detail",{
            templateUrl:"/user/product/ProductDetail.html",
            controller:"UserController"
        })
        .when("/address", {
            templateUrl:"/user/address/addressTable.html",
            controller:"address-form-ctrl"
        })
        .when("/purchase", {
            templateUrl:"/user/purchase/purchase.html",
            controller:"order-ctrl"
        })
        .when("/purchaseDetail", {
            templateUrl:"/user/purchase/purchaseDetail.html",
            controller:"order-detail-ctrl"
        })
        .when("/contact", {
            templateUrl:"/user/product/contact.html",
        })
})