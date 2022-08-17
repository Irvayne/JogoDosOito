# DAY 2 BLOCK 36 - Programação orientada a objetos na prática

---

### Conteúdo para ser falado vs Conteúdo para ser lido pela pessoa que dará a aula a título de orientação pessoal.

Todas as orientações especificas para a pessoa que dará a aula serão marcadas com **OE** - Orientação de Execução.

### Apresentação da dinâmica do dia

**Expositiva:** Boa tarde, vocês não precisam replicar o que vou fazer durante a aula. Concentrem os esforços mentais para prestar atenção e vão enviando suas dúvidas no _Sli.do_.

A monitoria estará, também, 100% online após a aula para ajudar.

## Aula ao vivo

### O que vai ser feito hoje?

Na aula de hoje vamos botar a mão na massa implementando um jogo de boliche orientado a objetos, baseado no [Kata do Uncle Bob](http://butunclebob.com/ArticleS.UncleBob.TheBowlingGameKata). Mas antes de irmos pro código, vamos entender as regras.

#### Regras

**OE** Abra a imagem abaixo. Vamos usá-la como base para explicar as regras.

![Frames](images/bowling-frames.png)

Um jogo de boliche consiste de 10 frames, como vocês podem ver na imagem. Cada frame tem 10 pinos e a pessoa que estiver jogando tem duas chances para derrubar todos.

A pontuação do jogo é calculada com base na quantidade de pinos derrubadas em cada frame, mais os bônus por spares e strikes.

- Um spare acontece quando a pessoa que está jogando derruba todos os 10 pinos em duas tentativas. E o bônus para esse tipo de jogada é calculado com base na quantidade de pinos derrubados na próxima jogada;

- Um strike acontece quando a pessoa que está jogando derruba todos os 10 pinos em uma única tentativa. E o bônus para esse tipo de jogada é calculado com base na quantidade de pinos derrubados nas próximas duas jogadas.

E por fim, a última regra é que no décimo frame, se acontecer um spare ou um strike, a pessoa pode fazer jogadas extra até acumular um máximo de três jogadas.

Por exemplo:

- No nosso primeiro frame tivemos uma jogada que derrubou 1 pino e outra que derrubou 4, somando um total de 5 pontos;

- Já no nosso antepenúltimo frame derrubamos 6 pinos na primeira jogada e os 4 restantes na segunda, fazendo um spare. Logo temos 10 pontos, mais o bônus da próxima jogada, que foi um strike, somando mais 10 pontos. Ou seja, se somarmos esses 20 pontos aos 77 que tínhamos acumulados, chegamos ao total de 97 pontos;

- No nosso penúltimo frame fizemos um strike, então temos 10 pontos, mais o bônus das próximas duas jogadas, que foram um spare. Logo temos 10 pontos de bônus. Com isso, chegamos a um total de 117 pontos (97 + 20);

- E por fim, na última jogada, como fizemos um spare, podemos rolar 3 bolas. Então, se somarmos o total de pinos derrubados, que foram 16, com a pontuação acumulada de 117, chegamos à nossa pontuação final de 133 pontos.

Eu sei que são muitas regras, mas fiquem tranquilas e tranquilos, que vamos repassando elas ao longo da aula.

#### Diagrama de classes

Com base nisso, ao fim da aula devemos chegar num código com a estrutura do seguinte diagrama de classes:

**OE** Abra a imagem e mostre-a para a turma.

![Diagrama de classes](images/bowling-uml.png)

Nesse diagrama temos uma classe `Bowling`, que marca em qual frame estamos, quais os frames jogados e calcula a pontuação que pode ser parcial ou final. E se alguém jogar mais que 10 frames, devemos lançar um `IndexError`.

Essa lista de frames é composta por um frame normal, que tem duas jogadas, e pelo décimo frame, que pode ter até 3 jogadas. Cada `Frame` tem o resultado de suas jogadas e um tipo, que pode ser uma jogada normal, um spare ou um strike.

Esses tipos de frames são compostos pela classe `FrameTypes`, que é um enumerado. Um enumerado é nada mais nada menos que um conjunto de símbolos, que nesse caso foram usados para definir os tipos de frames válidos.

#### Implementação

Agora que temos um panorama geral de onde queremos chegar, vamos começar nosso código.

**OE** A ideia aqui é utilizar TDD. Ou seja, primeiro vamos fazer um teste que falha e depois vamos fazer um código que faz esse teste passar, para então ir pro próximo teste e assim por diante.

**1)** Primeiro vamos criar uma pasta `bowling-game` e dentro da pasta, vamos criar uma outra pasta chamada `tests`. Dentro de `tests` vamos criar um arquivo de testes para a nossa classe `Frame`:

