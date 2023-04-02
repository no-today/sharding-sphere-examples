package io.github.notoday.sharding.sphere.support.encrypt.algorithm;

import org.apache.shardingsphere.encrypt.api.encrypt.standard.StandardEncryptAlgorithm;
import org.apache.shardingsphere.encrypt.spi.context.EncryptContext;

import java.util.Objects;
import java.util.Properties;

public class HashEncryptAlgorithm implements StandardEncryptAlgorithm<Object, String> {

    @Override
    public void init(Properties properties) {
    }

    @Override
    public String getType() {
        return "HASH";
    }

    @Override
    public String encrypt(Object o, EncryptContext encryptContext) {
        return Objects.isNull(o) ? null : String.valueOf(Math.abs(o.hashCode()));
    }

    @Override
    public Object decrypt(String o, EncryptContext encryptContext) {
        return null;
    }
}