package rs.ac.uns.psppaypal.controller

import org.springframework.boot.logging.LogLevel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import rs.ac.uns.psppaypal.controller.dto.*
import rs.ac.uns.psppaypal.exceptions.MerchantNotFoundException
import rs.ac.uns.psppaypal.exceptions.PlanNotFoundException
import rs.ac.uns.psppaypal.service.RegistrationService

@RestController
@RequestMapping(value = ["/registration"])
class RegistrationController(private val registrationService: RegistrationService) {

    @PostMapping
    fun register(@RequestBody registrationRequest: RegistrationRequest): ResponseEntity<RegistrationResponse> {
        return ResponseEntity(registrationService.register(registrationRequest), HttpStatus.OK)
    }

    @PostMapping("/subscription")
    fun addSubscriptionPlan(@RequestBody addSubscriptionPlanRequest: AddSubscriptionPlanRequest): ResponseEntity<Any> {
        return try {
            ResponseEntity(
                AddSubscriptionPlanResponse(
                    uuid = registrationService.addSubscriptionPlan(
                        addSubscriptionPlanRequest
                    )
                ), HttpStatus.OK
            )
        } catch (merchantNotFoundException: MerchantNotFoundException) {
            ResponseEntity(merchantNotFoundException.message, HttpStatus.BAD_REQUEST)
        } catch (planNotFoundException: PlanNotFoundException) {
            ResponseEntity(planNotFoundException.message, HttpStatus.BAD_REQUEST)
        }
    }

    @PostMapping("/disable")
    fun disableMerchant(@RequestBody disableRequest: DisableRequest): ResponseEntity<Any> {
        return try {
            registrationService.disable(disableRequest.merchantUuid)
            ResponseEntity(HttpStatus.OK)
        } catch (e: MerchantNotFoundException) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }
}