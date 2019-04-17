SELECT count (*) FROM 
(SELECT m.name FROM member m
EXCEPT
SELECT m.name FROM member m, checkout_item c
WHERE c.member_id = m.id)
