package be.ordina.threesixty.person.repository;

import be.ordina.threesixty.person.model.Person;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by stevedezitter on 14/04/15.
 */
public interface PersonRepository extends PagingAndSortingRepository<Person, String> {
}