```bash
$ mkdir bowling-game && cd bowling-game

$ mkdir tests && cd tests && touch frame_test.py
```

Voltando para a raiz da aplicação, vamos criar também nosso ambiente, que vai usar a `pytest`:

**OE** Antes de executar o comando abaixo certifique-se de que está na raiz da aplicação.

```bash
$ python3 -m venv .venv && source .venv/bin/activate

$ python3 -m pip install pytest
```

**2)** Vamos começar nossa implementação pelos frames, porque o jogo depende deles para ser executado. Basicamente, sem `Frame` não tem jogo.

Inicialmente nenhum frame foi jogado e portanto sua rolagem de pinos deve estar zerada.

> tests/frame_test.py

```python
from bowling_game.frame import Frame, FrameTypes


def test_frame_base_state():
    frame = Frame()
    assert frame.first_roll == 0
    assert frame.second_roll == 0
    assert frame.type == FrameTypes.UNPLAYED
```

**3)** O teste está falhando, pois ainda não temos a classe `Frame` e não sabemos quais são os tipos de frames válidos. Para resolver isso vamos criar outro diretório para armazenar as operações da aplicação. Vamos chamar esse arquivo de `bowling_game` e criar o arquivo `frame.py` dentro da mesma.

**OE** Crie a pasta `bowling_game` na raiz da aplicação e, em seguida, entre na pasta e crie o arquivo `frame.py`:

```bash
$ mkdir bowling_game && cd bowling_game && touch frame.py
```

Agora que criamos o arquivo, vamos criar a clase `Frame`:

> bowling_game/frame.py

```python
from enum import Enum


class Frame:
    PINS = 10

    def __init__(self):
        self.first_roll = 0
        self.second_roll = 0
        self.type = FrameTypes.UNPLAYED


class FrameTypes(Enum):
    UNPLAYED = 0
    REGULAR = 1
    SPARE = 2
    STRIKE = 3
```

Basicamente um `Frame` tem 10 pinos, pode ter sido ou não jogado. E se for jogado, pode ser uma jogada normal/regular, um spare ou um strike.

Como temos um conjunto limitado de tipos de frames válidos, utilizamos a estrutura `Enum` do Python para definir isso.

**4)** Com a estrutura base do frame pronta, vamos testar o caso de uso de uma jogada regular, que consiste de duas tentativas que não derrubaram todos os pinos.

> tests/frame_test.py

```python
from unittest.mock import patch

# from bowling_game.frame import Frame, FrameTypes


# ...


@patch('bowling_game.frame.randint')
def test_frame_regular_play(randint_mock):
    randint_mock.return_value = 1
    frame = Frame()
    frame.play()

    assert frame.first_roll == 1
    assert frame.second_roll == 1
    assert frame.type == FrameTypes.REGULAR
```

Jogar um frame significa fazer duas tentativas. Como cada tentativa de rolagem derruba um número de pinos aleatórios, precisamos _mockar_ o método `randint` para verificar isso.

**OE** Rode os testes com `python3 -m pytest` e mostre que o teste `test_frame_regular_play` está falhando.

**5)** Com o teste falhando, precisamos refatorar nosso código.

> bowling_game/frame.py

```python
# from enum import Enum
from random import randint


class Frame:
    # PINS = 10

    # def __init__(self):
    #     self.first_roll = 0
    #     self.second_roll = 0
    #     self.type = FrameTypes.UNPLAYED

    def play(self):
        self.first_roll = self._roll()
        pins_left = self.PINS - self.first_roll
        self.second_roll = self._roll(pins_left)
        self.type = FrameTypes.REGULAR

    @classmethod
    def _roll(cls, pins_left=PINS):
        return randint(0, pins_left)

# ...
```

Uma jogada consiste de duas rolagens, que derrubam um número aleatório de pinos. Esse número vai de 0 a 10 na primeira rolagem e de 0 até a quantidade de pinos restantes na segunda rolagem.

Como nosso método `_roll` não precisa de nada da instância e é um detalhe de implementação, definimos ele como um método de classe "protegido" (mais a frente vamos precisar herdar esse comportamento em `TenthFrame`).

