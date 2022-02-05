package io.microprofile.kotlin.model

import javax.persistence.*

@Entity
@Table(name = "adm_phrase")
data class AdmPhrase (

        @Id
        @Column(name = "phrase_id")
        @GeneratedValue(strategy = GenerationType.AUTO)
        var phraseId:Long? = null,

        @Column(name = "author")
        var author:String = "",

        @Column(name = "phrase")
        var phrase:String = ""
)