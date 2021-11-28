package com.come.in.exchange;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ExchangeRepository extends MongoRepository<Exchange, String>{

	@Query(value="{ 'userId' : ?0 }")
	List<Exchange> findByUserId(String userId);
	
	@Query(value="{'myW' : { $gte: ?1 }, 'exR' : { $lte: ?0 }}")
	List<Exchange> findMatchingByExchangeRW(int myR, int exW);
	
	@Query(value="{'myR' : { $gte: ?1 }, 'exW' : { $lte: ?0 }}")
	List<Exchange> findMatchingByExchangeWR(int myW, int exR);
}
