-- 1. Popolamento tabella FILM
INSERT INTO film (id, data_uscita, descrizione, durata_minuti, genere, titolo) VALUES 
	(1, '2026-05-20', 'Un avventuriero esplora terre ignote.', 120, 'Avventura', 'L\'Orizzonte Lontano'),
    (2, '2026-06-01', 'Un mistero avvolto nel buio.', 105, 'Thriller', 'Ombre Urbane'),
	(3, '2026-06-05', 'Una commedia esilarante in ufficio.', 95, 'Commedia', 'Ridere in Ufficio'),
	(4, '2026-06-08', 'Guerra galattica tra stelle.', 140, 'Fantascienza', 'Star Command'),
	(5, '2026-06-10', 'Documentario sulla natura selvaggia.', 80, 'Documentario', 'Wild Planet'); 

-- 2. Popolamento tabella SALA 
INSERT INTO sala (id, nome, numero_posti) VALUES 
	(1, 'Sala Gold', 100), 
	(2, 'Sala Silver', 50), 
	(3, 'Sala VIP', 20), 
	(4, 'Sala 4DX', 60), 
	(5, 'Sala Cinema Paradiso', 120);

-- 3. Popolamento tabella CLIENTE
INSERT INTO cliente (id, email, nominativo) VALUES
	(1, 'mario.rossi@email.it', 'Mario Rossi'), 
	(2, 'lucia.bianchi@email.it', 'Lucia Bianchi'), 
	(3, 'luca.verdi@email.it', 'Luca Verdi'), 
	(4, 'anna.neri@email.it', 'Anna Neri'), 
	(5, 'giovanni.gialli@email.it', 'Giovanni Gialli'); 

-- 4. Popolamento tabella SPETTACOLO 
INSERT INTO spettacolo (id, data_ora, prezzo, film_id, sala_id) VALUES 
	(1, '2026-06-15 18:30:00', 8.50, 1, 1), 
	(2, '2026-06-15 21:00:00', 9.00, 2, 2),
	(3, '2026-06-16 19:00:00', 7.50, 3, 3),
	(4, '2026-06-16 20:30:00', 10.00, 4, 4), 
	(5, '2026-06-17 17:00:00', 6.00, 5, 5);

-- 5. Popolamento tabella PRENOTAZIONE
INSERT INTO prenotazione (id, numero_posti_prenotati, cliente_id, spettacolo_id) VALUES 
	(1, 2, 1, 1), 
	(2, 1, 2, 2), 
	(3, 4, 3, 3), 
	(4, 2, 4, 4), 
	(5, 3, 5, 5);