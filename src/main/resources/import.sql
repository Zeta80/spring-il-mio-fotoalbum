
INSERT INTO photos (title, description, url, visible) VALUES('Photo 1', 'la foto number 1', 'https://i.ytimg.com/vi/lPOrjROuheY/maxresdefault.jpg', 1);
INSERT INTO photos (title, description, url, visible) VALUES('Photo 2', 'la foto number 2', 'https://i.ytimg.com/vi/_7Mgb7xK3Dk/maxresdefault.jpg', 1);


INSERT INTO categories (description, name) VALUES('Bianco e nero', 'B&W');
INSERT INTO categories (description, name) VALUES('4k ultra hd', '4k');
INSERT INTO categories (description, name) VALUES('Paesaggio', 'Paesaggio');


INSERT INTO photo_category (photo_id, category_id) VALUES(1, 1);
INSERT INTO photo_category (photo_id, category_id) VALUES(1, 2);
INSERT INTO photo_category (photo_id, category_id) VALUES(2, 3);

INSERT INTO users (email, first_name, last_name, password) VALUES('mirko@email.it', 'Mirko', 'Robbi', '{noop}mirko');
INSERT INTO users (email, first_name, last_name, password) VALUES('benny@email.it', 'Benedetta', 'Scattolin', '{noop}benny');

INSERT INTO roles (id, name) VALUES(1, 'ADMIN');
INSERT INTO roles (id, name) VALUES(2, 'USER');

INSERT INTO users_roles(user_id, roles_id) VALUES (1,1);
INSERT INTO users_roles(user_id, roles_id) VALUES (2,2);
