package rs.ac.uns.psppaypal.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import rs.ac.uns.psppaypal.model.PurchaseUnit

interface PurchaseUnitRepository : CrudRepository<PurchaseUnit, Long> {
    @Query(
        value = "SELECT DISTINCT id, currency_code, description, email_address, merchant_id, uuid, value, merchant_join_id FROM public.purchase_unit WHERE merchant_join_id=:merchantId AND uuid=:purchaseUnitUuid",
        nativeQuery = true
    )
    fun findMerchantsPurchaseByUuid(merchantId: Long, purchaseUnitUuid: String): PurchaseUnit?
}