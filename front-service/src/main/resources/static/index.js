angular.module('socialnetwork', ['ngStorage']).controller('eventController', function ($rootScope, $scope, $http) {

    $scope.fillTable = function () {
        $http.get('http://localhost:5555/events/api/v1/types')
            .then(function (response) {
                $scope.typesList = response.data;
                console.log(response);
            });
    };


    $scope.showEventInfo = function (eventId) {
        $http.get('http://localhost:5555/events/api/v1/events/' + eventId)
            .then(function (response) {
                alert(response.data.title);
            });
    }

    $scope.createNewEvent = function () {
            // console.log($scope.newEvent);
            $http.post('http://localhost:5555/events/api/v1/events', $scope.newEvent)
                .then(function (response) {
                    $scope.newEvent = null;
                    $scope.fillTable();
                });
        }

    $scope.fillTable();
});