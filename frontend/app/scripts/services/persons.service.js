'use strict';

angular.module('empApp')
.factory('Persons', function() {
    
    var personItemAmount = 30;
    var persons = [];
    for (personItemAmount; personItemAmount > 0; personItemAmount--) {
        persons.push({
            firstname: 'Person ' + personItemAmount,
            lastname: 'Lastname ' + personItemAmount
        });
    }
    
    // Some fake testing data
    return {
        all: function() {
            return persons;
        }
    };
});
