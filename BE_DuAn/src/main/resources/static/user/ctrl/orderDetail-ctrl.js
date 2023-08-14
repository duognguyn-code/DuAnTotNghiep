app.controller('order-detail-ctrl',function($window,$rootScope,$scope,$http){
    var urlBillDetail=`http://localhost:8080/api/billDetail/rest/user`;
    $scope.orderDetails=[];
    $scope.formProductChange={};
    $scope.idCheckBox = {};
    $scope.seLected = [];
    $scope.index = 0;
    $scope.form={};
    $scope.formDetails= {};
    $scope.accountActive = {};
    $scope.logOut= function () {
        $rootScope.account=null;
        localStorage.removeItem('jwtToken');
    }
    const jwtToken = localStorage.getItem("jwtToken")
    const token = {
        headers: {
            Authorization: `Bearer `+jwtToken
        }
    }
    $scope.getAllByOrder=function(){
        let id = $rootScope.id;
        $http.get(urlBillDetail + `/${id}`).then(resp=>{
            $scope.orderDetails = resp.data;
        }).catch(error=>{
            console.log(error);
        })
    }
    $scope.getAcountActive = function () {
        $http.get(`/rest/user/getAccountActive`).then(function (respon){
            $scope.accountActive = respon.data;
            $rootScope.name = $scope.accountActive.username;
            console.log($scope.accountActive.username)
            alert($scope.accountActive.username);
        }).catch(err => {
            alert(err)
        })
    }
    $scope.saveProductChangeDetail = function (){
        var form = new FormData();
        angular.forEach($scope.files, function(file) {
            form.append('files', file);
        });
        alert($scope.formDetails.id + "rỗng ko")
        form.append("bill_detail", $scope.formDetails.id);
        let req = {
            method: 'POST',
            url: '/rest/user/productchange/saveRequest',
            headers: {
                'Content-Type': undefined,
            },
            data: form
        }
        $http(req).then(resp=>{
            console.log(resp.data+ " datanafy cos da ko");
        }).catch(error=>{
            console.log(error);
        })
    }
    $scope.getAcountActive();
    $scope.saveProductChange = function (){
        Swal.fire({
            title: 'Thực hiện gửi yêu cầu đổi trả ?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Xác nhận!'
        }).then((result) => {
            if (result.isConfirmed) {
                let timerInterval
                Swal.fire({
                    title: 'Tạo yêu cầu thành công' + '!',
                    html: 'Vui lòng chờ <b></b> milliseconds.',
                    timer: 1500,
                    timerProgressBar: true,
                    didOpen: () => {
                        Swal.showLoading()
                        var formData = new FormData();
                        angular.forEach($scope.files, function(file) {
                            formData.append('files', file);
                        });
                        formData.append("description", $scope.formProductChange.description);
                        formData.append("email", $scope.accountActive.email);
                        formData.append("quantityProductChange",$scope.formProductChange.quantity_product_change);
                        formData.append("account",$scope.accountActive.username);
                        formData.append("bill_detail",$scope.formDetails.id);
                        let req = {
                            method: 'POST',
                            url: '/rest/user/productchange/save',
                            headers: {
                                'Content-Type': undefined,
                            },
                            data: formData,
                            transformResponse: [
                                function (data) {
                                    return data;
                                }
                            ]
                        }
                        Swal.fire({
                            title: 'Đang gửi yêu cầu đến admin' + '!',
                            html: 'Vui lòng chờ <b></b> milliseconds.',
                            timer: 3500,
                            timerProgressBar: true,
                            didOpen: () => {
                                Swal.showLoading()
                                const b = Swal.getHtmlContainer().querySelector('b')
                                timerInterval = setInterval(() => {
                                    b.textContent = Swal.getTimerLeft()
                                }, 100)
                            },
                            willClose: () => {
                                clearInterval(timerInterval)
                            }
                        })
                        $http(req).then(response => {
                            console.log("ddd " + response.data);
                            $scope.saveProductChangeDetail();
                            $scope.message("Gửi yêu cầu đổi trả thành công");
                            $('#staticBackdrop').modal('hide');
                            $scope.formProductChange={};
                            $scope.files=null;
                            window.location.href = '/user/index.html#!';
                        }).catch(error => {
                            $scope.error('gửi  yêu cầu đổi trả thất bại');
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
                    if (result.dismiss === Swal.DismissReason.timer) {


                    }
                })
            }
        })
    }
    $scope.uploadFile = function (files) {
        $scope.files = files;
        console.log($scope.files);
        var previewImagesContainer = document.getElementById('previewImagesContainer');
        previewImagesContainer.innerHTML = ''; // Xóa bỏ các ảnh hiện có
        var imageCount = 0;

        // Biến đếm số lượng ảnh đã hiển thị

        for (var i = 0; i < files.length; i++) {
            if (imageCount >= 3) {
                break; // Đã đạt số lượng ảnh tối đa, thoát khỏi vòng lặp
            }

            var file = files[i];
            var reader = new FileReader();

            reader.onload = (function (file) {
                return function (e) {
                    var previewImage = document.createElement('img');
                    previewImage.src = e.target.result;
                    previewImage.className = 'previewImage';
                    previewImage.width = '100'; // Chỉnh kích thước ảnh
                    previewImagesContainer.appendChild(previewImage);
                    imageCount++; // Tăng biến đếm số lượng ảnh đã hiển thị
                };
            })(file);

            reader.readAsDataURL(file);
        }
    };
    $scope.getProductChange=function(formProductChange){
        $http.get(`/rest/user/productchange/findProductChange/${formProductChange.id}`).then(resp=>{
            console.log($scope.formDetails.id + "$scope.formDetails.id id")
            $scope.formDetails = resp.data;
            console.log(resp.data)
        }).catch(error=>{
            console.log(error);
        })
    }
    $scope.increaseQuantity = function (item) {
        item.quantity++;
    };

    $scope.decreaseQuantity = function (item) {
        if (item.quantity > 1) {
            item.quantity--;
        }
    };

    $scope.message = function (mes){
        const Toast = Swal.mixin({
            toast: true,
            position: 'top-end',
            showConfirmButton: false,
            timer: 1000,
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
    $scope.error =  function (err){
        const Toast = Swal.mixin({
            toast: true,
            position: 'top-end',
            showConfirmButton: false,
            timer: 1000,
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

    $scope.checkSelected= function (id){
        console.log('sdsadasadsa '+id)
        var check = true;
        for(var i=0;i<$scope.seLected.length;i++){
            if($scope.seLected[i]==id){
                check = false;
                console.log('đã kt id ' + id)
                $scope.seLected.splice(i,1);
            }
        }
        if (check){
            $scope.seLected.push(id);
        }
    }
    $scope.checkSelect=function (id){
        for (var i = 0; i < $scope.seLected.length; i++) {
            if ($scope.seLected[i] == id) {
                console.log('đã kiểm tra id là: '+ id)
                return true;
            }
        }
    }


    $scope.uploadFileChange = function(files){
        $scope.files = files;
        console.log($scope.files);
    }
// get accouunt





    $scope.getAllByOrder();
});