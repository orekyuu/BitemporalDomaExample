package net.orekyuu.bitemporaldomaexample.domain.model.type;

import org.seasar.doma.Column;
import org.seasar.doma.Embeddable;

import java.time.LocalDateTime;

/**
 * ビジネス的な有効時間
 */
@Embeddable
public class ValidTime {
    @Column
    private final LocalDateTime businessIn;
    @Column
    private final LocalDateTime businessOut;
    private static final LocalDateTime MAX = LocalDateTime.of(9999, 12, 31, 23, 59, 59);

    ValidTime(LocalDateTime businessIn, LocalDateTime businessOut) {
        this.businessIn = businessIn;
        this.businessOut = businessOut;
    }

    public static ValidTime create(LocalDateTime in, LocalDateTime out) {
        return new ValidTime(in, out);
    }

    public static ValidTime createInfinity(LocalDateTime in) {
        return create(in, LocalDateTime.MAX);
    }

    public ValidTime terminate(LocalDateTime time) {
        return new ValidTime(businessIn, time);
    }

    public LocalDateTime businessIn() {
        return businessIn;
    }

    public LocalDateTime businessOut() {
        return businessOut;
    }
}
