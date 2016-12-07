

angular.module('rhmf-admin').controller('EditVoteController', function($scope, $routeParams, $location, flash, VoteResource ) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.vote = new VoteResource(self.original);
        };
        var errorCallback = function() {
            flash.setMessage({'type': 'error', 'text': 'The vote could not be found.'});
            $location.path("/Votes");
        };
        VoteResource.get({VoteId:$routeParams.VoteId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.vote);
    };

    $scope.save = function() {
        var successCallback = function(){
            flash.setMessage({'type':'success','text':'The vote was updated successfully.'}, true);
            $scope.get();
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        };
        $scope.vote.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Votes");
    };

    $scope.remove = function() {
        var successCallback = function() {
            flash.setMessage({'type': 'error', 'text': 'The vote was deleted.'});
            $location.path("/Votes");
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        }; 
        $scope.vote.$remove(successCallback, errorCallback);
    };
    
    $scope.voteSelectionList = [
        "YELLOW",  
        "RED",  
        "GREEN"  
    ];
    
    $scope.get();
});