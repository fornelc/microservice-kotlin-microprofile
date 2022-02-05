package io.microprofile.kotlin.controller

import io.microprofile.kotlin.JerseySpec
import org.glassfish.jersey.server.ResourceConfig

class AdmPhraseControllerSpec extends JerseySpec {


    void "test controller"() {
        when:
            def response = target('/phrases').request().post(String)

        then:
            response == 'Hello World!'
    }

    @Override
    ResourceConfig config() {
        registerSingleton('phrases', new AdmPhraseControllerSpec())
        new ResourceConfig(AdmPhraseControllerSpec)
    }
}
