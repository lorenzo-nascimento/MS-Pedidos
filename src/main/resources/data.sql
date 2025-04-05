INSERT INTO clientes_tb (nome, email) VALUES
('João Silva', 'joao@email.com'),
('Maria Santos', 'maria@email.com'),
('Pedro Alves', 'pedro@email.com');

INSERT INTO pedidos_tb (status, data_criacao, cliente_id) VALUES
('PENDENTE', '2024-01-15 10:00:00', 1),
('FINALIZADO', '2024-01-16 11:30:00', 1),
('CANCELADO', '2024-01-17 09:15:00', 2),
('PENDENTE', '2024-01-18 14:20:00', 3);
