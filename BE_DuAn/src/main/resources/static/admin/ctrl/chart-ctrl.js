app.controller('chart-ctrl', function ($rootScope,$scope,$http) {
    const apiUrlChart = "http://localhost:8080/api/chart";
    $scope.a=[];
    $scope.account=[];

    $scope.year=[];
    $scope.getTotalMoney = function () {
        $http.get(apiUrlChart+'/'+$scope.searchYear)
            .then(function (response) {
                $scope.a=response.data;
                console.log(response);
                $scope.ChartTotalMoney();
            })
            .catch(function (error) {
                console.log(error);
            });
    };
    // $scope.getBill();
    $scope.getYear = function () {
        $http.get(apiUrlChart+'/account'+'/'+$scope.searchYear)
            .then(function (response) {
                $scope.account=response.data;
                console.log(response);
                $scope.getTotalMoney();
            })
            .catch(function (error) {
                console.log(error);
            });
    };
    $scope.getYear1 = function () {
        $http.get(apiUrlChart+'/account'+'/'+2023)
            .then(function (response) {
                $scope.account=response.data;
                console.log(response);
                $http.get(apiUrlChart+'/'+2023)
                    .then(function (response) {
                        $scope.a=response.data;
                        console.log(response);
                        $scope.ChartTotalMoney();
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            })
            .catch(function (error) {
                console.log(error);
            });
    };
    $scope.getYear1();

    $scope.ChartTotalMoney = function (){
        const  labels =['Tháng 1','Tháng 2','Tháng 3','Tháng 4','Tháng 5','Tháng 6','Tháng 7','Tháng 8','Tháng 9','Tháng 10','Tháng 11', 'Tháng 12']
        const  labels1 =[10,27,56,34,24,53]
        const data ={
            labels: labels,
            datasets:[
                {
                    label:'Tổng Tiền',
                    backdropColor:'blue',
                    borderColor:'blue',
                    data:$scope.a,
                },
                {
                    label:'Số Lượng Tài Khoản Mới',
                    backdropColor:'red',
                    borderColor:'red',
                    data:$scope.account,
                }
            ],
        }
        const config={
            type:'line',
            data:data,
        }
        const  canvas = document.getElementById('canvas')
        const  chart = new Chart(canvas,config)
    }

    $scope.searchYear="2023"

})