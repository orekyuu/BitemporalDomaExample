package net.orekyuu.bitemporaldomaexample.presentation.controller;

import net.orekyuu.bitemporaldomaexample.application.coupon.CouponService;
import net.orekyuu.bitemporaldomaexample.domain.model.User;
import net.orekyuu.bitemporaldomaexample.domain.model.coupon.Coupon;
import net.orekyuu.bitemporaldomaexample.domain.model.coupon.CouponAmount;
import net.orekyuu.bitemporaldomaexample.domain.model.type.Id;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("coupon")
public class CouponController {
    final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @GetMapping("valid/{year}/{month}/{day}")
    public List<Coupon> valid(@PathVariable int year, @PathVariable int month, @PathVariable int day) {
        LocalDateTime time = LocalDate.of(year, month, day).atStartOfDay();
        return couponService.findByValidTime(time);
    }

    @GetMapping("transaction/{year}/{month}/{day}")
    public List<Coupon> transaction(@PathVariable int year, @PathVariable int month, @PathVariable int day) {
        LocalDateTime time = LocalDate.of(year, month, day).atStartOfDay();
        return couponService.findByTransactionTime(time);
    }

    @PostMapping("gift")
    public long gift(@RequestParam CouponAmount amount, @RequestParam Id<User> user) {
        return couponService.giftToUser(user, amount).getValue();
    }

    @PostMapping("fix")
    public void fix(@RequestParam CouponAmount amount, @RequestParam Id<Coupon> id) {
        couponService.fixAmount(id, amount);
    }
}
