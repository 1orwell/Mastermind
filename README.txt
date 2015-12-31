DESCRIPTION OF PROJECT
----------------------
    This project is centerd arround the game mastermind. You have three
    different options on how the game is played:

        1) Computer codemaker verses computer codebreaker,
        2) Computer codemaker verses human codebreaker, and
        3) Human codemaker verses human codebreaker.

    After each guess is made, either by human or computer, the indicators for
    that guess are printed to the screen.
    The player gets two more guesses than the length of the code. For example,
    if the code is of length 4, the player gets 6 guesses.

-----------------------------------------------------------------------------

STARTING PROJECT
----------------
    To compile all the files, run:

        javac *.java

    To start the game, run:

        java Mastermind

    What will then be printed to the screen is:

        This game can be played in three ways.
            1) Computer codemaker verses computer codebreaker,
            2) Computer codemaker verses human codebreaker, and
            3) Human codemaker verses human codebreaker.
        Which one would you like to play? Enter 1, 2 or 3 -->

    To see how these option work in detail, go to the DIFFERENT PLAYER MODES
    section (below).

------------------------------------------------------------------------------

DIFFERENT PLAYER MODES
----------------------

    Computer v. Computer
    --------------------
    If the player chooses option 1, the program will generate a list of
    pseudorandom possible colours. From these possible colours, the
    program will generate a pseudorandom code. The possible colours and
    the length of the code are then printed to the screen. An example of
    what could be printed to the screen is:

        The colours you can choose from are: [red, pink, purple]
        The length of the code is 8

    The computer then makes a guess, the indactors for that guess are printed
    to the screen. This carries on until either the computer guesses the code
    or runs out of guesses.
    To see how the AI works, go to the AI section.


    Computer v. Human
    -----------------
    If the player chooses option 2, the program asks how long the player
    wishes the code generated to be. It will look like so:

        The code can be between 3 and 8 pegs inclusive.
        How many pegs would you like? -->

    If the user does not enter a number in the specified range, they are
    told what they entered is invalid, then the above two lines are
    printed again. What should be entered is something like this:

        How many pegs would you like? --> 3

    The player is then asked how many colour options they could like. It
    looks like so:

        The code can have between 3 and 8 colour options inclusive.
        How many colour options would you like? -->

    As above, only valid answer are accepted. What should be entered is
    something like this:

        How many colour options would you like? --> 3

    Once these numbers are entered, the program generates a pseudorandom
    list of possible colours, the number of colours in the list will be
    the same as the user specified. The program then generates a
    pseudorandom code from these possible colours. As before, the number
    of colours in the code will be the same as the user specified. The
    user will then be told they can save the game at any time, and then
    will be prompted for a guess. It will look something like this:

        The colours you can choose from are: [blue, green, yellow]
        You can save the game at any time by writing 'save'/
        guess:

    To see how the game is played, go the the PLAYING GAME section.


    Human v. Human
    --------------
    If the player chooses option 3, the human codemaker will be prompted to
    enter the colour options, it will look like this:

        Player 1, please enter the possible colour options -->

    The codemaker should then enter the colour options like so:

        Player 1, please enter the possible colour options --> red orange pink

    Once this is done, player 1 will then be asked to input the code, it will
    look like this:

        Player 1, please enter your code -->

    The codemaker should then enter the code like so:

        Player 1, please enter your code --> red red pink

    The program trusts that the codemaker will not use colours in the code that
    are not present in the colour options.
    Now that the code and colour options are set, the program will ask the
    user if they are ready for the screen to be cleared for players one, it
    will look like so:

        We will now clear the screen for player two.
        Are you ready? Type 'yes' or 'no' -->

    If no is typed, this message repeats till yes is typed. If yes is entered
    the screen is cleared. The colour options player 1 chose are printed
    to the screen, the length of the code player 1 entered is also printed
    to the screen. The player is also told they can save the game at any
    time. The player will then be prompted for a guess. Using the above
    examples, the screen will look like so:

        The colours you can choose from are: [red, orange, pink]
        The length of the code is 3
        You can save the game at any time by writing 'save'.
        guess:

    To see how the game is played, see the PLAYING GAME section of this
    README.

------------------------------------------------------------------------------

PLAYING GAME
------------
    When the program is ready for the player to make a guess, the player will
    see the following appear on the terminal:

        guess:

    At this point, the player can enter their guess in the following manner

        guess: red orange pink brown

    They can then press enter and see the indicators for that guess, the
    player will then be prompred for another for another guess. For
    example, if the code was [pink orange pink purple], the player would
    see:

        guess: red orange pink brown
        [2, 1, 0, 0]
        guess:

    This continues till the player cracks the code or runs out of guesses.

------------------------------------------------------------------------------

ARTIFICIAL INTELLIGENCE
-----------------------
    The program makes and breaks the code. A description of the computer
    generating the code can be found in DIFFERENT PLAYER MODES: OPTION 1.

    If the possible colours created by the computer were, say:

        [red, orange, brown, purple, pink]

    And the code was, say:

        [red red brown red]

    The programs's first guess would be:

        [red red red red]

    The guess and indicators for this guess will be printed to the screen
    like so:

        [red red red red]
        [2, 2, 2, 0]

    Then the programs next guess would be:

        [red red red orange]

    Then:

        [red red red brown]

    This will return the indicators:

        [2, 2, 1, 1]

    The program will then cycle through some of the possible permutations
    of this guess. Unfortunately, if does not go through then all as I
    have only made the program shift the colours to the right. For example,
    once [red red red brown] has been found, the next guess will be:

        [brown red red red]

    Then:

        [red brown red red]

    etc.

    The computer stops guessing when it either wins or runs out of guesses.

------------------------------------------------------------------------------
