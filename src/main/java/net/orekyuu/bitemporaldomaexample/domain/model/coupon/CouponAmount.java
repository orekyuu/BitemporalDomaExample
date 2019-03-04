package net.orekyuu.bitemporaldomaexample.domain.model.coupon;

import org.seasar.doma.Domain;

@Domain(valueType = long.class)
public class CouponAmount {
    final long value;

    public CouponAmount(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    public static CouponAmount valueOf(String value) {
        return new CouponAmount(Long.valueOf(value));
    }
}
