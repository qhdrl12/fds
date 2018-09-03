
--RuleA. 카카오머니 서비스 계좌 개설을 하고 1시간 이내, 20만원 충전 후 잔액이 1000원 이하가 되는 경우
--user1
insert into kakao_service_account_open_log (`create_time`,`user_id`,`account_number`) values (DATEADD('DAY', -7, now()), 1, '222-111-111');
insert into kakao_charge_log (`create_time`,`user_id`,`account_number`,`charge_amount`,`bank_account_number`) values (DATEADD('MINUTE', -10075, now()), 1, '222-111-111', 100000, '333333-44-333333');
insert into kakao_charge_log (`create_time`,`user_id`,`account_number`,`charge_amount`,`bank_account_number`) values (DATEADD('MINUTE', -10077, now()), 1, '222-111-111', 100000, '333333-44-333333');
insert into kakao_charge_log (`create_time`,`user_id`,`account_number`,`charge_amount`,`bank_account_number`) values (DATEADD('MINUTE', 2, now()), 1, '222-111-111', 300000, '333333-44-333333');

insert into kakao_sender_log (`create_time`,`user_id`,`sender_account_number`,`before_sender_account_balance`,`receiver_account_number`,`receiver_user_id`,`sender_amount`)
values (DATEADD('MINUTE', -10073, now()), 1, '222-111-111', 200000, '999-888-888', 2, 150000);
insert into kakao_sender_log (`create_time`,`user_id`,`sender_account_number`,`before_sender_account_balance`,`receiver_account_number`,`receiver_user_id`,`sender_amount`)
values (DATEADD('MINUTE', -10072, now()), 1, '222-111-111', 50000, '999-888-888', 2, 49000);

--user2
insert into kakao_service_account_open_log (`create_time`,`user_id`,`account_number`) values (DATEADD('DAY', -4, now()), 2, '333-222-222');
insert into kakao_charge_log (`create_time`,`user_id`,`account_number`,`charge_amount`,`bank_account_number`) values (DATEADD('MINUTE', -5750, now()), 2, '333-222-222', 60000, '553333-44-333333');
insert into kakao_charge_log (`create_time`,`user_id`,`account_number`,`charge_amount`,`bank_account_number`) values (DATEADD('MINUTE', -5749, now()), 2, '333-222-222', 100000, '553333-44-333333');

insert into kakao_sender_log (`create_time`,`user_id`,`sender_account_number`,`before_sender_account_balance`,`receiver_account_number`,`receiver_user_id`,`sender_amount`)
values (DATEADD('MINUTE', -5745, now()), 2, '333-222-222', 160000, '999-888-888', 1, 150000);
insert into kakao_sender_log (`create_time`,`user_id`,`sender_account_number`,`before_sender_account_balance`,`receiver_account_number`,`receiver_user_id`,`sender_amount`)
values (DATEADD('MINUTE', -5744, now()), 2, '333-222-222', 10000, '999-888-888', 1, 9000);

--user3
insert into kakao_service_account_open_log (`create_time`,`user_id`,`account_number`) values (DATEADD('DAY', -1, now()), 3, '444-222-222');
insert into kakao_charge_log (`create_time`,`user_id`,`account_number`,`charge_amount`,`bank_account_number`) values (DATEADD('MINUTE', -1430, now()), 3, '444-222-222', 160000, '663333-44-333333');
insert into kakao_charge_log (`create_time`,`user_id`,`account_number`,`charge_amount`,`bank_account_number`) values (DATEADD('MINUTE', -1429, now()), 3, '444-222-222', 150000, '663333-44-333333');

insert into kakao_sender_log (`create_time`,`user_id`,`sender_account_number`,`before_sender_account_balance`,`receiver_account_number`,`receiver_user_id`,`sender_amount`)
values (DATEADD('MINUTE', -1425, now()), 3, '444-222-222', 310000, '999-888-888', 1, 250000);
insert into kakao_sender_log (`create_time`,`user_id`,`sender_account_number`,`before_sender_account_balance`,`receiver_account_number`,`receiver_user_id`,`sender_amount`)
values (DATEADD('MINUTE', -1424, now()), 3, '444-222-222', 60000, '999-888-888', 1, 59000);

--user4
insert into kakao_service_account_open_log (`create_time`,`user_id`,`account_number`) values (DATEADD('DAY', -1, now()), 4, '555-222-222');
insert into kakao_charge_log (`create_time`,`user_id`,`account_number`,`charge_amount`,`bank_account_number`) values (DATEADD('MINUTE', -1430, now()), 4, '555-222-222', 160000, '663333-44-333333');
insert into kakao_charge_log (`create_time`,`user_id`,`account_number`,`charge_amount`,`bank_account_number`) values (DATEADD('MINUTE', -1429, now()), 4, '555-222-222', 150000, '663333-44-333333');

