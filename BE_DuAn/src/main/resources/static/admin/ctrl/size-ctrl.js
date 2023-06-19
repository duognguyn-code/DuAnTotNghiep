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
    $scope.addSize = function () {
        $http.post(apiUrlSize, $scope.formSize)
            .then(function (response) {
                $scope.sizes.push(response.data);
                $scope.formSize = {};
                $scope.resetSize();
                $scope.getSize();
            })
            .catch(function (error) {
                console.log(error);
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