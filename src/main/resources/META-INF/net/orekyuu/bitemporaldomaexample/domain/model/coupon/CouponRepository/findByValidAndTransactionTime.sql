select /*%expand*/*
from coupon
where process_from <= /*time*/'2019-01-01 00:00:00' and /*time*/'2019-01-01 00:00:00' < process_thru
  and business_in <= /*time*/'2019-01-01 00:00:00' and /*time*/'2019-01-01 00:00:00' < business_out
