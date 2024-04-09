package com.project.GardenOasis;

/**Current plant number and total number of seeds*/
public class SeedsAndPlantNumber
{
    private static int currentPlant = 0;
    private static int numberOfCoins = 50;
    private static int numberOfCoinsBeforeQuestCompletion = numberOfCoins;

    public static void setCurrentPlant(int value)
    {
        currentPlant = value;
    }

    public static void setNumberOfCoins(int value)
    {
        numberOfCoins = value;
    }

    public static void setNumberOfCoinsBeforeQuestCompletion(int value) { numberOfCoinsBeforeQuestCompletion = value; }

    public static int getCurrentPlant()
    {
        return currentPlant;
    }

    public static int getNumberOfCoins()
    {
        return numberOfCoins;
    }

    public static int getNumberOfCoinsBeforeQuestCompletion() { return numberOfCoinsBeforeQuestCompletion; }

}
