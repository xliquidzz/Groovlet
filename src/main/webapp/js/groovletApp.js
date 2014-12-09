var groovletApp = angular.module('groovletApp', ['ngRoute']);

groovletApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider
    .when('/', {
        templateUrl: 'partials/start.html',
        controller: 'GroovletController'
     })

   	 .when('/groovlists', {
        templateUrl: 'partials/groovlists.html',
        controller: 'GroovletController'
      })
     .when('/buddies', {
        templateUrl: 'partials/buddies.html',
        controller: 'GroovletController'
      })
     .when('/usersettings', {
        templateUrl: 'partials/usersettings.html',
        controller: 'GroovletController'
      })
     .otherwise({
        redirectTo: '/'
      });
  }]);

var controllers = {};
controllers.GroovletController = function($scope) {

};

groovletApp.controller(controllers);

