app.controller('color', function ($rootScope,$scope, $http) {
    const apiUrlColor = "http://localhost:8080/api/color";

    $scope.colors = [];
    $scope.formColor = {};


    $scope.getColors = function () {
        $http.get(apiUrlColor)
            .then(function (response) {
                $scope.colors = response.data;
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
    };
    $scope.addColor = function () {
        $http.post(apiUrlColor, $scope.formColor)
            .then(function (response) {
                $scope.colors.push(response.data);
                $scope.formColor = {};
                $scope.resetColor();
            })
            .catch(function (error) {
                console.log(error);
            });
    };
    $scope.editColor = function (color) {
        $scope.formColor = angular.copy(color);
    }
    $scope.updateColor = function () {
        var item = angular.copy($scope.formColor);
        $http.put(apiUrlColor + '/' + item.id, item).then(resp => {
            var index = $scope.colors.findIndex(p => p.id == item.id);
            $scope.colors[index] = item;
            alert("Cập nhật thành công");
            $scope.resetColor();
        }).catch(error => {
            alert("Cập nhật thất bại");
            console.log("Error", error);
        });
    }
    $scope.deleteColor = function (color) {
        $http.delete(apiUrlColor + '/' + color.id)
            .then(function (response) {
                var index = $scope.colors.findIndex(p => p.id === color.id);
                if (index !== -1) {
                    $scope.colors.splice(index, 1);
                }
            })
            .catch(function (error) {
                console.log(error);
            });
    };
    $scope.resetColor = function () {
        $scope.formColor = {
            status: 1
        }
    }
    $scope.pagerColor = {
        page: 0,
        size: 5,
        get colors() {
            var start = this.page * this.size;
            return $scope.colors.slice(start, start + this.size);

        },
        get count() {
            return Math.ceil(1.0 * $scope.colors.length / this.size);

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


    $scope.getColors();


});