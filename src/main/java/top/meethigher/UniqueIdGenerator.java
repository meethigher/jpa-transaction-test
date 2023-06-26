package top.meethigher;

import java.util.UUID;

/**
 * 生成唯一编号
 *
 * @author chenchuancheng github.com/meethigher
 * @since 2023/6/18 15:39
 */
public class UniqueIdGenerator {
    public static String generateUniqueId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
