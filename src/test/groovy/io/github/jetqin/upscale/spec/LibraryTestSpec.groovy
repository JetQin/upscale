package io.github.jetqin.upscale.spec

import spock.lang.Specification

class LibraryTestSpec extends Specification {

    def "test sum method"() {
        given:
        int left = 2
        int right = 2

        when:
        int result = left + right

        then:
        result == 4
    }
}
