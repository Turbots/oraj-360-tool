'use strict';

/**
 * @ngdoc function
 * @name empApp.controller:SearchCtrl
 * @description
 * # SearchCtrl
 * Controller of the frontendApp
 */
angular.module('empApp')
    .controller('SearchCtrl', function ($scope, $log, Persons) {
    
    $log.info('SearchCtrl loaded');
    
    $scope.persons = Persons.all();
  });
