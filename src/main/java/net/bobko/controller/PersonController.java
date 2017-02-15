package net.bobko.controller;

import net.bobko.domain.Person;
import net.bobko.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

/**
 * Created by TimeLine on 15.02.2017.
 */
 @RestController
 @ComponentScan("net.bobko")
 public class PersonController {

        private final PersonRepository persons;

        @Autowired
        public PersonController(final PersonRepository persons) {
            this.persons = persons;
        }

        @RequestMapping(value = "/{id}", method = RequestMethod.GET)
        public Object getById(@PathVariable int id) {
            Person person = this.persons.get(id);
            return person != null ? person : new Object() {
                public String getError() {
                    return "not found";
                }
            };
        }

        @RequestMapping(value = "/", method = RequestMethod.POST)
        public Object create(@RequestBody final Person person) {
            this.persons.create(person);
            return new Object() {
                public int getId() {
                    return person.getId();
                }
            };
        }

        @RequestMapping(value = "/", method = RequestMethod.PUT)
        public Object update(@RequestBody int id, String status) {
            final Person person = this.persons.get(id);
            if (person != null && !person.getStatus().equals(status)){
                person.setStatus(status);
                this.persons.update(person);
            }
            if (person != null) {
                return new Object() {
                    public int getId() {
                        return person.getId();
                    }

                    public String getStatus() {
                        return person.getStatus();
                    }
                };
            } else{
                return new Object() {
                    public String getError() {
                        return "not found";
                    }
                };
            }
        }
    }


