package org.jet.ch;

import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Predicate;

public class Router {
    private final SortedMap<Long, VirtualNode> ring = new TreeMap<>();
    private final HashingFunction hashFn;
    private final int replicasFactor;

    public Router(Collection<ServerNode> pNodes, int replicasFactor) throws Exception {
         this.hashFn = new Md5Function();
         this.replicasFactor = replicasFactor;

        pNodes.forEach(this::addServer);
    }

    public int getReplicasFactor() {
        return this.replicasFactor;
    }

    public int numberOfReplicas() {
        return ring.size();
    }

    public String route(String key) {
        long hash = hashFn.apply(key);
        SortedMap<Long, VirtualNode> tailMap = ring.tailMap(hash);
        long vNodeIdx  = tailMap.isEmpty() ? ring.firstKey() : tailMap.firstKey();
        return ring.get(vNodeIdx).getPhysicalNodeId();
    }

    public void register(ServerNode server) {
        this.addServer(server);
    }

    public void unregister(String physicalId) {
        this.removeServer(physicalId);
    }

    private void addServer(ServerNode node) {
        for (int i = 0; i < this.replicasFactor; i++) {
            VirtualNode vNode =  new VirtualNode(node, i + 1);
            ring.put(vNode.getHashIdx(hashFn), vNode);
        }
    }

    private void removeServer(String physicalId) {
        Predicate<Long> fn = hashIdx -> ring.get(hashIdx).isVirtualNodeOf(physicalId);
        ring.keySet().removeIf(fn);
    }

}
