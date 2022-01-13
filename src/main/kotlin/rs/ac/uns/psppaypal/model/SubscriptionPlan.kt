package rs.ac.uns.psppaypal.model

import javax.persistence.*

@Entity
class SubscriptionPlan (
    @Id
    @SequenceGenerator(
        name = "subscription_sequence_generator",
        sequenceName = "subscription_sequence",
        initialValue = 100
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subscription_sequence_generator")
    @Column(name = "id", unique = true)
    var id: Long = 0L,

    @Column
    var planId: String = "",

    @Column
    var uuid: String = "",
)