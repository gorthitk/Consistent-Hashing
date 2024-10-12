package org.jet.ch.ring;

import org.jet.ch.function.HashingFunction;
import org.jet.ch.function.Md5Function;
import org.jet.ch.node.ServerNode;
import org.jet.ch.node.VirtualNode;

import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Predicate;

/**
 * Implementation of {@link HashRing} using {@link TreeMap}
 */
public class HashRingImpl implements HashRing {
    private final SortedMap<Long, VirtualNode> ring = new TreeMap<>();
    private final HashingFunction hashFn;
    private final int replicasFactor;

    public HashRingImpl(HashingFunction hashFn, Collection<ServerNode> pNodes, int replicasFactor) throws Exception {
        this.hashFn = hashFn;
        this.replicasFactor = replicasFactor;

        pNodes.forEach(this::addServer);
    }

    @Override
    public int numberOfNodes() {
        return ring.size();
    }

    @Override
    public void register(ServerNode server) {
        this.addServer(server);
    }

    @Override
    public void unregister(String physicalId) {
        this.removeServer(physicalId);
    }

    @Override
    public String findServer(long hash) {
        SortedMap<Long, VirtualNode> tailMap = ring.tailMap(hash);
        long vNodeIdx  = tailMap.isEmpty() ? ring.firstKey() : tailMap.firstKey();
        return ring.get(vNodeIdx).getPhysicalNodeId();
    }

    private void addServer(ServerNode node) {
        for (int i = 0; i < this.replicasFactor; i++) {
            VirtualNode vNode = new VirtualNode(node, i + 1);
            ring.put(vNode.getHashIdx(hashFn), vNode);
        }
    }

    private void removeServer(String physicalId) {
        Predicate<Long> fn = hashIdx -> ring.get(hashIdx).isVirtualNodeOf(physicalId);
        ring.keySet().removeIf(fn);
    }
}
