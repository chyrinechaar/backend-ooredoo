# Product Management API Documentation

## Overview
This document describes the Product Management API that has been added to your Spring Boot application. The API provides comprehensive CRUD operations and analytics for product management.

## Base URL
```
http://localhost:8081/api/products
```

## API Endpoints

### Basic CRUD Operations

#### 1. Get All Products
- **GET** `/api/products`
- **Description**: Retrieves all products from the database
- **Response**: Array of Product objects

#### 2. Get Product by ID
- **GET** `/api/products/{id}`
- **Description**: Retrieves a specific product by its ID
- **Parameters**: `id` (path parameter) - Product ID
- **Response**: Product object or 404 if not found

#### 3. Create Product
- **POST** `/api/products`
- **Description**: Creates a new product
- **Request Body**: Product object (JSON)
- **Response**: Created Product object with generated ID

#### 4. Update Product
- **PUT** `/api/products/{id}`
- **Description**: Updates an existing product
- **Parameters**: `id` (path parameter) - Product ID
- **Request Body**: Product object (JSON)
- **Response**: Updated Product object

#### 5. Delete Product
- **DELETE** `/api/products/{id}`
- **Description**: Deletes a product by its ID
- **Parameters**: `id` (path parameter) - Product ID
- **Response**: Success message

### Filtering and Search Operations

#### 6. Get Active Products
- **GET** `/api/products/active`
- **Description**: Retrieves only active products
- **Response**: Array of active Product objects

#### 7. Get Products by Category
- **GET** `/api/products/category/{category}`
- **Description**: Retrieves products by category
- **Parameters**: `category` (path parameter) - Product category
- **Response**: Array of Product objects

#### 8. Search Products by Name
- **GET** `/api/products/search?name={searchTerm}`
- **Description**: Searches products by name (case-insensitive)
- **Parameters**: `name` (query parameter) - Search term
- **Response**: Array of matching Product objects

#### 9. Get Products by Price Range
- **GET** `/api/products/price-range?minPrice={min}&maxPrice={max}`
- **Description**: Retrieves products within a price range
- **Parameters**: 
  - `minPrice` (query parameter) - Minimum price
  - `maxPrice` (query parameter) - Maximum price
- **Response**: Array of Product objects

#### 10. Get In-Stock Products
- **GET** `/api/products/in-stock`
- **Description**: Retrieves products with stock quantity > 0
- **Response**: Array of Product objects

### Analytics and Reports

#### 11. Get Top Selling Products
- **GET** `/api/products/top-selling`
- **Description**: Retrieves products ordered by sales count (highest first)
- **Response**: Array of Product objects

#### 12. Get Low Stock Products
- **GET** `/api/products/low-stock?threshold={threshold}`
- **Description**: Retrieves products with stock below threshold
- **Parameters**: `threshold` (query parameter, default: 10) - Stock threshold
- **Response**: Array of Product objects

#### 13. Get Total Sales Count
- **GET** `/api/products/analytics/total-sales`
- **Description**: Returns total sales across all products
- **Response**: `{"totalSales": number}`

#### 14. Get Dashboard Summary
- **GET** `/api/products/dashboard-summary`
- **Description**: Returns comprehensive dashboard data
- **Response**: 
```json
{
  "totalProducts": number,
  "activeProducts": number,
  "lowStockProducts": number,
  "totalSales": number,
  "topSellingProducts": [Product objects (top 5)]
}
```

### Product Management Operations

#### 15. Record Sale
- **POST** `/api/products/{id}/record-sale?quantity={qty}`
- **Description**: Records a sale for a product (decreases stock, increases sales count)
- **Parameters**: 
  - `id` (path parameter) - Product ID
  - `quantity` (query parameter) - Quantity sold
- **Response**: Updated Product object

#### 16. Add Stock
- **POST** `/api/products/{id}/add-stock?quantity={qty}`
- **Description**: Adds stock to a product
- **Parameters**: 
  - `id` (path parameter) - Product ID
  - `quantity` (query parameter) - Quantity to add
- **Response**: Updated Product object

#### 17. Toggle Product Status
- **POST** `/api/products/{id}/toggle-status`
- **Description**: Toggles product active/inactive status
- **Parameters**: `id` (path parameter) - Product ID
- **Response**: Updated Product object

### Utility Endpoints

#### 18. Get All Categories
- **GET** `/api/products/categories`
- **Description**: Returns list of all unique product categories
- **Response**: Array of category names

#### 19. Validate Product Name
- **GET** `/api/products/validate/name?name={productName}`
- **Description**: Checks if product name is unique
- **Parameters**: `name` (query parameter) - Product name to validate
- **Response**: `{"isUnique": boolean}`

#### 20. Validate SKU
- **GET** `/api/products/validate/sku?sku={skuCode}`
- **Description**: Checks if SKU is unique
- **Parameters**: `sku` (query parameter) - SKU to validate
- **Response**: `{"isUnique": boolean}`

## Product Data Model

```json
{
  "id": 1,
  "name": "Product Name",
  "description": "Product description",
  "price": 99.99,
  "category": "Category Name",
  "stockQuantity": 100,
  "salesCount": 25,
  "isActive": true,
  "createdAt": "2025-08-27T15:27:27.617007",
  "updatedAt": "2025-08-27T15:27:27.617007",
  "imageUrl": "https://example.com/image.jpg",
  "sku": "PRODUCT_SKU"
}
```

## Database
The application uses H2 in-memory database with auto-initialization of sample products. The database is accessible via H2 Console at:
```
http://localhost:8081/h2-console
```

**Connection Details:**
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (empty)

## Sample Data
The application automatically initializes with 15 sample products across various categories:
- Electronics (iPhone, Samsung Galaxy, MacBook, Sony Headphones)
- Footwear (Nike Air Max, Adidas Ultraboost)
- Clothing (Levi's Jeans, North Face Jacket)
- Home & Kitchen (KitchenAid Mixer, Dyson Vacuum, Instant Pot)
- Sports & Fitness (Yoga Mat, Fitbit)
- Food & Beverages (Coffee Beans, Green Tea)

## Integration with Frontend
These endpoints can be integrated with your Angular frontend dashboard to:
1. Display dynamic product listings instead of static data
2. Show real-time sales analytics
3. Manage product inventory
4. Track low stock alerts
5. Generate sales reports

## Error Handling
All endpoints return appropriate HTTP status codes:
- 200: Success
- 201: Created (for POST operations)
- 400: Bad Request (validation errors)
- 404: Not Found
- 500: Internal Server Error

Error responses include descriptive messages in the format:
```json
{"error": "Error description"}
```