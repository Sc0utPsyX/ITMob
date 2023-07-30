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
            .when('/register', {
                templateUrl: 'register/register.html',
                controller: 'registerController'
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

    const contextPath = 'http://localhost:8191/front/';

   $scope.loadMenuList = function () {
        $http.get(contextPath + 'api/v1/menu_list').then(function (response) {
            $scope.menuList = response.data;
            console.log(response.data);
       });
     }

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

    $scope.loadMenuList();

});

