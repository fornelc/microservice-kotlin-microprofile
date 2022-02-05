package io.microprofile.kotlin.repository

import io.microprofile.kotlin.model.AdmPhrase
import javax.ejb.Stateless
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Stateless
class AdmPhraseRepository {

    @PersistenceContext
    private lateinit var em:EntityManager

    fun create(admPhrase: AdmPhrase) = em.persist(admPhrase)

    fun update(admPhrase: AdmPhrase) = em.merge(admPhrase)

    fun findById(phraseId: Long) = em.find(AdmPhrase::class.java, phraseId)

    fun delete(phraseId: Long) {
        var phrase: AdmPhrase? = this.findById(phraseId)
        if (phrase != null) {
            em.remove(phrase)
        }
    }

    fun listAll(author: String, phrase: String): List<AdmPhrase> {
        val query = """SELECT p FROM AdmPhrase p
            where p.author LIKE :author
            and p.phrase LIKE :phrase
        """.trimMargin()

        return em.createQuery(query, AdmPhrase::class.java)
                .setParameter("author", "%$author%")
                .setParameter("phrase", "%$phrase%")
                .resultList
    }
}