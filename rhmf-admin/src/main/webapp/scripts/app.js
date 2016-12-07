'use strict';

angular.module('rhmf-admin',['ngRoute','ngResource'])
  .config(['$routeProvider', function($routeProvider) {
    $routeProvider
      .when('/',{templateUrl:'views/landing.html',controller:'LandingPageController'})
      .when('/Devices',{templateUrl:'views/Device/search.html',controller:'SearchDeviceController'})
      .when('/Devices/new',{templateUrl:'views/Device/detail.html',controller:'NewDeviceController'})
      .when('/Devices/edit/:DeviceId',{templateUrl:'views/Device/detail.html',controller:'EditDeviceController'})
      .when('/Polls',{templateUrl:'views/Poll/search.html',controller:'SearchPollController'})
      .when('/Polls/new',{templateUrl:'views/Poll/detail.html',controller:'NewPollController'})
      .when('/Polls/edit/:PollId',{templateUrl:'views/Poll/detail.html',controller:'EditPollController'})
      .when('/Votes',{templateUrl:'views/Vote/search.html',controller:'SearchVoteController'})
      .when('/Votes/new',{templateUrl:'views/Vote/detail.html',controller:'NewVoteController'})
      .when('/Votes/edit/:VoteId',{templateUrl:'views/Vote/detail.html',controller:'EditVoteController'})
      .otherwise({
        redirectTo: '/'
      });
  }])
  .controller('LandingPageController', function LandingPageController() {
  })
  .controller('NavController', function NavController($scope, $location) {
    $scope.matchesRoute = function(route) {
        var path = $location.path();
        return (path === ("/" + route) || path.indexOf("/" + route + "/") == 0);
    };
  });
