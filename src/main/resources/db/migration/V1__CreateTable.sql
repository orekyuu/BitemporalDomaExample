CREATE TABLE `coupon`
(
  `id`           bigint(20) NOT NULL AUTO_INCREMENT,
  `process_from` datetime   NOT NULL,
  `process_thru` datetime   NOT NULL,
  `business_in`  datetime   NOT NULL,
  `business_out` datetime   NOT NULL,
  `amount`       int(11)    NOT NULL,
  `user_id`      bigint(20) NOT NULL,
  PRIMARY KEY (`id`, `process_from`)
) ENGINE = InnoDB

