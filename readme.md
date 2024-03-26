# Life simulation
## The subject of objected oriented programming(OOP)

The goal of the project is to write an object-oriented life simulation application in Java. For visualization of the simulation the graphical library Swing is used.

The application implements 2 main classes of organisms:
* Animals 
* Plants

These classes are expanded into subspecies, which have their own behavioral and characteristic features. The player can control the movement of the person. Also, the person has a special ability when activated which he can pass 2 squares for 1 move. When activated, it works for 5 rounds. And the next 5 rounds can't be activated. It is also possible to add an organism to a specific position. To do this, click on the place where you want to add an organism, and in the menu that appears, select the creature you want to add.

There are 2 methods available to control the simulation:
* With buttons in the graphical interface
* With keys on the keyboard

The application also allows you to save and load the current state of the simulation. On startup, the simulation world is filled with creatures in random order

## Key Bindings

Hotkeys can be viewed in the application by pressing the button `Show hint` or in the table above
| Key | Action
| :-----: | -----
| <kbd>Space</kbd> | Go to the next round
| <kbd>U</kbd> | Use special ability
| <kbd>S</kbd> | Save current state to file
| <kbd>L</kbd> | Load current state from file
| <kbd>Arrows</kbd> | Move around the field

## Installation

To run the application locally you need to clone the repository.
```bash
$ git clone https://github.com/greedann/World-simulation-java.git
$ cd World-simulation-java
```
The build application uses maven to build the application. Therefore, you need to build the application using maven to run it. To build use jdk 19 or higher.