package main.jobing.mynewjobing.specialty;

import android.graphics.Bitmap;

public class MySpecialty {
    public int iIndex;
    public String specialty_name;

    public MySpecialty()
    {

    }
    public MySpecialty(int iIndex, String specialty_name)
    {
        this.iIndex = iIndex;
        this.specialty_name = specialty_name;
    }
    public Integer getId() {
        return iIndex;
    }
}
