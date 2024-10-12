package org.jet.ch;

import org.jet.ch.function.HashingFunction;
import org.jet.ch.function.Md5Function;
import org.jet.ch.node.PhysicalNode;
import org.jet.ch.node.ServerNode;
import org.jet.ch.ring.HashRing;
import org.jet.ch.ring.HashRingImpl;

import java.util.ArrayList;
import java.util.List;

public final class Main {
    private static final String PHYSICAL_IDENTIFIER_1 = "node-us-1";
    private static final String PHYSICAL_IDENTIFIER_2 = "node-us-2";
    private static final String PHYSICAL_IDENTIFIER_3 = "node-us-3";
    private static final String PHYSICAL_IDENTIFIER_4 = "node-us-4";

    private static List<ServerNode> createCollection(String... identifiers) {
        List<ServerNode> nodes = new ArrayList<>();

        for (String identifier : identifiers) {
            nodes.add(new PhysicalNode(identifier));
        }

        return nodes;
    }

    private static void printSeparator() {
        System.out.println("--------------------------------------------");
    }

    private static void runRoutes(Router router) {
        System.out.println(router.route("identifier-1"));
        System.out.println(router.route("identifier-2"));
        System.out.println(router.route("typical-id"));
        System.out.println(router.route("teja"));
        System.out.println(router.route("archana"));
        System.out.println(router.route("wofincweoinfpwe"));
        System.out.println(router.route("fbwjbcoihewqo'bwei"));
        System.out.println(router.route("sasank"));
    }

    public static void main(String[] args) throws Exception {
        List<ServerNode> nodes = createCollection(
                PHYSICAL_IDENTIFIER_1,
                PHYSICAL_IDENTIFIER_2,
                PHYSICAL_IDENTIFIER_3
        );

        HashingFunction hashFn = new Md5Function();
        HashRing ring = new HashRingImpl(hashFn, nodes, 2);
        Router router = new Router(ring, hashFn);

        printSeparator();
        System.out.println("Number of Replicas: "+ ring.numberOfNodes());
        printSeparator();

        runRoutes(router);

        printSeparator();
        ring.register(new PhysicalNode(PHYSICAL_IDENTIFIER_4));
        System.out.println("Number of Replicas: "+ ring.numberOfNodes());
        printSeparator();


        runRoutes(router);

        printSeparator();
        ring.unregister(PHYSICAL_IDENTIFIER_4);
        System.out.println("Number of Replicas: "+ ring.numberOfNodes());
        printSeparator();

        runRoutes(router);
    }
}
