angular.module('market', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {
    if ($localStorage.winterMarketUser) {
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
    }

    $scope.fillTable = function () {
        $http.get('http://localhost:5555/core/api/v1/products')
            .then(function (response) {
                $scope.productsList = response.data;
                // console.log(response);
            });
    };

    $scope.tryToAuth = function () {
        $http.post('http://localhost:5555/auth/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.marchMarketUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;
                }
            }, function errorCallback(response) {
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        $scope.user = null;
    };

    $scope.clearUser = function () {
        delete $localStorage.winterMarketUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.isUserLoggedIn = function () {
        if ($localStorage.winterMarketUser) {
            return true;
        } else {
            return false;
        }
    };

    $scope.authCheck = function () {
        $http.get('http://localhost:5555/core/auth_check')
            .then(function (response) {
                alert(response.data.value);
            });
    }

    $scope.showProductInfo = function (productId) {
        $http.get('http://localhost:5555/core/api/v1/products/' + productId)
            .then(function (response) {
                alert(response.data.title);
            });
    }

    $scope.deleteProduct = function (productId) {
        $http.delete('http://localhost:8189/winter/api/v1/products/' + productId)
            .then(function (response) {
                $scope.fillTable();
            });
    }

    $scope.createNewProduct = function () {
        // console.log($scope.newProduct);
        $http.post('http://localhost:8189/winter/api/v1/products', $scope.newProduct)
            .then(function (response) {
                $scope.newProduct = null;
                $scope.fillTable();
            });
    }

    $scope.createOrder = function () {
        $http.post('http://localhost:5555/core/api/v1/orders')
            .then(function (response) {
                alert('Заказ оформлен');
                $scope.loadCart();
            });
    }

    $scope.addToCart = function (productId) {
        console.log("Click-1")
        $http.get('http://localhost:5555/cart/api/v1/cart/add/' + productId)
            .then(function (response) {
                $scope.loadCart();
            });
    }

    $scope.changeQuantity = function (productId, delta) {
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
    }

    $scope.loadCart = function () {
        $http.get('http://localhost:5555/cart/api/v1/cart')
            .then(function (response) {
                $scope.cart = response.data;
            });
    }

    $scope.clearCart = function () {
        console.log("Click-2")
        $http.get('http://localhost:5555/cart/api/v1/cart/clear')
            .then(function (response) {
                //$scope.cart = response.data;
                $scope.loadCart();
            });
    }

    $scope.fillTable();
    $scope.loadCart();
});