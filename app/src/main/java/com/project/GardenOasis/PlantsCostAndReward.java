package com.project.GardenOasis;

import java.util.HashMap;

/**Final cost and reward for planting and collecting each plant*/
public class PlantsCostAndReward
{
    public final static int[] costOfPlants = new int [] {2, 3, 4, 5, 6, 7};
    public final static int[] rewardOfPlants = new int [] {3, 4, 6, 7, 9, 10};
    private static HashMap<Integer, GrownPlantsContainer> growingPlantsContainerWithIndexDict;
    private static HashMap<String, Integer> costOfEachPlant;
    private static HashMap<String, Integer> rewardForEachPlant;

    public static void initializePlantsIndexCostAndReward()
    {
        setIndexToPlants();
        setCostToEachPlant();
        setRewardForEachPlant();
    }

    private static void setIndexToPlants()
    {
        growingPlantsContainerWithIndexDict = new HashMap<>();
        growingPlantsContainerWithIndexDict.put(0, GrownPlantsContainer.Mushroom);
        growingPlantsContainerWithIndexDict.put(1, GrownPlantsContainer.Wisteria);
        growingPlantsContainerWithIndexDict.put(2, GrownPlantsContainer.Azalea);
        growingPlantsContainerWithIndexDict.put(3, GrownPlantsContainer.Rose);
        growingPlantsContainerWithIndexDict.put(4, GrownPlantsContainer.Corn);
        growingPlantsContainerWithIndexDict.put(5, GrownPlantsContainer.Christmas_Tree);
    }

    private static void setRewardForEachPlant()
    {
        rewardForEachPlant = new HashMap<>();
        rewardForEachPlant.put(DropDownListResources.Mushroom.name(), PlantsCostAndReward.rewardOfPlants[0]);
        rewardForEachPlant.put(DropDownListResources.Wisteria.name(), PlantsCostAndReward.rewardOfPlants[1]);
        rewardForEachPlant.put(DropDownListResources.Azalea.name(), PlantsCostAndReward.rewardOfPlants[2]);
        rewardForEachPlant.put(DropDownListResources.Rose.name(), PlantsCostAndReward.rewardOfPlants[3]);
        rewardForEachPlant.put(DropDownListResources.Corn.name(), PlantsCostAndReward.rewardOfPlants[4]);
        rewardForEachPlant.put(DropDownListResources.Christmas_Tree.name(), PlantsCostAndReward.rewardOfPlants[5]);
    }

    private static void setCostToEachPlant()
    {
        costOfEachPlant = new HashMap<>();
        costOfEachPlant.put(DropDownListResources.Mushroom.name(), PlantsCostAndReward.costOfPlants[0]);
        costOfEachPlant.put(DropDownListResources.Wisteria.name(), PlantsCostAndReward.costOfPlants[1]);
        costOfEachPlant.put(DropDownListResources.Azalea.name(), PlantsCostAndReward.costOfPlants[2]);
        costOfEachPlant.put(DropDownListResources.Rose.name(), PlantsCostAndReward.costOfPlants[3]);
        costOfEachPlant.put(DropDownListResources.Corn.name(), PlantsCostAndReward.costOfPlants[4]);
        costOfEachPlant.put(DropDownListResources.Christmas_Tree.name(), PlantsCostAndReward.costOfPlants[5]);
    }

    public static HashMap<Integer, GrownPlantsContainer> getPlantsIndex()
    {
        return growingPlantsContainerWithIndexDict;
    }

    public static HashMap<String, Integer> getCostOfEachPlant()
    {
        return costOfEachPlant;
    }

    public static HashMap<String, Integer> getRewardForEachPlant()
    {
        return rewardForEachPlant;
    }

}
