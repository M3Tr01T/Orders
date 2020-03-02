package org.acacho.orders.adapter.out;

import org.acacho.orders.adapter.out.dto.MongoOrderDto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpringMongoOrderRepository extends MongoRepository<MongoOrderDto, String> {

}
