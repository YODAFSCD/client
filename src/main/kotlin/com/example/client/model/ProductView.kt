package com.example.client.model

import java.util.Date
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="product_view")
class ProductView {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    var code :String? = null
    @Column(name="create_at")
    var createAt:Date? = null
    var total:Double? = null
    @Column(name="client_id")
    var clientId:Long? = null
    var client: String? = null

}
