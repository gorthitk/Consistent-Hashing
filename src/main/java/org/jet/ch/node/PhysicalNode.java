package org.jet.ch.node;


/**
 * Represents a Physical Server.
 */
public class PhysicalNode implements ServerNode {
    private final String identifier;

    public PhysicalNode(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String getIdentifier() {
        return this.identifier;
    }
}
