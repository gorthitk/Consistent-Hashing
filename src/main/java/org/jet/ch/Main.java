package org.jet.ch;

import java.util.List;

public final class Main {
    public static void main(String[] args) throws Exception {
        List<ServerNode> nodes = List.of(
                new PhysicalNode("node-us-1"),
                new PhysicalNode("node-us-2"),
                new PhysicalNode("node-us-3")
        );

        Router router = new Router(nodes, 2);

        System.out.println("Number of Replicas: "+ router.numberOfReplicas());

        router.register(new PhysicalNode("node-us-4"));
        System.out.println("Number of Replicas: "+ router.numberOfReplicas());

        System.out.println(router.route("identifier-1"));
        System.out.println(router.route("identifier-2"));
        System.out.println(router.route("typical-id"));
        System.out.println(router.route("teja"));
        System.out.println(router.route("archana"));
        System.out.println(router.route("wofincweoinfpwe"));
        System.out.println(router.route("fbwjbcoihewqo'bwei"));
        System.out.println(router.route("sasank"));

        router.unregister("node-us-4");
        System.out.println("Number of Replicas: "+ router.numberOfReplicas());


        System.out.println(router.route("identifier-1"));
        System.out.println(router.route("identifier-2"));
        System.out.println(router.route("typical-id"));
        System.out.println(router.route("teja"));
        System.out.println(router.route("archana"));
        System.out.println(router.route("wofincweoinfpwe"));
        System.out.println(router.route("fbwjbcoihewqo'bwei"));
        System.out.println(router.route("sasank"));
    }
}
