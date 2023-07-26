angular.module('socialnetwork').controller('eventsController2', function ($scope, $http) {
    const contextPath = 'http://localhost:5555/events/';

     $scope.fillTable = function () {
            $http.get(contextPath + '/api/v1/events')
                .then(function (response) {
                    $scope.eventsList = response.data;
                    console.log(response);
                });
        };

    /*$scope.loadEvents = function () {
        $http.get(contextPath + 'api/v1/events').then(function (response) {
            $scope.events = response.data;
        });
    }*/

    $scope.deleteEvent = function (eventId) {
            $http.delete('http://localhost:5555/events/api/v1/events/' + eventId)
                .then(function (response) {
                    $scope.fillTable();
                });
        }

    $scope.fillTable();
   /* $scope.loadEvents();*/
});