package net.bobko.repository;

import net.bobko.domain.Person;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by TimeLine on 15.02.2017.
 */
@Repository
public class PersonRepository {

    private final Map<Integer, Person> persons = new ConcurrentHashMap<Integer, Person>();

    private final AtomicInteger ids = new AtomicInteger(0);

    /**
     *
     * @param person
     * @return
     */
    public Person create(Person person) {
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        person.setId(this.ids.incrementAndGet());
        persons.put(person.getId(), person);
        return person;
    }

    public Person get(int id) {
        return this.persons.get(id);
    }

    public void update(Person person) {
        this.persons.put(person.getId(), person);
    }
}


