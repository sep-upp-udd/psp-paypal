package rs.ac.uns.psppaypal.controller.dto

import rs.ac.uns.psppaypal.model.enums.CurrencyCode
import rs.ac.uns.psppaypal.model.enums.DisbursementMode
import java.io.Serializable

class GetOrderResponse(
    var purchaseUnit: PurchaseUnitDto,
    var clientId: String,
    var successAddress: String,
    var errorAddress: String,
    var name: String,
    var shoppers: Boolean,
    var disbursementMode: DisbursementMode,
    var platformFeeValue: Double,
    var platformFeeCurrency: CurrencyCode,
) : Serializable