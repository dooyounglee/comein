package com.come.in;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface FrequencyRepository extends MongoRepository<Frequency, String>{

	@Query(value="{ 'userId' : ?0 }", fields="{ 'myWhite' : 1, 'myRed' : 1, 'wantWhite' : 1, 'wantRed' : 1 }")
	Optional<Frequency> findByUserId(String userId);
}
