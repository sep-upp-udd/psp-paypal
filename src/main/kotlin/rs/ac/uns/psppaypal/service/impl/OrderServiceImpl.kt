package rs.ac.uns.psppaypal.service.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import rs.ac.uns.psppaypal.controller.dto.*
import rs.ac.uns.psppaypal.exceptions.WrongMerchantException
import rs.ac.uns.psppaypal.model.PurchaseUnit
import rs.ac.uns.psppaypal.repository.MerchantRepository
import rs.ac.uns.psppaypal.repository.PurchaseUnitRepository
import rs.ac.uns.psppaypal.service.OrderService
import java.lang.Exception
import java.util.*

@Service
class OrderServiceImpl(
    private val merchantRepository: MerchantRepository,
    private val purchaseUnitRepository: PurchaseUnitRepository,
) : OrderService {

    @Value("\${PSP_FRONT_PATH}")
    private val pspFrontUri: String = ""

    override fun getOrder(order: GetOrderRequest): GetOrderResponse {

        val merchant = merchantRepository.findOneByUuid(order.merchantUuid)
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
        val merchant = merchantRepository.findOneByUuid(saveOrderRequest.merchantUuid)
        val purchaseUnit = saveOrderRequest.purchaseUnit.toPurchaseUnit()
        purchaseUnit.uuid = UUID.randomUUID().toString();
        merchant.purchases.add(purchaseUnit)
        println(merchant.purchases.size)
        merchantRepository.save(merchant)
        return SaveOrderResponse(paypalUri = pspFrontUri + "paypal/purchase/" + merchant.uuid + "/" + purchaseUnit.uuid)
    }
}