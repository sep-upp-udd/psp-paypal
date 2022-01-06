package rs.ac.uns.psppaypal.controller.dto

import java.io.Serializable

class SaveOrderRequest(
    var merchantUuid: String,
    var purchaseUnit: PurchaseUnitDto
) : Serializable