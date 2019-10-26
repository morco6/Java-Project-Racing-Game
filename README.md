# Java-Project-Racing-Game
For pictures: http://www.morc.io/GRAPHIC%20RACING%20GAME%20SIMULATOR
## In General
This is a project that I built as part of the "Advanced Object Oriented Programming" course during my degree in Software Engineering.

The emphasis in this project is object-oriented programming using classic design patterns and implementation of multi-threading methods.

 

## More About The Project
The simulator simulates a computer game of racing between competitors.

At the beginning of the game you are required to build the arena, ie choose sea / land / air, arena length, and maximum amount of competitors.

Then, you are required to build players as the maximum amount you entered in the previous step.

Depending on the arena you selected in the previous step, you can choose the desired competitor (for example, if you chosed "air" for the arena, you can choose between the helicopter and a regular airplane).

Each competitor has characteristics such as: competitor name, color, maximum speed and acceleration.

During the race we can see the state of the competitors in real time. In addition, each competitor may experience a malfunction and we will see how he manage with the malfunction, whether it can be fixed, and how long it took him to recover.

Each racer is a thread and the first one who finish his process - wins.

 

## RaceBuilder - Builder, Factory Method, Singleton and Reflection

The RaceBuilder class use the singleton, Builder and Factory Method DP's to build the arena and the racers. The Java Reflection use for dynamic racer initialization.

 

## Observer and Observable - Arena and Racer

The arena use the observer DP such that during the racing the arena listen for updates from each racer (the observable). 

 

## Prototype

I used this DP to let the user to clone a racer with his default preferences with the option to make some changes. 

 

## Decorator

Designed to allow custom competitor building based on user settings.

 

## GUI

The GUI implemented with the built-in java class "Swing" to create window-based applications.
