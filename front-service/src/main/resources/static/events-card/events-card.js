angular.module('socialnetwork').controller('eventsCardController', function ($scope, $http) {

    const contextPath = 'http://localhost:8906/event/';
    const username = 'Bob'; //TODO передавать имя юзера

    $scope.loadEventTypes = function () {
        $http.get(contextPath + 'api/v1/event_types').then(function (response) {
            $scope.eventTypesList = response.data;
            //console.log(response.data);
       });
     }

    $scope.eventCreate = function () {
        $scope.event.author = username;
        $http.post(contextPath + 'api/v1/events', $scope.event).then(function (response) {
        });
    }

    $scope.loadEventTypes();

});
