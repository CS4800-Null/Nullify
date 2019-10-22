// This is angular controller for homepage

var webapp = angular.module('webapp', []);

webapp.controller('NullifyController', function ($scope, $http) {

    $scope.loadWebsite = function() {
        $http.get("/")
            .success(function(data){
                $scope.sitelist = data;
            });
    }

    $scope.searchWebsites = function() {
        $http.get("/search/" + $scope.websitesearch)
            .success(function(data){
                if(data == null)
                    $scope.foundwebsite = "Website not found";
                $scope.foundwebsite = data;
            });
    }

    $scope.sortAZ = function() {
        $http.get("/sortAZ/")
            .success(function(data){
                $scope.sitelistAZ = data;
            });
    }

    $scope.sortZA = function() {
        $http.get("/sortZA/")
            .success(function(data){
                $scope.sitelistZA = data;
            });
    }

    $scope.showCanDelete = function() {
        $http.get("/candelete/")
            .success(function(data){
                $scope.canBeDeleted = data;
            });
    }


    /*$scope.addUser = function() {
        $http.post("cs480/user/" + $scope.new_id + "?name=" + $scope.new_name + "&major=" + $scope.new_major)
            .success(function(data){
                $scope.loadUsers();
            });
    }

    $scope.deleteUser = function(userId) {
        $http.delete("cs480/user/" + userId)
            .success(function(data){
                $scope.loadUsers();
            });
    }*/

    $scope.loadWebsite();

});