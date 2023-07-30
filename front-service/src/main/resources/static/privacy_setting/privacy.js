angular.module('socialnetwork').controller('privacyController', function ($scope, $http) {

    $scope.changePrivacy = function () {
        $http.put('http://localhost:9001/privacy', $scope.privacy)
            .then(function successCallback(response) {

            }, function errorCallback(response) {
            });
    };

    $scope.getPrivacy = function () {
        $http.get('http://localhost:9001/privacy')
            .then(function successCallback(response) {
               $scope.privacy =  response.data;
            }, function errorCallback(response) {
            });
    }

    $scope.getPrivacy();
});