app.controller('category-ctrl', function ($rootScope,$scope, $http) {

    const apiUrlCategory = "http://localhost:8080/api/category";
    $scope.categories = [];
    $scope.formCategory = {};

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

    $scope.getCategory = function () {
        $http.get(apiUrlCategory)
            .then(function (response) {
                $scope.categories = response.data;
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
    };
    $scope.addCategory = function () {
        var categoryData = {
            name: $scope.formCategory.name,
            status: $scope.formCategory.status,
            type: $scope.formCategory.type
        };
        var req = {
            method: 'POST',
            url: apiUrlCategory,
            data: categoryData
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
            $scope.message("thêm mới thể loại thành công");
            $scope.resetCategory();
            $scope.getCategory();
        }).catch(error => {
            $scope.error('thêm mới thất bại');
        });
    };
    $scope.editCategory = function (category) {
        $scope.formCategory = angular.copy(category);
    }
    $scope.updateCategory = function () {
        var item = angular.copy($scope.formCategory);
        $http.put(apiUrlCategory + '/' + item.id, item).then(resp => {
            var index = $scope.categories.findIndex(p => p.id == item.id);
            $scope.categories[index] = item;
            alert("Cập nhật thành công");
            $scope.resetCategory();
            $scope.getCategory();
        }).catch(error => {
            alert("Cập nhật thất bại");
            console.log("Error", error);
        });
    }
    $scope.deleteCategory = function (size) {
        $http.delete(apiUrlCategory + '/' + size.id)
            .then(function (response) {
                var index = $scope.categories.findIndex(p => p.id === size.id);
                if (index !== -1) {
                    $scope.categories.splice(index, 1);
                    $scope.getCategory();
                }
            })
            .catch(function (error) {
                console.log(error);
            });
    };
    $scope.resetCategory = function () {
        $scope.formCategory = {
            status: 1
        }
    }
    $scope.getCategory();
});