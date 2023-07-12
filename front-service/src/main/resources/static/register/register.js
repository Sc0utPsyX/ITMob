angular.module('socialnetwork').controller('registerController', function ($scope, $http) {
    //@TODO 12.07.2023 Дождаться получения токена сразу после регистрации, либо переделать на редирект к login.html
    $scope.tryToRegister = function () {
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