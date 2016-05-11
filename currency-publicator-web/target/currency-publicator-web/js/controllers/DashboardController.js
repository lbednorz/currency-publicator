function DashboardController($stateParams, $scope, $rootScope, $http, RateService) {
   console.log('DashboardController');

   $scope.waitAfterConvertion = false;

   $scope.showResultArea = false;
   $scope.selectedFromCurrency = "PLN";
   $scope.selectedToCurrency = "USD";
   $scope.selectedValue= 10;
   $scope.convertedValue= 0;

   $scope.travelersMap = new Array();

   $scope.waitAfterLoadedTravelersCheetsheet=true;
   $scope.showTravelersCheetsheetErrorMessage=false;

   $scope.showCurrentCurrenciesRateLoader=true;

   RateService.getAllCurrencies($stateParams.key).then(function (data) {
          $scope.currencies = data;
           $scope.showCurrentCurrenciesRateLoader=false;
       })
       .catch(function (response) {
           $scope.showCurrentCurrenciesRateLoader=false;
          if (response.status === 404) {
             toastr.error(response.data.message);
          }
       });


    RateService.getCurrent( ).then(function (data) {
            $scope.current = data;
        })
        .catch(function (response) {
            if (response.status === 404) {
                toastr.error(response.data.message);
            }
        });

   $scope.convert = function(){
      $scope.showResultArea = false;
      $scope.waitAfterConvertion = true;
      $scope.selectedFromCurrency = $scope.selectedFrom;
      $scope.selectedToCurrency = $scope.selectedTo;
      $scope.selectedValue = $scope.value;
      $scope.loadedCounter = 0;

      RateService.changeValue($scope.selectedFrom,$scope.selectedTo,  $scope.value).then(function (data) {
             $scope.convertedValue = data;
             console.log($scope.convertedValue);
             $scope.waitAfterConvertion = false;
             $scope.showResultArea = true;
             $scope.travelersMap = new Array();
             $scope.loadedCounter = 0;
             $scope.updateTravelersCurrency($scope.selectedFromCurrency,$scope.selectedToCurrency, 5 );
             $scope.updateTravelersCurrency($scope.selectedFromCurrency,$scope.selectedToCurrency, 10 );
             $scope.updateTravelersCurrency($scope.selectedFromCurrency,$scope.selectedToCurrency, 15 );
             $scope.updateTravelersCurrency($scope.selectedFromCurrency,$scope.selectedToCurrency, 20 );
             $scope.updateTravelersCurrency($scope.selectedFromCurrency,$scope.selectedToCurrency, 25 );
             $scope.updateTravelersCurrency($scope.selectedFromCurrency,$scope.selectedToCurrency, 50 );
             $scope.updateTravelersCurrency($scope.selectedFromCurrency,$scope.selectedToCurrency, 65 );
             $scope.updateTravelersCurrency($scope.selectedFromCurrency,$scope.selectedToCurrency, 80 );
             $scope.updateTravelersCurrency($scope.selectedFromCurrency,$scope.selectedToCurrency, 90 );
             $scope.updateTravelersCurrency($scope.selectedFromCurrency,$scope.selectedToCurrency, 100 );
          })
          .catch(function (response) {
             $scope.waitAfterConvertion = false;
             if (response.status === 404) {
                toastr.error(response.data.message);
             }
          });
   }


   $scope.updateTravelersCurrency = function(from, tom, value){

      RateService.changeValue(from,tom,  value).then(function (data) {

             $scope.loadedCounter++;
             console.log($scope.convertedValue);
             $scope.travelersMap.push({'value':data,'oryginal':value});
             console.log($scope.travelersMap);
             console.log("position: "+$scope.loadedCounter);
            if($scope.loadedCounter >=10){
               $scope.waitAfterLoadedTravelersCheetsheet=false;
            }

          })
          .catch(function (response) {

             if (response.status === 404) {
                $scope.waitAfterLoadedTravelersCheetsheet=false;
                $scope.showTravelersCheetsheetErrorMessage=true;
                toastr.error(response.data.message);
             }
          });
   }
}


angular
    .module('inspinia')
    .controller('DashboardController', 'RateService',DashboardController)
