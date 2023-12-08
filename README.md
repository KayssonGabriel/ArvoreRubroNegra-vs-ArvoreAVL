# Árvore rubro-negra e árvore AVL

Experimento em Java que compara o preenchimento e o processamento de operações em duas árvores binárias de busca balanceadas. O programa carrega o mesmo conjunto de 100 mil números para as duas árvores e reutiliza uma sequência de 50 mil inteiros sorteados de −9.999 a 9.999.

Para cada valor sorteado, o programa insere os múltiplos de 3, remove os demais múltiplos de 5 e registra quantas vezes cada outro valor aparece. Múltiplos de 3 e de 5 seguem a regra de inserção. A atividade original pede apresentar a comparação em PDF.

- [`src/app/App.java`](src/app/App.java) define a carga de trabalho e mede o preenchimento e o tempo total de cada árvore.
- [`src/arvoreAVL/ArvoreAVL.java`](src/arvoreAVL/ArvoreAVL.java) implementa a AVL.
- [`src/arvoreRubroNegra/ArvoreRubroNegra.java`](src/arvoreRubroNegra/ArvoreRubroNegra.java) implementa a árvore rubro-negra.
- [`src/arquivoDados/`](src/arquivoDados/) traz o arquivo de entrada, as rotinas de leitura e escrita e amostras de resultados em `Gravados/`.

## Como executar

Use o JDK 21 ou posterior. Na raiz do repositório, rode no PowerShell.

```powershell
javac --release 21 -encoding UTF-8 -d build (Get-ChildItem -Path src -Recurse -Filter *.java -File).FullName
java -cp build app.App
```

Por padrão, o programa lê `src/arquivoDados/dados100_mil.txt` e grava as contagens em `resultados/`. Passe um caminho de entrada e um diretório de saída como primeiro e segundo argumentos para usar outros locais. Uma execução substitui as contagens no diretório escolhido.

A AVL restringe mais a diferença de altura entre subárvores, enquanto a rubro-negra mantém o equilíbrio por meio das cores e de rotações. As duas buscas por ocorrências aproveitam a ordenação das chaves. Os tempos incluem o processamento da carga de trabalho e a preparação das mensagens no tempo total. Os arquivos são gravados após as medições.

## Enunciado original

Implemente a árvore Rubro Negra e uma árvore AVL.

1) Para isso será utilizado o mesmo arquivo de dados da aula anterior (100 mil números), marque o tempo necessário para cada árvore ser completamente preenchida com os dados do arquivo.

2) Após a inserir todos os dados, faça o sorteio aleatório de outros 50.000 números entre -9999 e 9999. Caso o número sorteado seja múltiplo de 3, inserir esse número na árvore, caso o número sorteado seja multiplo de 5, remover esse número da árvore. Caso não seja nem multiplo de 3 ou de 5, contar quantas vezes esse número aparece na árvore.

Obs 1..: Os conjuntos de números devem ser os mesmos utilizados em ambas as ávores

Obs 2..: Marque o tempo de execução para uma árvore Rubro Negra e para uma árvore AVL e faça um comparativo explicando entre esses dois tipos de árvores.

Obs 3..: Os resultados da comparação devem ser apresentados no arquivo PDF.
