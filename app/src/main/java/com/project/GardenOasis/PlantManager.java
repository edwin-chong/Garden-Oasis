package com.project.GardenOasis;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

/**Manages growing plant process*/
public class PlantManager extends SeedsAndPlantNumber implements GrowingUpParams
{
    private String plantedPlant;
    private boolean isReadyToBeCollected = false;
    private Button currentButton;
    private Boolean plantIsRising = false;
    private int initialSeedsNumber;

    PlantManager(Button button)
    {
        collectOrSetUpPlant(button, GrownPlantsContainer.SEED.getValue());
        countDownToPlantSeedling(button);
    }

    /**Counts down to the moment when plant is ready to be collected and when it is sets proper button image
     * @param button final growing place for planted plant
     */
    @Override
    public void countDownToPlantSeedling(final Button button)
    {
        plantedPlant = PlantsCostAndReward.getPlantsIndex().get(getCurrentPlant()).name();

        if (numberOfSeedsAboveZero())
        {
            plantIsRising = true;
            button.setEnabled(false);
            isReadyToBeCollected = false;

            new CountDownTimer(timeOfGrowingSeedling, growingTimeInterval)
            {
                int growingPlantResource = PlantsCostAndReward.getPlantsIndex().get(getCurrentPlant()).getValue();

                @Override
                public void onTick(long l) { }

                @Override
                public void onFinish()
                {
                    button.setBackgroundResource(growingPlantResource);

                    button.setEnabled(true);
                    currentButton = button;
                    isReadyToBeCollected = true;
                    plantIsRising = false;
                }
            }.start();
        }
    }

    private boolean numberOfSeedsAboveZero()
    {
        return initialSeedsNumber - PlantsCostAndReward.getCostOfEachPlant().get((plantedPlant)) >= 0;
    }

    /**If plant is ready to be collected this method sets button image as fully grown plant and adds proper number of points for collected plant.
     * In other case sets chosen image as brown, decreases number of seeds and starts count down.
     * It also checks if current quest is already completed, if indeed it is than this method adds additional seeds for completing the quest
     * and shows message which informs that current quest is completed.
     * @param button currently clicked button
     * @param resource current plant resource
     */
    @Override
    public void collectOrSetUpPlant(final Button button, int resource)
    {
        if (isReadyToBeCollected)
        {
            ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(currentButton, "rotation", 0f, 90f);
            rotateAnimator.setDuration(100);
            rotateAnimator.setInterpolator(new LinearInterpolator());

            ObjectAnimator rotateBackAnimator = ObjectAnimator.ofFloat(currentButton, "rotation", 90f, 0f);
            rotateBackAnimator.setDuration(0);
            rotateBackAnimator.setInterpolator(new LinearInterpolator());

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(rotateAnimator, rotateBackAnimator);
            animatorSet.start();

            new CountDownTimer(100, 1000) {

                public void onTick(long millisUntilFinished) {
                    // This method is called every second during the countdown
                    long secondsLeft = millisUntilFinished / 1000;
//                    Log.d("Timer", "Seconds left: " + secondsLeft);
                }
                public void onFinish() {
                    // This method is called when the countdown is finished
//                    Log.d("Timer", "Countdown finished!");
                    button.setBackgroundResource(R.color.brown);
                }
            }.start();

            isReadyToBeCollected = false;
            QuestDataManager.addToPlantedPlantsForQuest(plantedPlant);
            addNumberOfSeedsAndSetText();

            /*checking if quest is completed*/
            if (questIsCompleted())
            {
                int questReward = QuestDataManager.getQuestReward(QuestDataManager.getCurrentNumberOfQuest());
                addAdditionalSeeds(questReward);
                QuestDataManager.changeOccurrenceOfQuest(QuestDataManager.getCurrentNumberOfQuest());
                if (QuestDataManager.getCurrentNumberOfQuest() == 3) {
                    PlayGameActivity.showQuestMessage("Congratulations! You have completed quest! Press the square button on your phone to exit");
                } else {
                    PlayGameActivity.showQuestMessage("Congratulations! You have completed quest " + QuestDataManager.getCurrentNumberOfQuest() + ".\nNow ");
                }

                setNumberOfCoinsBeforeQuestCompletion(getNumberOfCoins());
            }
        }

        else
        {
            String nameOfCurrentPlant = PlantsCostAndReward.getPlantsIndex().get(getCurrentPlant()).name();
            int costOfCurrentPlant = PlantsCostAndReward.getCostOfEachPlant().get(nameOfCurrentPlant);
            if (getNumberOfCoins() - costOfCurrentPlant >= 0)
            {
                decreaseNumberOfSeedsAndSetText(nameOfCurrentPlant);
                button.setBackgroundResource(resource);
                countDownToPlantSeedling(button);
            }
        }
    }

    private boolean questIsCompleted()
    {
        return QuestCompletionChecker.checkIfQuestIsCompleted() && !QuestDataManager.getOccurrenceOfQuest()[QuestDataManager.getCurrentNumberOfQuest()];
    }

    @Override
    public void decreaseNumberOfSeedsAndSetText(String nameOfCurrentPlant)
    {
        int numberOfSeeds;
        numberOfSeeds = getNumberOfCoins();
        initialSeedsNumber = numberOfSeeds;
        numberOfSeeds -= PlantsCostAndReward.getCostOfEachPlant().get(nameOfCurrentPlant);
        setNumberOfCoins(numberOfSeeds);
        PlayGameActivity.setScoreText();
    }

    @Override
    public void addNumberOfSeedsAndSetText()
    {
        String nameOfCollectedPlant = plantedPlant;
        int numberOfSeeds;
        plantedPlant = "";
        numberOfSeeds = getNumberOfCoins();
        numberOfSeeds += PlantsCostAndReward.getRewardForEachPlant().get(nameOfCollectedPlant);
        setNumberOfCoins(numberOfSeeds);
        PlayGameActivity.setScoreText();
    }

    @Override
    public void addAdditionalSeeds(int number)
    {
        int wholeNumberOfSeeds;
        wholeNumberOfSeeds = getNumberOfCoins();
        wholeNumberOfSeeds += number;
        setNumberOfCoins(wholeNumberOfSeeds);
        PlayGameActivity.setScoreText();
    }

    public void clearCurrentPlant(final Button button)
    {
        if (currentButton != null && plantIsRising == false)
        {
            String backgroundColor = "#FF453313";
            currentButton.setBackgroundColor(Color.parseColor(backgroundColor));
            isReadyToBeCollected = false;
        }
    }
}
