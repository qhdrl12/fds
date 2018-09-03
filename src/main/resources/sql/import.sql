CREATE TABLE `kakao_service_account_open_log` (
  `create_time` datetime NOT NULL,
  `user_id` bigint NOT NULL,
  `account_number` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `kakao_sender_log` (
  `create_time` datetime NOT NULL,
  `user_id` bigint NOT NULL,
  `sender_account_number` varchar(32) NOT NULL,
  `before_sender_account_balance` bigint NOT NULL,
  `receiver_account_number` varchar(30) NOT NULL,
  `receiver_user_id` bigint NOT NULL,
  `sender_amount` bigint NOT NULL,
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `kakao_charge_log` (
  `create_time` datetime NOT NULL,
  `user_id` bigint NOT NULL,
  `account_number` varchar(32) NOT NULL,
  `charge_amount` bigint NOT NULL,
  `bank_account_number` varchar(32) NOT NULL,
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `kakao_receive_log` (
  `create_time` datetime NOT NULL,
  `user_id` bigint NOT NULL,
  `receiver_account_number` varchar(32) NOT NULL,
  `before_receiver_account_blance` bigint NOT NULL,
  `sender_account_number` varchar(32) NOT NULL,
  `sender_user_id` int NOT NULL,
  `receiver_amount` bigint NOT NULL,
) ENGINE=InnoDB DEFAULT CHARSET=utf8;