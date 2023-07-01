app.controller('bill-ctrl', function ($rootScope, $scope, $http, $filter,$location,$routeParams) {
    const apiUrlBill = "http://localhost:8080/api/bill";
    const apiUrlBillDetails = "http://localhost:8080/api/billDetail";

    $scope.bills = [];
    $scope.formBill = {};
    $scope.bills1 = [];
    $scope.formBill1 = {};
    $scope.billDetails = [];
    $scope.formBillDetail = {};
    $scope.form = {};
    $scope.status = [
        {id: '', name: "Thay đổi"},
        {id: 1, name: "Chờ xác nhận"},
        {id: 2, name: "Xác nhận"},
        {id: 3, name: "Đang giao hàng"},
        {id: 4, name: "Hoàn tất giao dịch"},
        {id: 5, name: "Hủy đơn"}
    ];
    // $scope.date= "";

    $scope.getBill = function () {
        $http.get(apiUrlBill)
            .then(function (response) {
                $scope.bills = response.data;
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
    };

    // $scope.getBillDetails = function (bill) {
    //     $http.get(apiUrlBill + '/' + bill.id)
    //         .then(function (response) {
    //             $scope.billID = response.data;
    //             console.log(response);
    //         })
    //         .catch(function (error) {
    //             console.log(error);
    //         });
    //
    //
    // };
    $scope.getBillDetail = function () {
        var billId = $routeParams.id;
        $http.get(apiUrlBillDetails+'/'+billId)
            .then(function (response) {
                $scope.billDetails = response.data;
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
    }
    $scope.getBillByID = function () {
        var billId = $routeParams.id;
        $http.get(apiUrlBill+'/'+billId)
            .then(function (response) {
                $scope.bills1 = response.data;
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
    }
    $scope.getBillByID();
    $scope.getBillDetail();
    $scope.getBill();
    $scope.resetSearch = function () {
        $scope.searchPhone = " ";
        $scope.searchStatus = "6";
        $scope.date1 = null;
        $scope.getBill();
    }

    $scope.resetSearch();
    $scope.searchBill = function () {
        if ($scope.searchPhone === "") {
            $scope.searchPhone = " "
        }
        let date1 = $filter('date')($scope.date1, "yyyy/MM/dd");
        let date2 = $filter('date')($scope.date2, "yyyy/MM/dd");
        if (date1 == null) {
            date1 = null
        }
        if (date2 == null) {
            date2 = null
        }
        $http.get(apiUrlBill + '/' + $scope.searchPhone + '/' + $scope.searchStatus + `/date?date1=` + date1+'&&date2='+date2)
            .then(function (response) {
                $scope.bills = response.data;
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            })
    };
    $scope.updateStatus = function (id) {
        Swal.fire({
            title: 'Bạn có chắc muốn đổi trạng thái không?',
            text: "Đổi không thể quay lại!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Xác nhận'
        }).then((result) => {
            if (result.isConfirmed) {
                let timerInterval
                Swal.fire({
                    title: 'Đang gửi thông báo cho khách hàng!',
                    html: 'Vui lòng chờ <b></b> milliseconds.',
                    timer: 4000,
                    timerProgressBar: true,
                    didOpen: () => {
                        Swal.showLoading();
                        $scope.form.id = id;
                        $http.put(apiUrlBill + '/updateStatus', $scope.form).then(function (response) {
                            if (response.data) {
                                $scope.form.status = null;
                                $scope.getBill();
                                $scope.messageSuccess("Đổi trạng thái thành công");
                            } else {
                                $scope.messageError("Đổi trạng thái thất bại");
                            }
                        }).catch(error => {
                            $scope.messageError("Đổi trạng thái thất bại");
                        });
                        const b = Swal.getHtmlContainer().querySelector('b')
                        timerInterval = setInterval(() => {
                            b.textContent = Swal.getTimerLeft()
                        }, 100)
                    },
                    willClose: () => {
                        clearInterval(timerInterval)
                    }
                }).then((result) => {
                    /* Read more about handling dismissals below */
                    if (result.dismiss === Swal.DismissReason.timer) {
                        console.log('I was closed by the timer')
                    }
                })
            }
        })
    }
    $scope.messageSuccess=function (text) {
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
            title: text
        })
    }
    $scope.messageError=function (text) {
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
            icon: 'error',
            title: text
        })
    }


    $scope.edit = function(billId) {
        if (!$scope.isRedirected) {
            $scope.isRedirected = true;
            $location.path('/billDetail/').search({id: billId});
        }
    };
    $scope.pagerBill = {
        page: 0,
        size: 5,
        get bills() {
            var start = this.page * this.size;
            return $scope.bills.slice(start, start + this.size);

        },
        get count() {
            return Math.ceil(1.0 * $scope.bills.length / this.size);
            return $scope.bills.slice(start, start + this.size);

        },
        get count() {
            return Math.ceil(1.0 * $scope.bills.length / this.size);

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