# Trabalho Prático 3
**Aluno**: João Pedro Elias de Moura;

**Matrícula**:16/0152615

## Simplicidade
#### Descrição
<p style="text-indent: 20px; text-align: justify">
  Quando um projeto apresenta essa característica, se torna fácil de entender, para uma pessoa que está vendo o código pela primeira vez, o que cada método, classe, atributo significa. Não apresentam manchas desnecessárias e é fácil de implementar.
</p>

#### Maus cheiros evitados:
- **Método Longo**: pois evita comentários para explicar um trecho de código
#### Refatoração:
- Extrair método;

## Elegância
#### Descrição
<p style="text-indent: 20px; text-align: justify">
    A elegância de um código anda de mãos dadas a simplicidade. Ter ela no seu código, significa que seu código foi bem desenhado, bem estruturado. Cada parte complementa a outra, adicionando algo diferente e valioso ao código. Simples mudanças não geram modificações em outras partes.
</p>

#### Maus cheiros evitados:
- **Método Longo** e **Classe Grande**: por ter seu código bem estruturado, é pressuposto que os métodos são curtos, o código possui uma alta coesão e baixo acoplamento.
- **Cirurgia com rifle**: Como dito anteriormente, em um código elegante simples mudanças não afetam outras partes do código.
#### Refatoração:
- Extrair método;
- Extrair classe;
- Mover método ou Mover campo.

## Modularidade
#### Descrição
<p style="text-indent: 20px; text-align: justify">
    A modularidade em um código significa que o mesmo é bem dividido. O código é decomposto em subsistemas, bibliotecas, pacotes, classes e assim por diante. As qualidades chaves para essa modularidade é o baixo acoplamento e a alta coesão.
</p>

#### Maus cheiros evitados:
- **Método Longo** e **Classe Grande**: por ter seu código bem estruturado, é pressuposto que os métodos são curtos, o código possui uma alta coesão e baixo acoplamento.
- **Cirurgia com rifle** e **Mudanças Divergentes**: Como dito anteriormente, em um código elegante simples mudanças não afetam outras partes do código.
#### Refatoração:
- Extrair método;
- Extrair classe;
- Mover método ou Mover campo.

## Ausência de duplicidade
#### Descrição
<p style="text-indent: 20px; text-align: justify">
    Um código bem projetado não possui duplicações. Duplicação é a inimiga da elegância e simplicidade. Redundância desnecessária levam a um programa frágil.
</p>

#### Maus cheiros evitados:
- **Código Duplicado**.
#### Refatoração:
- Extrair método / método template;

## Extensabilidade
#### Descrição
<p style="text-indent: 20px; text-align: justify">
    Essa característica permite que funcionalidades extras sejam encaixadas em lugares apropriadas, se necessário. Um bom projetista pensa cuidadosamente sobre como seu software será ampliado.
</p>

#### Maus cheiros evitados:
- **Generalidade especulativa**: pois assim o projetista escreve seu código para que as funcionalidades futuras que venham a ser necessárias, sejam desenvolvidas depois e podendo ser encaixadas sem causar problemas no código, evitando que ele faça previsões de quais funcionalidade poderão vir a ser implementadas.
#### Refatoração:
- Diminuir hierarquia;
- Remover parâmetro.
