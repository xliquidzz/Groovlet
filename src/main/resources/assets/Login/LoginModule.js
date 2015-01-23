var loginModule = angular.module('loginModule', []);

loginModule.service('UserService', ['$cookieStore','$http',function($cookieStore,$http) {
    this.user = {};

    this.storeToSession = function() {
        $cookieStore.put('userHeader', this.user.header);
    };

    this.loadFromSession = function() {
        var userHeader = $cookieStore.get('userHeader');
        if ( userHeader ) {
            this.loadCurrentUser(userHeader);
        }
    };

    this.loadCurrentUser = function(loadCurrentUser) {
        /* $http get to load user info with given header */
        $http.get(<url>,{headers: {'Authorization': 'Basic ' + UserService.user.header}})
    };

    this.login = function(email,password,ctrl) {
        var userIn = {
            username : username,
            password : password
        }
        var that = this;
        $http.post('./login', userIn).success(function(data) {
            if (data.success === true) {
                that.user.username = userIn.username;
                that.user.header = btoa(userIn.username + ':' + userIn.password);
                ctrl.errorMessage = '';
                that.storeToSession();
            } else {
                console.log('error');
            }
        }).error(function(arg) {
            console.log('error earlier');
        });
    }

    this.logout = function() {
        this.user = {};
        this.storeToSession();
    }

    this.loadFromSession();
}]);

loginModule.controller('loginController', function() {

});