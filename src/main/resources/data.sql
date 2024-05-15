INSERT INTO restaurant (ID, name, zip_code, overall_score, peanut_score, egg_score, dairy_score)
VALUES
(1, 'Restaurant1', 11111, 4.51, ROUND((RAND() * 400 + 100) / 100, 2), ROUND((RAND() * 400 + 100) / 100, 2), ROUND((RAND() * 400 + 100) / 100, 2)),
(2, 'Restaurant2', 11112, 4.14, ROUND((RAND() * 400 + 100) / 100, 2), ROUND((RAND() * 400 + 100) / 100, 2), ROUND((RAND() * 400 + 100) / 100, 2)),
(3, 'Restaurant3', 11113, 3.58, ROUND((RAND() * 400 + 100) / 100, 2), ROUND((RAND() * 400 + 100) / 100, 2), ROUND((RAND() * 400 + 100) / 100, 2)),
(4, 'Restaurant4', 11114, 4.43, ROUND((RAND() * 400 + 100) / 100, 2), ROUND((RAND() * 400 + 100) / 100, 2), ROUND((RAND() * 400 + 100) / 100, 2)),
(5, 'Restaurant5', 11115, 2.83, ROUND((RAND() * 400 + 100) / 100, 2), ROUND((RAND() * 400 + 100) / 100, 2), ROUND((RAND() * 400 + 100) / 100, 2)),
(6, 'Restaurant6', 11116, 4.69, ROUND((RAND() * 400 + 100) / 100, 2), ROUND((RAND() * 400 + 100) / 100, 2), ROUND((RAND() * 400 + 100) / 100, 2)),
(7, 'Restaurant7', 11117, 4.26, ROUND((RAND() * 400 + 100) / 100, 2), ROUND((RAND() * 400 + 100) / 100, 2), ROUND((RAND() * 400 + 100) / 100, 2)),
(8, 'Restaurant8', 11118, 3.11, ROUND((RAND() * 400 + 100) / 100, 2), ROUND((RAND() * 400 + 100) / 100, 2), ROUND((RAND() * 400 + 100) / 100, 2)),
(9, 'Restaurant9', 11119, 4.23, ROUND((RAND() * 400 + 100) / 100, 2), ROUND((RAND() * 400 + 100) / 100, 2), ROUND((RAND() * 400 + 100) / 100, 2)),
(10, 'Restaurant10', 11120, 2.94, ROUND((RAND() * 400 + 100) / 100, 2), ROUND((RAND() * 400 + 100) / 100, 2), ROUND((RAND() * 400 + 100) / 100, 2));

INSERT INTO users (ID,user_name, city, state, zipcode, peanut_allergy, egg_allergy, dairy_allergy)
VALUES
(1, 'User1', 'City1', 'State1', 12345, TRUE, FALSE, TRUE),
(2, 'User2', 'City2', 'State2', 23456, FALSE, TRUE, FALSE),
(3, 'User3', 'City3', 'State3', 34567, TRUE, TRUE, TRUE),
(4, 'User4', 'City4', 'State4', 45678, FALSE, FALSE, FALSE),
(5, 'User5', 'City5', 'State5', 56789, TRUE, FALSE, FALSE),
(6, 'User6', 'City6', 'State6', 67890, FALSE, TRUE, TRUE),
(7, 'User7', 'City7', 'State7', 78901, TRUE, TRUE, FALSE),
(8, 'User8', 'City8', 'State8', 89012, FALSE, FALSE, TRUE),
(9, 'User9', 'City9', 'State9', 90123, TRUE, FALSE, TRUE),
(10, 'User10', 'City10', 'State10', 12348, FALSE, TRUE, FALSE);

INSERT INTO dining_review (restaurant_id, user_name, peanut_score, egg_score, dairy_score, commentary, review_status)
VALUES
(1, 'User1', 4, 5, 4, 'Good food', 'ACCEPTED'),
(1, 'User13', 3, 5, 2, 'Not bad', 'ACCEPTED'),
(2, 'User2', 3, 4, 3, 'Average experience', 'PENDING'),
(3, 'User3', 5, 5, 5, 'Excellent service', 'ACCEPTED'),
(4, 'User4', 2, 3, 2, 'Poor quality', 'DENIED'),
(5, 'User5', 4, 4, 4, 'Nice ambiance', 'ACCEPTED'),
(6, 'User6', 3, 3, 3, 'Okayish', 'PENDING'),
(7, 'User7', 5, 4, 5, 'Amazing food', 'ACCEPTED'),
(8, 'User8', 2, 2, 2, 'Not recommended', 'DENIED'),
(9, 'User9', 4, 3, 4, 'Good experience', 'ACCEPTED'),
(10, 'User10', 3, 4, 3, 'Could be better', 'PENDING');