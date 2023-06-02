app.controller('product-ctrl', function ($scope, $http, $rootScope){

    const pathApiProduct = "/api/product";
    $scope.products = [];
    $scope.getProducts = function () {
        $http.get(`${pathAPI}/page/pushedlist?page=0`, token).then(function (response) {
            $scope.products = response.data.list;
            $scope.totalPages = response.data.totalPages;
            $scope.currentPage = response.data.currentPage;
        }).catch(error => {
            console.log(error);
        });
        $scope.getCategories();
        $scope.getRam();
        $scope.getColor();
        $scope.getCapacity();
    }
});