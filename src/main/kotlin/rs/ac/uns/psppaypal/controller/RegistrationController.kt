package rs.ac.uns.psppaypal.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import rs.ac.uns.psppaypal.controller.dto.RegistrationRequest
import rs.ac.uns.psppaypal.controller.dto.RegistrationResponse
import rs.ac.uns.psppaypal.service.RegistrationService

@RestController
@RequestMapping(value = ["/registration"])
class RegistrationController(private val registrationService: RegistrationService) {
    @PostMapping
    fun register(@RequestBody registrationRequest: RegistrationRequest): ResponseEntity<RegistrationResponse> {
        return ResponseEntity(registrationService.register(registrationRequest), HttpStatus.OK)
    }
}