**OE** Rode novamente os testes e mostre que agora o teste está funcionando!

**6)** Além do caso de uso de uma jogada regular, temos o spare e o strike. Sendo um spare um tipo de jogada que ocorre quando a pessoa que está jogando derruba todos os 10 pinos em duas tentativas. E um strike quando a pessoa que está jogando derruba todos os 10 pinos de primeira.

> tests/frame_test.py

```python
# ...


@patch('bowling_game.frame.randint')
def test_frame_spare_play(randint_mock):
    randint_mock.return_value = 5
    frame = Frame()
    frame.play()

    assert frame.first_roll == 5
    assert frame.second_roll == 5
    assert frame.type == FrameTypes.SPARE


@patch('bowling_game.frame.randint')
def test_frame_strike_play(randint_mock):
    randint_mock.return_value = 10
    frame = Frame()
    frame.play()

    assert frame.first_roll == 10
    assert frame.second_roll == 0
    assert frame.type == FrameTypes.STRIKE
```

Para o **SPARE** poderíamos ter, por exemplo, duas tentativas derrubando 5 pinos cada. E para o **STRIKE** a segunda tentativa não tem pinos para derrubar.

**OE** Rode os testes com `python3 -m pytest` e mostre que os novos testes estão falhando.

**7)** Com os novos testes falhando, precisamos refatorar novamente nosso código.

> bowling_game/frame.py

```python
# from enum import Enum
from random import randint


class Frame:
    # ...

    def play(self):
    #     self.first_roll = self._roll()
    #     pins_left = self.PINS - self.first_roll
        self.second_roll = self._roll(pins_left) if self.first_roll < 10 else 0
        self.__check_type()

    def pins(self):
        return self.first_roll + self.second_roll

    # @classmethod
    # def _roll(cls, pins_left=PINS):
    #     return randint(0, pins_left)

    def __check_type(self):
        if self.first_roll == self.PINS:
            self.type = FrameTypes.STRIKE
        elif self.pins() == self.PINS:
            self.type = FrameTypes.SPARE
        else:
            self.type = FrameTypes.REGULAR

# ...
```

Agora, como temos diferentes tipos de jogada, tivemos que implementar um método privado `__check_type`, que baseado na primeira e segunda tentativas, define o tipo da jogada.

Além disso, caso todos os pinos tenham sido derrubados de primeira, não precisamos nem fazer uma segunda tentativa nesse frame, pois já fizemos um strike.

**OE** Rode novamente os testes e mostre que agora o teste está funcionando!

**8)** Enfim o nosso frame está funcionando para o caso padrão. Bora fazer o caso especial do décimo frame?

> tests/frame_test.py

```python
# ...


def test_tenth_frame_base_state():
    frame = TenthFrame()

    assert frame.first_roll == 0
    assert frame.second_roll == 0
    assert frame.third_roll == 0
    assert frame.type == FrameTypes.UNPLAYED
```

A principal diferença do décimo frame é com as jogadas extras, podemos ter até três rolagens.

**OE** Rode os testes com `python3 -m pytest` e mostre que o teste está falhando.

**9)** Vamos implementar o décimo frame com essas características.

> bowling_game/frame.py

```python
# from unittest.mock import patch


from bowling_game.frame import Frame, FrameTypes, TenthFrame

# ...


class TenthFrame(Frame):
    def __init__(self):
        super().__init__()
        self.third_roll = 0

    def pins(self):
        return super().pins() + self.third_roll


# class FrameTypes(Enum):
#     UNPLAYED = 0
#     REGULAR = 1
#     SPARE = 2
#     STRIKE = 3
```

**OE** Não se esqueça de atualizar o import! Você tem que adicionar o `TenthFrame` à ele.

Repararam que utilizamos a função `super`? Pois é, é ela que nos permite reutilizar os métodos da superclasse `Frame` em trechos que fazemos a sobrescrita de código.

O que quero dizer com isto é que, como temos de reescrever o método `__init__` para especializá-lo, precisamos de uma forma de fazer isto sem duplicar o código.

O mesmo vale para o método `pins`, já que o somatório muda.

**OE** Rode novamente os testes e mostre que agora o teste está funcionando!

**10)** Seguindo esta linha de pensamento, podemos fazer o mesmo para a primeira rolagem do décimo frame e só acrescentar o comportamento adicional para as jogadas extras.

