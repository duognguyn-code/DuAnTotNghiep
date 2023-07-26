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
        .when("/Pageupdateproduct",{
            templateUrl:"/admin/Product/UpdateProduct.html",
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
        .when("/category",{
            templateUrl:"/admin/Product/Category.html",
            controller:"category-ctrl"
        })
        .when("/cart",{
            templateUrl:"/admin/Cart/List_Cart.html",
            controller:"cart-ctrl"
        })
        .when("/cartdetail",{
            templateUrl:"/admin/Cart/CartDetails.html",
            controller:"cart-ctrl"
        })
        .when("/bill",{
            templateUrl:"/admin/Bill/List_Bill.html",
            controller:"bill-ctrl"
        })
        .when("/billDetail",{
            templateUrl:"/admin/Bill/List_BillDetail.html",
            controller:"billDetails-ctrl"
        })
        .when("/payment",{
            templateUrl:"/admin/Payment/Payment.html",
            controller:"cart_admin-ctrl"
        })
        .when("/statistical",{
            templateUrl:"/admin/Statistical/statistical.html",
        })
        .when("/productReturn",{
            templateUrl:"/admin/Bill/Product_Return.html",
        })
        .when("/Account",{
             templateUrl:"/admin/Account/Account.html",
            controller:"account-ctrl"
        })
        .when("/CreateAccount",{
             templateUrl:"/admin/Account/CreateAccount.html",
            controller:"account-ctrl"
        })
        .when("/UpdateAccount",{
             templateUrl:"/admin/Account/UpdateAccount.html",
            controller:"account-ctrl"
        })
})