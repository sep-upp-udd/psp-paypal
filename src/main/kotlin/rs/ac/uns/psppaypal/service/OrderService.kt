package rs.ac.uns.psppaypal.service

import rs.ac.uns.psppaypal.controller.dto.*

interface OrderService {
    fun getOrder(order: GetOrderRequest): GetOrderResponse
    fun saveOrder(saveOrderRequest: SaveOrderRequest): SaveOrderResponse
    fun getSubscription(merchantUuid: String, planId: String): GetSubscriptionResponse
}