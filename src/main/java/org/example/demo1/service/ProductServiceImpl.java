package org.example.demo1.service;
import org.example.demo1.model.Product;
import org.example.demo1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
@Service
public class ProductServiceImpl implements ProductService {
@Autowired
private ProductRepository productRepository;
@Override
@Transactional
public Product saveProduct(Product product) {
    if (product.getId() == null && productRepository.existsByName(product.getName())) {
        throw new RuntimeException("Product with name '" + product.getName() + "' already exists");
    }
    if (product.getSku() != null && !product.getSku().isEmpty()) {
        if (product.getId() == null && productRepository.existsBySku(product.getSku())) {
            throw new RuntimeException("Product with SKU '" + product.getSku() + "' already exists");
        }
    }
    Product saved = productRepository.save(product);
    System.out.println("Saved product to repository: " + saved);
    return saved;
}
@Override
public List<Product> getAllProducts() {
    return productRepository.findAll();
         }
@Override
public Optional<Product> getProductById(Long id) {
    return productRepository.findById(id);
}
@Override
@Transactional
public Product updateProduct(Long id, Product product) {
    Optional<Product> existingProduct = productRepository.findById(id);
    if (existingProduct.isEmpty()) {
        throw new RuntimeException("Product with ID " + id + " not found");
    }
    Product productToUpdate = existingProduct.get();
    productToUpdate.setName(product.getName());
    productToUpdate.setDescription(product.getDescription());
    productToUpdate.setPrice(product.getPrice());
    productToUpdate.setCategory(product.getCategory());
    productToUpdate.setStockQuantity(product.getStockQuantity());
    productToUpdate.setIsActive(product.getIsActive());
    productToUpdate.setImageUrl(product.getImageUrl());
    productToUpdate.setSku(product.getSku());
    return productRepository.save(productToUpdate);
}
@Override
@Transactional
public void deleteProduct(Long id) {
    if (!productRepository.existsById(id)) {
        throw new RuntimeException("Product with ID " + id + " not found");
    }
        productRepository.deleteById(id);
}
@Override
public List<Product> getActiveProducts() {
    return productRepository.findByIsActiveTrue();
}
@Override
public List<Product> getProductsByCategory(String category) {
    return productRepository.findByCategory(category);
}
@Override
public List<Product> searchProductsByName(String name) {
    return productRepository.findByNameContainingIgnoreCase(name);
          }
@Override
public List<Product> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
    return productRepository.findByPriceBetween(minPrice, maxPrice);
          }
@Override
public List<Product> getInStockProducts() {
    return productRepository.findByStockQuantityGreaterThan(0);
          }
@Override
public List<Product> getTopSellingProducts() {
    return productRepository.findTopSellingProducts();
          }
@Override
public List<Product> getLowStockProducts(int threshold) {
    return productRepository.findLowStockProducts(threshold);
          }
@Override
@Transactional
public Product recordSale(Long productId, int quantity) {
    Optional<Product> productOpt = productRepository.findById(productId);
    if (productOpt.isEmpty()) {
        throw new RuntimeException("Product with ID " + productId + " not found");
               }
        Product product = productOpt.get();
        if (product.getStockQuantity() < quantity) {
            throw new RuntimeException("Insufficient stock. Available: " + product.getStockQuantity() + ", Requested: " + quantity);
                }
        product.recordSale(quantity);
        return productRepository.save(product);
           }
@Override
@Transactional
public Product addStock(Long productId, int quantity) {
    Optional<Product> productOpt = productRepository.findById(productId);
    if (productOpt.isEmpty()) {
        throw new RuntimeException("Product with ID " + productId + " not found");
                  }
        Product product = productOpt.get();
        product.addStock(quantity);
        return productRepository.save(product);
          }
@Override
@Transactional
public Product toggleProductStatus(Long productId) {
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isEmpty()) {
            throw new RuntimeException("Product with ID " + productId + " not found");
                  }
        Product product = productOpt.get();
        product.setIsActive(!product.getIsActive());
        return productRepository.save(product);
           }
@Override
public Long getTotalSalesCount() {
    return productRepository.getTotalSalesCount();
           }
@Override
public List<Product> getProductsByCategoryOrderBySales(String category) {
    return productRepository.findByCategoryOrderBySalesDesc(category);
          }
@Override
public boolean isProductNameUnique(String name) {
    return !productRepository.existsByName(name);
          }
@Override
public boolean isSkuUnique(String sku) {
    return !productRepository.existsBySku(sku);
          }
}