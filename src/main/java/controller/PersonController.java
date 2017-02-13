package controller;

import com.sun.javafx.scene.layout.region.Margins;
import domain.Person;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by TimeLine on 13.02.2017.
 */

@RestController
@RequestMapping("/person")
public class PersonController {

    private  Person person;

    private final Map<Integer, Person> persons = new ConcurrentHashMap<Integer, Person>();

    private final AtomicInteger ids = new AtomicInteger(0);

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Person getById(@PathVariable String id) {
        return persons.get(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String create(@RequestBody Person person) {
        final int id = ids.incrementAndGet();
        person.setId(id);
        this.persons.put(id, person);
        return Convert.objectToJson(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public Person update(@RequestBody int id, String status) {
        String previousStatus = person.getStatus();
        if (status.equals(person.getStatus())){
            return person;
        }
        person.setStatus(status);
        this.persons.put(person.getId(), person);
        return person;
    }



}
