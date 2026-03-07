INSERT INTO brands (name)
VALUES
    ('Toyota'),
    ('Ford'),
    ('MAN');


INSERT INTO models (brands_id,category_vehicle, name, start_year, end_year)
VALUES
    (1,'CAR', 'Camry',1989, 2026),
    (1,'CAR','Corolla',1976, 2025),
    (2,'CAR','Focus', 1972, 2024),
    (2,'CAR','Fiesta', 1989, 2026),
    (3,'TRUCK','TGX', 1970, 2024);
