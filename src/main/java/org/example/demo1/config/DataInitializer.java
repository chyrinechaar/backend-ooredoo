package org.example.demo1.config;

import org.example.demo1.model.Product;
import org.example.demo1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        // Initialize sample products only if the database is empty
        if (productRepository.count() == 0) {
            initializeProducts();
            System.out.println("Sample products have been initialized in the database.");
        } else {
            System.out.println("Products already exist in the database. Skipping initialization.");
        }
    }

    private void initializeProducts() {
        List<Product> sampleProducts = Arrays.asList(
                createProduct("iPhone 15 Pro", "Latest Apple smartphone with A17 Pro chip", 
                             new BigDecimal("999.99"), "Electronics", 50, 25, "IPHONE15PRO", 
                             "https://example.com/iphone15pro.jpg"),
                             
                createProduct("Samsung Galaxy S24", "High-end Android smartphone with AI features", 
                             new BigDecimal("899.99"), "Electronics", 30, 18, "GALAXY_S24", 
                             "https://example.com/galaxys24.jpg"),
                             
                createProduct("MacBook Air M3", "Lightweight laptop with Apple M3 chip", 
                             new BigDecimal("1299.99"), "Electronics", 20, 12, "MACBOOK_AIR_M3", 
                             "https://example.com/macbookair.jpg"),
                             
                createProduct("Nike Air Max 270", "Comfortable running shoes with Air Max technology", 
                             new BigDecimal("150.00"), "Footwear", 100, 45, "NIKE_AIRMAX_270", 
                             "https://example.com/airmax270.jpg"),
                             
                createProduct("Adidas Ultraboost 22", "Premium running shoes with Boost midsole", 
                             new BigDecimal("180.00"), "Footwear", 75, 32, "ADIDAS_UB22", 
                             "https://example.com/ultraboost22.jpg"),
                             
                createProduct("Levi's 501 Jeans", "Classic straight fit denim jeans", 
                             new BigDecimal("79.99"), "Clothing", 200, 89, "LEVIS_501", 
                             "https://example.com/levis501.jpg"),
                             
                createProduct("The North Face Jacket", "Waterproof outdoor jacket for all weather", 
                             new BigDecimal("250.00"), "Clothing", 40, 15, "TNF_JACKET", 
                             "https://example.com/tnfjacket.jpg"),
                             
                createProduct("Sony WH-1000XM5", "Wireless noise-canceling headphones", 
                             new BigDecimal("399.99"), "Electronics", 60, 28, "SONY_WH1000XM5", 
                             "https://example.com/sonywh1000xm5.jpg"),
                             
                createProduct("KitchenAid Stand Mixer", "Professional 5-quart stand mixer", 
                             new BigDecimal("379.99"), "Home & Kitchen", 25, 8, "KITCHENAID_MIXER", 
                             "https://example.com/kitchenaidmixer.jpg"),
                             
                createProduct("Dyson V15 Vacuum", "Cordless vacuum with laser dust detection", 
                             new BigDecimal("749.99"), "Home & Kitchen", 15, 6, "DYSON_V15", 
                             "https://example.com/dysonv15.jpg"),
                             
                createProduct("Instant Pot Duo 7-in-1", "Multi-use pressure cooker", 
                             new BigDecimal("99.99"), "Home & Kitchen", 80, 42, "INSTANT_POT_DUO", 
                             "https://example.com/instantpot.jpg"),
                             
                createProduct("Yoga Mat Premium", "Non-slip yoga mat with extra cushioning", 
                             new BigDecimal("45.99"), "Sports & Fitness", 150, 67, "YOGA_MAT_PREM", 
                             "https://example.com/yogamat.jpg"),
                             
                createProduct("Fitbit Charge 5", "Advanced fitness tracker with GPS", 
                             new BigDecimal("179.99"), "Sports & Fitness", 90, 34, "FITBIT_CHARGE5", 
                             "https://example.com/fitbitcharge5.jpg"),
                             
                createProduct("Coffee Beans - Ethiopian", "Premium single-origin coffee beans", 
                             new BigDecimal("24.99"), "Food & Beverages", 300, 156, "COFFEE_ETHIOPIAN", 
                             "https://example.com/ethiopiancoffee.jpg"),
                             
                createProduct("Organic Green Tea", "High-quality organic green tea leaves", 
                             new BigDecimal("18.99"), "Food & Beverages", 250, 98, "GREEN_TEA_ORG", 
                             "https://example.com/greentea.jpg")
        );

        productRepository.saveAll(sampleProducts);
    }

    private Product createProduct(String name, String description, BigDecimal price, 
                                 String category, int stockQuantity, int salesCount, 
                                 String sku, String imageUrl) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategory(category);
        product.setStockQuantity(stockQuantity);
        product.setSalesCount(salesCount);
        product.setSku(sku);
        product.setImageUrl(imageUrl);
        product.setIsActive(true);
        return product;
    }
}