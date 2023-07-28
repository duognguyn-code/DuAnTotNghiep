app.controller('account-ctrl', function ($rootScope,$scope, $http,$location) {
    const apiUrlAccount = "http://localhost:8080/api/account";
    const apiUrlAuthor = "http://localhost:8080/api/auth";

    $scope.Accounts = [];
    $scope.formAccount = {};
    $scope.roles1 = [];
    $scope.roles = [
        { id: 1, name: 'Admin' },
        { id: 2, name: 'Staff' },
        { id: 3, name: 'User' },
        { id: 4, name: 'Guest' }
    ];
    $scope.selectedRole = $scope.roles[0];

    $scope.formAuth={};
    $scope.addAccount = function () {
        var colorData = angular.copy($scope.formAccount);
        colorData.role = $scope.selectedRole.id;
        var req = {
            method: 'POST',
            url: "http://localhost:8080/api/account/create",
            data: colorData
        }
        alert("đây 1")
        let timerInterval
        Swal.fire({
            title: 'Đang thêm  mới vui lòng chờ!',
            html: 'Vui lòng chờ <b></b> milliseconds.',
            timer: 5500,
            timerProgressBar: true,
            didOpen: () => {
                Swal.showLoading()
                const b = Swal.getHtmlContainer().querySelector('b')
                timerInterval = setInterval(() => {
                    b.textContent = Swal.getTimerLeft()
                }, 100)
            },
            willClose: () => {
                clearInterval(timerInterval)
            }
        });
        $http(req).then(response => {
            alert("đây")
            $scope.message("Thêm mới tài khoản thành công");
        }).catch(error => {
            alert(error)
            console.log(error);
            $scope.error('Thêm  mới thất bại');
        });
    };

    $scope.addAuthor= function (username, roleId){
        var auth={
            role :{  idRole:roleId},
            account: { username: username}
        }
        $http.post(apiUrlAuthor,auth).then(response => {
            alert("thanh cong")
        }).catch(error => {
            alert("that bai")
        });
    }
    $scope.getALlRole = function (){
        $http.get(apiUrlAccount + "/getAllrole").then(resp => {
            $scope.roles1 = resp.data;
            // console.log($scope.roles)
        }).catch(error => {
            console.log(error);
        });
    }
    $scope.getALlRole();
    $scope.getAccounts = function () {
        $http.get(apiUrlAccount)
            .then(function (response) {
                $scope.Accounts = response.data;
                alert(JSON.stringify($scope.Accounts))
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
    };
    $scope.getAccounts();

    $scope.edit=function (username){
        if (!$scope.isRedirected) {
            $scope.isRedirected = true;
            $location.path('/UpdateAccount/').search({username: username});
        }
    }
    // alert($routeParams.username)
    $scope.formUpdate=function (){

    }


    $scope.message = function (mes) {
        const Toast = Swal.mixin({
            toast: true,
            position: 'top-end',
            showConfirmButton: false,
            timer: 2000,
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
    $scope.error = function (err) {
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
})