De acordo com a regra, quando um _spare_ ocorre no último frame, a pessoa que está jogando ganha uma rolagem extra. Vamos testar este cenário?

> tests/frame_test.py

```python
# ...


@patch('bowling_game.frame.randint')
def test_tenth_frame_spare(randint_mock):
    randint_mock.return_value = 5
    frame = TenthFrame()
    frame.play()

    assert frame.first_roll == 5
    assert frame.second_roll == 5
    assert frame.third_roll == 5
    assert frame.type == FrameTypes.SPARE
```

**OE** Rode os testes com `python3 -m pytest` e mostre que o teste está falhando.

**11)** O método `play` original, fica faltando a jogada extra do _spare_. Notem que o teste falhou porque seu valor permaneceu em 0.

Temos que sobrescrever (traduzindo para o inglês, _override_) o método `play`, dentro da classe `TenthFrame`, para corrigir isto.

> bowling_game/frame.py

```python
# ...


class TenthFrame(Frame):
    # def __init__(self):
    #     super().__init__()
    #     self.third_roll = 0

    # def pins(self):
    #     return super().pins() + self.third_roll

    def play(self):
        super().play()

        if self.type == FrameTypes.SPARE:
            self.third_roll = self._roll()

# ...
```

**OE** Rode novamente os testes e mostre que agora o teste está funcionando!

**12)** Uma outra regra do décimo frame é que quando um _strike_ ocorre, a pessoa que está jogando tem direito a mais duas jogadas extras.

> tests/frame_test.py

```python
# ...


@patch('bowling_game.frame.randint')
def test_tenth_frame_strike(randint_mock):
    randint_mock.side_effect = (
        10,
        4,
        2,
    )
    frame = TenthFrame()
    frame.play()

    assert frame.first_roll == 10
    assert frame.second_roll == 4
    assert frame.third_roll == 2
    assert frame.type == FrameTypes.STRIKE
```

Neste caso tivemos um strike e mais duas jogadas extras, cujos retornos estão representados no `side_effect`.

**OE** Rode os testes com `python3 -m pytest` e mostre que o teste está falhando.

**13)** Da mesma forma que precisamos de um tratamento especial no _spare_, também precisamos no _strike_.

> bowling_game/frame.py

```python
# ...


class TenthFrame(Frame):
    # def __init__(self):
    #     super().__init__()
    #     self.third_roll = 0

    # def pins(self):
    #     return super().pins() + self.third_roll

    # def play(self):
    #     super().play()

    #     if self.type == FrameTypes.SPARE:
    #         self.third_roll = self._roll()
        elif self.type == FrameTypes.STRIKE:
            self.second_roll = self._roll()
            pins_left = self.PINS - self.second_roll
            self.third_roll = self._roll(pins_left) if self.second_roll < 10 else 0

# ...
```

**OE** Execute os testes e mostre que estão passando!

**14)** Agora os testes estão passando, mas repararam que temos uma duplicação de código entre o `play` de `Frame` e `TenthFrame`?

Hora de refatorar!

> bowling_game/frame.py

```python
# ...


class Frame:
#   ...

    def play(self):
        self.first_roll, self.second_roll = self._consecutive_rolls()
        self.__check_type()

    # def pins(self):
    #     return self.first_roll + self.second_roll

    @classmethod
    def _consecutive_rolls(cls):
        first_roll = cls._roll()
        pins_left = cls.PINS - first_roll
        second_roll = cls._roll(pins_left) if first_roll < 10 else 0

        return first_roll, second_roll

    # @classmethod
    # def _roll(cls, pins_left=PINS):
    #     return randint(1, pins_left)

    # def __check_type(self):
    #     if self.first_roll == self.PINS:
    #         self.type = FrameTypes.STRIKE
    #     elif self.pins() == self.PINS:
    #         self.type = FrameTypes.SPARE
    #     else:
    #         self.type = FrameTypes.REGULAR


# class TenthFrame(Frame):
#     def __init__(self):
#         super().__init__()
#         self.third_roll = 0

#     def pins(self):
#         return super().pins() + self.third_roll

    # def play(self):
    #     super().play()

    #     if self.type == FrameTypes.SPARE:
    #         self.third_roll = self._roll()
        elif self.type == FrameTypes.STRIKE:
            self.second_roll, self.third_roll = self._consecutive_rolls()

# ...
```

