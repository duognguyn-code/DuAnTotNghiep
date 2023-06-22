app.controller('size-ctrl', function ($rootScope,$scope, $http) {

    const apiUrlSize = "http://localhost:8080/api/size";
    $scope.sizes = [];
    $scope.formSize = {};

    $scope.getSize = function () {
        $http.get(apiUrlSize)
            .then(function (response) {
                $scope.sizes = response.data;
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
    $scope.addSize = function () {
        var sizeData = {
            name: $scope.formSize.name,
        };
        var req = {
            method: 'POST',
            url: apiUrlSize,
            data: sizeData
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
            console.log("ddd " + response);
            $scope.message("thêm mới size thành công");
            $scope.resetSize();
            $scope.getSize();
        }).catch(error => {
            $scope.error('thêm mới thất bại');
        });
    };
    $scope.editSize = function (size) {
        $scope.formSize = angular.copy(size);
    }
    $scope.updateSize = function () {
        var item = angular.copy($scope.formSize);
        $http.put(apiUrlSize + '/' + item.id, item).then(resp => {
            var index = $scope.sizes.findIndex(p => p.id == item.id);
            $scope.sizes[index] = item;
            alert("Cập nhật thành công");
            $scope.resetSize();
            $scope.getSize();
        }).catch(error => {
            alert("Cập nhật thất bại");
            console.log("Error", error);
        });
    }
    $scope.deleteSize = function (size) {
        $http.delete(apiUrlSize + '/' + size.id)
            .then(function (response) {
                var index = $scope.sizes.findIndex(p => p.id === size.id);
                if (index !== -1) {
                    $scope.sizes.splice(index, 1);
                    $scope.getSize();
                }
            })
            .catch(function (error) {
                console.log(error);
            });
    };
    $scope.resetSize = function () {
        $scope.formSize = {
            status: 1
        }
    }
    $scope.getSize();
});