package net.orekyuu.bitemporaldomaexample.domain.model.type;

import org.seasar.doma.Column;
import org.seasar.doma.Embeddable;

import java.time.LocalDateTime;

/**
 * システム的な有効時間
 */
@Embeddable
public class TransactionTime {
    @Column
    private final LocalDateTime processFrom;
    @Column
    private final LocalDateTime processThru;
    static final LocalDateTime MAX = LocalDateTime.of(9999, 12, 31, 23, 59, 59);

    TransactionTime(LocalDateTime processFrom, LocalDateTime processThru) {
        this.processFrom = processFrom;
        this.processThru = processThru;
    }

    public static TransactionTime create(LocalDateTime time) {
        return new TransactionTime(time, MAX);
    }

    public TerminatedTransactionTime terminate(LocalDateTime time) {
        return new TerminatedTransactionTime(
                new TransactionTime(time, MAX),
                new TransactionTime(processFrom, time));
    }

    public LocalDateTime processFrom() {
        return processFrom;
    }

    public LocalDateTime processThru() {
        return processThru;
    }
}
