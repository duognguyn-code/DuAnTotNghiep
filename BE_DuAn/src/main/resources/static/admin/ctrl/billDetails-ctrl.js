app.controller('billDetails-ctrl', function ($rootScope,$scope, $http) {
    const apiUrlBillDetails = "http://localhost:8080/api/billDetail";

    $scope.billDetails=[];
    $scope.billID=[];
    $scope.formBillDetail={};
    $scope.formBill={};

    $scope.getBillDetails = function () {
        $http.get(apiUrlBillDetails)
            .then(function (response) {
                $scope.billDetails = response.data;
                console.log(response);

            })
            .catch(function (error) {
                console.log(error);
            });
            $http.get(apiUrlBillDetails+'/getBillByID')
                .then(function (response) {
                    $scope.billID = response.data;
                    console.log(response);
                })
                .catch(function (error) {
                    console.log(error);
                });
        // $scope.set();

    }
    $scope.set =function (bil){
    $scope.formBill = angular.copy(bil);
    alert($scope.formBill+"aaaasdas")
    }
    $scope.updateBill = function () {
        var item = angular.copy($scope.formBill);
        alert(item.id)
        $http.put(apiUrlBillDetails + '/' + item.id, item).then(resp => {
            var index = $scope.billID.findIndex(p => p.id == item.id);
            $scope.billID[index] = item;
            alert("Cập nhật thành công");
            $scope.resetColor();
        }).catch(error => {
            alert("Cập nhật thất bại");
            console.log("Error", error);
        });
    }
    // $scope.reset= function (){
    //     $scope.designSearch=
    // }
    $scope.getBillDetails();

});