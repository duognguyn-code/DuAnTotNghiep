app.controller('material', function ($rootScope,$scope, $http) {

    const apiUrlDesign = "http://localhost:8080/api/material";
    $scope.materials = [];
    $scope.formMaterial = {};

    $scope.pagerMaterial = {
        page: 0,
        size: 5,
        get materials() {
            var start = this.page * this.size;
            return $scope.materials.slice(start, start + this.size);

        },
        get count() {
            return Math.ceil(1.0 * $scope.materials.length / this.size);

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

    $scope.getMaterials = function () {
        $http.get(apiUrlDesign)
            .then(function (response) {
                $scope.materials = response.data;
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
    $scope.addMaterial = function () {
        var materialData = {
            name: $scope.formMaterial.name,
        };
        var req = {
            method: 'POST',
            url: apiUrlDesign,
            data: materialData
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
            $scope.message("thêm mới chất liệu thành công");
            $scope.resetMaterial();
            $scope.getMaterials();
        }).catch(error => {
            $scope.error('thêm mới thất bại');
        });
    };
    $scope.editMaterial = function (material) {
        $scope.formMaterial = angular.copy(material);
    }
    $scope.updateMaterial = function () {
        var item = angular.copy($scope.formMaterial);
        $http.put(apiUrlDesign + '/' + item.id, item).then(resp => {
            var index = $scope.materials.findIndex(p => p.id == item.id);
            $scope.materials[index] = item;
            alert("Cập nhật thành công");
            $scope.resetMaterial();
        }).catch(error => {
            alert("Cập nhật thất bại");
            console.log("Error", error);
        });
    }
    $scope.deleteMaterial = function (material) {
        $http.delete(apiUrlDesign + '/' + material.id)
            .then(function (response) {
                var index = $scope.materials.findIndex(p => p.id === material.id);
                if (index !== -1) {
                    $scope.materials.splice(index, 1);
                }
            })
            .catch(function (error) {
                console.log(error);
            });
    };
    $scope.resetMaterial = function () {
        $scope.formMaterial = {
            status: 1
        }
    }


    $scope.getMaterials();


});