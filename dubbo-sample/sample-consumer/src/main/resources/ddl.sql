CREATE TABLE tb_account_two (
  acct_id varchar(16),
  amount double(10, 2),
  frozen double(10, 2),
  PRIMARY KEY (acct_id)
) ENGINE=InnoDB;

insert into tb_account_two (acct_id, amount, frozen) values('2001', 10000.00, 0.00);