**OE** Execute os testes e mostre que estão passando.

Extraímos o código duplicado para um método de classe "protegido", porque `TenthFrame` também precisa executá-lo.

**15)** Legal! Mas e se a segunda rolagem também for um _strike_?

> tests/frame_test.py

```python
# ...


@patch('bowling_game.frame.randint')
def test_tenth_frame_multiple_strikes(randint_mock):
    randint_mock.return_value = 10
    frame = TenthFrame()
    frame.play()

    assert frame.first_roll == 10
    assert frame.second_roll == 10
    assert frame.third_roll == 10
    assert frame.type == FrameTypes.STRIKE
```

**OE** Rode os testes com `python3 -m pytest` e mostre que o teste está falhando.

**16)** Do jeito que está o nosso código, quando acontece um segundo _strike_, ainda falta uma rolagem extra.

> bowling_game/frame.py

```python
class TenthFrame(Frame):
#     ...

#     def play(self):
#         super().play()

#         if self.type == FrameTypes.SPARE:
#             self.third_roll = self._roll()
#         elif self.type == FrameTypes.STRIKE:
#             self.second_roll, self.third_roll = self._consecutive_rolls()
              if self.second_roll == self.PINS:
                  self.third_roll = self._roll()

```

Para verificar que houve um segundo _strike_, podemos apenas verificar se a segunda rolagem derrubou todos os pinos.

**17)** Com os frames implementados, podemos começar a desenvolver a nossa classe que de fato executa o jogo.

Nosso primeiro teste vai apenas verificar a pontuação do jogo quando ainda não foi feita nenhuma jogada.

**OE** Crie o arquivo `bowling_test.py` dentro da pasta `tests`.

> tests/bowling_test.py

```python
from bowling_game.bowling import Bowling


def test_initial_score():
    game = Bowling()
    assert game.score() == 0
```

**OE** Rode os testes com `python3 -m pytest` e mostre que o teste está falhando.

**18)** O teste está falhando, pois ainda não temos a classe `Bowling` e nem o método `score`. Vamos fazer a alteração mínima para resolver isso.

**OE** Crie o arquivo `bowling.py` dentro da pasta `bowling_game`.

> bowling_game/bowling.py

```python
class Bowling:
    def score(self):
        return 0
```

**19)** Agora que nosso teste está passando, vamos para o próximo passo, que é criar os frames e limitar a quantidade de jogadas.

> tests/bowling_test.py

```python
from unittest.mock import patch

import pytest

# from bowling_game.bowling import Bowling


# ...

@patch('bowling`_`game.frame.Frame')
@patch('bowling_game.frame.TenthFrame')
def test_frame_limit(_frame, _tenth_frame):
    game = Bowling()

    for i in range(10):
        assert game.frame_number() == i + 1
        game.play_frame()

    with pytest.raises(IndexError) as error:
        assert game.frame_number() == 11
        game.play_frame()

    assert error.match('No more frames to play!')
```

O que fizemos aqui foi jogar os 10 frames e verificar a cada passo em que frame estamos. Porém, quando chegamos no décimo-primeiro, não podemos jogá-lo, então uma exceção deve ser lançada avisando isso.

Outro ponto importante aqui é que, como nesse momento não precisamos do código dos frames, _mockamos_ eles para melhorar a performance do nosso teste.

**OE** Rode os testes com `python3 -m pytest` e mostre que o teste está falhando.

**20)** Com mais um teste falhando, precisamos refatorar novamente o nosso código.

> bowling_game/bowling.py

```python
from bowling_game.frame import Frame, TenthFrame


class Bowling:
    MAX_FRAMES = 10

    def __init__(self):
        self.__current_frame_index = 0
        self.__frames = [Frame() for i in range(self.MAX_FRAMES - 1)]
        self.__frames.append(TenthFrame())

    def play_frame(self):
        if self.__current_frame_index == self.MAX_FRAMES:
            raise IndexError('No more frames to play!')

        self.__frames[self.__current_frame_index].play()
        self.__current_frame_index += 1

    def frame_number(self):
        return self.__current_frame_index + 1

    # def score(self):
    #     return 0
```

Aqui, criamos uma constante `MAX_FRAMES` para indicar quando chegamos ao limite de frames.

Criamos também um inicializador que começa o jogo no índice do primeiro frame e que cria seus 10 frames, sendo o décimo do tipo `TenthFrame`. Pois, no décimo frame podemos ter jogadas extras.

