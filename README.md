# Balls-In-Bins

Visualizing the Pigeon-hole Principle, and the veridical Birthday Paradox with a game of balls in bins.

## The Math
When throwing balls randomly into a collection of n bins, observe that a collision (a ball going into a bin which already has a ball) is guaranteed to happen after n-1 trials. This is the pigeonhole principle (simple but important). But in fact a collision is extremely likely to happen sooner than this. This phenomenon is called the birthday paradox.

In a room of just 23 people there’s a 50-50 chance of at least two people having the same birthday. In a room of 75 there’s a 99.9% chance of at least two people matching. How is this possible with so many days in a year and so few babies? This is a veridical paradox; it seems absurd but we have the Math to prove it is so.

Think of it like: The probability that a person has the same birthday as another is 1/365, so let’s assume they have a different one. But now the third person has to have a different one from the first two. The next person has to have a different one from all three. With each new person, it gets less likely.

[![Video on the Birthday Paradox](https://img.youtube.com/vi/v=ofTb57aZHZs/0.jpg)](https://www.youtube.com/watch?v=ofTb57aZHZs/)

## The Program and Demos
Check how soon it is you get a collision.
The Balls in Bins simulation has the ability to run a regular random bin-picking game as well as 
the Power of Two Choices variation -- where when choosing which bin in which to place a ball, instead of choosing a single bin, we choose two bins. If the two bins have the same occupancy,
then the ball is placed in either bin; otherwise, the ball is placed in the smaller bin. 

https://user-images.githubusercontent.com/97693483/186259823-e9e4e6b5-0d24-43b2-bb3c-17452062c6a8.mp4

https://user-images.githubusercontent.com/97693483/186259848-de0800d9-75aa-4e57-b344-ffd013c4983e.mp4

 ### Areas for Improvement.
  1. Add a collation mechanism for gathering multiple, statistically usable results.
  2. Adjust the throwing animation so the balls appear in the bins one after the other, after each mouse click.
  3. Functionalize the back button.






