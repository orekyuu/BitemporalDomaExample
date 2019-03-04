package net.orekyuu.bitemporaldomaexample.domain.model.type;

/**
 * システム的な変更時のシステム時間の変化
 */
public class TerminatedTransactionTime {
    private final TransactionTime newTransaction;
    private final TransactionTime terminatedTransaction;

    TerminatedTransactionTime(TransactionTime newTransaction, TransactionTime terminatedTransaction) {
        this.newTransaction = newTransaction;
        this.terminatedTransaction = terminatedTransaction;
    }

    public TransactionTime newTransaction() {
        return newTransaction;
    }

    public TransactionTime terminatedTransaction() {
        return terminatedTransaction;
    }
}
