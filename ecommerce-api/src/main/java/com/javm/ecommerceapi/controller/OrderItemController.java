package com.javm.ecommerceapi.controller;

import com.javm.ecommerceapi.entities.OrderItem;
import com.javm.ecommerceapi.service.orderItem.OrderItemServiceImpl;
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
@RequestMapping(value = OrderItemController.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Order Item API", description = "E-Commerce Order Item API")
@Slf4j
public class OrderItemController {

    public static final String PATH = "/api/v1/order-items";

    private final OrderItemServiceImpl orderItemService;

    public OrderItemController(OrderItemServiceImpl orderItemService) {
        this.orderItemService = orderItemService;
    }

    @Operation(
            summary = "Get all order items"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {
                                    @Content(schema = @Schema(implementation = OrderItem[].class))
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
    public ResponseEntity<List<OrderItem>> getOrderItems() {
        try {
            List<OrderItem> orderItems = orderItemService.getOrderItems();
            return new ResponseEntity<>(orderItems, HttpStatus.OK);
        }
        catch (Exception e) {
            log.error("Error getting all order items: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(
            summary = "Get an order item by ID"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {
                                    @Content(schema = @Schema(implementation = OrderItem.class))
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
    public ResponseEntity<OrderItem> getOrderItem(@PathVariable("id") Long id) {
        try {
            Optional<OrderItem> orderItem = orderItemService.getOrderItem(id);
            return ResponseEntity.of(orderItem);
        }
        catch (Exception e) {
            log.error("Error getting order item with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(
            summary = "Create a new order item"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "CREATED",
                            content = {
                                    @Content(schema = @Schema(implementation = OrderItem.class))
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
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItem orderItem) {
        try {
            OrderItem newOrderItem = orderItemService.createOrderItem(orderItem);
            return new ResponseEntity<>(newOrderItem, HttpStatus.CREATED);
        }
        catch (Exception e) {
            log.error("Error creating new order item {}: {}", orderItem, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(
            summary = "Update an existing order item"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {
                                    @Content(schema = @Schema(implementation = OrderItem.class))
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
    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable("id") Long id, @RequestBody OrderItem orderItem) {
        try {
            Optional<OrderItem> updatedOrderItem = orderItemService.updateOrderItem(id, orderItem);
            return ResponseEntity.of(updatedOrderItem);
        }
        catch (Exception e) {
            log.error("Error updating order item with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(
            summary = "Delete a order item by ID"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {
                                    @Content(schema = @Schema(implementation = OrderItem.class))
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
    public ResponseEntity<OrderItem> deleteOrderItem(@PathVariable("id") Long id) {
        try {
            Optional<OrderItem> deletedOrderItem = orderItemService.deleteOrderItem(id);
            return ResponseEntity.of(deletedOrderItem);
        }
        catch (Exception e) {
            log.error("Error deleting order item with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
