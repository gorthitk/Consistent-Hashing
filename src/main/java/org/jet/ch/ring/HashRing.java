package org.jet.ch.ring;

import org.jet.ch.node.ServerNode;

/**
 * Interface for representing a Hash-Ring.
 */
public interface HashRing {
    public int numberOfNodes();

    void register(ServerNode server);

    void unregister(String physicalId);

    /**
     * To find out which server a key is mapped to,
     * go clockwise from the key position until the first server on the ring is found.
     * @param hash long
     * @return String
     */
    String findServer(long hash);
}
