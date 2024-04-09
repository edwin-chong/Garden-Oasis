package com.project.GardenOasis;

import java.util.ArrayList;
import java.util.HashMap;

/**All quests data*/
public class QuestDataManager extends QuestGeneralDescription
{
    private static ArrayList<String> PlantedPlantsForQuest = new ArrayList<>();
    private static boolean[] firstOccurrenceOfQuest = {false, false, false};
    private static int currentNumberOfQuest = 0;
    private static int[] questReward = {20, 60, 100};

    /*dictionaries with name of plant and number of those plants required to get quest points*/
    private static HashMap <String, Integer> firstQuestRequirements = new HashMap<String, Integer>(){
        {put(DropDownListResources.Mushroom.name(), 5); put(DropDownListResources.Wisteria.name(), 5);}};
    private static HashMap <String, Integer> secondQuestRequirements = new HashMap<String, Integer>(){
        {put(DropDownListResources.Azalea.name(), 7); put(DropDownListResources.Rose.name(), 4);}};
    private static HashMap <String, Integer> thirdQuestRequirements = new HashMap<String, Integer>(){
        {put(DropDownListResources.Corn.name(), 10); put(DropDownListResources.Christmas_Tree.name(), 10);}};


    public static void addToPlantedPlantsForQuest(String name)
    {
        PlantedPlantsForQuest.add(name);
    }

    public static void clearListOfPlantedPlantsForQuest()
    {
        PlantedPlantsForQuest = new ArrayList<>();
    }

    public static ArrayList<String> getPlantedPlantsForQuest()
    {
        return PlantedPlantsForQuest;
    }

    public static void changeOccurrenceOfQuest(int numberOfQuest)
    {
        firstOccurrenceOfQuest[numberOfQuest] = true;
        QuestDataManager.currentNumberOfQuest += 1;
    }

    public static int getCurrentNumberOfQuest()
    {
        return currentNumberOfQuest;
    }

    public static boolean[] getOccurrenceOfQuest()
    {
        return firstOccurrenceOfQuest;
    }

    public static int getQuestReward(int number)
    {
        return questReward[number];
    }

    public static HashMap <String, Integer> getQuestRequirements(int questNumber)
    {
        switch (questNumber)
        {
            case 0:
                return firstQuestRequirements;
            case 1:
                return secondQuestRequirements;
            case 2:
                return thirdQuestRequirements;
        }
        return null;
    }

    public static String getQuestDescription()
    {
        switch (currentNumberOfQuest)
        {
            case 0:
                return firstQuest();
            case 1:
                return secondQuest();
            case 2:
                return thirdQuest();
        }
        return "";
    }
}