Fizemos parte do jogo, de modo que a cada frame jogado passamos para o próximo, até chegar no limite de frames, quando lançamos um erro.

E por fim, desenvolvemos o método `frame_number`, que expõe o frame atual.

**21)** Dado que nós já temos a movimentação de frames, o próximo passo seria calcular a pontuação de um jogo que só tem jogadas regulares.

> tests/bowling_test.py

```python
# from unittest.mock import patch

# import pytest

# from bowling_game.bowling import Bowling

# ...

@patch('bowling_game.frame.randint')
def test_score_rolling_zeros(randint_mock):
    randint_mock.return_value = 0
    game = Bowling()

    for i in range(10):
        game.play_frame()

    assert game.score() == 0


@patch('bowling_game.frame.randint')
def test_score_rolling_ones(randint_mock):
    randint_mock.return_value = 1
    game = Bowling()

    for i in range(10):
        game.play_frame()

    assert game.score() == 20
```

Como cada frame tem duas rolagens, se todas forem 0, teremos 0 pontos e se todas forem 1, teremos 20 pontos. E já que cada rolagem derruba um número de pinos aleatórios, precisamos _mockar_ o `randint` de `frame` para verificar isso.

**OE** Rode os testes com `python3 -m pytest` e mostre que o teste está falhando.

**22)** O teste falha, porque nossa pontuação está sempre retornando zero. Isso nos força a refatorar o código e desenvolver uma implementação real para o método `score`.

> bowling_game/bowling.py

```python
# from bowling_game.frame import Frame, TenthFrame


class Bowling:
#     ...

    def score(self):
        result = 0
        for frame_index in range(self.__current_frame_index):
            frame = self.__frames[frame_index]
            result += frame.pins()

        return result
```

#### BÔNUS (PULE PARA O ENCERRAMENTO SE NÃO HOUVER TEMPO)

Se houver tempo, implemente os cálculos de pontos com os bônus de _spare_ e de _strike_.

**23)** Corrigimos nossa implementação de `score` para o caso de jogadas regulares, mas e se tivéssemos algum _spare_?

Nesse caso teríamos que somar os pinos derrubados pela próxima rolagem como um bônus na pontuação.

Como temos cálculos de pontuação parcial e um spare depende da rolagem do próximo frame para calcular seu bônus, vamos fazer dois testes:

- Um para o caso de termos um spare, mas a próxima jogada ainda não ter ocorrido e portanto não seria possível calcular o bônus;

- E outro para o caso de já termos a jogada seguinte ao spare, em que seria possível calcular o bônus.

> tests/bowling_test.py

```python
# ...

@patch('bowling_game.frame.randint')
def test_score_rolling_just_a_spare(randint_mock):
    randint_mock.return_value = 5
    game = Bowling()
    game.play_frame()

    assert game.score() == 10


@patch('bowling_game.frame.randint')
def test_score_rolling_a_spare_and_next_frame(randint_mock):
    randint_mock.side_effect = (
        7,
        3,
        6,
        2,
    )
    game = Bowling()
    game.play_frame()
    game.play_frame()

    assert game.score() == 24
```

No nosso teste em que temos o spare e o bônus, o primeiro frame vai valer 10 pontos que, com mais 6 de bônus, alcança um total de 16 pontos, somado com a pontuação do segundo frame, que é 8, resulta em 24 pontos.

**OE** Rode os testes com `python3 -m pytest` e mostre que o teste está falhando.

**24)** A próxima refatoração consiste no tratamento do bônus para o _spare_.

> bowling_game/bowling.py

```python
from bowling_game.frame import Frame, TenthFrame, FrameTypes


class Bowling:
    # ...

    def score(self):
        # result = 0
        # for frame_index in range(self.__current_frame_index):
        #     frame = self.__frames[frame_index]
        #     result += frame.pins()

              if frame.type == FrameTypes.SPARE:
                  result += self.__spare_bonus(frame_index)

        # return result

    def __spare_bonus(self, frame_index):
        return self.__frames[frame_index + 1].first_roll
```

**OE** Não se esqueça de adicionar `FrameTypes` ao import!

**25)** Porém, o que acontece se tivermos um spare no décimo frame? Afinal, não existe um décimo-primeiro.

