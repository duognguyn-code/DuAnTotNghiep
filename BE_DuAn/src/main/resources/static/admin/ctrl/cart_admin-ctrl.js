app.controller('cart_admin-ctrl', function ($rootScope,$scope, $http) {
    const apiUrlCart = "http://localhost:8080/api/cart";
    const apiUrlBill = "http://localhost:8080/api/bill";
    $scope.cart=[];
    $scope.formCart={};

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
})
