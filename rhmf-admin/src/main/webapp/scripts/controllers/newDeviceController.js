
angular.module('rhmf-admin').controller('NewDeviceController', function ($scope, $location, locationParser, flash, DeviceResource ) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.device = $scope.device || {};
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            flash.setMessage({'type':'success','text':'The device was created successfully.'});
            $location.path('/Devices');
        };
        var errorCallback = function(response) {
            if(response && response.data) {
                flash.setMessage({'type': 'error', 'text': response.data.message || response.data}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        };
        DeviceResource.save($scope.device, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Devices");
    };
});