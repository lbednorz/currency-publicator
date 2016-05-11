
function CurrenciesController($stateParams, $scope, $rootScope, $http, RateService) {

    $scope.hideLoader = false;
    $scope.currenciesArray = [];
    RateService.getAllCurrencies( ).then(function (data) {
            $scope.currencies = data;
            angular.forEach(data, function(value, key) {
                $scope.currenciesArray.push({'key':key,'value':value})
            });
            console.log($scope.currenciesArray);
            $scope.hideLoader = true;
        })
        .catch(function (response) {
            $scope.hideLoader = true;
            if (response.status === 404) {
                toastr.error(response.data.message);
            }
        });

    console.log('CurrenciesController');
}

angular
    .module('inspinia')
    .controller('CurrenciesController', ['$scope', 'RateService',  CurrenciesController])
