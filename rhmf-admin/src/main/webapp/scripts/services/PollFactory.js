angular.module('rhmf-admin').factory('PollResource', function($resource){
    var resource = $resource('rest/polls/:PollId',{PollId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});