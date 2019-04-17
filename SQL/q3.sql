
SELECT b.title FROM book b
EXCEPT
SELECT b.title FROM book b , checkout_item c WHERE c.book_id=b.id
UNION
SELECT m.title FROM movie m
EXCEPT
SELECT m.title FROM movie m , checkout_item c WHERE c.book_id=m.id

