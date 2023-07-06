app.controller('cart_admin-ctrl', function ($rootScope,$scope, $http) {
    const apiUrlCart = "http://localhost:8080/api/cart";
    const apiUrlBill = "http://localhost:8080/api/bill";
    const apiUrlProduct = "http://localhost:8080/api/product";
    $scope.cart=[];
    $scope.formCart={};
    $scope.products = [];
    $scope.formProduct = {};
    $scope.productData = {};
    $scope.sizes = [];
    $scope.formSize = {};
    $scope.colors = [];
    $scope.formColor = {};
    $scope.materials = [];
    $scope.formMaterial = {};
    $scope.designs = [];
    $scope.formDesign = {};
    $scope.categories = [];
    $scope.formCategory = {};
    $scope.index = 0;
    $scope.checkButton = true;
    $scope.checkSubmit = false;

    $scope.message = function (mes) {
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
            title: mes,
        })
    }
    $scope.error = function (err) {
        const Toast = Swal.mixin({
            toast: true,
            position: 'top-end',
            showConfirmButton: false,
            timer: 1500,
            timerProgressBar: true,
            didOpen: (toast) => {
                toast.addEventListener('mouseenter', Swal.stopTimer)
                toast.addEventListener('mouseleave', Swal.resumeTimer)
            }
        })

        Toast.fire({
            icon: 'error',
            title: err,
        })
    }

    // Lấy danh sách sản phẩm
    $scope.getProducts = function () {
        $http.get(apiUrlProduct)
            .then(function (response) {
                $scope.products = response.data;
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
    };
    $scope.detailProduct = {}
    $scope.idCheck = undefined;
    $scope.getDetailProduct = function (id) {
        console.log(id)
        if (id == 0) {
            id = localStorage.getItem('idDetail');
            $http.post(`/rest/guest/product/product_detail/` + id).then(function (response) {
                $scope.detailProduct = response.data;
            }).catch(error => {
                console.log(error, "lỗi")
            })
        } else {
            localStorage.removeItem('idDetail');
            localStorage.setItem('idDetail', id);
            $window.location.href = '#!product_detail';
        }
    }
    $scope.checkDesign = 0;
    $scope.checkMaterial = 0;
    $scope.checkSize = 0;
    $scope.checkColor = 0;
    $scope.PrD={};
    $scope.checkProduct = function (id, check){

        if(check==0){
            $scope.checkDesign=id;
        }else if(check==1){
            $scope.checkSize=id;
        }else if(check==2){
            $scope.checkColor=id;
        }else if(check==3){
            $scope.checkMaterial=id;
        }
        if($scope.checkDesign!=0 && $scope.checkSize!=0 && $scope.checkColor!=0 && $scope.checkMaterial!=0){
//            let url = 'rest/guest/product/get_detail_product' +'/' +$scope.checkDesign +'/' +$scope.checkSize +'/'+$scope.checkColor +'/'+$scope/checkMaterial
            $http.get(`rest/guest/product/get_detail_product/` +$scope.checkDesign +`/` +$scope.checkSize +`/`+$scope.checkColor +`/`+$scope.checkMaterial).then(function(response){
                $scope.PrD = response.data;
                if($scope.PrD!=''){
                    $scope.checkQuantity = false;
                }else if($scope.PrD==''){
                    $scope.checkQuantity = true;
                }
                alert($scope.checkQuantity);
            }).catch(error => {
                console.log(error,'lỗi check product')
            })
        }
    };

    // Cập nhật thông tin sản phẩm

    // Xóa sản phẩm

    // Thêm sản phẩm mới


    $scope.getColors = function () {
        $http.get(`${apiUrlProduct}/getAllColor`)
            .then(function (response) {
                $scope.colors = response.data;
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
    };

    $scope.getMaterials = function () {
        $http.get(`${apiUrlProduct}/getAllMaterial`)
            .then(function (response) {
                $scope.materials = response.data;
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
    };

    $scope.getDesign = function () {
        $http.get(`${apiUrlProduct}/getAllDesign`)
            .then(function (response) {
                $scope.designs = response.data;
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
    };

    $scope.getSize = function () {
        $http.get(`${apiUrlProduct}/getAllSize`)
            .then(function (response) {
                $scope.sizes = response.data;
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
    };

    $scope.getCategory = function () {
        $http.get(`${apiUrlProduct}/getAllCategory`)
            .then(function (response) {
                $scope.categories = response.data;
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
    };
    $scope.getProductByQRCode = function () {
        $http.get(apiUrlCart)
            .then(function (response) {
                $scope.formCart = response.data;
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });

    }
    $scope.cart= {
        items: [],
        add() {
            var item = this.items.find(item => item.id == $scope.formCart.id);
            if (item) {
                item.qty++;
                this.saveToLocalStorage();
            } else {
                $http.get(apiUrlCart + '/' + $scope.formCart.id).then(resp => {
                    resp.data.qty = 1;
                    this.items.push(resp.data);
                    this.saveToLocalStorage();
                })
            }

        },
        saveToLocalStorage() {
            var json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cart", json);
        },
        get count() {
            return this.items.map(item => item.qty)
                .reduce((total, qty) => total += qty, 0);
        }, get amount() {
            return this.items.map(item => item.qty * item.price)
                .reduce((total, qty) => total += qty, 0);
        }
        ,tru(id) {
            var item = this.items.find(item => item.id == id);
            if (item.qty==1){
                alert("khong the")
            }else {
                item.qty--;
                this.saveToLocalStorage();
            }
        }
        ,cong(id) {
            var item = this.items.find(item => item.id == id);

                item.qty++;
                this.saveToLocalStorage();

        },
        remove(id){
            var index =this.items.findIndex(item => item.id == id);
            this.items.splice(index,1);
            this.saveToLocalStorage();
        }
        , loadFromLocalStorage() {
            var json = localStorage.getItem("cart");
            this.items = json ? JSON.parse(json) : [];
        }
    }
        ,$scope.cart.loadFromLocalStorage();
    $scope.bill = {
        createDate: new Date(),
        address: "",
        account: {username: "user1"},
        phoneTake: "",
        personTake: "",
        timeReceive: new Date(),
        totalMoney: $scope.cart.amount,
        moneyShip: "1",
        typePayment: "1",
        description: "1",
        statusBuy: "1",
        status: "1",
        get billDetails() {
            return $scope.cart.items.map(item => {
                return {
                    product: {id: item.id},
                    price: item.price,
                    quantity: item.qty,
                    dateReturn: null ,
                    moneyRefund: null,
                    description:"123",
                    status: 1,
                    previousBillDetail:null
                }
            })
        },purchase(){
            // alert($scope.cart.amount+"abcd")
            var bill = angular.copy(this);
            $http.post(apiUrlBill,bill).then(resp =>{
                alert("Dat hang thanh cong");
                // $scope.cart.clear();
                // location.href="/order/detail/" + resp.data.id;
            }).catch(error =>{
                alert("Loi~")
                console.log(error)
            })
        }
    }
    $scope.test = function () {
        alert($scope.order.account.username+"diachi")
    }
    $scope.getSize()
    $scope.getDesign();
    $scope.getMaterials();
    $scope.getColors();
    $scope.getCategory();
    $scope.getProducts();
});
