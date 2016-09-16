SELECT *
FROM temperatura, pielegniarka, pacjent
WHERE
temperatura.id_piel = pielegniarka.id
AND pacjent.id_temp = temperatura.id