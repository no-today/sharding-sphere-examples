package io.github.notoday.sharding.sphere.data.migrate.support;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.shardingsphere.encrypt.api.encrypt.like.LikeEncryptAlgorithm;
import org.apache.shardingsphere.encrypt.api.encrypt.standard.StandardEncryptAlgorithm;

@Data
@Accessors(chain = true)
public class EncryptColumnRuleConfig {
    private String tableName; // 冗余

    private String column;
    private String cipherColumn;
    private String assistedQueryColumn;
    private String likeQueryColumn;
    private StandardEncryptAlgorithm<Object, String> cipherEncryptor;
    private StandardEncryptAlgorithm<Object, String> assistedQueryEncryptor;
    private LikeEncryptAlgorithm<Object, String> likeQueryEncryptor;
}