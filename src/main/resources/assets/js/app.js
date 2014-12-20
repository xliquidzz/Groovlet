var app = angular.module('groovlet', ['ngRoute', 'songModule', 'youtube']);

app.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider
   	 .when('/', {
             templateUrl: 'Home/home.html',
             controller: 'mainController'
      })
   	 .when('/login', {
        templateUrl: 'Login/login.html',
        controller: 'mainController'
      })
      .when('/songlists', {
              templateUrl: 'SongList/songlist.html',
              controller: 'mainController'
      })
     .when('/songs', {
        templateUrl: 'Song/song.html',
        controller: 'songController'
      })
      .when('/artists', {
             templateUrl: 'Artist/artist.html',
             controller: 'mainController'
      })
     .when('/user', {
             templateUrl: 'User/user.html',
             controller: 'mainController'
      })
      .when('/youtube', {
           templateUrl: 'Youtube/youtube.html',
           controller: 'youtubeController'
      })
     .otherwise({
        redirectTo: '/'
      });
  }]);

app.controller('mainController', function($scope) {
    $scope.any = 'any';
});
/*
app.service('MainService', function() {

});*/
