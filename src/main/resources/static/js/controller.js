var app = angular.module('app', []);
app.controller('controller', function($scope, $window, $http) {
    $scope.countries = [];
    $scope.getAllCC = function(){
        $http({
            method: 'GET',
            url: 'http://localhost:8080/getAll'
        }).then(function (success){
            $scope.countries = success.data;
            $scope.tablea = false;
        },function (error){

        });
    }
    $scope.download = function(){
            $window.open("http://localhost:8080/downloadCsv");
     }
});



