
function HistoryController($stateParams, $scope, $rootScope, $http, RateService) {

    console.log($stateParams);
    $scope.hideLoader = false;
    $scope.selectedCurrency = $stateParams.key;
    RateService.getHistory($stateParams.key).then(function (data) {
            $scope.history = data;
            console.log($scope.history);
            $scope.hideLoader = true;
        })
        .catch(function (response) {
            $scope.hideLoader = true;
            if (response.status === 404) {
                toastr.error(response.data.message);
            }
        });



    console.log('HistoryController');
}


angular
    .module('inspinia')
    .controller('HistoryController', ['$scope', 'RateService',  HistoryController])
