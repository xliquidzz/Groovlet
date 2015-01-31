var artistModule = angular.module('artistModule', []);

artistModule.service('artistService', ['$resource', function($resource) {

    this.allArtists = function() {
        var resource = $resource('/api/artist');
        return resource.query();
    };


}]);

artistModule.controller('artistController', ['$scope', 'artistService', function($scope, artistService) {
    $scope.allArtists = artistService.allArtists();
}]);