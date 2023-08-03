angular.module('socialnetwork').controller('eventsCardController', function ($rootScope, $scope, $http, $location, $localStorage) {

    const contextPath = 'http://localhost:8907/event/';

    $scope.loadEventTypes = function () {
        $http.get(contextPath + 'api/v1/event_types').then(function (response) {
            $scope.eventTypesList = response.data;
            //console.log(response.data);
       });
     }

    $scope.eventCreate = function () {
        $scope.event.author = $localStorage.itMobUser.username;
        $http.post(contextPath + 'api/v1/events', $scope.event).then(function (response) {
              $scope.toGoPage();
        });
    }

    $scope.toGoPage = function () {
        document.location.href = '#!/profile';
    }

    $scope.loadEventTypes();

});
