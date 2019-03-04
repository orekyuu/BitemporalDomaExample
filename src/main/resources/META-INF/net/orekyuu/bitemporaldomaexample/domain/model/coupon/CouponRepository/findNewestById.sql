select /*%expand*/*
from coupon
where process_thru = '9999-12-31 23:59:59' and id = /*id*/1
