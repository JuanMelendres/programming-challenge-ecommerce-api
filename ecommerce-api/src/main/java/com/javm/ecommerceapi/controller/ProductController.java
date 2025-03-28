package com.javm.ecommerceapi.controller;

import com.javm.ecommerceapi.entities.Product;
import com.javm.ecommerceapi.service.product.ProductServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = ProductController.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Product API", description = "E-Commerce Product API")
@Slf4j
public class ProductController {

    public static final String PATH = "/api/v1/products";

    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @Operation(
            summary = "Get all products"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {
                                    @Content(schema = @Schema(implementation = Product[].class))
                            }),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = {
                                    @Content(schema = @Schema())
                            }),
            }
    )
    @GetMapping()
    public ResponseEntity<List<Product>> getProducts() {
        try {
            List<Product> products = productService.getProducts();
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        catch (Exception e) {
            log.error("Error getting all products: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(
            summary = "Get a product by ID"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {
                                    @Content(schema = @Schema(implementation = Product.class))
                            }),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found",
                            content = {
                                    @Content(schema = @Schema())
                            }),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = {
                                    @Content(schema = @Schema())
                            }),
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") long id) {
        try {
            Optional<Product> product = productService.getProduct(id);
            return ResponseEntity.of(product);
        }
        catch (Exception e) {
            log.error("Error getting product with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(
            summary = "Create a new product"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "CREATED",
                            content = {
                                    @Content(schema = @Schema(implementation = Product.class))
                            }),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = {
                                    @Content(schema = @Schema())
                            }),
            }
    )
    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        try {
            Product newProduct = productService.createProduct(product);
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        }
        catch (Exception e) {
            log.error("Error creating new product {}: {}", product, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(
            summary = "Update an existing product"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {
                                    @Content(schema = @Schema(implementation = Product.class))
                            }),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found",
                            content = {
                                    @Content(schema = @Schema())
                            }),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = {
                                    @Content(schema = @Schema())
                            }),
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
        try {
            Optional<Product> updatedProduct = productService.updateProduct(id, product);
            return ResponseEntity.of(updatedProduct);
        }
        catch (Exception e) {
            log.error("Error updating product with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(
            summary = "Delete a product by ID"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "No Content",
                            content = {
                                    @Content(schema = @Schema())
                            }),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found",
                            content = {
                                    @Content(schema = @Schema())
                            }),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = {
                                    @Content(schema = @Schema())
                            }),
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") long id) {
        try {
            Optional<Product> deletedProduct = productService.getProduct(id);

            if (deletedProduct.isEmpty()) {
                log.warn("Product with ID {} not found", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            productService.deleteProduct(id);
            log.info("Product with ID {} deleted successfully", id);
            return ResponseEntity.noContent().build();

        } catch (Exception e) {
            log.error("Error deleting product with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}

