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
    $scope.addMaterial = function () {
        $http.post(apiUrlDesign, $scope.formMaterial)
            .then(function (response) {
                $scope.materials.push(response.data);
                $scope.formMaterial = {};
                $scope.resetMaterial();
            })
            .catch(function (error) {
                console.log(error);
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