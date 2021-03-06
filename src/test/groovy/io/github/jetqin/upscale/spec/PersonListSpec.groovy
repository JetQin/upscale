package io.github.jetqin.upscale.spec

import io.github.jetqin.upscale.domain.Person
import org.junit.Test
import spock.lang.Specification

class PersonListSpec extends Specification{

    @Test
    def 'update person name'() {

        given:
        def person = new Person(1L, "Jet", Collections.emptyList())

        when:
        person.setName("Bruce")

        then:
        person.getName() == "Bruce"

    }

    @Test
    def "Should be able to remove from list"() {
        given:
        def list = [1, 2, 3, 4]

        when:
        list.remove(0)

        then:
        list == [2, 3, 4]
    }
}
