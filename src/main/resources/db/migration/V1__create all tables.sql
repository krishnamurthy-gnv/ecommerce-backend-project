/*Tables for Ecommerce Website:
-------------------------------*/
/*Users*/
create table Users
(
	user_id serial primary key,
	first_name varchar(100) not null,
	last_name  varchar(100) not null,
	email  varchar(200) not null unique,
	password_hash  varchar(200) not null,
	phone_number varchar(200),
	created_on timestamp default current_timestamp,
	updated_on timestamp default current_timestamp
);

-- Trigger function to update the `updated_on` column
CREATE OR REPLACE FUNCTION update_updated_on_column()
RETURNS TRIGGER AS $$
BEGIN
   NEW.updated_on = CURRENT_TIMESTAMP;
   RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger to call the function before each row update
CREATE TRIGGER update_user_updated_on
BEFORE UPDATE ON Users
FOR EACH ROW
EXECUTE FUNCTION update_updated_on_column();

/*Category*/
create table category
(
	category_id serial primary key,
	name varchar(100) not null,
	description text
);

/*Products*/
create table products
(
	product_id serial primary key,
	title varchar(200) not null,
	description text,
	price decimal (10,2) not null,
	stock int not null default 0,
	category_id int,
	images text,
	created_on timestamp default current_timestamp,
	updated_on timestamp default current_timestamp,
	foreign key (category_id) references category(category_id) on delete set null
);

-- Trigger function to update the `updated_on` column
CREATE OR REPLACE FUNCTION update_updated_on_column()
RETURNS TRIGGER AS $$
BEGIN
   NEW.updated_on = CURRENT_TIMESTAMP;
   RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger to call the function before each row update
CREATE TRIGGER update_products_updated_on
BEFORE UPDATE ON products
FOR EACH ROW
EXECUTE FUNCTION update_updated_on_column();

/*Carts*/
create table carts
(
	cart_id serial primary key,
	user_id int not null,
	foreign key (user_id) references users(user_id) on delete cascade
);

/*Cart Items*/

create table cart_items
(
	cart_item_id serial primary key,
	cart_id int not null,
	product_id int not null,
	quantity int not null,
	created_on timestamp default current_timestamp,
	updated_on timestamp default current_timestamp,
	foreign key (cart_id) references carts(cart_id) on delete cascade,
	foreign key (product_id) references products(product_id) on delete cascade
);

-- Trigger function to update the `updated_on` column
CREATE OR REPLACE FUNCTION update_updated_on_column()
RETURNS TRIGGER AS $$
BEGIN
   NEW.updated_on = CURRENT_TIMESTAMP;
   RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger to call the function before each row update
CREATE TRIGGER update_cart_items_updated_on
BEFORE UPDATE ON cart_items
FOR EACH ROW
EXECUTE FUNCTION update_updated_on_column();

/*Whishlist*/
create table wishlists
(
	wishlist_id serial primary key,
	name varchar(100),
	created_on timestamp default current_timestamp,
	updated_on timestamp default current_timestamp
);

-- Trigger to call the function before each row update
CREATE TRIGGER update_wishlist_updated_on
BEFORE UPDATE ON wishlists
FOR EACH ROW
EXECUTE FUNCTION update_updated_on_column();


/*Whishlist Items*/

create table wishlist_items
(
	wishlist_item_id serial primary key,
	wishlist_id int not null,
	product_id int not null,
	quantity int not null,
	created_on timestamp default current_timestamp,
	updated_on timestamp default current_timestamp,
	foreign key (wishlist_id) references wishlists(wishlist_id) on delete cascade,
	foreign key (product_id) references products(product_id) on delete cascade
);

-- Trigger function to update the `updated_on` column
CREATE OR REPLACE FUNCTION update_updated_on_column()
RETURNS TRIGGER AS $$
BEGIN
   NEW.updated_on = CURRENT_TIMESTAMP;
   RETURN NEW;
END;
$$ LANGUAGE plpgsql;



-- Trigger to call the function before each row update
CREATE TRIGGER update_wishlist_items_updated_on
BEFORE UPDATE ON wishlist_items
FOR EACH ROW
EXECUTE FUNCTION update_updated_on_column();


/*Addresses*/
-- Create the address_type enum
CREATE TYPE address_type AS ENUM ('home', 'apartment', 'office', 'other');

-- Create a table addresses
create table addresses
(
	address_id serial primary key,
	user_id int not null,
	address_line1 varchar(200),
	address_line2 varchar(200),
	city varchar(100),
	state varchar(100),
	zipcode varchar(100),
	country varchar(100),
	is_default boolean default false,
	address_type address_type NOT NULL,
	created_on timestamp default current_timestamp,
	updated_on timestamp default current_timestamp,
	foreign key (user_id) references users(user_id) on delete cascade
);
-- Trigger to call the function before each row update
CREATE TRIGGER update_address_updated_on
BEFORE UPDATE ON addresses
FOR EACH ROW
EXECUTE FUNCTION update_updated_on_column();

/* Orders */
-- Create the order_status enum
CREATE TYPE order_status AS ENUM ('processing', 'pending', 'completed', 'cancelled');

-- Create a table Orders
create table orders
(
	order_id serial primary key,
	user_id int not null,
	total_amount decimal(10,2),
	order_status order_status not null,
	billing_address_id int,
	shipping_address_id int,
	created_on timestamp default current_timestamp,
	updated_on timestamp default current_timestamp,
	foreign key (user_id) references users(user_id) on delete cascade,
	foreign key (billing_address_id) references addresses(address_id) on delete cascade,
	foreign key (shipping_address_id) references addresses(address_id) on delete cascade
);
-- Trigger to call the function before each row update
CREATE TRIGGER update_order_updated_on
BEFORE UPDATE ON orders
FOR EACH ROW
EXECUTE FUNCTION update_updated_on_column();

/* Order Items */
-- Create a table Orders Items
create table order_items
(
	order_item_id serial primary key,
	order_id int not null,
	product_id int not null,
	price decimal(10,2),
	quantity int,
	created_on timestamp default current_timestamp,
	updated_on timestamp default current_timestamp,
	foreign key (order_id) references orders(order_id) on delete cascade,
	foreign key (product_id) references products(product_id) on delete cascade
);
-- Trigger to call the function before each row update
CREATE TRIGGER update_order_items_updated_on
BEFORE UPDATE ON orders
FOR EACH ROW
EXECUTE FUNCTION update_updated_on_column();

/*Coupons*/

-- Create the offer_type enum
CREATE TYPE offer_type AS ENUM ('value', 'percentage');

-- Create a table Coupons
create table coupons
(
	coupon_id serial primary key,
	coupon_code varchar(255),
	offer_type offer_type not null,
	offer_value int,
	valid_till timestamp,
	created_on timestamp default current_timestamp,
	updated_on timestamp default current_timestamp
);
-- Trigger to call the function before each row update
CREATE TRIGGER update_coupons_updated_on
BEFORE UPDATE ON orders
FOR EACH ROW
EXECUTE FUNCTION update_updated_on_column();

/* Reviews */
-- Create a reviews
create table reviews
(
	review_id serial primary key,
	user_id int,
	order_id int,
	product_id int,
	review_text varchar(200),
	images_path varchar(200),
	videos_path varchar(200),
	is_verified boolean default false,
	is_deleted boolean default false,
	created_on timestamp default current_timestamp,
	updated_on timestamp default current_timestamp
);
-- Trigger to call the function before each row update
CREATE TRIGGER update_reviews_updated_on
BEFORE UPDATE ON orders
FOR EACH ROW
EXECUTE FUNCTION update_updated_on_column();