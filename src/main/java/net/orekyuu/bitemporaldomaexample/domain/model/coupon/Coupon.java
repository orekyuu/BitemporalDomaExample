package net.orekyuu.bitemporaldomaexample.domain.model.coupon;

import net.orekyuu.bitemporaldomaexample.domain.model.User;
import net.orekyuu.bitemporaldomaexample.domain.model.type.Id;
import net.orekyuu.bitemporaldomaexample.domain.model.type.TransactionTime;
import net.orekyuu.bitemporaldomaexample.domain.model.type.ValidTime;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Table;
import org.seasar.doma.jdbc.entity.NamingType;

@Entity(immutable = true, naming = NamingType.SNAKE_LOWER_CASE)
@Table
public class Coupon {
    @org.seasar.doma.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    final Id<Coupon> id;
    final TransactionTime transactionTime;
    final ValidTime validTime;
    final CouponAmount amount;
    final Id<User> userId;

    public Coupon(Id<Coupon> id, TransactionTime transactionTime, ValidTime validTime, CouponAmount amount, Id<User> userId) {
        this.id = id;
        this.transactionTime = transactionTime;
        this.validTime = validTime;
        this.amount = amount;
        this.userId = userId;
    }

    public Id<Coupon> getId() {
        return id;
    }

    public TransactionTime getTransactionTime() {
        return transactionTime;
    }

    public ValidTime getValidTime() {
        return validTime;
    }

    public CouponAmount getAmount() {
        return amount;
    }

    public Id<User> getUserId() {
        return userId;
    }
}
