This software is under MIT license and is free for use at your own risk. It was developed for educational purposes, and it may contain bugs that won`t be fixed by its developers.
The original activity was assigned by Professor Geucimar Brilhador from Universidade Positivo in Brazil, as part of the software engineering course, on software developing studies, with the following text (in brazilian portuguese):

"
Orientações
1. Foi solicitado o desenvolvimento de um software para listar e incluir itens
no cardápio de um restaurante com as opções de pratos, bebidas e vinhos
disponíveis para seus clientes;
2. O cardápio deverá permitir ao cliente selecionar os itens que deseja em sua
refeição e possibilitar a inclusão de uma observação no pedido;
3. Para ajudar no desenvolvimento foram enviados os seguintes dados:
pratos.csv, bebidas-tabuladas.txt e vinhos-tabulados.txt;
4. Também foi solicitado que sejam armazenados os dados de cada pedido
realizado, incluindo os itens e preços, e que seja possível visualizar os
pedidos posteriormente com sua totalização;
5. Apresentar no dia 14/09/2020 ou 15/09/2020.
"

BUGS
1- Charset definition is nor working, so whenever we input a special char, like ^, ', ~, etc.. scanner inputs a question mark ?;
2- Error handling is very precary and you should probably review that before using in production.

Feel free to download, fork or modify this software whenever you want.

Victor L. M. Tavares 
v20.dev.br