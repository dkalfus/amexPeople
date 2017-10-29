package amex.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import amex.domain.Person;
import amex.repository.PersonRepository;
import amex.dto.PersonDto;

@RestController
@RequestMapping("/people")
public class PersonController {
	
	@Autowired
	PersonRepository personRepository;
	
	private static DateTimeFormatter DATE_OF_BIRTH_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public PersonDto getPerson(@PathVariable Long id) {
		//Person person = personRepository.findOne(id);			

		return personRepository.findById(id)
        .map(thePerson-> personToPersonDto(thePerson))
        .orElseThrow(() -> 
        new ResourceNotFoundException(Person.class.getSimpleName() + 
        		": id=" + id.toString() + " not found."));	
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public Person addPerson(PersonDto personDto) {
		Person person = personDtoToPerson(personDto);
		personRepository.save(person);
		return person;
	}
	
	// For large-scale apps, Dozer might be a better way to perform such mapping
	private static PersonDto personToPersonDto(Person person) {
		PersonDto personDto = new PersonDto();		
		personDto.setName(person.getName());
		personDto.setAge(person.getAge());
		personDto.setEmailAddress(person.getEmailAddress());
		personDto.setDateOfBirth( person.getDateOfBirth().format(DATE_OF_BIRTH_FORMATTER));   
		
		return personDto;
	}
	
	private static Person personDtoToPerson(PersonDto personDto) {
		Person person = new Person();
		person.setName(personDto.getName());
		person.setAge(personDto.getAge());
		person.setEmailAddress(personDto.getEmailAddress());
		person.setDateOfBirth(LocalDate.parse(personDto.getDateOfBirth(),DATE_OF_BIRTH_FORMATTER));
		return person;		
	}	
		
}

