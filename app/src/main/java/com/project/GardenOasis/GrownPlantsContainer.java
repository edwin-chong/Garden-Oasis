package com.project.GardenOasis;

/**Holder of growing plants resources*/
public enum GrownPlantsContainer {
    //flower used is based on the image of the seed bag, all flowers can be found on this directory
    // C:\Users\****\Garden-Oasis\app\src\main\res\drawable
    SEED(R.drawable.seed),
    Mushroom(R.drawable.grown_mushrooms_bg),
    Wisteria(R.drawable.iris),
    //red dahlia png added
    Azalea(R.drawable.red_dahlia),
    Rose(R.drawable.red_rose),
    Corn(R.drawable.grown_corn_bg),
    Christmas_Tree(R.drawable.christmastree);


    private final int pictureNumber;

    GrownPlantsContainer(int pictureNumber) {
        this.pictureNumber = pictureNumber;
    }

    public int getValue() {
        return pictureNumber;
    }
}
