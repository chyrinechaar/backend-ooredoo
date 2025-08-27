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
    
    // Find products by category
    List<Product> findByCategory(String category);
    
    // Find products by name containing (case insensitive)
    List<Product> findByNameContainingIgnoreCase(String name);
    
    // Find active products only
    List<Product> findByIsActiveTrue();
    
    // Find products by category and active status
    List<Product> findByCategoryAndIsActiveTrue(String category);
    
    // Find products with stock quantity greater than 0
    List<Product> findByStockQuantityGreaterThan(int quantity);
    
    // Find products by price range
    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
    
    // Find product by SKU
    Optional<Product> findBySku(String sku);
    
    // Check if product exists by name (for duplicate checking)
    boolean existsByName(String name);
    
    // Check if SKU exists (for unique SKU validation)
    boolean existsBySku(String sku);
    
    // Custom query to find top selling products
    @Query("SELECT p FROM Product p ORDER BY p.salesCount DESC")
    List<Product> findTopSellingProducts();
    
    // Custom query to find low stock products
    @Query("SELECT p FROM Product p WHERE p.stockQuantity <= :threshold AND p.isActive = true")
    List<Product> findLowStockProducts(@Param("threshold") int threshold);
    
    // Get total sales count for all products
    @Query("SELECT COALESCE(SUM(p.salesCount), 0) FROM Product p WHERE p.isActive = true")
    Long getTotalSalesCount();
    
    // Get products by category ordered by sales
    @Query("SELECT p FROM Product p WHERE p.category = :category AND p.isActive = true ORDER BY p.salesCount DESC")
    List<Product> findByCategoryOrderBySalesDesc(@Param("category") String category);
}