Para facilitar a nossa vida, vamos fazer um teste em que todas as jogadas são _spares_ e acreditem em mim, eu calculei a pontuação final e ela deve ser de `150`.

> tests/bowling_test.py

```python
# ...

@patch('bowling_game.frame.randint')
def test_score_rolling_only_spares(randint_mock):
    randint_mock.return_value = 5
    game = Bowling()
    for i in range(10):
        game.play_frame()

    assert game.score() == 150
```

**OE** Rode os testes com `python3 -m pytest` e mostre que o teste está falhando.

**26)** Viram que deu erro no calculo do bônus do `frame_index` igual a `9`? Pois é, esse é o nosso décimo frame.

O erro aconteceu porque não é possível acessar o próximo frame, quando já estamos no último.

E se abstraírmos essa lógica para um novo método e corrigirmos isso? Nesse novo método, vamos precisar apenas de tratar a exceção e retornar `None`, caso não haja um próximo frame.

> bowling_game/bowling.py

```python
# ...

    def __spare_bonus(self, frame_index):
        frame = self.__next_frame(frame_index)

        if not frame:
            return 0

        return frame.first_roll

    def __next_frame(self, frame_index):
        try:
            frame = self.__frames[frame_index + 1]
        except IndexError:
            frame = None

        return frame
```

**OE** Execute os testes e mostre que estão passando.

**27)** Com os testes passando, falta fazer o caso análogo, que é o do strike. Contudo, o bônus do strike é calculado com base nos pinos derrubados nas próximas duas rolagens.

E o jogo perfeito (só strikes) resulta numa pontuação final de `300`.

> tests/bowling_test.py

```python
# ...


@patch('bowling_game.frame.randint')
def test_score_rolling_just_a_strike(randint_mock):
    randint_mock.return_value = 10
    game = Bowling()
    game.play_frame()

    assert game.score() == 10


@patch('bowling_game.frame.randint')
def test_score_rolling_a_strike_and_next_frame(randint_mock):
    randint_mock.side_effect = (
        10,
        3,
        6,
    )
    game = Bowling()
    game.play_frame()
    game.play_frame()

    # 10 pinos + 9 de bônus + 9 pinos
    assert game.score() == 28


@patch('bowling_game.frame.randint')
def test_score_rolling_only_strikes(randint_mock):
    randint_mock.return_value = 10
    game = Bowling()
    for i in range(10):
        game.play_frame()

    assert game.score() == 300
```

**28)** Como temos que verificar as próximas duas rolagens para calcular o bônus do _strike_, ele possui um caso especial a mais, se comparado com o _spare_.

> bowling_game/bowling.py

```python
# ...

    def score(self):
        # result = 0
        # for frame_index in range(self.__current_frame_index):
        #     frame = self.__frames[frame_index]
        #     result += frame.pins()

        #     if frame.type == FrameTypes.SPARE:
        #         result += self.__spare_bonus(frame_index)
              elif frame.type == FrameTypes.STRIKE:
                  result += self.__strike_bonus(frame_index)

        # return result

    # def __spare_bonus(self, frame_index):
    #     frame = self.__next_frame(frame_index)

    #     if not frame:
    #         return 0

    #     return frame.first_roll

    def __strike_bonus(self, frame_index):
        frame = self.__next_frame(frame_index)

        # Caso não haja próximo frame, não há bônus
        if not frame:
            return 0

        # Caso a próxima jogada seja outro strike e frame seja do 8o para trás:
        # Soma a rolagem do segundo strike e a primeira rolagem do próximo frame
        if frame.type == FrameTypes.STRIKE and frame_index < self.MAX_FRAMES - 2:
            other_frame = self.__next_frame(frame_index + 1)
            return frame.first_roll + other_frame.first_roll

        # Caso não haja caso especial, apenas soma as próximas duas rolagens
        return frame.first_roll + frame.second_roll

    # def __next_frame(self, frame_index):
    #     try:
    #         frame = self.__frames[frame_index + 1]
    #     except IndexError:
    #         frame = None

    #     return frame
```

### Encerrando a aula

**OE** Recapitule as habilidades adquiridas nessa aula:

- Aprendemos quando usar herança e quando usar composição;

- Revisamos encapsulamento e seus tipos de restrições de visibilidade;

- Utilizamos métodos de instância e métodos de classe;

- E também exploramos exceções customizadas.

**OE** Tire as dúvidas finais e encerre a aula.
