package io.microprofile.kotlin.controller

import io.microprofile.kotlin.model.AdmPhrase
import io.microprofile.kotlin.repository.AdmPhraseRepository
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.UriBuilder

@Path("/phrases")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class AdmPhraseController @Inject constructor(adm: AdmPhraseRepository) {

    var admPhraseRepository = adm

    @GET
    fun findAll(@QueryParam("author") @DefaultValue("%") author: String,
                @QueryParam("phrase") @DefaultValue("%") phrase: String)
        = admPhraseRepository.listAll(author, phrase)

    @POST
    fun create(phrase: AdmPhrase): Response {
        admPhraseRepository.create(phrase)
        return Response.created(
                UriBuilder.fromResource(this::class.java).build()
        ).build()
    }
}