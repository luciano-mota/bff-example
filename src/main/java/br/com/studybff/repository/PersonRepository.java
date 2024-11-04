package br.com.studybff.repository;

import br.com.studybff.entity.Person;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class PersonRepository {

    public List<Person> getPerson() {
        final String uri = "http://jsonplaceholder.typicode.com/users";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        return this.getPersonBFF(result);
    }

    private List<Person> getPersonBFF(String result) {
        ArrayList<Person> persons = new ArrayList<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
            List<Person> personList = Arrays.asList(mapper.readValue(result, Person[].class));
            if (!personList.isEmpty()) {
                for (Person p : personList) {
                    Person person = new Person(p.getId(), p.getName(), p.getEmail(), p.getUser());
                    persons.add(person);
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return persons;
    }
}
