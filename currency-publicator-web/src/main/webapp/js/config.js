/**
 * INSPINIA - Responsive Admin Theme
 * Copyright 2015 Webapplayers.com
 *
 * Inspinia theme use AngularUI Router to manage routing and views
 * Each view are defined as state.
 * Initial there are written state for all view in theme.
 *
 */
function config($stateProvider, $urlRouterProvider, $ocLazyLoadProvider, IdleProvider, KeepaliveProvider) {

    // Configure Idle settings
    IdleProvider.idle(5); // in seconds
    IdleProvider.timeout(120); // in seconds

    $urlRouterProvider.otherwise("/app/dashboard");

    $ocLazyLoadProvider.config({
        // Set to true if you want to see what and when is dynamically loaded
        debug: false
    });

    $stateProvider


        .state('app', {
            abstract: true,
            url: "/app",
            templateUrl: "views/common/content.html",
        })
        .state('app.dashboard', {
            url: "/dashboard",
            controller: DashboardController,
            templateUrl: "views/dashboard.html",
            data: { pageTitle: 'Dashboard' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            insertBefore: '#loadBefore',
                            name: 'localytics.directives',
                            files: ['css/plugins/chosen/chosen.css','js/plugins/chosen/chosen.jquery.js','js/plugins/chosen/chosen.js']
                        },
                        {
                            files: ['js/plugins/jasny/jasny-bootstrap.min.js']
                        }
                    ]);
                }
            }
        })
        .state('app.currencies', {
            url: "/currencies",
            controller: CurrenciesController,
            templateUrl: "views/currencies.html",
            data: { pageTitle: 'Currencies' },
        })
        .state('app.history', {
            url: "/history?key",
            controller: HistoryController,
            templateUrl: "views/history.html",
            data: { pageTitle: 'Currency Historical Data' },

        })



       
}


angular
    .module('inspinia')
    .config(config)
    .factory('ServerErrorInterceptor', function ($q, $injector) {
    var canceller = $q.defer();
    var interceptor = {

        request: function(config) {
           // alert('aaa');
            config.timeout = canceller.promise;
            return config;
        },
        

        responseError: function(response) {             
            console.log("ERROR during connection: "+response.state);
            return $q.reject(response);
        }
    }

    return interceptor;
})
    .config(['$httpProvider',function(  $httpProvider) {
        //Http Intercpetor to check auth failures for xhr requests
        $httpProvider.interceptors.push('ServerErrorInterceptor');

       
    }])
    .run(function($rootScope, $state, $http, $location) {
        var host =  $location.absUrl();
        var hostArr=host.split("#");
        $rootScope.SERVER_DEF_URL =hostArr[0];
        console.log($rootScope.SERVER_DEF_URL);
        $rootScope.$state = $state;
         
    });
