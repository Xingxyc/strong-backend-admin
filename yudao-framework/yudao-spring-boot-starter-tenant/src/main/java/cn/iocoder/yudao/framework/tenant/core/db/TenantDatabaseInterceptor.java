package cn.iocoder.yudao.framework.tenant.core.db;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.tenant.config.TenantProperties;
import cn.iocoder.yudao.framework.tenant.core.context.TenantContextHolder;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import lombok.AllArgsConstructor;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;

/**
 * 基于 MyBatis Plus 多租户的功能，实现 DB 层面的多租户的功能
 *
 * @author 芋道源码
 */
@AllArgsConstructor
public class TenantDatabaseInterceptor implements TenantLineHandler {

    private final TenantProperties properties;

    @Override
    public Expression getTenantId() {
        // TODO 芋艿：暂时不考虑获取不到的情况。此时，会存在 NPE 的报错
        return new StringValue(TenantContextHolder.getTenantId().toString());
    }

    @Override
    public boolean ignoreTable(String tableName) {
        // 不包含，说明要过滤
        return !CollUtil.contains(properties.getTables(), tableName);
    }

}
