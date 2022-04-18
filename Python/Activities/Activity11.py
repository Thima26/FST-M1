fruit_shop = {
    "apple": 10,
    "banana": 15,
    "orange": 8,
    "peaches": 15
}

check = input("What are you looking for? ").lower()

if(check in fruit_shop):
    print("Yes, this is available")
else:
    print("No, this is not available")