'use strict';

/**
 * @ngdoc function
 * @name empApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the frontendApp
 */
angular.module('empApp')
    .controller('MainCtrl', function ($scope, $log) {
    
    $log.info('MainCtrl loaded');
    
    $scope.notifications = {
        amount: 3
    };
    
    $scope.navmenuItems = [
        { title: 'Dashboard', href: '/' },
        { title: 'Mijn profiel', href: '/profile' },
        { title: 'Zoek', href: '/search' },
        { title: 'Agenda', href: '/agenda' },
        { title: 'Opvolging', href: '/follow-up' },
        { title: 'Services', href: '/services' }
    ];
    
    $scope.closeNavigation = function() {
        angular.element('.offcanvas').offcanvas('hide');
    };
  });
