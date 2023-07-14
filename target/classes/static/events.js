
angular.module('app', []).controller('eventsController', function ($scope, $http) {

    $scope.loadEvents = function () {
        $http.get('http://localhost:8195/event/api/v1/events').then(function (response) {
            $scope.eventsList = response.data;
            //console.log(response.data);
        });
    }

    $scope.showEventInfo = function (eventId) {
        $http.get('http://localhost:8195/event/api/v1/events/' + eventId).then(function (response) {
            alert(response.data.description);
            console.log('http://localhost:8195/event/api/v1/events/' + eventId);
        });
    }

    $scope.deleteEventById = function (eventId) {
        $http.delete('http://localhost:8195/event/api/v1/events/' + eventId).then(function (response) {
            $scope.loadEvents();
        });
    }

    $scope.isUserLoggedIn = function () {
        //TODO $localStorage.winterMarketUser
        return true;
    };

    $scope.isUserAdmin = function () {
        //TODO как будет модерация, можно будет события удалять
        return false;
    };

    $scope.loadEvents();

});
