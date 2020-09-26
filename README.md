This software is under MIT license and is free for use at your own risk. It was developed for educational purposes, and it may contain bugs that won`t be fixed by its developers.
The original activity was assigned by Professor Geucimar Brilhador from Universidade Positivo in Brazil as part of the software engineering course, on software developing studies, with the following text (in brazilian portuguese):

"""
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
6. Com base nas orientações da atividade 4, crie funções de CRUD para os
itens que precisam ser armazenados e recuperados do disco. São eles:
pratos, bebidas, vinhos e pedidos;
7. O programa desenvolvido deverá seguir as boas práticas de orientação a
objetos, criando classes específicas de acordo com as características dos
atributos e funções a serem processados;
8. Utilizar coleções e adotar o conceito de “separação de interesses”, fazendo
com que cada classe processe apenas algoritmos pertinentes a ela;
9. A apresentação deverá ser feita no dia 28/09/2020;
10. O código do programa, executando, deverá ser versionado no github até o
dia 27/09/2020 e a URL publicada no portal até as 23h59min desse dia.
"""



RUNNING

Delete all files inside bin folder.
Compile all the classes using javac and put the .class files inside bin folder.
To run the program you`ll need to navigate to bin folder and run the following command: 
    Java App

BUGS
1- Charset definition is nor working, so whenever we input a special char, like ^, ', ~, etc.. scanner inputs a question mark ?;

Feel free to download, fork or modify this software whenever you want.

Victor L. M. Tavares 
v20.dev.br