insert into kakao_sender_log (`create_time`,`user_id`,`sender_account_number`,`before_sender_account_balance`,`receiver_account_number`,`receiver_user_id`,`sender_amount`)
values (DATEADD('MINUTE', -1425, now()), 4, '555-222-222', 310000, '999-888-888', 1, 250000);
insert into kakao_sender_log (`create_time`,`user_id`,`sender_account_number`,`before_sender_account_balance`,`receiver_account_number`,`receiver_user_id`,`sender_amount`)
values (DATEADD('MINUTE', -1424, now()), 4, '555-222-222', 60000, '999-888-888', 1, 59000);


--RuleB. 카카오머니 서비스 계좌 개설을 하고 7일 이내, 카카오머니 받기로 10만원 이상 금액을 5회 이상 하는 경우
--RuleC. 2시간 이내, 카카오머니 받기로 5만원 이상 금액을 3회 이상 하는 경우
--user1
insert into kakao_receiver_log (`create_time`,`user_id`,`receiver_account_number`,`before_receiver_account_balance`,`sender_account_number`,`sender_user_id`,`receiver_amount`)
values (DATEADD('MINUTE', -45, now()), 1, '222-111-111', 1000, '999-888-888', 2, 150000);

insert into kakao_receiver_log (`create_time`,`user_id`,`receiver_account_number`,`before_receiver_account_balance`,`sender_account_number`,`sender_user_id`,`receiver_amount`)
values (DATEADD('MINUTE', -44, now()), 1, '222-111-111', 151000, '999-888-888', 2, 150000);

insert into kakao_receiver_log (`create_time`,`user_id`,`receiver_account_number`,`before_receiver_account_balance`,`sender_account_number`,`sender_user_id`,`receiver_amount`)
values (DATEADD('MINUTE', -43, now()), 1, '222-111-111', 301000, '999-888-888', 2, 150000);

insert into kakao_receiver_log (`create_time`,`user_id`,`receiver_account_number`,`before_receiver_account_balance`,`sender_account_number`,`sender_user_id`,`receiver_amount`)
values (DATEADD('MINUTE', -42, now()), 1, '222-111-111', 451000, '999-888-888', 2, 150000);

insert into kakao_receiver_log (`create_time`,`user_id`,`receiver_account_number`,`before_receiver_account_balance`,`sender_account_number`,`sender_user_id`,`receiver_amount`)
values (DATEADD('MINUTE', -41, now()), 1, '222-111-111', 601000, '999-888-888', 2, 150000);

--user2
insert into kakao_receiver_log (`create_time`,`user_id`,`receiver_account_number`,`before_receiver_account_balance`,`sender_account_number`,`sender_user_id`,`receiver_amount`)
values (DATEADD('MINUTE', -545, now()), 2, '333-222-222', 1000, '999-888-888', 2, 150000);

insert into kakao_receiver_log (`create_time`,`user_id`,`receiver_account_number`,`before_receiver_account_balance`,`sender_account_number`,`sender_user_id`,`receiver_amount`)
values (DATEADD('MINUTE', -544, now()), 2, '333-222-222', 151000, '999-888-888', 2, 150000);

insert into kakao_receiver_log (`create_time`,`user_id`,`receiver_account_number`,`before_receiver_account_balance`,`sender_account_number`,`sender_user_id`,`receiver_amount`)
values (DATEADD('MINUTE', -119, now()), 2, '333-222-222', 301000, '999-888-888', 2, 150000);

insert into kakao_receiver_log (`create_time`,`user_id`,`receiver_account_number`,`before_receiver_account_balance`,`sender_account_number`,`sender_user_id`,`receiver_amount`)
values (DATEADD('MINUTE', -42, now()), 2, '333-222-222', 451000, '999-888-888', 2, 150000);

insert into kakao_receiver_log (`create_time`,`user_id`,`receiver_account_number`,`before_receiver_account_balance`,`sender_account_number`,`sender_user_id`,`receiver_amount`)
values (DATEADD('MINUTE', -41, now()), 2, '333-222-222', 601000, '999-888-888', 2, 150000);

--user4
insert into kakao_receiver_log (`create_time`,`user_id`,`receiver_account_number`,`before_receiver_account_balance`,`sender_account_number`,`sender_user_id`,`receiver_amount`)
values (DATEADD('MINUTE', -43, now()), 4, '555-222-222', 301000, '999-888-888', 2, 150000);

insert into kakao_receiver_log (`create_time`,`user_id`,`receiver_account_number`,`before_receiver_account_balance`,`sender_account_number`,`sender_user_id`,`receiver_amount`)
values (DATEADD('MINUTE', -42, now()), 4, '555-222-222', 451000, '999-888-888', 2, 150000);

insert into kakao_receiver_log (`create_time`,`user_id`,`receiver_account_number`,`before_receiver_account_balance`,`sender_account_number`,`sender_user_id`,`receiver_amount`)
values (DATEADD('MINUTE', -41, now()), 4, '555-222-222', 601000, '999-888-888', 2, 150000);
