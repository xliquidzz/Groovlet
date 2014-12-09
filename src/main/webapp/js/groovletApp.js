var groovletApp = angular.module('groovletApp', ['ngRoute']);

groovletApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider
   	 .when('/view1', {
        templateUrl: 'partials/view1.html',
        controller: 'GroovletController'
      })
     .when('/view2', {
        templateUrl: 'partials/view2.html',
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

