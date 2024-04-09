package com.project.GardenOasis;

/**Holder of dropdown list of plants*/
public enum DropDownListResources
{
    Mushroom(R.drawable.seed1),
    Wisteria(R.drawable.seed2),
    Azalea(R.drawable.seed3),
    Rose(R.drawable.seed4),
    Corn(R.drawable.seed5),
    Christmas_Tree(R.drawable.seed6);

    private int pictureNumber;

    DropDownListResources(int pictureNumber)
    {
        this.pictureNumber = pictureNumber;
    }

    public int getValue()
    {
        return pictureNumber;
    }
}
