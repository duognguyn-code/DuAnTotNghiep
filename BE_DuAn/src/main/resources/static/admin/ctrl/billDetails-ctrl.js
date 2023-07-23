app.controller('billDetails-ctrl', function ($rootScope,$scope, $http,$routeParams) {
    const apiUrlBillDetails = "http://localhost:8080/api/billDetail";
    const apiUrlBill = "http://localhost:8080/api/bill";

    $scope.bill = [];
    $scope.formBill={};
    $scope.billDetails = [];
    $scope.formbillDetails = {};
    $scope.items= [];
    $scope.getBillDetailForMoney =[];
    $scope.getBillByID = function () {
        var billId = $routeParams.idBill;
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


        var item = $scope.bill.find(item => item.id == $routeParams.idBill )
        var item1 = $scope.billDetails.find(item => item.bill.id == $routeParams.idBill)

        $scope.formBill = angular.copy(item);
    }
    $scope.getBillDetail = function () {
        var billId = $routeParams.idBill;
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
    $scope.getBillDetailForMoney1 = function () {
        var billId = $routeParams.idBill;
        $http.get(apiUrlBillDetails+'/forMoney'+'/'+billId)
            .then(function (response) {
                $scope.getBillDetailForMoney = response.data;
                $scope.items.push(response.data);
                console.log(response);
                $scope.cop();
            })
            .catch(function (error) {
                console.log(error);
            });
    }
    $scope.getBillDetailForMoney1();
    // $scope.updateBill=function (){
    //     var item = angular.copy($scope.formBill);
    //     $http.put(apiUrlBill + '/updateBill', item).then(resp => {
    //         var index = $scope.bill.findIndex(p => p.id == item.id);
    //         $scope.bill[index] = item;
    //         alert("Cập nhật thành công");
    //         $scope.cop();
    //     }).catch(error => {
    //         alert("Cập nhật thất bại");
    //         console.log("Error", error);
    //     });
    // }
    $scope.updateQuantity = function (bill){
        var item = angular.copy(bill);
        item.quantity = bill.quantity;
        if (bill.status ===5||bill.status ===4||bill.status ===3){
            alert("Bạn Không Thể Sửa Sản Phầm Này")
            return
        }
        $http.put(apiUrlBillDetails + '/updateBillDetail', item).then(resp => {
            var index = $scope.getBillDetailForMoney.findIndex(p => p.id == item.id);
            $scope.getBillDetailForMoney[index] = item;
            alert("Cập nhật thành công1");
            $scope.updateToTalMoney();
        }).catch(error => {
            alert("Cập nhật thất bại12");
            console.log("Error", error);
        });
    }
    $scope.CancelBillDetails= function (bill){
        var item = angular.copy(bill);
        item.status = 5;
        var item1 = angular.copy(bill);
        item1.status = 5;
        item1.quantity=0;
        $http.put(apiUrlBillDetails + '/updateBillDetail',item).then(resp => {
            var index = $scope.billDetails.findIndex(p => p.id == item.id);
            $scope.billDetails[index] = item;
            var index1 = $scope.getBillDetailForMoney.findIndex(p => p.id == item1.id);
            $scope.getBillDetailForMoney[index1] = item1;
            alert("Hủy hàng thành công");
            $scope.updateToTalMoney();
            $scope.CancelBill();
        }).catch(error => {
            alert("Hủy hàng thất bại");
            console.log("Error", error);
        });
    }
    $scope.CancelBill= function (){
        var item = angular.copy($scope.formBill)
        $http.put(apiUrlBill + '/updateStatus'+'/'+$routeParams.idBill,item).then(function (response) {
            if (response.data) {
            } else {
            }
        }).catch(error => {
        });
    }
    $scope.updateToTalMoney=function (){
        var totalMn = $scope.formBill.moneyShip + $scope.checktotal.checkbill
        alert( $scope.checktotal.checkbill)
        $http.put(apiUrlBill + '/updateTotalMoney' +'/'+totalMn +'/'+$routeParams.idBill).then(resp => {
           $scope.formBill.totalMoney =totalMn;
        }).catch(error => {
            console.log("Error", error);
        });

    }
     $scope.checktotal={
    get checkbill() {
        return $scope.getBillDetailForMoney.map(item => item.quantity * item.price)
            .reduce((total, qty) => total += qty, 0);
    }
    } ;
    $scope.pagerbillDetails = {
        page: 0,
        size: 5,
        get billdetails() {
            var start = this.page * this.size;
            return $scope.billDetails.slice(start, start + this.size);

        },
        get count() {
            return Math.ceil(1.0 * $scope.billDetails.length / this.size);
            return $scope.billDetails.slice(start, start + this.size);

        },
        get count() {
            return Math.ceil(1.0 * $scope.billDetails.length / this.size);

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
});