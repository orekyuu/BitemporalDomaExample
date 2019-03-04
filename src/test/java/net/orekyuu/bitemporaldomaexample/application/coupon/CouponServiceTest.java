package net.orekyuu.bitemporaldomaexample.application.coupon;

import net.orekyuu.bitemporaldomaexample.application.TimeSignal;
import net.orekyuu.bitemporaldomaexample.domain.model.coupon.Coupon;
import net.orekyuu.bitemporaldomaexample.domain.model.coupon.CouponAmount;
import net.orekyuu.bitemporaldomaexample.domain.model.type.Id;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.mockito.Mockito.doReturn;

@SpringBootTest
@Transactional
class CouponServiceTest {
    @MockBean
    TimeSignal timeSignal;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    CouponService couponService;

    void setTime(LocalDate time) {
        doReturn(time.atStartOfDay()).when(timeSignal).now();
    }

    @Test
    void クーポン発行() {
        setTime(LocalDate.of(2019, 3, 1));
        couponService.giftToUser(Id.of(1), new CouponAmount(100));

        Long count = jdbcTemplate.queryForObject("select count(id) from coupon", Long.class);
        Assertions.assertThat(count).isEqualTo(count);
    }

    @Test
    void 発行したクーポンのamount修正() {
        setTime(LocalDate.of(2019, 3, 1));
        Id<Coupon> couponId = couponService.giftToUser(Id.of(1), new CouponAmount(1000));

        Coupon coupon = couponService.findByValidTime(LocalDate.of(2019, 3, 1).atStartOfDay()).get(0);
        Assertions.assertThat(coupon.getAmount().getValue()).isEqualTo(1000);

        // 1000 -> 100に修正
        setTime(LocalDate.of(2019, 3, 2));
        couponService.fixAmount(couponId, new CouponAmount(100));

        // Valid Timeベースだと 3/1日時点でもamountは100に修正されている
        {
            Coupon fixed = couponService.findByValidTime(LocalDate.of(2019, 3, 1).atStartOfDay()).get(0);
            Assertions.assertThat(fixed.getAmount().getValue()).isEqualTo(100);
        }
        // Transaction Timeベースだと 3/1日時点ではamountが1000のまま
        {
            Coupon fixed = couponService.findByTransactionTime(LocalDate.of(2019, 3, 1).atStartOfDay()).get(0);
            Assertions.assertThat(fixed.getAmount().getValue()).isEqualTo(1000);
        }
    }
}
