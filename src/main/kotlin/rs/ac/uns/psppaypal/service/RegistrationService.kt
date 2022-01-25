package rs.ac.uns.psppaypal.service

import rs.ac.uns.psppaypal.controller.dto.AddSubscriptionPlanRequest
import rs.ac.uns.psppaypal.controller.dto.AddSubscriptionPlanResponse
import rs.ac.uns.psppaypal.controller.dto.RegistrationRequest
import rs.ac.uns.psppaypal.controller.dto.RegistrationResponse

interface RegistrationService {
    fun register(registrationRequest: RegistrationRequest): RegistrationResponse
    fun addSubscriptionPlan(addSubscriptionPlanRequest: AddSubscriptionPlanRequest): String
    fun disable(merchantUuid: String)
}