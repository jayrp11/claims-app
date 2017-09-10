var claimApp = angular.module('claimApp', ['ngRoute', 'ngResource', 'LocalStorageModule']);

claimApp.service('Persons', function($resource) {
  this.fetch = function() {
    return $resource('/persons', { personId:'@id' }).query();
  };
});

claimApp.service('Claims', function($resource, localStorageService) {
  this.fetch = function(personId) {
	if(!localStorageService.get('person')) {
	  var person = $resource('/persons/:personId', { personId:'@id' }).get(
        {personId: personId},
        function() {
          localStorageService.set('person', person);
        }
      );
	}
    return $resource('/persons/:personId/claims', {personId: '@Id'}).query({personId:personId});
  };
});

claimApp.config(function($routeProvider) {
  var resolvePersons = {
    persons: function(Persons) {
      return Persons.fetch();
    }
  }

  var resolveClaims = {
    claims: function(Claims, $route) {
      return Claims.fetch($route.current.params.personId);
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
      resolve: resolveClaims
    })
    .otherwise({ redirectTo: '/' })
});

claimApp.controller('PersonController', function(persons, localStorageService, $location) {
  var personCtrl = this;
  personCtrl.persons = persons;

  personCtrl.loadClaims = function(person) {
    localStorageService.set('person', person);
    $location.path('/claims/' + person.id);
  }
});

claimApp.controller('ClaimController', function(claims, localStorageService) {
  var claimCtrl = this;
  claimCtrl.claims = claims;
  claimCtrl.person = localStorageService.get('person');
});