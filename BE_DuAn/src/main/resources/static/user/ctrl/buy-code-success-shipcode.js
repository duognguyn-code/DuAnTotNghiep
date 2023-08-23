const app = angular.module('app-buyd', ['ngRoute']);
app.controller('buy-cod-success-shipcode-ctrl',function($scope,$window,$timeout,$http){
    var urlOrder = "http://localhost:8080/rest/guest/order";

    $scope.show = function () {
        Swal.fire({
            title: 'Đặt hàng thành công!',
            text: 'Quý khách sẽ được chuyển đến trang chủ sau giây lát',
            width: 600,
            padding: '3em',
            color: '#716add',
            background: '#fff url(/images/trees.png)',
            backdrop: `
            rgba(0,0,123,0.4)
            url("/images/accommodation/1.jpg")
            left top
            no-repeat
         `
        });

        $timeout(function () {
            $window.location.href = 'http://localhost:8080/user/index.html#!';
        }, 4000);
    };
    $scope.show();

})