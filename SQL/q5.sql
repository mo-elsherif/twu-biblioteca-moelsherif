
SELECT m.name FROM member m, checkout_item c
WHERE c.member_id = m.id
GROUP BY m.name 
HAVING COUNT(c.member_id) >1
