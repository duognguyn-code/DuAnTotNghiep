var app = angular.module("Myapp",["ngRoute"]);
app.config(function($routeProvider){
    $routeProvider
        .when("/home/main",{
            templateUrl:"/admin/Product/ListProduct.html",
            controller:"main"
        })
        .when("/designs",{
            templateUrl:"/admin/Product/Designs.html",
            controller:"design"
        })
        .when("/createproduct",{
            templateUrl:"/admin/Product/CreateProduct.html",
            controller:"productController"
        })
        .when("/listproduct",{
            templateUrl:"/admin/Product/ListProduct.html",
            controller:"productController"
        })
        .when("/color",{
            templateUrl:"/admin/Product/color.html",
            controller:"color"
        })
        .when("/material",{
            templateUrl:"/admin/Product/material.html",
            controller:"material"
        })
        .when("/size",{
            templateUrl:"/admin/Product/size.html",
            controller:"size-ctrl"
        })
})