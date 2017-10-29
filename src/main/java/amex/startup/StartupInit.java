package amex.startup;

import java.time.LocalDate;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import amex.domain.Person;
import amex.repository.PersonRepository;

/**
 * @author David
 *
 */
@Component
public class StartupInit {
	
	@Autowired
	PersonRepository personRepository;
	
	@PostConstruct
	public void initializeDB (){

		amex.domain.Person person = new Person();
		
		person.setName("John Smith");
		person.setAge(45);
		person.setDateOfBirth(LocalDate.parse("1972-05-12"));
		person.setEmailAddress("John.Smith@yahoo.com");
		personRepository.save(person);
		
		person = new Person();
		
		person.setName("Paul Jones");
		person.setAge(35);
		person.setDateOfBirth(LocalDate.parse("1982-05-12"));
		person.setEmailAddress("Paul.Jones@gmail.com");
		personRepository.save(person);
		
		person.setName("Mark Milleer");
		person.setAge(25);
		person.setDateOfBirth(LocalDate.parse("1982-05-12"));
		person.setEmailAddress("Mark.Miller@yahoo.com");
		personRepository.save(person);
		
	
	}
	
}