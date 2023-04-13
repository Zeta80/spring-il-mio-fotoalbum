INSERT INTO photos (title, description, visible) VALUES('Photo 1', 'la foto number 1', 1);
INSERT INTO photos (title, description, visible) VALUES('Photo 2', 'la foto number 2', 1);
INSERT INTO photos (title, description, visible) VALUES('Photo 3', 'la foto number 3', 1);

INSERT INTO categories (description, name) VALUES('Bianco e nero', 'B&W');
INSERT INTO categories (description, name) VALUES('4k ultra hd', '4k');
INSERT INTO categories (description, name) VALUES('Paesaggio', 'Paesaggio');


INSERT INTO photo_category (photo_id, category_id) VALUES(1, 1);
INSERT INTO photo_category (photo_id, category_id) VALUES(1, 2);
INSERT INTO photo_category (photo_id, category_id) VALUES(2, 3);
INSERT INTO photo_category (photo_id, category_id) VALUES(3, 2);