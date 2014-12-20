var songModule = angular.module('songModule',['youtube']);

songModule.factory('songService', function() {

    return {
        getSongs: function() {
            var songs = [{title:'Song1', artist: 'artist1', genre:'genre1',votes:8, youtube:'bESQmBFyNMg'},
                       {title:'Song2', artist: 'artist2', genre:'genre2',votes:12, youtube:'PGeX6oE-dMM'},
                       {title:'Song3', artist: 'artist3', genre:'genre3',votes:21, youtube:'f4W7uZBaIaE'},
                       {title:'Song4', artist: 'artist4', genre:'genre4',votes:50, youtube:'nRvA08ALkGU'},
                       {title:'Song5', artist: 'artist5', genre:'genre5',votes:23, youtube:'frJKUPjqWgg'},
                       {title:'Song6', artist: 'artist6', genre:'genre6',votes:2, youtube:'f4W7uZBaIaE'},
                       {title:'Song7d', artist: 'artist7', genre:'genre7',votes:15, youtube:'frJKUPjqWgg'}];
            return songs;
        }
    };
});

songModule.controller('songController', function($scope, songService) {

    $scope.songs = songService.getSongs();

    $scope.setYoutubeSong = function(newYoutubeSong) {
        $scope.youtubeSong = newYoutubeSong;
    };

    $scope.close = function() {

    };
});