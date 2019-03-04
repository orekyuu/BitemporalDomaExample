select /*%expand*/*
from coupon
where process_thru = '9999-12-31 23:59:59'
  and business_in <= /*time*/'2019-01-01 00:00:00' and /*time*/'2019-01-01 00:00:00' < business_out
