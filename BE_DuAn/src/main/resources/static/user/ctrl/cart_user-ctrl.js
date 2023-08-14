app.controller('cart_user-ctrl', function ($rootScope,$scope, $http) {

    const apiUrlAccout = "http://localhost:8080/rest/user";

    $scope.accountActive= {};
    $scope.item= {};
    $rootScope.carts=[];
    $rootScope.qtyCart=0;
    $rootScope.name="";
    $scope.checkDesign = 0;
    $scope.checkMaterial = 0;
    $scope.checkSize = 0;
    $scope.checkColor = 0;
    $scope.PrD={};

    $scope.getAcountActive = function () {
        $http.get(apiUrlAccout+`/getAccountActive`).then(function (respon){
            $scope.accountActive = respon.data;
            $rootScope.name = $scope.accountActive.username;
            console.log($scope.accountActive.username)
        }).catch(err => {
            $scope.accountActive = null;
            $rootScope.account = null;
        })

    }
    $scope.addCart = function(product) {
        // Get the selected options (design, size, color, material)
        var selectedDesign = $scope.checkDesign.id;
        var selectedSize = $scope.checkSize.id;
        var selectedColor = $scope.checkColor.id;
        var selectedMaterial = $scope.checkMaterial.id;

        // Create a cart item object
        var cartItem = {
            product: product,
            design: selectedDesign,
            size: selectedSize,
            color: selectedColor,
            material: selectedMaterial,
            quantity: 1
        };

        // Retrieve existing cart items from local storage
        var cartItems = JSON.parse(localStorage.getItem('cartItems')) || [];

        // Check if the same product with the same options already exists in the cart
        var existingItem = cartItems.find(function(item) {
            return (
                item.product.id === cartItem.product.id &&
                item.design === cartItem.design &&
                item.size === cartItem.size &&
                item.color === cartItem.color &&
                item.material === cartItem.material
            );
        });

        if (existingItem) {
            // If the same item exists, increase its quantity
            existingItem.quantity++;
        } else {
            // Otherwise, add the new item to the cart
            cartItems.push(cartItem);
        }

        // Update the cart items in local storage
        localStorage.setItem('cartItems', JSON.stringify(cartItems));
    };
    $scope.cartItems = JSON.parse(localStorage.getItem('cartItems')) || [];

    // Hàm để xóa một mục khỏi giỏ hàng
    $scope.removeItem = function(index) {
            var json = localStorage.getItem("cartItems");
            this.cartItems = json ? JSON.parse(json) : [];
            this.saveToLocalStorage();
    };
    $scope.saveToLocalStorage = function () {
        var json = JSON.stringify(angular.copy(this.cartItems));
        localStorage.setItem("cartItems", json);
    }

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
                // alert($scope.checkQuantity);
            }).catch(error => {
                console.log(error,'lỗi check product')
            })
        }
    }
});