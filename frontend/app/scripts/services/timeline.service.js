'use strict';

angular.module('empApp')
.factory('Timeline', function() {
    
    var timelineItemAmount = 30;
    var timelineItems = [];
    for (timelineItemAmount; timelineItemAmount > 0; timelineItemAmount--) {
        timelineItems.push({
            title: 'Timeline item ' + timelineItemAmount,
            description: 'This is the description of timeline item ' + timelineItemAmount,
            date: new Date()
        });
    }
    
    // Some fake testing data
    return {
        all: function() {
            return timelineItems;
        }
    };
});
