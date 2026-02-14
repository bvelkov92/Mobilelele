sql
-- data.sql
-- Този файл ще бъде изпълнен автоматично от Spring Boot при стартиране

-- Добавяне на примерен потребител
-- Паролата '95c1933b8ffe84f685f283989901f422' е примерна хеширана парола (вероятно за "test")
INSERT INTO users (id, active, email, first_name, last_name, password)
VALUES (1, 1, 'admin@example.com', 'Admin', 'Adminov', '95c1933b8ffe84f685f283989901f422');

-- Добавяне на брандове
INSERT INTO brands (id, brand) -- Уверете се, че името на колоната е 'brand' или 'name'
VALUES
    (1, 'Toyota'),
    (2, 'Ford');

-- Добавяне на модели, свързани с брандовете чрез brand_id
INSERT INTO models (id, category, brand_id, name)
VALUES
    (1, 'CAR', 1, 'Camry'),   -- Camry е модел на Toyota (brand_id 1)
    (2, 'CAR', 1, 'Corolla'),  -- Corolla е модел на Toyota (brand_id 1)
    (3, 'CAR', 2, 'Focus'),   -- Focus е модел на Ford (brand_id 2)
    (4, 'CAR', 2, 'Fiesta');  -- Fiesta е модел на Ford (brand_id 2)