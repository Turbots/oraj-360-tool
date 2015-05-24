'use strict';

/**
 * @ngdoc overview
 * @name empApp
 * @description
 * # empApp
 *
 * Main module of the application.
 */
angular
    .module('empApp', [
        'ngAnimate',
        'ngAria',
        'ngCookies',
        'ngMessages',
        'ngResource',
        'ngRoute',
        'ngSanitize',
        'ngTouch',
        'ui.bootstrap'
    ])
    .config(function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'views/dashboard.html',
                controller: 'DashboardCtrl'
            })
            .when('/profile', {
                templateUrl: 'views/profile.html',
                controller: 'ProfileCtrl'
            })
            .when('/search', {
                templateUrl: 'views/search.html',
                controller: 'SearchCtrl'
            })
            .when('/persons', {
                templateUrl: 'views/persons.html',
                controller: 'PersonsCtrl'
            })
            .otherwise({
                redirectTo: '/'
            });
    });
