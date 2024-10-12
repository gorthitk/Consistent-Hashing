package org.jet.ch;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Function implements HashingFunction {

    private final MessageDigest instance;

    Md5Function() throws NoSuchAlgorithmException {
        this.instance = MessageDigest.getInstance("MD5");
    }

    @Override
    public Long apply(String key) {
        instance.reset();
        instance.update(key.getBytes());
        byte[] digest = instance.digest();

        long hash = 0;
        for (int i = 0; i < 4; i++) {
            hash <<= 8;
            hash |= ((int) digest[i]) & 0xFF;
        }

        return hash;
    }
}
