angular.module('socialnetwork').controller('eventsController', function ($scope, $http) {

    const contextPath = 'http://localhost:8907/event/';
    const username = 'Bob'; //TODO передавать имя юзера

    $scope.loadEventTypes = function () {
        $http.get(contextPath + 'api/v1/event_types').then(function (response) {
            $scope.eventTypesList = response.data;
            $scope.EventTypesCheckedList = response.data;
            //console.log(response.data);
       });
     }

    $scope.showEventTypesInfo = function (description) {
        alert(description);
    }

    $scope.eventTypesChecked = function (event_types) {
        var matches = document.querySelectorAll("input.form-check-input");
        let EventTypesCheckedList = [];
        for (let i = 0; i < matches.length; i++) {
             if (matches[i].value == event_types.name) {
                  matches[i].checked = true;
                  EventTypesCheckedList.push(event_types);
             }
             else {
                  matches[i].checked = false;
             }
        }
        $scope.EventTypesCheckedList = EventTypesCheckedList;
        $scope.loadEvents();
    }

    $scope.modalBodyClose = function () {
        var matches = document.querySelectorAll("input.form-check-input");
        let EventTypesCheckedList = [];
        for (let i = 0; i < matches.length; i++) {
             if (matches[i].value == $scope.eventTypesList[i].name & matches[i].checked == true) {
                  EventTypesCheckedList.push($scope.eventTypesList[i]);
             }
        }
        $scope.EventTypesCheckedList = EventTypesCheckedList;
        $scope.loadEvents();
    }

    $scope.loadEvents = function (pageIndex = 1) {
        let out = [];
        var matches = document.querySelectorAll("input.form-check-input");
        for (let i = 0; i < matches.length; i++) {
             if (matches[i].checked) {
                  out.push(matches[i].value);
             }
        }
        var event_place = document.querySelector('input[type=text][name=event_place]').value;
        $http({
            url: contextPath + 'api/v1/events',
            method: 'GET',
            params: {
                "event_types_name": out,
                "event_place": event_place,
                "username": username,
                "p": pageIndex
            }
        }).then(function (response) {
            //console.log(response.data);
            $scope.actualPageIndex = pageIndex;
            $scope.eventsPage = response.data;
            $scope.generatePagesList($scope.eventsPage.totalPages);
        });
    }

    $scope.showEventInfo = function (event) {
         $http({
             url: contextPath + 'api/v1/events/' + event.id,
             method: 'GET',
             params: {
                 "username": username
             }
         }).then(function (response) {
            alert(response.data.description);
            //console.log(response.data.description);
        });
    }

    $scope.deleteEventById = function (event) {
        $http.delete(contextPath + 'api/v1/events/' + event.id).then(function (response) {
            $scope.loadEvents();
        });
    }

    $scope.generatePagesList = function (totalPages) {
        out = [];
        for (let i = 0; i < totalPages; i++) {
            out.push(i + 1);
        }
        $scope.pagesList = out;
        //console.log(out);
    }

    $scope.followToEvent = function (event) {
        $http({
            url: contextPath + 'add_event_members',
            method: 'POST',
            params: {
                "event_id": event.id,
                "username": username
            }
        }).then(function (response) {
            event.isfollow = true;
            //console.log(response.data);
        });
    }

    $scope.deleteFollowToEvent = function (event) {
        $http({
            url: contextPath + 'delete_event_members',
            method: 'DELETE',
            params: {
                "event_id": event.id,
                "username": username
            }
        }).then(function (response) {
            event.isfollow = false;
        });
    };

    $scope.showxFollowToEvent = function (event) {
        $http({
            url: contextPath + 'find_event_members',
            method: 'GET',
            params: {
                "event_id": event.id,
                "username": username
            }
        }).then(function (response) {
            alert (response.data == "[object Object]");
            //console.log(response.data );
        });
    };

    $scope.isUserLoggedIn = function () {
        //TODO $localStorage.winterMarketUser
        return true;
    };

    $scope.isUserAdmin = function () {
        //TODO как будет модерация, можно будет события удалять
        return false;
    };

    $scope.getOnlyTime = function (current_datetime) {

        var date = new Date(current_datetime);
        var hours = date.getHours();
        var minutes = date.getMinutes();

        if(minutes < 10)
        {
            minutes = '0' + minutes;
        }

        return hours + ':' + minutes;

    }

    $scope.getOnlyDay = function (current_datetime) {

       var monthList = new Array('января', 'февраля', 'марта', 'апреля', 'мая', 'июня', 'июля','августа', 'сентября', 'октября', 'ноября', 'декабря');
       var dayList = new Array('Пн', 'Вт', 'Ср', 'Ст', 'Пт', 'Сб', 'Вс');
              var date = new Date(current_datetime);

       var date = new Date(current_datetime);
       var day = dayList[date.getDay()];
       var month = monthList[date.getMonth()];
       var date = date.getDate();

       return day + ', ' +  date + ' ' + month;

    }

    $scope.loadEventTypes();
    $scope.loadEvents();

});
