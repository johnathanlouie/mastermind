# Mastermind
Mastermind is a codebreaking for game for two players. One player takes the role of a codemaker and the other, the codebreaker. The codemaker chooses a secret codeword and the codebreaker tries to decypher the codeword using the clues the codemaker leaves behind.

# How to Run
```bash
docker run -it $(docker build . -q)
```

# Default Rules
1. The codemaker chooses a secret codeword, a four digit PIN in which each digit can only be from 0 to 5.
2. The codebreaker guesses the codeword.
3. The codemaker tells the codebreaker how close the guess was by saying the number of digits in the guess that have the:
    * (Black) Correct value and correct position
    * (White) Correct value but incorrect position
    * (None) Incorrect value and incorrect position
4. When the clue is all black, the codebreaker wins.

# Example Gameplay
```
========Game 1========
Enter secret code: 


========Turn 1========
Enter secret code: 1100
Response: black=1, white=0, none=3

========Turn 2========
Enter secret code: 3320
Response: black=1, white=2, none=1

========Turn 3========
Enter secret code: 4302
Response: black=3, white=0, none=1

========Turn 4========
Enter secret code: 2302
Response: black=3, white=0, none=1

========Turn 5========
Enter secret code: 5302
Response: black=4, white=0, none=0

========Winner========
Answer: 5302
```

# Algorithms
The computer codebreaker uses Donald Knuth's minimax algorithm, which can break the code in five or fewer moves.

# Related
* [Donald Knuth's Paper](http://www.cs.uni.edu/~wallingf/teaching/cs3530/resources/knuth-mastermind.pdf)
* [Wikipedia](https://en.wikipedia.org/wiki/Mastermind_(board_game))
