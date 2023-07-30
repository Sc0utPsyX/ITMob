angular.module('socialnetwork').controller('loginController', function ($scope, $http) {
    $scope.tryToAuth = function () {
        $scope.user.login = $scope.user.username;
        $http.post('http://localhost:9001/auth', $scope.user)
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