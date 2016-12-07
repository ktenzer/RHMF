angular.module('rhmf-admin').factory('VoteResource', function($resource){
    var resource = $resource('rest/votes/:VoteId',{VoteId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});