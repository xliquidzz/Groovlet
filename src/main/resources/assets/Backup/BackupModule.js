var backupModule = angular.module('backupModule',[]);

backupModule.service('backupService', function($resource) {

    this.allArtists = function() {
        var resource = $resource('/api/artist');
        return resource.query();
    };

    this.postBackup = function(data) {
        var resource = $resource('/api/backup/restore');
        var jsonStringified = JSON.stringify(data);
        var parsedJson = JSON.parse(data);
        resource.save(parsedJson);
    };
});

backupModule.controller('backupController', function ($scope, backupService){

    $scope.postBackup = function() {
        backupService.postBackup($scope.inputData);
    };
});