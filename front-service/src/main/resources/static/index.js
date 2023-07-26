(function () {
    angular
        .module('socialnetwork',  ['ngRoute'])
        .config(config);

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'welcome/welcome.html',
                controller: 'welcomeController'
            })
            .when('/events', {
                templateUrl: 'events/events.html',
                controller: 'eventsController2'
            })
            .when('/created', {
                templateUrl: 'created/created.html',
                controller: 'createdController'
            });
    }
})();

angular.module('socialnetwork', ['ngRoute']).controller('eventController', function ($scope, $http, $location) {

    $scope.fillTable = function () {
        $http.get('http://localhost:5555/events/api/v1/types')
            .then(function (response) {
                $scope.typesList = response.data;
                console.log(response);
            });
    };

   /* $scope.showEventInfo = function (eventId) {
        $http.get('http://localhost:5555/events/api/v1/events/' + eventId)
            .then(function (response) {
                alert(response.data.title);
            });
    }*/

     $scope.addType = function (typeId) {
            $http.get(cartContextPath + 'api/v1/cart/' + $localStorage.winterMarketGuestCartId + '/add/' + productId).
            then(function (response) {
            });
        }

    $scope.createNewEvent = function () {
            // console.log($scope.newEvent);
            $http.post('http://localhost:5555/events/api/v1/events', $scope.newEvent)
                .then(function (response) {
                    $scope.newEvent = null;
                    $scope.fillTable();
                    $location.path("/created");
                });
        }

    $scope.fillTable();
});