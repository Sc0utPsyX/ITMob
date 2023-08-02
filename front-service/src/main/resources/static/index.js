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
            .when('/profile', {
                templateUrl: 'profile/profile.html',
                controller: 'profileController'
            })
            .when('/events', {
                templateUrl: 'events/events.html',
                controller: 'eventsController'
            })
            .when('/events-card', {
                templateUrl: 'events-card/events-card.html',
                controller: 'eventsCardController'
            })
            .when('/privacy', {
                templateUrl: 'privacy_setting/privacy.html',
                controller: 'privacyController'
            })
            .when('/agreement', {
                templateUrl: 'agreement/agreement.html',
                controller: 'agreementController'
            })
            .when('/confidentiality', {
                templateUrl: 'confidentiality/confidentiality.html',
                controller: 'confidentialityController'
            })
            .when('/contact', {
                templateUrl: 'contact/contact.html',
                controller: 'contactController'
            })
            .when('/aboutNet', {
                templateUrl: 'about-net/about-net.html',
                controller: 'aboutNetController'
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

