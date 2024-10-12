package org.jet.ch.node;

import org.jet.ch.function.HashingFunction;
import org.jet.ch.ring.HashRing;

/**
 * Represents a Virtual Server Node on the {@link HashRing}
 */
public class VirtualNode implements ServerNode {
    private final ServerNode physicalNode;
    private final int replicaIndex;

    public VirtualNode(ServerNode physicalNode, int replicaIndex) {
        this.replicaIndex = replicaIndex;
        this.physicalNode = physicalNode;
    }

    @Override
    public String getIdentifier() {
        return physicalNode.getIdentifier() + "-" + replicaIndex;
    }

    public boolean isVirtualNodeOf(String physicalIdentifier) {
        return physicalNode.getIdentifier().equals(physicalIdentifier);
    }

    public String getPhysicalNodeId() {
        return physicalNode.getIdentifier();
    }

    public long getHashIdx(HashingFunction hashFn) {
        return hashFn.apply(this.getIdentifier());
    }
}
