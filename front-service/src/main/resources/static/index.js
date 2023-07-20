/*
function () {
    angular
        .module('socialnetwork', ['ngRoute', 'ngStorage'])
        .config(config)

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'start-page/start-page.html',
                controller: 'startController'
            });
    }
 }

*/

angular.module('socialnetwork', ['ngStorage']).controller('mainController', function ($rootScope, $scope, $http) {
    /*if ($localStorage.winterMarketUser) {
        try {
            let jwt = $localStorage.winterMarketUser.token;
            let payload = JSON.parse(atob(jwt.split('.')[1]));
            let currentTime = parseInt(new Date().getTime() / 1000);
            if (currentTime > payload.exp) {
                console.log("Token is expired!!!");
                delete $localStorage.winterMarketUser;
                $http.defaults.headers.common.Authorization = '';
            }
        } catch (e) {
        }

        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.winterMarketUser.token;
    }*/

    $scope.fillTable = function () {
        $http.get('http://localhost:5555/events/api/v1/types')
            .then(function (response) {
                $scope.typesList = response.data;
                console.log(response);
            });
    };


    $scope.showEventInfo = function (eventId) {
        $http.get('http://localhost:5555/events/api/v1/events/' + eventId)
            .then(function (response) {
                alert(response.data.title);
            });
    }

    /*$scope.deleteProduct = function (productId) {
        $http.delete('http://localhost:8189/winter/api/v1/products/' + productId)
            .then(function (response) {
                $scope.fillTable();
            });
    }*/

   /* $scope.createNewProduct = function () {
        // console.log($scope.newProduct);
        $http.post('http://localhost:8189/winter/api/v1/products', $scope.newProduct)
            .then(function (response) {
                $scope.newProduct = null;
                $scope.fillTable();
            });
    }*/

    /*$scope.createOrder = function () {
        $http.post('http://localhost:5555/core/api/v1/orders')
            .then(function (response) {
                alert('Заказ оформлен');
                $scope.loadCart();
            });
    }*/

    /*$scope.addToCart = function (productId) {
        console.log("Click-1")
        $http.get('http://localhost:5555/cart/api/v1/cart/add/' + productId)
            .then(function (response) {
                $scope.loadCart();
            });
    }*/

   /* $scope.changeQuantity = function (productId, delta) {
        console.log("Click-33")
        $http({
            url: 'http://localhost:5555/cart/api/v1/cart/remove/' + productId,
            method: 'GET',
            params: {
                delta: delta
            }
        }).then(function (response) {
            $scope.loadCart();
        });
    }*/

    /*$scope.loadCart = function () {
        $http.get('http://localhost:5555/cart/api/v1/cart')
            .then(function (response) {
                $scope.cart = response.data;
            });
    }*/

   /* $scope.clearCart = function () {
        console.log("Click-2")
        $http.get('http://localhost:5555/cart/api/v1/cart/clear')
            .then(function (response) {
                //$scope.cart = response.data;
                $scope.loadCart();
            });
    }*/

    $scope.fillTable();
});