
package org.example.demo1.service;
import org.example.demo1.model.Product;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
public interface ProductService {
Product saveProduct(Product product);
List<Product> getAllProducts();
Optional<Product> getProductById(Long id);
Product updateProduct(Long id, Product product);

void deleteProduct(Long id);
List<Product> getActiveProducts();
List<Product> getProductsByCategory(String category);
List<Product> searchProductsByName(String name);
List<Product> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);
List<Product> getInStockProducts();
List<Product> getTopSellingProducts();
List<Product> getLowStockProducts(int threshold);
Product recordSale(Long productId, int quantity);
Product addStock(Long productId, int quantity);
Product toggleProductStatus(Long productId);
Long getTotalSalesCount();
List<Product> getProductsByCategoryOrderBySales(String category);
boolean isProductNameUnique(String name);
boolean isSkuUnique(String sku);
}