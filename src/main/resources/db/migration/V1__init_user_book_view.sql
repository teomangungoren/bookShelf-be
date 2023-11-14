CREATE VIEW  user_book_view AS
SELECT user_book.id as id,
       users.email as username,
       user_book.book_id as book_id,
       book.title as book_name,
       users.name as first_name,
       users.surname as surname
FROM user_book
         inner join users on user_book.username = users.email
         inner join book on user_book.book_id = book.id;