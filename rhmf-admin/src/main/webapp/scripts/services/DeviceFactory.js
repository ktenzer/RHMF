angular.module('rhmf-admin').factory('DeviceResource', function($resource){
    var resource = $resource('rest/devices/:DeviceId',{DeviceId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});