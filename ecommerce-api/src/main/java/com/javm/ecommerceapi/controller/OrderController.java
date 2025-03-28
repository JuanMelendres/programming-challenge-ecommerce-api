package com.javm.ecommerceapi.controller;

import com.javm.ecommerceapi.entities.Order;
import com.javm.ecommerceapi.service.order.OrderServiceImpl;
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
@RequestMapping(value = OrderController.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Order API", description = "E-Commerce Order API")
@Slf4j
public class OrderController {

    public static final String PATH = "/api/v1/orders";

    private final OrderServiceImpl orderService;

    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @Operation(
            summary = "Get all orders"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {
                                    @Content(schema = @Schema(implementation = Order[].class))
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
    public ResponseEntity<List<Order>> getOrders() {
        try {
            List<Order> orders = orderService.getOrders();
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
        catch (Exception e) {
            log.error("Error getting all orders: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(
            summary = "Get an order by ID"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {
                                    @Content(schema = @Schema(implementation = Order.class))
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
    public ResponseEntity<Order> getOrder(@PathVariable("id") Long id) {
        try {
            Optional<Order> order = orderService.getOrder(id);
            return ResponseEntity.of(order);
        }
        catch (Exception e) {
            log.error("Error getting order with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(
            summary = "Create a new order"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "CREATED",
                            content = {
                                    @Content(schema = @Schema(implementation = Order.class))
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
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        try {
            Order newOrder = orderService.createOrder(order);
            return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
        }
        catch (Exception e) {
            log.error("Error creating new order {}: {}", order, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(
            summary = "Update an existing order"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {
                                    @Content(schema = @Schema(implementation = Order.class))
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
    public ResponseEntity<Order> updateOrder(@PathVariable("id") Long id, @RequestBody Order order) {
        try {
            Optional<Order> updatedOrder = orderService.updateOrder(id, order);
            return ResponseEntity.of(updatedOrder);
        }
        catch (Exception e) {
            log.error("Error updating order with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(
            summary = "Delete a order by ID"
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
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = {
                                    @Content(schema = @Schema())
                            }),
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable("id") Long id) {
        try {
            Optional<Order> deletedOrder = orderService.deleteOrder(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            log.error("Error deleting order with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
