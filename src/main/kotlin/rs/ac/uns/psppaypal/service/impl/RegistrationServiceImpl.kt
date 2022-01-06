package rs.ac.uns.psppaypal.service.impl

import org.springframework.stereotype.Service
import rs.ac.uns.psppaypal.controller.dto.RegistrationRequest
import rs.ac.uns.psppaypal.controller.dto.RegistrationResponse
import rs.ac.uns.psppaypal.model.Merchant
import rs.ac.uns.psppaypal.repository.MerchantRepository
import rs.ac.uns.psppaypal.service.RegistrationService
import java.util.*

@Service
class RegistrationServiceImpl(private val merchantRepository: MerchantRepository) : RegistrationService {

    override fun register(registrationRequest: RegistrationRequest): RegistrationResponse {
        val uuid = UUID.randomUUID().toString()
        merchantRepository.save(
            Merchant(
                clientId = registrationRequest.clientId,
                uuid = uuid,
                disbursementMode = registrationRequest.disbursementMode,
                platformFeeCurrency = registrationRequest.platformFeeCurrency,
                platformFeeValue = registrationRequest.platformFeeValue,
                shoppers = registrationRequest.shoppers,
                name = registrationRequest.name,
                successAddress = registrationRequest.successAddress,
                errorAddress = registrationRequest.errorAddress
            )
        )
        return RegistrationResponse(uuid = uuid)
    }
}