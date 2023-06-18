app.controller('category-ctrl', function ($rootScope,$scope, $http) {

    const apiUrlCategory = "http://localhost:8080/api/category";
    $scope.categories = [];
    $scope.formCategory = {};

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
        $http.post(apiUrlCategory, $scope.formCategory)
            .then(function (response) {
                $scope.categories.push(response.data);
                $scope.formCategory = {};
                $scope.resetCategory();
                $scope.getCategory();
            })
            .catch(function (error) {
                console.log(error);
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