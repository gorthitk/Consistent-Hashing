package org.jet.ch;

public class PhysicalNode implements ServerNode {
    private final String identifier;

    PhysicalNode(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String getIdentifier() {
        return this.identifier;
    }
}
