package rs.ac.uns.psppaypal.service

import rs.ac.uns.psppaypal.controller.dto.GetOrderRequest
import rs.ac.uns.psppaypal.controller.dto.GetOrderResponse
import rs.ac.uns.psppaypal.controller.dto.SaveOrderRequest
import rs.ac.uns.psppaypal.controller.dto.SaveOrderResponse

interface OrderService {
    fun getOrder(order: GetOrderRequest):GetOrderResponse
    fun saveOrder(saveOrderRequest: SaveOrderRequest): SaveOrderResponse
}