# Project 4: Asteroid Rally

## Objectives

By the time you have completed this project, you should be able to:
* Implement nontrivial classes.
* Use a class diagram to decide on an order with which you write/implement those classes.

## Playing the Game

Right click on `AsteroidRally.jar` and run it. This is the completed version of the project.

This game is heavily inspired by the 1979 Atari game [Asteroids](https://en.wikipedia.org/wiki/Asteroids_(video_game)). It is a two-player game. Follow the instructions on the starting screen and play the game a few times by yourself or with a housemate.

## Overview

You have only been given some of the files needed to create the game. Your first job is to complete the program so that it behaves exactly like the completed version. After that, you are free to make graphical enhancements and modifications -- such as a starfield background or flames shooting out of the ship when it accelerates. Don't modify the game logic until you have the completed game working.

## Class diagram

The following class diagram shows the organization of the classes in the complete game (note that the image file is also in the project [here](img/asteroid.png)):

<img src="https://storage.googleapis.com/142-files/asteroid.png"/>

The classes that are shaded have been written for you and you should not modify them. `AsteroidRallyModel` is half-shaded because you must implement parts of it; you have been given an incomplete skeleton. The other classes you must write yourself.

## Implementation Process

Take notes as you work through the project. Note any bugs or conceptual difficulties. How did you overcome them? What did you learn?

For each of the classes you will need to create, there is a corresponding test class. The project does not build yet because the classes you need to implement do not yet exist. In order to get started, create skeleton versions of those classes:

0. The __very first__ thing you need to do is __carefully__ read through the `AsteroidRally` and `AsteroidRallyModel` classes in order to understand what they do and how they interact with both each other, as well as with the missing classes. Make notes about what the methods do, how those methods interact, and how they implement the game's behavior. This is the most important step for this lab -- it will help you understand __exactly__ what each of the classes that you need to implement need to do. I would anticipate that you would spend about an hour on this step alone, playing the game, looking at the code, going back and forth, and thinking about how the game works, even though you have an incomplete implementation.
1. Create the class files (`Flag.java`, `Extent.java` and `Ship.java`) for the corresponding classes.
2. Try to build the project, by running one of the tests or the main game (`AsteroidRally`). The compiler/build errors will highlight methods and constructors that don't exist.
3. Create dummy versions of these methods that take the right parameter types and return the right type, but don't actually do anything.
4. Run the tests.
5. Work through the test cases, making them pass.

## Submitting
You should submit five files for this project: `Extent.java`, `Flag.java`, `Ship.java`, `AsteroidRallyModel.java`, and `README.md`.

## Implementing the Classes

Note that unlike in other projects, there is not a prescribed order for completing the project. You should look at the class diagram to decide which classes to implement first. Their roles are described below.

Note that some classes do not need to be implemented _completely_ in order for other classes to be implemented.

### Extent

An `Extent` represents the [_extent_](https://www.merriam-webster.com/dictionary/extent) or area that an object centered at a given location covers. 

You should look at the `ExtentTest` tests and determine what the parameters to the constructor mean by looking at the first few tests.

The remaining tests validate that an extent can compute the distance to another extent, and whether it can compute whether two extents intersect.

### Flag

A `Flag` represents a flag on the board to be touched by a player. Look at the `FlagTest` to determine how to implement a `Flag`.

### AsteroidRallyModel

The `AsteroidRallyModel` class implements the rules of the game. 

### Ship

The `Ship` class represents a single ship. One of the most challenging parts of this project is implementing how a ship should move.

First, play the game a few times and get a feel for how the ship moves. 

#### Ship movement

First, notice that you have a ship.

<img src="https://storage.googleapis.com/142-files/p05-00-ship-ship.png"/>

That ship is pointing in a _direction_.

<img src="https://storage.googleapis.com/142-files/p05-01-ship-direction.png"/>

You can use the A/D keys (if you are red) to change the ship's direction.

<img src="https://storage.googleapis.com/142-files/p05-02-change-directions.png"/>

We can represent that direction by an angle. Here, we'll use radians.

<img src="https://storage.googleapis.com/142-files/p05-03-angle.png"/>

So we can say that a ship pointing right has a direction of 0º, a ship pointing up has a direction of 𝝅/2º, etc.

<img src="https://storage.googleapis.com/142-files/p05-04-ship-angles.png"/>

A ship also has a _heading_ -- the vector along which it is moving. Note that the heading of a ship is __not__ the same as the direction it is pointing. For example, a ship with a x heading of 0.5 and a y heading of 0 will drift to the right, no matter what direction the ship is pointing.

<img src="https://storage.googleapis.com/142-files/p05-05-heading.png"/>

A player can change this heading by _accelerating_ the ship in the _direction_ it is facing. The magnitude of the acceleration here is 0.25.

<img src="https://storage.googleapis.com/142-files/p05-06-accelerate.png"/>

This changes the heading of the ship. Here, the new heading in the y direction increases by the magnitude of the acceleration.

<img src="https://storage.googleapis.com/142-files/p05-07-heading-change.png"/>

Making changes to the heading are simple when the ship is facing in one of the compass directions when it accelerates, but a little more complicated when the ship is facing a non-compass direction.

<img src="https://storage.googleapis.com/142-files/p05-08-non-compass.png"/>

So what happens when the ship accelerates in an arbitrary direction, with an arbitrary amount of acceleration?

<img src="https://storage.googleapis.com/142-files/p05-09-arbitrary-acceleration.png"/>

The amount that this will affect the x and y heading is related to the direction the ship is facing when it accelerates.

<img src="https://storage.googleapis.com/142-files/p05-10-accelerate-angle.png"/>

How do we determine how much this affects the x and y heading? We need to determine the magnitude of the x and y component changes (e.g., the base and height of this triangle). 

<img src="https://storage.googleapis.com/142-files/p05-11-magnitude.png"/>

But note that the hypotenuse is the magnitude of the acceleration, so the corresponding sides are computed as such:

<img src="https://storage.googleapis.com/142-files/p05-12-do-the-trig.png"/>

And now we can compute the new x and y heading.

<img src="https://storage.googleapis.com/142-files/p05-13-new-heading.png"/>

With this, you should now be able to implement the corresponding behavior in `Ship`.

<img src="https://storage.googleapis.com/142-files/p05-14-red-sus.png"/>
