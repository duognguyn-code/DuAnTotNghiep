app.controller('order-ctrl',function($rootScope,$scope,$http,$filter){
    var urlOrder=`http://localhost:8080/api/bill/rest/user/order`;
    $scope.orders=[];
    $scope.form={};
    $rootScope.id=null;
    $scope.logOut= function () {
        $rootScope.account=null;
        localStorage.removeItem('jwtToken');
    }
    $scope.huyDon=async function (id) {
        $scope.form.id = id;

        const {value: text} = await Swal.fire({
            input: 'textarea',
            inputLabel: 'Lý do hủy đơn hàng',
            inputPlaceholder: 'Nhập lý do của bạn ở đây...',
            inputAttributes: {
                'aria-label': 'Type your message here'
            },
            showCancelButton: true
        })
        if (text) {
            $scope.form.note=text;
            Swal.fire({
                title: 'Bạn muốn hủy đơn hàng?',
                text: "Xác nhận không thể khôi phục lại!",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, delete it!'
            }).then((result) => {
                if (result.isConfirmed) {
                    $http.post(urlOrder+'/change',$scope.form).then(function(response){
                        Swal.fire(
                            'Hủy đơn hàng thành công!',
                            'Click để tiếp tục.',
                            'success'
                        )
                        $scope.getAllByUser();
                    }).catch(error=>{
                        Swal.fire(
                            'Hủy đơn hàng thành công!',
                            'Click để tiếp tục.',
                            'success'
                        )
                        $scope.getAllByUser();
                    });
                }
            })
        }
    }
    $scope.getAllByUser=function(){
        $http.get(urlOrder).then(function(response){
            $scope.orders=response.data;
            $rootScope.id = $scope.orders.id;
        }).catch(error=>{
            console.log('error getOrder',error);
        });
    }
    $scope.getOrder=function(id){
        $rootScope.id= id;
    }
    $scope.getOrderDetail=function (id){
        let url=`/api/billDetail/rest/user/`+id;
        $http.get(url).then(function(response){
            if(response.data){
                $scope.ordersDetail=response.data;
            }
        }).catch(error=>{
            console.log(error);
        });

    }
    $scope.getAllByUser();
    $scope.pagerBill = {
        page: 0,
        size: 5,
        get orders() {
            var start = this.page * this.size;
            return $scope.orders.slice(start, start + this.size);

        },
        get count() {
            return Math.ceil(1.0 * $scope.orders.length / this.size);
            return $scope.orders.slice(start, start + this.size);

        },
        get count() {
            return Math.ceil(1.0 * $scope.orders.length / this.size);

        },
        first() {
            this.page = 0;
        },
        prev() {
            this.page--;
            if (this.page < 0) {
                this.first();
                // alert("Bạn đang ở trang đầu")
                $scope.messageSuccess("Bạn đang ở trang đầu");
            }
        },
        next() {
            this.page++;
            if (this.page >= this.count) {
                this.last();
                // alert("Bạn đang ở trang cuối")
                $scope.messageSuccess("Bạn đang ở trang cuối");
            }
        },
        last() {
            this.page = this.count - 1;
        }
    }
});