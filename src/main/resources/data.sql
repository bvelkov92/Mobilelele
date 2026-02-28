INSERT INTO brands (name,start_year,end_year)
VALUES
    ('Toyota', 1930, 2025),
    ('Ford', 1932, 2018),
    ('MAN', 1970, 2024);


INSERT INTO models (brands_id,category_vehicle, name)
VALUES
    (1,'CAR', 'Camry'),
    (1,'CAR','Corolla'),
    (2,'CAR','Focus'),
    (2,'CAR','Fiesta'),
    (3,'TRUCK','Forester');
