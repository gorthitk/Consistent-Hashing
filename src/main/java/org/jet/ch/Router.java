package org.jet.ch;

import org.jet.ch.function.HashingFunction;
import org.jet.ch.ring.HashRing;


/**
 * Router Class.
 */
public class Router {
    private final HashingFunction hashFn;
    private final HashRing ring;

    public Router(HashRing ring, HashingFunction hashFn) {
         this.hashFn = hashFn;
         this.ring = ring;
    }

    public String route(String key) {
        long hash = hashFn.apply(key);
        return ring.findServer(hash);
    }
}
