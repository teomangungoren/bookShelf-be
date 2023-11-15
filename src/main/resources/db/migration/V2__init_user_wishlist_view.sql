CREATE VIEW user_wish_list_view AS
    SELECT user_wish_list.id as id,
           users.email as username,
           user_wish_list.book_id as book_id,
           book.title as book_name,
           users.name as first_name,
           users.surname as surname
    FROM user_wish_list
    INNER JOIN users ON user_wish_list.username = users.email
    inner join book on user_wish_list.book_id = book.id;