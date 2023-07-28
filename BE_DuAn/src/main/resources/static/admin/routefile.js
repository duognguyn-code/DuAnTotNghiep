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
            controller:"product-change"
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

app.controller("mainAdmin", function($scope,$http) {
    $scope.lang = sessionStorage.getItem('lang');
    if ($scope.lang == null) {
        sessionStorage.setItem('lang', 'vi');
    }
    const API_LANGUAGE_NAV_ADMIN = 'http://localhost:8080/rest/language/nav/admin';
    $http.get(`${API_LANGUAGE_NAV_ADMIN}?lang=${$scope.lang}`)
        .then(resp => {
            $scope.languageNav = resp.data;
            $scope.isLoading = false;
        })
        .catch(error => {
            console.log(error);
            $scope.isLoading = false;
        })
    $scope.languages = [
        { code: 'vi', name: 'Tiếng Việt', img:'https://viblo.asia/images/vi-flag-32x48.png'},
        { code: 'en', name: 'English', img:'https://viblo.asia/images/en-flag-32x48.png'}
    ];
    $scope.currentLanguage = $scope.languages[0].code;
    $scope.showDropdown = false;
    $scope.changeLanguage = function(languageCode) {
        $scope.currentLanguage = languageCode;
    };

    $scope.getCurrentLanguageName = function() {
        for (var i = 0; i < $scope.languages.length; i++) {
            if ($scope.languages[i].code === $scope.currentLanguage) {
                return $scope.languages[i].name;
            }
        }
        return '';
    };
    $scope.isLoading = true;
    $scope.navClick = {
        languageVN() {
            $scope.isLoading = true;
            // $http.get(`${API_CHANGE_LANGUAGE}/vi`)
            //     .then(resp => {
            //         $scope.isLoading = false;
            //         location.reload();
            //     })
            //     .catch(error => {
            //         console.log(error);
            //         $scope.isLoading = false;
            //     })
            sessionStorage.setItem('lang', 'vi');
            location.reload();
        },
        languageUS() {
            sessionStorage.setItem('lang', 'en');
            location.reload();
        }
    }

});