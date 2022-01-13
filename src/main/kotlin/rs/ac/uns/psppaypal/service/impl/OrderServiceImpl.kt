package rs.ac.uns.psppaypal.service.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import rs.ac.uns.psppaypal.controller.dto.*
import rs.ac.uns.psppaypal.exceptions.MerchantNotFoundException
import rs.ac.uns.psppaypal.exceptions.PlanNotFoundException
import rs.ac.uns.psppaypal.exceptions.WrongMerchantException
import rs.ac.uns.psppaypal.repository.MerchantRepository
import rs.ac.uns.psppaypal.repository.PurchaseUnitRepository
import rs.ac.uns.psppaypal.repository.SubscriptionPlanRepository
import rs.ac.uns.psppaypal.service.OrderService
import java.util.*

@Service
class OrderServiceImpl(
    private val merchantRepository: MerchantRepository,
    private val purchaseUnitRepository: PurchaseUnitRepository,
    private val subscriptionPlanRepository: SubscriptionPlanRepository
) : OrderService {

    @Value("\${PSP_FRONT_PATH}")
    private val pspFrontUri: String = ""

    override fun getOrder(order: GetOrderRequest): GetOrderResponse {

        val merchant = merchantRepository.findOneByUuid(order.merchantUuid) ?: throw MerchantNotFoundException()
        val purchaseUnit =
            purchaseUnitRepository.findMerchantsPurchaseByUuid(merchant.id, order.purchaseUnitUuid)
                ?: throw WrongMerchantException()
        val purchaseUnitDto = PurchaseUnitDto(purchaseUnit)

        return GetOrderResponse(
            purchaseUnit = purchaseUnitDto,
            clientId = merchant.clientId,
            errorAddress = merchant.errorAddress,
            successAddress = merchant.successAddress,
            name = merchant.name,
            shoppers = merchant.shoppers,
            disbursementMode = merchant.disbursementMode,
            platformFeeValue = merchant.platformFeeValue,
            platformFeeCurrency = merchant.platformFeeCurrency
        )
    }

    override fun saveOrder(saveOrderRequest: SaveOrderRequest): SaveOrderResponse {
        val merchant =
            merchantRepository.findOneByUuid(saveOrderRequest.merchantUuid) ?: throw MerchantNotFoundException()
        val purchaseUnit = saveOrderRequest.purchaseUnit.toPurchaseUnit()
        purchaseUnit.uuid = UUID.randomUUID().toString();
        merchant.purchases.add(purchaseUnit)
        merchantRepository.save(merchant)
        return SaveOrderResponse(paypalUri = pspFrontUri + "paypal/purchase/" + merchant.uuid + "/" + purchaseUnit.uuid)
    }

    override fun getSubscription(merchantUuid: String, planUuid: String): GetSubscriptionResponse {
        val merchant = merchantRepository.findOneByUuid(merchantUuid) ?: throw MerchantNotFoundException()
        val plan = subscriptionPlanRepository.findMerchantsSubscriptionByPlanUuid(merchant.id, planUuid)
            ?: throw PlanNotFoundException()
        return GetSubscriptionResponse(clientId = merchant.clientId, planId = plan.planId)
    }
}