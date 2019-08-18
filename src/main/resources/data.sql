DROP TABLE IF EXISTS customer;
 
CREATE TABLE customer (
  id bigint PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
);

DROP TABLE IF EXISTS customer_phone;
 
CREATE TABLE customer_phone (
  id bigint PRIMARY KEY,
  customer_id long NOT NULL,
  phone VARCHAR(20) NOT NULL,
  is_active boolean,
  foreign key (customer_id) references customer(id)
);
 
INSERT INTO customer (id, name) VALUES
  ('1', 'Bruce Wayne'),
  ('2', 'Peter Parker');
  
INSERT INTO customer_phone (id, customer_id, phone, is_active) VALUES
  ('1', '1', '1111-1111-1111', false),
  ('2', '1', '1234-1234-1234', false),
  ('3', '2', '9999-9999-9999', false),
  ('4', '2', '9876-9876-9876', false),
  ('5', '2', '5555-5555-5555', false);