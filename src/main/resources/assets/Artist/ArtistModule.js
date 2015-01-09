var artistModule = angular.module('artistModule', []);

artistModule.service('artistService', function($resource) {

    this.allArtists = function() {
        var resource = $resource('/api/artist');
        return resource.query();
    };

});

artistModule.controller('artistController', function(artistService) {
    $scope.allArtists = artistService.allArtists();
});