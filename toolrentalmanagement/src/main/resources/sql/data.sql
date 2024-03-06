INSERT INTO tool_type VALUES (1, 'Chainsaw');
INSERT INTO tool_type VALUES (2, 'Ladder');
INSERT INTO tool_type VALUES (3, 'Jackhammer');

INSERT INTO brand_type VALUES (1, 'Stihl');
INSERT INTO brand_type VALUES (2, 'Werner');
INSERT INTO brand_type VALUES (3, 'DeWalt');
INSERT INTO brand_type VALUES (4, 'Ridgid');

INSERT INTO tool_detail VALUES (1, 'CHNS',1,1);
INSERT INTO tool_detail VALUES (2, 'LADW',2,2);
INSERT INTO tool_detail VALUES (3, 'JAKD',3,3);
INSERT INTO tool_detail VALUES (4, 'JAKR',3,4);

INSERT INTO tool_charge_detail(id,type_id,daily_charge,weekday_charge,weekend_charge,holiday_charge) VALUES (1, 2,1.99,1,1,0);
INSERT INTO tool_charge_detail(id,type_id,daily_charge,weekday_charge,weekend_charge,holiday_charge) VALUES (2, 1,1.49,1,0,1);
INSERT INTO tool_charge_detail(id,type_id,daily_charge,weekday_charge,weekend_charge,holiday_charge) VALUES (3, 3,2.99,1,0,0);