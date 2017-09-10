var claimApp = angular.module('claimApp', ['ngRoute', 'ngResource']);

claimApp.factory('Persons', function($resource) {
  return $resource('/persons/:id');
});

claimApp.config(function($routeProvider) {
  var resolvePersons = {
    persons: function(Persons) {
      return Persons.query().$promise;
    }
  }

  var resolvePerson = {
    person: function(Persons, $route) {
      return Persons.get({id: $route.current.params.personId}).$promise;
    }
  }

  $routeProvider
    .when('/', { 
      controller:'PersonController as personCtrl',
      templateUrl:'personlist.html',
      resolve: resolvePersons
    })
    .when('/claims/:personId', {
      controller:'ClaimController as claimCtrl',
      templateUrl:'claimlist.html',
      resolve: resolvePerson
    })
    .when('/oops', {
      templateUrl:'oops.html'
    })
    .otherwise({ redirectTo: '/' })
});

claimApp.run(function($rootScope, $location) {
  $rootScope.$on('$routeChangeError', function(event, current, previous, error) {
    $location.path('/oops');
  })
});

claimApp.controller('PersonController', function(persons, $location) {
  var personCtrl = this;
  personCtrl.persons = persons;
  
  personCtrl.loadClaims = function(person) {
    $location.path('/claims/' + person.id);
  }
});

claimApp.controller('ClaimController', function(person) {
  var claimCtrl = this;
  claimCtrl.person = person;
});