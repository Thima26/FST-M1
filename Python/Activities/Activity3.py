user1 = input("Player 1's name:")
user2 = input("Player 2's name:")
	
user1_choice = input(user1 + ", do you want to choose rock, paper or scissors? ").lower()
user2_choice = input(user2 + ", do you want to choose rock, paper or scissors? ").lower()

if user1_choice == user2_choice:
    print("It's a tie!")
elif user1_choice == 'rock':
    if user2_choice == 'scissors':
        print("Rock wins!")
    else:
        print("Paper wins!")
elif user1_choice == 'scissors':
    if user2_choice == 'paper':
        print("Scissors win!")
    else:
        print("Rock wins!")
elif user1_choice == 'paper':
    if user2_choice == 'rock':
        print("Paper wins!")
    else:
        print("Scissors win!")
else:
    print("Invalid input! You have not entered rock, paper or scissors, try again.")