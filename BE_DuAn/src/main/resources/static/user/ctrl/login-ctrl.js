app.controller('login-ctrl',function($rootScope,$scope,$http,$window){
    $scope.form = {};
    $scope.jwt ;
    const pathAPI = "http://localhost:8080/api/auth/signin";

    $scope.message = function (mes){
        const Toast = Swal.mixin({
            toast: true,
            position: 'top-end',
            showConfirmButton: false,
            timer: 1500,
            timerProgressBar: true,
            didOpen: (toast) => {
                toast.addEventListener('mouseenter', Swal.stopTimer)
                toast.addEventListener('mouseleave', Swal.resumeTimer)
            }
        })
        Toast.fire({
            icon: 'success',
            title: mes,
        })
    }
    $scope.error =  function (err){
        const Toast = Swal.mixin({
            toast: true,
            position: 'top-end',
            showConfirmButton: false,
            timer: 1500,
            timerProgressBar: true,
            didOpen: (toast) => {
                toast.addEventListener('mouseenter', Swal.stopTimer)
                toast.addEventListener('mouseleave', Swal.resumeTimer)
            }
        })

        Toast.fire({
            icon: 'error',
            title: err,
        })
    }

    localStorage.removeItem('jwtToken');
    $scope.onLogin = function () {
        console.log(pathAPI)
        $http.post(pathAPI, JSON.stringify($scope.form)).then(respon =>{
            $scope.message('Đăng nhập thành công');
            localStorage.setItem('jwtToken', respon.data.token);
            $scope.jwt = localStorage.getItem('jwtToken')
            $rootScope.account=respon.data;
            $window.location.href = 'http://localhost:8080/user/index.html#!';
            location.reload();
        }).catch(error => {
            $scope.error('Đăng nhập thất bại');
            console.log(error)
            alert(error)
            $rootScope.account=null;
        })
    }

    $scope.logOut= function () {
        alert("dang xuat ben login")
        $rootScope.account=null;
        localStorage.removeItem('jwtToken');
    }
})