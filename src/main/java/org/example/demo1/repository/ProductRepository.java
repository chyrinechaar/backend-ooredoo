
package org.example.demo1.repository;
import org.example.demo1.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(String category);
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByIsActiveTrue();
    List<Product> findByCategoryAndIsActiveTrue(String category);
    List<Product> findByStockQuantityGreaterThan(int quantity);
    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
    Optional<Product> findBySku(String sku);
    boolean existsByName(String name);
    boolean existsBySku(String sku);
   @Query("SELECT p FROM Product p ORDER BY p.salesCount DESC")
   List<Product> findTopSellingProducts();
    @Query("SELECT p FROM Product p WHERE p.stockQuantity <= :threshold AND p.isActive = true")
    List<Product> findLowStockProducts(@Param("threshold") int threshold);
    @Query("SELECT COALESCE(SUM(p.salesCount), 0) FROM Product p WHERE p.isActive = true")
    Long getTotalSalesCount();
    @Query("SELECT p FROM Product p WHERE p.category = :category AND p.isActive = true ORDER BY p.salesCount DESC")
   List<Product> findByCategoryOrderBySalesDesc(@Param("category") String category);
}