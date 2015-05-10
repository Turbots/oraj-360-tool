package be.ordina.threesixty.timeline.repository;

import be.ordina.threesixty.timeline.model.TimelineEvent;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by stevedezitter on 22/04/15.
 */
public interface TimelineRepository extends CrudRepository<TimelineEvent, String> {
}
