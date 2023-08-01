angular.module('socialnetwork').controller('registerController', function ($scope, $http, $localStorage) {
    //@TODO 12.07.2023 Дождаться получения токена сразу после регистрации, либо переделать на редирект к login.html
    $scope.tryToRegister = function () {
        $scope.user.login = $scope.user.username;
        $http.post('http://localhost:9001/register', $scope.user)
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