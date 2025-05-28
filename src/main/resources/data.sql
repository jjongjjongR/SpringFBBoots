-- 1. 리뷰가 있는 축구화
SELECT
    b.*,
    AVG(r.overall) AS avg_overall,
    COUNT(r.id) AS review_count
FROM fbboot b
JOIN review r ON b.id = r.boot_id
GROUP BY b.id, b.model_name, b.comfort, b.width, b.length, b.touch, b.shooting, b.outsole, b.overall

UNION ALL

-- 2. 리뷰가 없는 축구화
SELECT
    b.*,
    NULL AS avg_overall,
    0 AS review_count
FROM fbboot b
LEFT JOIN review r ON b.id = r.boot_id
WHERE r.id IS NULL

ORDER BY
    avg_overall DESC NULLS LAST,
    review_count DESC,
    model_name ASC;

