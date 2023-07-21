app.controller('billDetails-ctrl', function ($rootScope,$scope, $http,$routeParams) {
    const apiUrlBillDetails = "http://localhost:8080/api/billDetail";
    const apiUrlBill = "http://localhost:8080/api/bill";

    $scope.bill = [];
    $scope.formBill={};
    $scope.billDetails = [];
    $scope.formbillDetails = {};
    $scope.items= [];
    $scope.getBillByID = function () {
        var billId = $routeParams.id;
        $http.get(apiUrlBill+'/'+billId)
            .then(function (response) {
                $scope.bill = response.data;

                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
    }
    $scope.getBillByID();
    $scope.cop=function (){
        var item = $scope.bill.find(item => item.id == $routeParams.id)
        var item1 = $scope.billDetails.find(item => item.bill.id == $routeParams.id)
        $scope.formBill = angular.copy(item);
        $scope.formbillDetails = angular.copy(item1);
    }
    $scope.getBillDetail = function () {
        var billId = $routeParams.id;
        alert(billId);
        $http.get(apiUrlBillDetails+'/'+billId)
            .then(function (response) {
                $scope.billDetails = response.data;
                $scope.items.push(response.data);
                console.log(response);
                $scope.cop();
            })
            .catch(function (error) {
                console.log(error);
            });
    }
    $scope.getBillDetail();

    $scope.updateBill=function (){
        var item = angular.copy($scope.formBill);
        $http.put(apiUrlBill + '/updateBill', item).then(resp => {
            var index = $scope.bill.findIndex(p => p.id == item.id);
            $scope.bill[index] = item;
            alert("Cập nhật thành công");
            $scope.cop();
        }).catch(error => {
            alert("Cập nhật thất bại");
            console.log("Error", error);
        });
    }
    $scope.updateQuantity = function (bill){
        var item = angular.copy(bill);
        item.quantity = bill.quantity
        $http.put(apiUrlBillDetails + '/updateBillDetail', item).then(resp => {
            var index = $scope.billDetails.findIndex(p => p.id == item.id);
            $scope.billDetails[index] = item;
            alert("Cập nhật thành công");
            $scope.upd();
            $scope.cop();
        }).catch(error => {
            alert("Cập nhật thất bại");
            console.log("Error", error);
        });

    }
    $scope.upd=function (){
        var totalMn = $scope.formBill.moneyShip + $scope.checktotal.checkbill

        $http.put(apiUrlBill + '/updateTotalMoney' +'/'+totalMn +'/'+$routeParams.id).then(resp => {
            alert("Cập nhật thành công");
        }).catch(error => {
            alert("Cập nhật thất bại");
            console.log("Error", error);
        });

    }
     $scope.checktotal={
    get checkbill() {
        return $scope.billDetails.map(item => item.quantity * item.price)
            .reduce((total, qty) => total += qty, 0);
    }
    }
});