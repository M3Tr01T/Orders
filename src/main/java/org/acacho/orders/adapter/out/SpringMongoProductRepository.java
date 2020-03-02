package org.acacho.orders.adapter.out;

import org.acacho.orders.adapter.out.dto.MongoProductDto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpringMongoProductRepository extends MongoRepository<MongoProductDto, String> {

}
