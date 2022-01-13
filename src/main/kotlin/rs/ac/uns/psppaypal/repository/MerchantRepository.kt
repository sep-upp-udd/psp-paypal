package rs.ac.uns.psppaypal.repository

import org.springframework.data.repository.CrudRepository
import rs.ac.uns.psppaypal.model.Merchant

interface MerchantRepository : CrudRepository<Merchant, Long> {
    fun findOneByUuid(uuid: String): Merchant?
}