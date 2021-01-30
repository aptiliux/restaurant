package org.aptiliux.restaurant.repository;

import org.aptiliux.restaurant.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
