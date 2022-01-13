package rs.ac.uns.psppaypal.model

import rs.ac.uns.psppaypal.model.enums.CurrencyCode
import rs.ac.uns.psppaypal.model.enums.DisbursementMode
import javax.persistence.*

@Entity
class Merchant(
    @Id
    @SequenceGenerator(
        name = "merchant_sequence_generator",
        sequenceName = "merchant_sequence",
        initialValue = 100
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "merchant_sequence_generator")
    @Column(name = "id", unique = true)
    var id: Long = 0L,

    @Column(unique = true)
    var uuid: String = "",

    @Column
    var clientId: String = "",

    @Column
    var successAddress: String = "",

    @Column
    var errorAddress: String = "",

    @Column
    var name: String = "",

    @Column
    var shoppers: Boolean = false,

    @Column
    var disbursementMode: DisbursementMode = DisbursementMode.INSTANT,

    @Column
    var platformFeeValue: Double = 0.0,

    @Column
    var platformFeeCurrency: CurrencyCode = CurrencyCode.EUR,

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "merchant_join_id")
    var purchases: MutableSet<PurchaseUnit> = mutableSetOf(),

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "merchant_id")
    var subscriptionPlans: MutableSet<SubscriptionPlan> = mutableSetOf(),
)