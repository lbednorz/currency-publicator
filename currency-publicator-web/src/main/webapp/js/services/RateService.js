angular.module('inspinia')
    .service('RateService', function ($http, $rootScope) {
        var baseUrl = $rootScope.SERVER_DEF_URL+'api/currencies';
        return {
            getAllCurrencies: function () {
                return $http.get(baseUrl + '/list').then(getData);
            },
            getHistory: function (key) {
                return $http.get(baseUrl + '/hist/' +key).then(getData);
            },
            getCurrent: function () {
                return $http.get(baseUrl + '/current/').then(getData);
            },
            changeValue: function (oryg, target, value) {
                return $http.get(baseUrl + '/change/'+oryg+'/'+target+'/'+value).then(getData);
            }

        };
        function getData(response) {
            return response.data;
        }
        function indexRunMessage(response) {
            console.log(response);
        }
    });
