'use strict';

/**
 * @ngdoc function
 * @name empApp.controller:PersonsCtrl
 * @description
 * # PersonsCtrl
 * Controller of the frontendApp
 */
angular.module('empApp')
  .controller('PersonsCtrl', ['$scope', '$modal', '$log', '$http', '$location', 'PersonFactory', function ($scope, $modal, $log, $http, $location, PersonFactory) {
    $log.info('PersonsCtrl loaded');

    $scope.persons = PersonFactory.all();

    $scope.add = function () {
      $scope.modal = $modal.open({
        animation: $scope.animationsEnabled,
        templateUrl: 'userModalContent',
        controller: 'PersonsCtrl',
        size: 'lg',
        scope: $scope
      });
      $scope.modal.result.then(function (message) {
        console.log(message);
      }, function () {
        console.log('Modal dismissed');
      });
    };

    $scope.ok = function () {
      console.log('Adding person...');

      $http.post('http://localhost:9900/api/person-service/persons', {
        firstName: $scope.firstName,
        lastName: $scope.lastName,
        credentials: {
          username: $scope.username,
          password: $scope.password
        }
      }).success(function (data, status, headers) {
        console.log('headers: ' + headers('location'));
        $scope.persons = PersonFactory.update();
        $scope.modal.close('User created');
      });
    };

    $scope.selectPerson = function (person) {
      $http.get(person._links.self.href).success(function (data) {
        $scope.selectedPerson = data;
      });
    };
  }]);
