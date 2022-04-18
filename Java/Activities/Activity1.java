package activities;

public class Activity1 {

    public static void main(String args[])
    {
        Car benz = new Car();

        benz.color = "Silver";
        benz.transmission = "Automatic";
        benz.make = 2022;

        benz.displayCharacteristics();
        benz.accelerate();
        benz.brake();

    }
}
