package rs.ac.uns.psppaypal.controller.dto

import rs.ac.uns.psppaypal.model.PurchaseUnit
import rs.ac.uns.psppaypal.model.enums.CurrencyCode
import java.io.Serializable

class PurchaseUnitDto(
    var description: String,
    var currencyCode: CurrencyCode,
    var value: Double,
    var emailAddress: String?,
    var merchantId: String?
) : Serializable {
    fun toPurchaseUnit(): PurchaseUnit {
        return PurchaseUnit(
            description = this.description,
            currencyCode = this.currencyCode,
            value = this.value,
            emailAddress = this.emailAddress,
            merchantId = this.merchantId
        )
    }

    constructor(purchaseUnit: PurchaseUnit) : this(
        description = purchaseUnit.description,
        currencyCode = purchaseUnit.currencyCode,
        value = purchaseUnit.value,
        emailAddress = purchaseUnit.emailAddress,
        merchantId = purchaseUnit.merchantId
    )
}