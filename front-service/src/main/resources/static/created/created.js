angular.module('socialnetwork').controller('createdController', function ($scope, $http) {

 $scope.fillTable = function () {
        $http.get('http://localhost:5555/events/api/v1/types')
            .then(function (response) {
                $scope.createdEventList = response.data;
                console.log(response);
            });
    };
$scope.deleteEvent = function (eventId) {
        $http.delete('http://localhost:5555/events/api/v1/events/' + eventId)
            .then(function (response) {
                $scope.fillTable();
            });
    }
    $scope.fillTable();
});