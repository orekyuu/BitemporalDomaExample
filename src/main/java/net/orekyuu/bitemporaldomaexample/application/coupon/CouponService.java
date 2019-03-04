package net.orekyuu.bitemporaldomaexample.application.coupon;

import net.orekyuu.bitemporaldomaexample.application.TimeSignal;
import net.orekyuu.bitemporaldomaexample.domain.model.User;
import net.orekyuu.bitemporaldomaexample.domain.model.coupon.Coupon;
import net.orekyuu.bitemporaldomaexample.domain.model.coupon.CouponAmount;
import net.orekyuu.bitemporaldomaexample.domain.model.coupon.CouponRepository;
import net.orekyuu.bitemporaldomaexample.domain.model.type.Id;
import net.orekyuu.bitemporaldomaexample.domain.model.type.TerminatedTransactionTime;
import net.orekyuu.bitemporaldomaexample.domain.model.type.TransactionTime;
import net.orekyuu.bitemporaldomaexample.domain.model.type.ValidTime;
import org.seasar.doma.jdbc.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
public class CouponService {

    final CouponRepository repository;
    final TimeSignal timeSignal;

    public CouponService(CouponRepository repository, TimeSignal timeSignal) {
        this.repository = repository;
        this.timeSignal = timeSignal;
    }

    /**
     * 7日間有効なクーポンを発行
     * @param userId user
     * @param amount amount
     */
    public Id<Coupon> giftToUser(Id<User> userId, CouponAmount amount) {
        LocalDateTime now = timeSignal.now();
        Coupon coupon = new Coupon(Id.notAssigned(), TransactionTime.create(now), ValidTime.create(now, now.plusDays(7)), amount, userId);
        Result<Coupon> result = repository.insertIdIncrement(coupon);
        return result.getEntity().getId();
    }

    /**
     * amountを修正します
     * @param id coupon id
     * @param fixedAmount 修正後のamount
     */
    public void fixAmount(Id<Coupon> id, CouponAmount fixedAmount) {
        LocalDateTime now = timeSignal.now();

        Coupon coupon = repository.findNewestById(id).orElseThrow(() -> new RuntimeException("record not found"));
        TerminatedTransactionTime terminate = coupon.getTransactionTime().terminate(now);

        // terminatedTransactionを入れて期限切れにする
        repository.update(new Coupon(coupon.getId(), terminate.terminatedTransaction(), coupon.getValidTime(), coupon.getAmount(), coupon.getUserId()));
        // newTransactionで新しくinsertする。修正した項目以外は前のコピー
        repository.insert(new Coupon(coupon.getId(), terminate.newTransaction(), coupon.getValidTime(), fixedAmount, coupon.getUserId()));
    }

    /**
     * 事実としての時間でクーポンの一覧を取得
     * @param validTime valid time
     */
    public List<Coupon> findByValidTime(LocalDateTime validTime) {
        return repository.findByValidTime(validTime);
    }

    /**
     * システム的な時間でクーポンの一覧を取得
     * @param transactionTime transaction time
     */
    public List<Coupon> findByTransactionTime(LocalDateTime transactionTime) {
        return repository.findByValidAndTransactionTime(transactionTime);
    }
}
