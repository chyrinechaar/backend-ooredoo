package org.example.demo1.controller;

import jakarta.validation.Valid;
import org.example.demo1.model.Product;
import org.example.demo1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody Product product) {
        try {
            Product saved = productService.saveProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
        try {
            Product updated = productService.updateProduct(id, product);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.ok(Map.of("message", "Product deleted successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/active")
    public ResponseEntity<List<Product>> getActiveProducts() {
        return ResponseEntity.ok(productService.getActiveProducts());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(productService.getProductsByCategory(category));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String name) {
        return ResponseEntity.ok(productService.searchProductsByName(name));
    }

    @GetMapping("/price-range")
    public ResponseEntity<List<Product>> getProductsByPriceRange(
            @RequestParam BigDecimal minPrice,
            @RequestParam BigDecimal maxPrice) {
        return ResponseEntity.ok(productService.getProductsByPriceRange(minPrice, maxPrice));
    }

    @GetMapping("/in-stock")
    public ResponseEntity<List<Product>> getInStockProducts() {
        return ResponseEntity.ok(productService.getInStockProducts());
    }

    @GetMapping("/top-selling")
    public ResponseEntity<List<Product>> getTopSellingProducts() {
        return ResponseEntity.ok(productService.getTopSellingProducts());
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<Product>> getLowStockProducts(
            @RequestParam(defaultValue = "10") int threshold) {
        return ResponseEntity.ok(productService.getLowStockProducts(threshold));
    }

    @GetMapping("/category/{category}/top-selling")
    public ResponseEntity<List<Product>> getTopSellingByCategory(@PathVariable String category) {
        return ResponseEntity.ok(productService.getProductsByCategoryOrderBySales(category));
    }

    @GetMapping("/analytics/total-sales")
    public ResponseEntity<Map<String, Long>> getTotalSales() {
        Long totalSales = productService.getTotalSalesCount();
        return ResponseEntity.ok(Map.of("totalSales", totalSales));
    }

    @PostMapping("/{id}/record-sale")
    public ResponseEntity<?> recordSale(@PathVariable Long id, @RequestParam int quantity) {
        try {
            Product product = productService.recordSale(id, quantity);
            return ResponseEntity.ok(product);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/{id}/add-stock")
    public ResponseEntity<?> addStock(@PathVariable Long id, @RequestParam int quantity) {
        try {
            Product product = productService.addStock(id, quantity);
            return ResponseEntity.ok(product);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/{id}/toggle-status")
    public ResponseEntity<?> toggleProductStatus(@PathVariable Long id) {
        try {
            Product product = productService.toggleProductStatus(id);
            return ResponseEntity.ok(product);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // ✅ Ces méthodes doivent être à l’intérieur de la classe
    @GetMapping("/validate/name")
    public ResponseEntity<Map<String, Boolean>> validateProductName(@RequestParam String name) {
        boolean isUnique = productService.isProductNameUnique(name);
        return ResponseEntity.ok(Map.of("isUnique", isUnique));
    }

    @GetMapping("/validate/sku")
    public ResponseEntity<Map<String, Boolean>> validateSku(@RequestParam String sku) {
        boolean isUnique = productService.isSkuUnique(sku);
        return ResponseEntity.ok(Map.of("isUnique", isUnique));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<String>> getAllCategories() {
        List<String> categories = productService.getAllProducts()
                .stream()
                .map(Product::getCategory)
                .distinct()
                .sorted()
                .toList();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/dashboard-summary")
    public ResponseEntity<Map<String, Object>> getDashboardSummary() {
        List<Product> allProducts = productService.getAllProducts();
        List<Product> activeProducts = productService.getActiveProducts();
        List<Product> lowStockProducts = productService.getLowStockProducts(10);
        Long totalSales = productService.getTotalSalesCount();
        Map<String, Object> summary = Map.of(
                "totalProducts", allProducts.size(),
                "activeProducts", activeProducts.size(),
                "lowStockProducts", lowStockProducts.size(),
                "totalSales", totalSales,
                "topSellingProducts", productService.getTopSellingProducts().stream().limit(5).toList()
        );
        return ResponseEntity.ok(summary);
    }
}
