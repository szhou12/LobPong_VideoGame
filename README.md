# Lob Pong - The Video Game: A CSC171 Project

## Synopsis
This is a Lob Pong game. 
The player's goal is to catch the ball with the paddle as many times as possible.
![2018-06-26 10 34 21](https://user-images.githubusercontent.com/35708194/41923838-1faa3c86-792e-11e8-8255-e7bd466657c4.png)

![2018-06-26 10 34 32](https://user-images.githubusercontent.com/35708194/41923871-374cca52-792e-11e8-9703-3b3bdfb44f94.png)

## Getting Started
### How to Run:
- Please run Game_frame.java
- Please move graphics folder into src folder if you run the file in terminal.

### Setup:
**1. level setup**

This game has 3 levels in total. 

Each level has different countdown timer.
- Level 1 has 20 seconds for the player to play;
- Level 2 has 30 seconds;
- Lever 3 has 60 seconds.

If the player keeps the ball bouncing for the duration of the timer, s/he “wins the level” and receives bonus points.
- Bonus points for winning level 1 are 20; 
- Bonus for winning level 2 are 50; 
- Bonus for winning level 3 are 100.

When going to the next level, the player will need to launch a new ball to start the next level.

If the player gets through all three levels (run out of countdown timer), s/he will win the whole game.
	
**2. star-coins**

The player can try to control the ball to hit the star-coins throughout the whole game.
Each time the ball successfully hits a star-coin, the player can earn 100 bonus points.
		
**3. bricks**

Bricks in the game will make the ball bounce back.
They can make it harder for the player to hit star-coins or make the whole game harder generally.
NOTE: The code that checks if the ball intersects with a brick and makes the ball bounce back if so is not working constantly. 
The problem here may be associated with how java processes the whole programme. 
	   	 
**4. Life**

Throughout the whole game, the player has three lives that allow the player to replay if s/he doesn't catch the ball.
If the player runs out of lives before the game ends, s/he will lose the game.
	 	
**5. Score system**

The player's goal is to earn as many scores as possible.
Each time the player catches the ball, s/he will earn one point.
S/He can also have bonus points as described above.

### How to Play:
- At the beginning of each round, press SPACE KEY to launch the ball.
- During each round, press Left-arrow Key and Right-arrow Key to move the paddle horizontally.
- Note: 
1. if the ball hits the left half of the paddle, the horizontal direction of the ball will be changed towards left.
2. if the ball hits the right half of the paddle, the horizontal direction of the ball will be changed towards right.
3. The purpose of doing this is to increase the variation of the motion of the ball.

## Acknowledgments
This assignment was originally designed and written by Prof. George Ferguson.

## Contributor
Shuyu Zhou
