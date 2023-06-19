app.controller('design', function ($rootScope,$scope, $http) {
    const apiUrlDesign = "http://localhost:8080/api/design";

    $scope.designs = [];
    $scope.formDesign = {};


    $scope.pagerDesign = {
        page: 0,
        size: 5,
        get designs() {
            var start = this.page * this.size;
            return $scope.designs.slice(start, start + this.size);

        },
        get count() {
            return Math.ceil(1.0 * $scope.designs.length / this.size);

        },
        first() {
            this.page = 0;
        },
        prev() {
            this.page--;
            if (this.page < 0) {
                this.first();
                alert("Bạn đang ở trang đầu")
            }
        },
        next() {
            this.page++;
            if (this.page >= this.count) {
                this.last();
                alert("Bạn đang ở trang cuối")
            }
        },
        last() {
            this.page = this.count - 1;
        }
    }
    $scope.getDesign = function () {
        $http.get(apiUrlDesign)
            .then(function (response) {
                $scope.designs = response.data;
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
    $scope.addDesign = function () {
        var designData = {
            name: $scope.formDesign.name,
            status: $scope.formDesign.status
        };
        var req = {
            method: 'POST',
            url: apiUrlDesign,
            data: designData
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
            $scope.message("thêm mới kiểu dáng thành công");
            $scope.resetDesign();
            $scope.getDesign();
        }).catch(error => {
            $scope.error('thêm mới thất bại');
        });
    };
    $scope.editDesign = function (design) {
        $scope.formDesign = angular.copy(design);
    }
    $scope.updateDesign = function () {
        var item = angular.copy($scope.formDesign);
        $http.put(apiUrlDesign + '/' + item.id, item).then(resp => {
            var index = $scope.designs.findIndex(p => p.id == item.id);
            $scope.designs[index] = item;
            alert("Cập nhật thành công");
            $scope.resetDesign();
        }).catch(error => {
            alert("Cập nhật thất bại");
            console.log("Error", error);
        });
    }
    $scope.deleteDesign = function (design) {
        $http.delete(apiUrlDesign + '/' + design.id)
            .then(function (response) {
                var index = $scope.designs.findIndex(p => p.id === design.id);
                if (index !== -1) {
                    $scope.designs.splice(index, 1);
                }
            })
            .catch(function (error) {
                console.log(error);
            });
    };
    $scope.resetDesign = function () {
        $scope.formDesign = {
            status: 1
        }
    }
    $scope.getDesign();
});