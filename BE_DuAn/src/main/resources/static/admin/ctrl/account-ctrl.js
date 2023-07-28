app.controller('account-ctrl', function ($rootScope,$scope, $http,$location,$routeParams) {
    const apiUrlAccount = "http://localhost:8080/api/account";
    const apiUrlAuthor = "http://localhost:8080/api/auth";

    $scope.Accounts = [];
    $scope.formAccount = {};;
    $scope.formAccountUpdate={}
    $scope.formAuth={};
    $scope.addAccount = function () {
        var colorData = angular.copy($scope.formAccount)
        var req = {
            method: 'POST',
            url: apiUrlAccount,
            data: colorData
        }
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
            $scope.addAuthor();
            $scope.message("Thêm mới tài khoản thành công");
        }).catch(error => {
            $scope.error('Thêm  mới thất bại');
        });
    };

    $scope.addAuthor= function (){
        var auth={
            role :{  idRole:$scope.formAuth.role},
            account: { username: $scope.formAccount.username}
        }
        // // $scope.formAuth.account.username = $scope.formAccount.username;
        // alert(JSON.stringify(auth));
        // var item = angular.copy(auth)
        $http.post(apiUrlAuthor,auth).then(response => {
            alert("thanh cong")
        }).catch(error => {
            alert("that bai")
        });
    }
    // alert("abc")
    // $scope.getAccounts = function () {
    //     $http.get(apiUrlAccount)
    //         .then(function (response) {
    //             $scope.Accounts = response.data;
    //             alert(JSON.stringify($scope.Accounts))
    //             console.log(response);
    //         })
    //         .catch(function (error) {
    //             console.log(error);
    //         });
    // };
    $scope.getAccounts = function () {
        // alert("abc")
        $http.get(apiUrlAuthor)
            .then(function (response) {
                $scope.Accounts = response.data;
                var item = angular.copy($scope.Accounts)
                alert(item.account.username)
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
    };
    $scope.getAccounts();

    $scope.edit=function (username){
        alert(username)
        if (!$scope.isRedirected) {
            $scope.isRedirected = true;
            $location.path('/UpdateAccount/').search({username: username});
        }
    }
    // alert($routeParams.username)
    $scope.formUpdate=function (){
        var us =$routeParams.username
        $http.get(apiUrlAccount+'/findByUsername'+'/'+us)
            .then(function (response) {
                $scope.formAccountUpdate = response.data;
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    $scope.formUpdate();

    $scope.updateAccount = function (){
        var acc = angular.copy($scope.formAccountUpdate)
        $http.put(apiUrlAccount,acc)
            .then(function (response) {
                alert("Update thành công")
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
    }
    $scope.resetFormUpdate = function (){
       $scope.formUpdate()
    }
    $scope.deleteAccount = function (username) {
        alert(username)
        $http.get(apiUrlAccount+'/findByUsername'+'/'+username)
            .then(function (response) {
                $scope.formAccountUpdate = response.data;
                var acc = angular.copy($scope.formAccountUpdate)
                acc.status =0
                $http.put(apiUrlAccount,acc)
                    .then(function (response) {
                        alert("Xóa thành công")
                        console.log(response);
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });


    };
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