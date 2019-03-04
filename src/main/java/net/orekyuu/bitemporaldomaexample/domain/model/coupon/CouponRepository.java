package net.orekyuu.bitemporaldomaexample.domain.model.coupon;

import net.orekyuu.bitemporaldomaexample.domain.model.User;
import net.orekyuu.bitemporaldomaexample.domain.model.type.Id;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.Result;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Dao
@ConfigAutowireable
public interface CouponRepository {

    @Insert
    Result<Coupon> insertIdIncrement(Coupon coupon);

    @Insert
    Result<Coupon> insert(Coupon coupon);

    @Update(sqlFile = true)
    Result<Coupon> update(Coupon coupon);

    @Select
    Optional<Coupon> findNewestById(Id<Coupon> id);

    @Select
    List<Coupon> findByValidTime(LocalDateTime time);

    @Select
    List<Coupon> findByValidAndTransactionTime(LocalDateTime time);
}
