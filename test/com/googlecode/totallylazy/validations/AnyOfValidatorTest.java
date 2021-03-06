package com.googlecode.totallylazy.validations;

import org.junit.Test;

import static com.googlecode.totallylazy.predicates.Predicates.is;
import static com.googlecode.totallylazy.matchers.IterableMatcher.hasExactly;
import static com.googlecode.totallylazy.matchers.IterableMatcher.isEmpty;
import static com.googlecode.totallylazy.validations.Validators.validateThat;
import static org.hamcrest.MatcherAssert.assertThat;

public class AnyOfValidatorTest {
    @Test
    public void shouldPassIfAnyValidatorPasses() {
        LogicalValidator<String> validator = validateThat(is("A")).
                or(validateThat(is("B"))). // or() creates an AnyOfValidator
                assigningFailuresTo("key").
                castValidator();

        assertThat(validator.validate("A").messages("key"), isEmpty(String.class));
        assertThat(validator.validate("B").messages("key"), isEmpty(String.class));
    }

    @Test
    public void shouldFailIfAllValidatorsFail() {
        LogicalValidator<String> validator = validateThat(is("A")).
                or(validateThat(is("B"))). // or() creates an AnyOfValidator
                assigningFailuresTo("key").
                castValidator();

        assertThat(validator.validate("C").messages("key"), hasExactly(AnyOfValidator.DEFAULT_MESSAGE));
    }
}
