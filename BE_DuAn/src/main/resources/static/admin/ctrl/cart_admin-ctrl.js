app.controller('cart_admin-ctrl', function ($rootScope,$scope, $http,$filter) {
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
    $scope.billProduct = [];
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
    $scope.tabs = [];
    $scope.showTab = false;
    $scope.tabid=0;
    $scope.yourJsonData=[];

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
    $scope.tabid =1;
    $scope.addTab = function() {
        var newTab = {
            id: $scope.tabid ,
            title: 'Tab ' + ($scope.tabid),
            active: false
        };

        // Kiểm tra trùng id
        while ($scope.tabExists(newTab.id)) {
            newTab.id++; // Tăng id lên 1
        }
        $scope.tabid++;
        // Thêm tab vào danh sách
        $scope.tabs.push(newTab);

        // Hiển thị tab
        $scope.showTab = true;

        // Đặt tab mới được thêm là active
        $scope.activateTab(newTab);
    };

    // Hàm kiểm tra xem tab có tồn tại trong danh sách hay không
    $scope.tabExists = function(id) {
        for (var i = 0; i < $scope.tabs.length; i++) {
            if ($scope.tabs[i].id === id) {
                return true;
            }
        }
        return false;
    };
    $scope.activateTab = function(tab) {
        // Vô hiệu hóa tất cả các tab

        $scope.tabid = tab.id
        alert($scope.tabid)
        $scope.cart.saveToLocalStorage();

        alert(tab.id)

        $scope.tabs.forEach(function(t) {
            t.active = false;
        });

        // Kích hoạt tab được chọn
        tab.active = true;
    };
    $scope.removeTab = function(tab){
       var index = $scope.tabs.indexOf(tab);
        $scope.tabs.splice(index,1);
//        $scope.tabs.length + 1;
    };

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


    $scope.getP ={
        idP:null,
        getID(id){
            this.idP = id
        },getD(){
            $http.get(apiUrlProduct+'/searchBill' +'/'+ this.idP +'/' +$scope.searchDesign +'/' +$scope.searchMaterial +'/' +$scope.searchColor +'/'+$scope.searchSize)
                .then(function (response) {
                    $scope.billProduct = response.data;
                    console.log(response);
                    $scope.cart.addP();

                })
                .catch(function (error) {
                    console.log(error);
                });
        }

    }



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
                $scope.cart.add();
            })
            .catch(function (error) {
                console.log(error);
            });

    }
    // $scope.av =[];
    $scope.cart= {
        items: [],
        av: [],
        add() {
            var item = null;
            var item = this.items.find(item => item.id == $scope.formCart.id && item.idTab == $scope.tabid);
            alert(item+'ss')
            if (item) {
                item.qty++;
                this.saveToLocalStorage();
            } else {
                $http.get(apiUrlCart + '/' + $scope.formCart.id).then(resp => {
                    resp.data.qty = 1;
                    resp.data.idTab = $scope.tabid;
                    this.items.push(resp.data);
                    this.saveToLocalStorage();
                })
            }

        },
        addP() {
            var item = null;
            var item = this.items.find(item => item.id == $scope.billProduct.id && item.idTab == $scope.tabid)
              //   &&
              // this.items.find(item => item.idTab == $scope.tabid);
            alert(item + "ssss")
            alert($scope.billProduct.id+" idp--------" + $scope.tabid)
            // var idT = this.av.find(item => item.idTab == $scope.tabid);
            // var IT = this.av.find(item => item.id == $scope.billProduct.id);
            // if (idT){
            //     alert("ngok")
            // }
            if (item) {
                // if (idT){
                item.qty++;
                this.saveToLocalStorage();
                // }
            } else {
                $http.get(apiUrlCart + '/' + $scope.billProduct.id).then(resp => {
                    resp.data.qty = 1;
                    resp.data.idTab = $scope.tabid;
                    this.items.push(resp.data);
                    this.saveToLocalStorage();
                })
            }

        },
        saveToLocalStorage() {
            // var av =[];
           $scope.cart.av=[]
            function getObjectById(jsons) {
                for (var i = 0; i < jsons.length; i++) {
                    if (jsons[i].idTab === $scope.tabid ) {
                        $scope.cart.av.push(jsons[i])
                    }
                }
                return null;
            }
            var object = getObjectById($scope.cart.items);
            var df = JSON.stringify(angular.copy($scope.cart.av));
            alert(df+"2")
            // av.push(df)
            // alert(av + "av")
            //
            //   var jsons={"id":122,"name":"Category 2Material 2Design 2 Màu Color 2 Size Size 2","status":1,"images":[],"price":2,"barcode":407336622,"category":{"idCategory":2,"name":"Category 2","type":1,"status":1},"size":{"id":2,"name":"Size 2"},"color":{"id":2,"name":"Color 2"},"design":{"id":2,"name":"Design 2"},"material":{"id":2,"name":"Material 2"},"files":null,"qty":4,"idTab":0}
            // var aa = JSON.stringify(angular.copy(jsons));
            // av = JSON.stringify(angular.copy(jsons));
            // alert(av+'23212a')
            //
            // this.items.push(av)
            // alert($scope.tabid+"id2")
            // var json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cart", df);

        },
        get count() {
            return this.av.map(item => item.qty)
                .reduce((total, qty) => total += qty, 0);
        }, get amount() {
            return this.av.map(item => item.qty * item.price)
                .reduce((total, qty) => total += qty, 0);
        }, get amc() {
           return this.amount + 0;
        },clear(){
            // $scope.cart.av= [];
            var index =$scope.cart.items.findIndex(item => item.idTab == $scope.tabid);
            alert(index)
            $scope.cart.items.splice(index,2);
            $scope.bill.address="";
            $scope.bill.personTake="";
            $scope.bill.phoneTake="";
            this.saveToLocalStorage();
        }
        ,tru(id) {
            var item = this.items.find(item => item.id == id && item.idTab == $scope.tabid);
            if (item.qty==1){
                alert("khong the")
            }else {
                item.qty--;
                this.saveToLocalStorage();
            }
        }
        ,cong(id) {
            var item = this.items.find(item => item.id == id && item.idTab == $scope.tabid);

                item.qty++;
                this.saveToLocalStorage();

        },
        remove(id){
            var index =this.items.findIndex(item => item.id == id && item.idTab == $scope.tabid);
            this.items.splice(index,1);
            this.saveToLocalStorage();
        }
        , loadFromLocalStorage() {
            var json = localStorage.getItem("cart");
            this.av = json ? JSON.parse(json) : [];
        }
    }
        ,$scope.cart.loadFromLocalStorage();
    $scope.bill = {
        createDate: new Date(),
        address: "",
        account: {username: "Duong"},
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
            return $scope.cart.av.map(item => {
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
            var bill = angular.copy(this);
            $http.post(apiUrlBill,bill).then(resp =>{
                alert("Dat hang thanh cong");
                $scope.cart.clear();
            }).catch(error =>{
                alert("Loi~")
                console.log(error)
            })
        }
    }
    $scope.generationName = function () {
            if ($scope.formProduct.name != undefined || $scope.formProduct.name != null || $scope.formProduct.name != '') {
                $scope.formProduct.name = '';
            }
            if ($scope.formProduct.category != undefined || $scope.formProduct.category != null || $scope.formProduct.category != '') {
                for (let i = 0; i < $scope.categories.length; i++) {
                    if ($scope.formProduct.category == $scope.categories[i].idCategory) {
                        $scope.formProduct.name = $scope.categories[i].name;
                    }
                }
            }
            if ($scope.formProduct.color != undefined || $scope.formProduct.color != null || $scope.formProduct.color != '') {
                for (let i = 0; i < $scope.colors.length; i++) {
                    if ($scope.formProduct.color == $scope.colors[i].id) {
                        $scope.formProduct.name += 'C' + $scope.colors[i].name;
                    }
                }
            }
            if ($scope.formProduct.design != undefined || $scope.formProduct.design != null || $scope.formProduct.design != '') {
                for (let i = 0; i < $scope.designs.length; i++) {
                    if ($scope.formProduct.design == $scope.designs[i].id) {
                        $scope.formProduct.name += 'D' + $scope.designs[i].name;
                    }
                }
            }
            if ($scope.formProduct.material != undefined || $scope.formProduct.material != null || $scope.formProduct.material != '') {
                for (let i = 0; i < $scope.colors.length; i++) {
                    if ($scope.formProduct.material == $scope.materials[i].id) {
                        $scope.formProduct.name += 'M' + $scope.materials[i].name;
                    }
                }
            }
            if ($scope.formProduct.size != undefined || $scope.formProduct.size != null || $scope.formProduct.size != '') {
                for (let i = 0; i < $scope.sizes.length; i++) {
                    if ($scope.formProduct.size == $scope.sizes[i].id) {
                        $scope.formProduct.name += 'S' + $scope.sizes[i].name;
                    }
                }
            }

        }
        $scope.generationNameForUpdate = function () {
            if ($scope.productData.name != undefined || $scope.productData.name != null || $scope.productData.name != '') {
                $scope.productData.name = '';
            }
            if ($scope.productData.category != undefined || $scope.productData.category != null || $scope.productData.category != '') {
                for (let i = 0; i < $scope.categories.length; i++) {
                    if ($scope.productData.category == $scope.categories[i].idCategory) {
                        $scope.productData.name = $scope.categories[i].name;
                    }
                }
            }
            if ($scope.productData.color != undefined || $scope.productData.color != null || $scope.productData.color != '') {
                for (let i = 0; i < $scope.colors.length; i++) {
                    if ($scope.productData.color == $scope.colors[i].id) {
                        $scope.productData.name += ' Màu ' + $scope.colors[i].name;
                    }
                }
            }
            if ($scope.productData.design != undefined || $scope.productData.design != null || $scope.productData.design != '') {
                for (let i = 0; i < $scope.designs.length; i++) {
                    if ($scope.productData.design == $scope.designs[i].id) {
                        $scope.productData.name += ' Thiết kế ' + $scope.designs[i].name;
                    }
                }
            }
            if ($scope.productData.material != undefined || $scope.productData.material != null || $scope.productData.material != '') {
                for (let i = 0; i < $scope.colors.length; i++) {
                    if ($scope.formProduct.material == $scope.materials[i].id) {
                        $scope.productData.name += ' Chất Liệu ' + $scope.materials[i].name;
                    }
                }
            }
            if ($scope.productData.size != undefined || $scope.productData.size != null || $scope.productData.size != '') {
                for (let i = 0; i < $scope.sizes.length; i++) {
                    if ($scope.productData.size == $scope.sizes[i].id) {
                        $scope.productData.name += ' Size ' + $scope.sizes[i].name;
                    }
                }
            }

        }
        $scope.searchProduct = function () {
            if ($scope.searchPriceMin === "") {
                $scope.searchPriceMin = "Min"

            }
            if ($scope.searchProducts === "") {
                $scope.searchProducts = " "

            }
            if ($scope.searchPriceMax === "") {
                $scope.searchPriceMax = "Max"
            }
            if ($scope.searchColor === 'undefined' && $scope.searchDesign === 'undefined' && $scope.searchMaterial === 'undefined'
                && $scope.searchSize === 'undefined' && $scope.searchPriceMin === "" && $scope.searchPriceMax === "" && $scope.searchProducts === 'undefined'
            ) {
                $scope.getProducts();
            } else {
                $http.get(apiUrlProduct + '/search' + '/' + $scope.searchProducts + '/' + $scope.searchColor + '/' + $scope.searchMaterial + '/' + $scope.searchSize + '/' + $scope.searchDesign + '/' + $scope.searchPriceMin + '/' + $scope.searchPriceMax + '/' + $scope.searchStatus)
                    .then(function (response) {
                        $scope.products = response.data;
                        console.log(response);
                    })
                    .catch(function (error) {
                        console.log(error);
                    })
            }
        };
        $scope.GetresetForm = function () {
            $http.get(apiUrlProduct + '/search' + '/' + "undefined" + '/' + "undefined" + '/' + "undefined" + '/' + "undefined" + '/' + "undefined" + '/' + "undefined" + '/' + "undefined" + '/' + "1")
                .then(function (response) {
                    $scope.products = response.data;
                    console.log(response);
                })
                .catch(function (error) {
                    console.log(error);
                })
        };
        $scope.pagerProducts = {
            page: 0,
            size: 5,
            get products() {
                var start = this.page * this.size;
                return $scope.products.slice(start, start + this.size);

            },
            get count() {
                return Math.ceil(1.0 * $scope.products.length / this.size);
                return $scope.products.slice(start, start + this.size);

            },
            get count() {
                return Math.ceil(1.0 * $scope.products.length / this.size);

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
        $scope.resetSearch = function () {
            $('#matesearch').prop('selectedIndex', 0);
            $('#colorSearch').prop('selectedIndex', 0);
            $('#designSearch').prop('selectedIndex', 0);
            $('#statusSearch').prop('selectedIndex', 0);
            $scope.searchColor = "undefined";
            $scope.searchDesign = "undefined";
            $scope.searchMaterial = "undefined";
            $scope.searchSize = "undefined";
            $scope.searchStatus = "1";
            $scope.searchPriceMin = "";
            $scope.searchPriceMax = "";
            $scope.searchProducts = " ";
            $('#sizeSearch').prop('selectedIndex', 0);
            $scope.GetresetForm();
        }
    $scope.resetSearch();
    $scope.getSize()
    $scope.getDesign();
    $scope.getMaterials();
    $scope.getColors();
    $scope.getCategory();
    $scope.getProducts();
});
