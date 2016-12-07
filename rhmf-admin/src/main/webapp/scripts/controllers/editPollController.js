

angular.module('rhmf-admin').controller('EditPollController', function($scope, $routeParams, $location, flash, PollResource , VoteResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.poll = new PollResource(self.original);
            VoteResource.queryAll(function(items) {
                $scope.voteSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.poll.vote){
                        $.each($scope.poll.vote, function(idx, element) {
                            if(item.id == element.id) {
                                $scope.voteSelection.push(labelObject);
                                $scope.poll.vote.push(wrappedObject);
                            }
                        });
                        self.original.vote = $scope.poll.vote;
                    }
                    return labelObject;
                });
            });
        };
        var errorCallback = function() {
            flash.setMessage({'type': 'error', 'text': 'The poll could not be found.'});
            $location.path("/Polls");
        };
        PollResource.get({PollId:$routeParams.PollId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.poll);
    };

    $scope.save = function() {
        var successCallback = function(){
            flash.setMessage({'type':'success','text':'The poll was updated successfully.'}, true);
            $scope.get();
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        };
        $scope.poll.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Polls");
    };

    $scope.remove = function() {
        var successCallback = function() {
            flash.setMessage({'type': 'error', 'text': 'The poll was deleted.'});
            $location.path("/Polls");
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        }; 
        $scope.poll.$remove(successCallback, errorCallback);
    };
    
    $scope.voteSelection = $scope.voteSelection || [];
    $scope.$watch("voteSelection", function(selection) {
        if (typeof selection != 'undefined' && $scope.poll) {
            $scope.poll.vote = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.poll.vote.push(collectionItem);
            });
        }
    });
    
    $scope.get();
});