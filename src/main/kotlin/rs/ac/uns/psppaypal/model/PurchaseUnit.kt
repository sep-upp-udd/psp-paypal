package rs.ac.uns.psppaypal.model

import rs.ac.uns.psppaypal.model.enums.CurrencyCode
import java.io.Serializable
import javax.persistence.*

@Entity
class PurchaseUnit(
    @Id
    @SequenceGenerator(
        name = "purchase_unit_sequence_generator",
        sequenceName = "purchase_unit_sequence",
        initialValue = 100
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purchase_unit_sequence_generator")
    @Column(name = "id", unique = true)
    var id: Long = 0L,

    @Column
    var description: String = "",

    @Column
    var currencyCode: CurrencyCode = CurrencyCode.EUR,

    @Column
    var value: Double = 0.0,

    @Column(nullable = true)
    var emailAddress: String? = "",

    @Column(nullable = true)
    var merchantId: String? = "",

    @Column
    var uuid: String = "",

) : Serializable