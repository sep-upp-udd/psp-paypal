package rs.ac.uns.psppaypal.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import rs.ac.uns.psppaypal.model.SubscriptionPlan

interface SubscriptionPlanRepository:CrudRepository<SubscriptionPlan, Long> {
    @Query(
        value = "SELECT DISTINCT id, plan_id, merchant_id, uuid FROM public.subscription_plan WHERE merchant_id=:merchantId AND uuid=:planUuid",
        nativeQuery = true
    )
    fun findMerchantsSubscriptionByPlanUuid(merchantId: Long, planUuid: String):SubscriptionPlan?
}