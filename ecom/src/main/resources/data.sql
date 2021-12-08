INSERT INTO product_category(category_name) VALUES ('BOOKS');


INSERT INTO product (name, description, active, category_id)
VALUES ('JavaScript - The Fun Parts', 'Learn JavaScript'
,1,1);
insert into product_image(path, product_id)
values('image/product/placeholder.png', 1)
insert into sku(sku, unit, unit_price, qty, status, product_id)
values('BOOK-TECH-1000', 'copy', '19.99', '5', 'AVAILABLE', 1);


INSERT INTO product (name, description, active, category_id)
VALUES ('Spring Framework Tutorial', 'Learn Spring'
,1,1);
insert into product_image(path, product_id)
values('image/product/placeholder.png', 2)
insert into sku(sku, unit, unit_price, qty, status, product_id)
values('BOOK-TECH-1001', 'copy', '29.99', '10', 'AVAILABLE', 2);


INSERT INTO product (name, description, active, category_id)
VALUES ('Kubernetes - Deploying Containers', 'Learn Kubernetes'
,1,1);
insert into product_image(path, product_id)
values('image/product/placeholder.png', 3)
insert into sku(sku, unit, unit_price, qty, status, product_id)
values('BOOK-TECH-1002', 'copy', '39.99', '15', 'AVAILABLE', 3);
