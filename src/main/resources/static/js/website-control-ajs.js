// This is angular controller for homepage

var webapp = angular.module('webapp', []);

webapp.controller('NullifyController', function ($scope, $http) {

    //$scope.searchedfor = false;

    $scope.loadWebsite = function() {
        $http.get("/websites")
            .success(function(data){
                //if($scope.sitelist != null)
                    $scope.sitelist = data;
            });
    }

    $scope.searchWebsites = function() {
        $scope.searchedfor = true;
        $http.get("/search/" + $scope.websitesearch)
            .success(function(data){
                $scope.foundwebsite = data;
            });
    }

    //$scope.addFave = function() {
    //    $http.get("/addFave/" + $scope.websites)
    //}

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


// this is the add website button
    $scope.addWebsite = function() {
    // add the rest of the fields
        $http.post("/cs480/" + $scope.websitenameinput + "?domain=" + $scope.domainnameinput + "&settings=" + $scope.changepasswordpageinput + "&changepassword=" + $scope.deleteaccountpageinput + "&deleteaccount=" + $scope.notesinput + "&notes") //  + $scope.imageinput + "&image"
            .success(function(data){
                $scope.loadUsers();
            });
    }

/*
    $scope.deleteUser = function(userId) {
        $http.delete("cs480/user/" + userId)
            .success(function(data){
                $scope.loadUsers();
            });
    }*/

    $scope.loadWebsite();

});