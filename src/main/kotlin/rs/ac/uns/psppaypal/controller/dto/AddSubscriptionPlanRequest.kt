package rs.ac.uns.psppaypal.controller.dto

import java.io.Serializable

class AddSubscriptionPlanRequest(
    var planId: String,
    var merchantUuid: String
) : Serializable