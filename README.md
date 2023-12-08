# Implemente a árvore Rubro Negra e uma árvore AVL.

1) Para isso será utilizado o mesmo arquivo de dados da aula anterior (100 mil números), marque o tempo necessário para cada árvore ser completamente preenchida com os dados do arquivo.

2) Após a inserir todos os dados, faça o sorteio aleatório de outros 50.000 números entre -9999 e 9999. Caso o número sorteado seja múltiplo de 3, inserir esse número na árvore, caso o número sorteado seja multiplo de 5, remover esse número da árvore. Caso não seja nem multiplo de 3 ou de 5, contar quantas vezes esse número aparece na árvore.

Obs 1..: Os conjuntos de números devem ser os mesmos utilizados em ambas as ávores

Obs 2..: Marque o tempo de execução para uma árvore Rubro Negra e para uma árvore AVL e faça um comparativo explicando entre esses dois tipos de árvores.

Obs 3..: Os resultados da comparação devem ser apresentados no arquivo PDF.


# Inserção e remoção:
As árvores Rubro Negra fornecem operações de inserção e remoção mais rápidas do que as árvores AVL, pois menos rotações são feitas devido ao balanceamento relativamente relaxado. Já as As árvores AVL fornecem operações complexas de inserção e remoção à medida que mais rotações são feitas devido ao balanceamento relativamente rigoroso.

# Pesquisa:
A árvore Rubro Negra não fornece uma pesquisa eficiente, pois as árvores Rubro Negra são aproximadamente equilibradas. Já s árvores AVL fornecem uma pesquisa eficiente, pois são árvores estritamente balanceadas.
