//wow!

(function () {
    angular
        .module('socialnetwork', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'start-page/start-page.html',
                controller: 'startController'
            })
            .when('/login', {
                templateUrl: 'login/login.html',
                controller: 'loginController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }

function run($rootScope, $http, $localStorage) {
    if ($localStorage.itMobUser) {
        try {
            let jwt = $localStorage.itMobUser.token;
            let payload = JSON.parse(atob(jwt.split('.')[1]));
            let currentTime = parseInt(new Date().getTime() / 1000);
            if (currentTime > payload.exp) {
                console.log("Token got expired");
                delete $localStorage.itMobUser;
                $http.defaults.headers.common.Authorization = '';
            }
        } catch (e) {
        }
        if ($localStorage.itMobUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.itMobUser.token;
        }
    }
}
})();

angular.module('socialnetwork').controller('mainController', function ($rootScope, $scope, $http, $location, $localStorage) {

    $scope.tryToLogout = function () {
        $scope.clearUser();
        $location.path('/');
    };

    $scope.clearUser = function () {
        delete $localStorage.itMobUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.itMobUser) {
            return true;
        } else {
            return false;
        }
    };
});

