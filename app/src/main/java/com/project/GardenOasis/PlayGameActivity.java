package com.project.GardenOasis;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.HashSet;

/**Contains methods connected with game screen and methods connected with buttons, which are placed on the play scene*/
public class PlayGameActivity extends AppCompatActivity
{
    private static TextView scoreTextView;
    private static TextView plantQuestTextView;
    private static Boolean isTextAnimating = false;
    private static float distanceFromButton = 330f;
    private PlantManager[] plantManager;
    private ViewsHolder viewsHolder;
    private int[] dropDownListImages;
    private String[] dropDownListText;
    private HashSet<Integer> alreadyClickedPlantingButtons;
    private HashSet<PlantManager> plantManagersInUse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        /*checks if a MenuInflater object exists in memory, used to delete double bar icons*/
        getMenuInflater();

        // Permanently leaves screen on
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        PlantsCostAndReward.initializePlantsIndexCostAndReward();
        initializePlantsParams();
        playMainSceneMusic();
        findNecessaryViews();
        setScoreText();
        initializeDropDownListParams();
        showQuestMessage("");
    }

    private void initializePlantsParams()
    {
        plantManager = new PlantManager[15];
        alreadyClickedPlantingButtons = new HashSet<Integer>();
        plantManagersInUse = new HashSet<PlantManager>();
    }

    private void playMainSceneMusic()
    {
        MusicManager gameMusicManager = new MusicManager(this, R.raw.plantasia);
        gameMusicManager.run();
    }

    private void findNecessaryViews()
    {
        viewsHolder = new ViewsHolder(findViewById(R.id.constraintLayout),
            findViewById(R.id.plantInfoTextView), findViewById(R.id.listView));
        scoreTextView = findViewById(R.id.scoreText);
        plantQuestTextView = findViewById(R.id.plantQuestTextView);
    }

    public static void setScoreText()
    {
        scoreTextView.setText(Integer.toString(SeedsAndPlantNumber.getNumberOfCoins()));
    }

    private void initializeDropDownListParams()
    {
        dropDownListText = new String[] {DropDownListResources.Mushroom.name(), DropDownListResources.Wisteria.name(), DropDownListResources.Azalea.name(),
                DropDownListResources.Rose.name(), DropDownListResources.Corn.name(), DropDownListResources.Christmas_Tree.name()};

        dropDownListImages = new int[] {DropDownListResources.Mushroom.getValue(), DropDownListResources.Wisteria.getValue(), DropDownListResources.Azalea.getValue(),
                DropDownListResources.Rose.getValue(), DropDownListResources.Corn.getValue(), DropDownListResources.Christmas_Tree.getValue()};
    }

    public void checkPositionAndChangeImage (Button button, int buttonIndex)
    {
        if (!alreadyClickedPlantingButtons.contains(buttonIndex))
        {
            plantManager[buttonIndex] = new PlantManager(button);
            plantManagersInUse.add(plantManager[buttonIndex]);
        }
        else
        {
            try
            {
                plantManager[buttonIndex].collectOrSetUpPlant(button, GrownPlantsContainer.SEED.getValue());
            }
           catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        alreadyClickedPlantingButtons.add(buttonIndex);
    }

    /**Sets current plant and shows name of the chosen plant
     *@param view current view*/
    public void onBottomButtonWithPlantsClick(View view)
    {
        switch (view.getTag().toString())
        {
            case "seed1":
                SeedsAndPlantNumber.setCurrentPlant(0);
                viewsHolder.plantInfoTextView.setText(DropDownListResources.Mushroom.name());
                break;
            case "seed2":
                SeedsAndPlantNumber.setCurrentPlant(1);
                viewsHolder.plantInfoTextView.setText(DropDownListResources.Wisteria.name());
                break;
            case "seed3":
                SeedsAndPlantNumber.setCurrentPlant(2);
                viewsHolder.plantInfoTextView.setText(DropDownListResources.Azalea.name());
                break;
            case "seed4":
                SeedsAndPlantNumber.setCurrentPlant(3);
                viewsHolder.plantInfoTextView.setText(DropDownListResources.Rose.name());
                break;
            case "seed5":
                SeedsAndPlantNumber.setCurrentPlant(4);
                viewsHolder.plantInfoTextView.setText(DropDownListResources.Corn.name());
                break;
            case "seed6":
                SeedsAndPlantNumber.setCurrentPlant(5);
                viewsHolder.plantInfoTextView.setText(DropDownListResources.Christmas_Tree.name());
                break;
        }
    }

    /**Initializes CustomAdapter class
     * @param view current view*/
    public void onClickButtonDropDownBoxList (View view)
    {
        CustomAdapter adapter = new CustomAdapter(this, dropDownListText, dropDownListImages, viewsHolder.parentView,
                viewsHolder.listView, plantQuestTextView, viewsHolder.plantInfoTextView);
        viewsHolder.listView.setAdapter(adapter);
    }

    public void showQuestDescription(View view)
    {
        String questDescription = QuestDataManager.getQuestDescription();
        if (plantedPlantsForQuestsAreNotNull())
        {
            Toast toast = Toast.makeText(getApplicationContext(), questDescription + TextCurrentNumberOfQuestPlantedPlants(), Toast.LENGTH_LONG);
            toast.show();
        }
        else
        {
            Toast toast = Toast.makeText(getApplicationContext(), questDescription, Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private boolean plantedPlantsForQuestsAreNotNull()
    {
        return QuestCompletionChecker.getKeys()[0] != "" || QuestCompletionChecker.getKeys()[1] != "";
    }

    private String TextCurrentNumberOfQuestPlantedPlants()
    {
        return "\nCurrent number of planted plants:\n" +
                       QuestCompletionChecker.getKeys()[0] + ": " + QuestCompletionChecker.getValues()[0] + " " +
                        QuestCompletionChecker.getKeys()[1] + ": " + QuestCompletionChecker.getValues()[1];
    }

    public static void showQuestMessage(String beforeQuestDescriptionText)
    {
        plantQuestTextView.setVisibility(View.VISIBLE);
        String wholeText = beforeQuestDescriptionText + QuestDataManager.getQuestDescription();
        plantQuestTextView.setText(wholeText);
    }

    public void buttonContainerOnClick(View view)
    {
        int buttonIndex = Integer.parseInt((String) view.getTag());
        int buttonId = view.getId();
        Button button = findViewById(buttonId);
        checkPositionAndChangeImage(button, buttonIndex);
    }

    public void buttonResetQuest (View view)
    {
        int buttonId = view.getId();
        Button button = findViewById(buttonId);
        for (PlantManager currentPlantManager : plantManagersInUse)
        {
            currentPlantManager.clearCurrentPlant(button);
        }
        QuestDataManager.clearListOfPlantedPlantsForQuest();
        SeedsAndPlantNumber.setNumberOfCoins(SeedsAndPlantNumber.getNumberOfCoinsBeforeQuestCompletion());
        QuestCompletionChecker.clearValues();
        setScoreText();
    }
}
