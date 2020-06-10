# Inżynieria oprogramowania 2019/2020 - Projekt końcowy ![Java CI](https://github.com/hjaremko/io-rpg/workflows/Java%20CI/badge.svg)
**Zespół:** Julia Cichosz, Klaudia Goska, Marek Grzelak, Hubert Jaremko, Anna Misiewicz, Łukasz Sereda

### Opis projektu

**Cute Animals** to internetowa, tekstowa gra RPG stworzona w ramach projektu końcowego
podczas kursu Inżynieria Oprogramowania prowadzonego
na Instytucie Informatyki i Matematyki Komputerowej Uniwersytetu Jagiellońskiego.  
Gra jest dostępna pod adresem https://io-rpg.herokuapp.com/.

#### Rozgrywka

Gra jest w pełni tekstowa i zainspirowana gatunkiem *Single-User Dungeon* (np. Otchłań).
Gracz dokonuje iterakcji ze światem poprzez wpisywanie odpowiednich poleceń,
takich jak `go`, `investigate` lub `pick`. Pełny spis możliwych akcji znajduje
się w sekcji *Dokumentacja*.


Formuła gry wygląda następująco:
1. Na samym początku gracz dokonuje wyboru klasy postaci.
1. Każda z klas oferuje unikalne zdolności.
1. Gracz pokonuje kolejne lokacje, w których ma możliwość eksploracji, interakcji z innymi postaciami (np.
rozmowa, walka), zbierania przedmiotów.
1. Na każdym etapie gry istnieją różne możliwości dalszego przebiegu rozgrywki.
1. Zebrane przedmioty gracz może wykorzystać w dalszym przebiegu gry (w zależności od przedmiotu do
walki, obrony lub zwiększenia danego atrybutu).
1. Wraz z pokonywaniem kolejnych przeszkód gracz będzie zdobywał doświadczenie rozwijając poszczególne atrybuty.

### Zrzuty ekranu
![](https://i.imgur.com/RdAZ0Jd.png)
![](https://i.imgur.com/e1WRDis.png)

### Użyte technologie
- Serwer
    - Java 11
    - Spring MVC
    - PostgreSQL
    - Heroku
- Klient
    - xterm.js
- Testy
    - JUnit 5
    - Mockito
    - AssertJ

### Budowa projektu
```
./gradlew build -x test
```
Wymaga ustawienia zmiennych środowiskowych zgodnie z opisem na [wiki](https://github.com/hjaremko/cute-animals/wiki/Konfiguracja-zmiennych-%C5%9Brodowiskowych).

### Dokumentacja

Pełną dokumentację, raporty testów i pokrycia oraz plik `jar`
można znaleźć w [artefaktach z ostatniego buildu](https://github.com/hjaremko/cute-animals/actions).

#### Diagramy klas znajdują się [tutaj](./assets/UML.md).


#### Dostępne komendy
- ```start``` - rozpoczyna rozgrywkę  
- ```investigate``` - udostępnia opis aktualnego otoczenia  
- ```talk <nazwa_postaci>``` - umożliwia rozmowę z poszczególnymi postaciami  
- ```go <nazwa_lokacji>``` - pozwala na przejście do innej lokacji.  
- ```pick <nazwa_przedmiotu>``` - pozwala podnieść dany przedmiot i umieścić go w plecaku  
- ```throw <nazwa_przedmiotu>``` - pozwala wyrzucić dany przedmiot z plecaka  
- ```equip <nazwa_przedmiotu>``` - umożliwia założenie przedmiotu z plecaka  
- ```off <nazwa_przedmiotu>```- umożliwia zdjęcie przedmiotu i włożenie go do plecaka  
- ```backpack``` - listuje aktualny stan plecaka  
- ```eq``` - listuje aktualnie założone przedmioty  
- ```stats``` - wypisuje statystyki postaci  
- ```skills``` - wypisuje zdolności postaci  
- ```fight <nazwa_postaci>``` - udostępnia możliwość walki z daną postacią  
- ```attack``` - wykonuje turę w trybie walki 
- ```block``` - zmiejsza obrażenia w dwóch następnych turach w trybie walki 
- ```use <nazwa_przedmiotu>``` - umożliwia użycie danego przedmiotu 
- ```cast <nazwa_umiejetnosci>``` - umożliwia użycie danej umięjętności w trybie walki
- ```suicide``` - umożliwia zresetowanie gry to stanu początkowego
  