package org.jet.ch.function;

import java.util.function.Function;

/**
 * Hashing function to map servers and keys on to the ring
 * using a uniformly distributed hash function.
 */
public interface HashingFunction extends Function<String, Long> {

}
