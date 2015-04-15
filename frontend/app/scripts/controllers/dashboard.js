'use strict';

/**
 * @ngdoc function
 * @name empApp.controller:DashboardCtrl
 * @description
 * # DashboardCtrl
 * Controller of the empApp
 */
angular.module('empApp')
    .controller('DashboardCtrl', function ($scope, $log, Timeline) {
    
    $log.info('DashboardCtrl loaded');
    
    $scope.timelineItems = Timeline.all();
  });