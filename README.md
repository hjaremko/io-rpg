# Software Engineering 2019/2020 - Final project
![Build](https://github.com/hjaremko/io-rpg/workflows/Build/badge.svg) ![Release](https://github.com/hjaremko/cute-animals/workflows/Release/badge.svg)

**Team:** Julia Cichosz, Klaudia Goska, Marek Grzelak, Hubert Jaremko, Anna Misiewicz, Łukasz Sereda

### Project description

**Cute Animals** is a dark fantasy single-user dungeon RPG made as a semester project for the Software Engineering course at Jagiellonian University  
The game is available at https://io-rpg.herokuapp.com/.

#### Gameplay

The game is using a text-user-interface inspired by the Single-User Dungeon genre *(ex. Otchłań (pol))*.
The player interacts with the world by typing in proper commands such as `go`, `investigate` or `pick`.
Full list of available commands is in the *Documentation* section.

The game flow is as follows:
1. First, the player picks his character's class.
1. Every class offers unique abilities.
1. The player goes through successive locations, where he has the opportunity to explore, interact with other characters (e.g. conversation, fight), and collect items.
1. At each stage of the game, there are different possible courses of the game.
1. The collected items can be used in the further course of the game (depending on the item: for combat, defense, or to increase a given attribute)
1. As the player overcomes successive obstacles, the player will gain experience and develop individual attributes.

### Screenshots
![](https://i.imgur.com/RdAZ0Jd.png)
![](https://i.imgur.com/e1WRDis.png)

### Used technologies
- Server
    - Java 11
    - Spring MVC
    - PostgreSQL
    - Heroku
- Client
    - xterm.js
- Tests
    - JUnit 5
    - Mockito
    - AssertJ

### Building
```
./gradlew build -x test
```
#### Required environment variables
`SPRING_DATASOURCE_URL` : `jdbc:postgresql://localhost:5432/<database name>`  
`SPRING_DATASOURCE_USERNAME` : `postgres`  
`SPRING_DATASOURCE_PASSWORD` : your password  
`SPRING_PROFILES_ACTIVE` : `dev` or `prod`

### Documentation


Full documentation, test and coverage reports, binary files are in [artifacts](https://github.com/hjaremko/cute-animals/actions).

#### UML diagrams are [here](./assets/UML.md).


#### Avaiable commands
- ```start``` - starts the game  
- ```investigate``` - provides a description of the current location  
- ```talk <character_name>``` - allows you to talk to individual characters
- ```go <location_name>``` - allows you to go to another location  
- ```pick <item_name>``` - allows you to pick up an item and put it in your backpack  
- ```throw <item_name>``` - allows you to discard an item out of the backpack  
- ```equip <item_name>``` - allows you to put on an item from a backpack  
- ```off <item_name>```- allows you to unequip an item and put it in the backpack  
- ```backpack``` - lists the current state of the backpack  
- ```eq``` - lists the currently equipped items  
- ```stats``` - lists the character's statistics  
- ```skills``` - lists the character's abilities  
- ```fight <character_name>``` - allows you to fight individual characters  
- ```attack``` - allows you to attack in combat mode 
- ```block``` - reduces damage in the next two turns in combat mode
- ```use <item_name>``` - allows you to use an item 
- ```cast <skill_name>``` - allows the use of a given skill in combat mode
- ```suicide``` - allows you to reset the game to its initial state
  