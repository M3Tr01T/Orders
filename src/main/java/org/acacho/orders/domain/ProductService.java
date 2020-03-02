package org.acacho.orders.domain;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.acacho.orders.domain.entity.Product;
import org.acacho.orders.domain.exception.ProductNotFoundException;
import org.acacho.orders.domain.repository.ProductRepository;

@Slf4j
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  /**
   * Creates a new Product and returns it.
   *
   * @return new Product created
   */
  public Product createProduct(Product product) {
    return productRepository.save(product);
  }

  /**
   * Updates the status of an Product if it exists.
   *
   * @return the product modified.
   * @throws ProductNotFoundException if the product with the specified id cannot be found.
   */
  public Product updateProduct(Product product) throws ProductNotFoundException {
    var existingProduct = findById(product.getId());
    if (!existingProduct.equals(product)) {
      return productRepository.save(product);
    }
    return product;
  }

  private Product findById(String id) throws ProductNotFoundException {
    return productRepository
        .findById(id)
        .orElseThrow(() -> new ProductNotFoundException("Product not found with id " + id));
  }

  /**
   * Returns a list of products which match the passed list of ids.
   *
   * @param ids list of ids to look for
   * @return list of products which match the passed list of ids
   */
  public List<Product> findAllByIds(List<String> ids) {
    return productRepository.findAllByIds(ids);
  }

  /**
   * retrieves all available products.
   *
   * @return list of all products
   */
  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }
}
