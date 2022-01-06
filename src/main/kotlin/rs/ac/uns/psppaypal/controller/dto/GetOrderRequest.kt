package rs.ac.uns.psppaypal.controller.dto

import java.io.Serializable

class GetOrderRequest (

    val purchaseUnitUuid: String,
    val merchantUuid: String,
        ):Serializable