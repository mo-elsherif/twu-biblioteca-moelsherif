SELECT m.name FROM member m, checkout_item c,book b 
WHERE b.title="The Hobbit" AND b.id=c.book_id 
AND c.member_id = m.id
