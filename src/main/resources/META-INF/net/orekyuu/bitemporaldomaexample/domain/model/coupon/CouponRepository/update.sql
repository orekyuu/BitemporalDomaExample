update coupon
set
  amount = /*coupon.amount*/0,
  user_id = /*coupon.userId*/0,
  process_from = /*coupon.transactionTime.processFrom*/'',
  process_thru = /*coupon.transactionTime.processThru*/'',
  business_in = /*coupon.validTime.businessIn*/'',
  business_out = /*coupon.validTime.businessOut*/''
where
  id = /*coupon.id*/1 and process_thru = '9999-12-31 23:59:59'

