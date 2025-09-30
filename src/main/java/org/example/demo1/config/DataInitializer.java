package org.example.demo1.config;

import org.example.demo1.model.Product;
import org.example.demo1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct; // ✅ version correcte pour Spring Boot 3+

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer {

    @Autowired
    private ProductRepository productRepository;

    @PostConstruct
    public void init() {
        if (productRepository.count() == 0) {
            initializeProducts();
            System.out.println("Sample products have been initialized in the database.");
        } else {
            System.out.println("Products already exist in the database. Skipping initialization.");
        }
    }

    private void initializeProducts() {
        List<Product> sampleProducts = Arrays.asList(

                // Internet Packages
                createProduct("Pack Small 4G",
                        "Model: Pack Small | Data: 45 Go | Commitment: 24 Mois | Services: @IP | Speed: 2 Mbps | Voice: 1H | Avantages: Premier mois gratuit + Report Forfait",
                        new BigDecimal("27"), "Internet Package", 100, 300, "PACK_SMALL_4G", "https://example.com/packsmall.jpg"),

                createProduct("Pack Mid 4G",
                        "Model: Pack Mid | Data: 75 Go | Commitment: 24 Mois | Services: @IP | Speed: 2 Mbps | Voice: 1H | Avantages: Premier mois gratuit + Report Forfait",
                        new BigDecimal("45"), "Internet Package", 100, 30, "PACK_MID_4G", "https://example.com/packmid.jpg"),

                createProduct("Pack High 4G",
                        "Model: Pack High | Data: 100 Go | Commitment: 24 Mois | Services: @IP | Speed: 2 Mbps | Voice: 2H | Avantages: Premier mois gratuit + Report Forfait",
                        new BigDecimal("60"), "Internet Package", 100, 299, "PACK_HIGH_4G", "https://example.com/packhigh.jpg"),

                // Phones
                createProduct("Apple iPhone 14",
                        "OS: iOS | Storage: 128GB | RAM: 6GB | Availability: Available",
                        new BigDecimal("1799"), "Smartphone", 50, 300, "IPHONE14", "https://example.com/iphone14.jpg"),

                createProduct("Samsung Galaxy S23",
                        "OS: Android | Storage: 256GB | RAM: 8GB | Availability: Out of Stock",
                        new BigDecimal("1899"), "Smartphone", 0, 250, "GALAXY_S23", "https://example.com/galaxys23.jpg"),

                createProduct("Xiaomi Redmi Note 12",
                        "OS: Android | Storage: 128GB | RAM: 4GB | Availability: Available",
                        new BigDecimal("999"), "Smartphone", 80, 600, "REDMI_NOTE_12", "https://example.com/redminote12.jpg"),

                // Connected Objects
                createProduct("Google Nest Cam",
                        "Category: Camera | Connectivity: Wi-Fi | Battery: Wired | Compatibility: Android/iOS",
                        new BigDecimal("129"), "Connected Object", 40, 150, "NEST_CAM", "https://example.com/nestcam.jpg"),

                createProduct("Amazon Echo Dot",
                        "Category: Smart Speaker | Connectivity: Wi-Fi | Battery: Plug-in | Compatibility: Alexa",
                        new BigDecimal("49"), "Connected Object", 70, 400, "ECHO_DOT", "https://example.com/echodot.jpg"),

                createProduct("Fitbit Charge 5",
                        "Category: Smartwatch | Connectivity: Bluetooth | Battery: 7 days | Compatibility: Android/iOS",
                        new BigDecimal("149"), "Connected Object", 60, 220, "FITBIT_CHARGE5", "https://example.com/fitbit.jpg"),

                // Gateways
                createProduct("GW-1000X",
                        "Type: Wi-Fi | Protocols: MQTT/HTTP | Max Devices: 50 | Location: Warehouse A | Status: Online | Firmware: v1.2.3",
                        new BigDecimal("199"), "IoT Gateway", 20, 20, "GW_1000X", "https://example.com/gw1000x.jpg"),

                createProduct("IoT-Hub 5G",
                        "Type: Cellular | Protocols: MQTT/CoAP | Max Devices: 100 | Location: Factory B | Status: Offline | Firmware: v2.0.1",
                        new BigDecimal("299"), "IoT Gateway", 15, 15, "IOT_HUB_5G", "https://example.com/iothub5g.jpg"),

                createProduct("EdgeBridge",
                        "Type: Ethernet | Protocols: HTTP/MQTT | Max Devices: 25 | Location: Office 1 | Status: Online | Firmware: v1.0.9",
                        new BigDecimal("159"), "IoT Gateway", 10, 10, "EDGE_BRIDGE", "https://example.com/edgebridge.jpg")
        );

        productRepository.saveAll(sampleProducts);
    }




private Product createProduct(String name,
                              String description,
                              BigDecimal price,
                              String category,
                              int stockQuantity,
                              int salesCount,
                              String sku,
                              String imageUrl) {
    Product product = new Product();
    product.setName(name);
    product.setDescription(description);
    product.setPrice(price);
    product.setCategory(category);
    product.setStockQuantity(stockQuantity);
    product.setSalesCount(salesCount);
    product.setSku(sku);
    product.setImageUrl(imageUrl);

    // If your Product entity does NOT have isActive field, remove this line
    product.setIsActive(true);

    return product;
}


        }
