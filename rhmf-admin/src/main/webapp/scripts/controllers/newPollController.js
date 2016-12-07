
angular.module('rhmf-admin').controller('NewPollController', function ($scope, $location, locationParser, flash, PollResource , VoteResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.poll = $scope.poll || {};
    
    $scope.voteList = VoteResource.queryAll(function(items){
        $scope.voteSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.id
            });
        });
    });
    $scope.$watch("voteSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.poll.vote = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.poll.vote.push(collectionItem);
            });
        }
    });


    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            flash.setMessage({'type':'success','text':'The poll was created successfully.'});
            $location.path('/Polls');
        };
        var errorCallback = function(response) {
            if(response && response.data) {
                flash.setMessage({'type': 'error', 'text': response.data.message || response.data}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        };
        PollResource.save($scope.poll, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Polls");
    };
});