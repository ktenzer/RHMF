
angular.module('rhmf-admin').controller('NewVoteController', function ($scope, $location, locationParser, flash, VoteResource ) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.vote = $scope.vote || {};
    
    $scope.voteSelectionList = [
        "YELLOW",
        "RED",
        "GREEN"
    ];
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            flash.setMessage({'type':'success','text':'The vote was created successfully.'});
            $location.path('/Votes');
        };
        var errorCallback = function(response) {
            if(response && response.data) {
                flash.setMessage({'type': 'error', 'text': response.data.message || response.data}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        };
        VoteResource.save($scope.vote, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Votes");
    };
});