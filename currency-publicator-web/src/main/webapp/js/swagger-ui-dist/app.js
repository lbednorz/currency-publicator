angular.module('docsApp', [])
        .controller('docsController', function ($scope, $timeout) {

            //console.log('aaa');
            //$scope.search.url = $scope.search.project.host+"currency-publicator-web/api-docs";

            $scope.projects = [
                {
                    host: "",
                    name: "Choose Project",
                    baseUrl: "",
                    docsUrl: ""
                },
                {
                    host: "/",
                    name: "Currency Publicator",
                    baseUrl: "currency-publicator-web",
                    docsUrl: "/api-docs"
                }

            ];

            $scope.search = {
                //init values
                project: $scope.projects[0],
                url: "" + $scope.projects[0].host + $scope.projects[0].baseUrl + $scope.projects[0].docsUrl
            };

            $scope.updateUrl = function () {
                $scope.search.url = $scope.search.project.host + $scope.search.project.baseUrl + "/api-docs";
            };
        });

