package amex.repository;
	 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;
import amex.domain.Person;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import amex.domain.Person;
	 
	@RepositoryRestResource
	public interface PersonRepository extends CrudRepository<Person, Long> {
		Optional<Person> findById(Long id);
	 
	}