angular.module('socialnetwork').controller('loginController', function ($scope, $http) {
    $scope.tryToAuth = function () {
        $http.post('**POST-ADDRESS-HERE**', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.itMobUser = {username: $scope.user.username, token: response.data.token};
                    $scope.user.username = null;
                    $scope.user.password = null;
                }
            }, function errorCallback(response) {
            });
    };
});