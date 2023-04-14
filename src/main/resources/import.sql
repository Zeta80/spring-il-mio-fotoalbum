INSERT INTO photos (title, description, url, visible) VALUES('Photo 1', 'la foto number 1', 'https://i.ytimg.com/vi/lPOrjROuheY/maxresdefault.jpg', 1);
INSERT INTO photos (title, description, url, visible) VALUES('Photo 2', 'la foto number 2', 'https://i.ytimg.com/vi/_7Mgb7xK3Dk/maxresdefault.jpg', 1);


INSERT INTO categories (description, name) VALUES('Bianco e nero', 'B&W');
INSERT INTO categories (description, name) VALUES('4k ultra hd', '4k');
INSERT INTO categories (description, name) VALUES('Paesaggio', 'Paesaggio');


INSERT INTO photo_category (photo_id, category_id) VALUES(1, 1);
INSERT INTO photo_category (photo_id, category_id) VALUES(1, 2);
INSERT INTO photo_category (photo_id, category_id) VALUES(2, 3);
