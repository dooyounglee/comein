package com.come.in.exchange;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ExchangeRepository extends MongoRepository<Exchange, String>{

	@Query(value="{ 'userId' : ?0 }")
	List<Optional<Exchange>> findByUserId(String userId);
}
