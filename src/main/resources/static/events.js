
angular.module('app', []).controller('eventsController', function ($scope, $http) {

    $scope.loadEvents = function () {
        $http.get('http://localhost:8193/event/api/v1/events').then(function (response) {
            $scope.eventsList = response.data;
            console.log(response.data);
        });
    }

    $scope.showEventInfo = function (eventId) {
        $http.get('http://localhost:8193/event/api/v1/events/' + eventId).then(function (response) {
            alert(response.data.description);
        });
    }

    $scope.deleteEventById = function (eventId) {
        $http.delete('http://localhost:8193/event/api/v1/events/' + eventId).then(function (response) {
            $scope.loadEvents();
        });
    }

    $scope.loadEvents();

});
