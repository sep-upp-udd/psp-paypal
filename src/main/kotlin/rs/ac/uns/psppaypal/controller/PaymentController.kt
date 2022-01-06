package rs.ac.uns.psppaypal.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import rs.ac.uns.psppaypal.controller.dto.GetOrderRequest
import rs.ac.uns.psppaypal.controller.dto.GetOrderResponse
import rs.ac.uns.psppaypal.controller.dto.SaveOrderRequest
import rs.ac.uns.psppaypal.controller.dto.SaveOrderResponse
import rs.ac.uns.psppaypal.service.OrderService

@RestController
@RequestMapping(value = ["/payment"])
class PaymentController(private val orderService: OrderService) {

    @PostMapping("/purchase")
    fun order(@RequestBody getOrderRequest: GetOrderRequest):ResponseEntity<GetOrderResponse>{
        return ResponseEntity(orderService.getOrder(getOrderRequest), HttpStatus.OK)
    }


    @PostMapping
    fun order(@RequestBody saveOrderRequest: SaveOrderRequest):ResponseEntity<SaveOrderResponse>{
        return ResponseEntity(orderService.saveOrder(saveOrderRequest), HttpStatus.OK)
    }
}