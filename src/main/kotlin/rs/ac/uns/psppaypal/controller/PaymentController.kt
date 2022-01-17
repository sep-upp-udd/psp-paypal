package rs.ac.uns.psppaypal.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import rs.ac.uns.psppaypal.controller.dto.GetOrderRequest
import rs.ac.uns.psppaypal.controller.dto.SaveOrderRequest
import rs.ac.uns.psppaypal.exceptions.MerchantNotFoundException
import rs.ac.uns.psppaypal.exceptions.PlanNotFoundException
import rs.ac.uns.psppaypal.exceptions.WrongMerchantException
import rs.ac.uns.psppaypal.service.OrderService

@RestController
@RequestMapping(value = ["/payment"])
class PaymentController(private val orderService: OrderService) {

    @PostMapping("/purchase")
    fun order(@RequestBody getOrderRequest: GetOrderRequest): ResponseEntity<Any> {
        return try {
            ResponseEntity(orderService.getOrder(getOrderRequest), HttpStatus.OK)
        } catch (e: WrongMerchantException) {
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        } catch (e: MerchantNotFoundException) {
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        }
    }

    @PostMapping("/")
    fun order(@RequestBody saveOrderRequest: SaveOrderRequest): ResponseEntity<Any> {
        return try {
            ResponseEntity(orderService.saveOrder(saveOrderRequest), HttpStatus.OK)
        } catch (e: MerchantNotFoundException) {
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping("/subscription/{merchantUuid}/{planUuid}")
    fun subscription(
        @PathVariable(value = "merchantUuid") merchantUuid: String,
        @PathVariable(value = "planUuid") planUuid: String
    ): ResponseEntity<Any> {
        return try {
            val subscriptionResponse = orderService.getSubscription(merchantUuid, planUuid)
            ResponseEntity(subscriptionResponse, HttpStatus.OK)
        } catch (e: MerchantNotFoundException) {
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        } catch (e: PlanNotFoundException) {
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        }
    }
}