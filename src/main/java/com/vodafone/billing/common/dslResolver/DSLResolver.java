package com.vodafone.billing.common.dslResolver;

public interface DSLResolver {
    String getResolverKeyword();
    Object resolveValue(String keyword);
}
