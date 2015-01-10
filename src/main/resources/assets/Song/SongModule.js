var songModule = angular.module('songModule',['ngResource','youtube']);

songModule.service('songService', function($resource) {

    var resource = function() {
        return $resource('/api/song');
    };

    this.getAllSongs = function() {
        return resource().query();
    };

    this.getSongById = function(songId) {
        return resource().get({ id: songId });
    };

    this.createSong = function(song) {
        resource().save(song);
    };

});

songModule.controller('songController', function($scope, songService) {

    $scope.songs = songService.getAllSongs();

    $scope.setYoutubeSong = function(newYoutubeSong) {
        $scope.youtubeSong = newYoutubeSong;
    };

    $scope.close = function() {

    };

    $scope.addVote = function (songVotedFor) {
        songVotedFor.votes = songVotedFor.votes + 1;
    };
});