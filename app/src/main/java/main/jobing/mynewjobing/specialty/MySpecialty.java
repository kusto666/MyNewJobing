package main.jobing.mynewjobing.specialty;

import android.graphics.Bitmap;

public class MySpecialty {
    public Long lIndex;
    public String specialty_name;

    public MySpecialty()
    {

    }
    public MySpecialty(Long iIndex, String specialty_name)
    {
        this.lIndex = iIndex;
        this.specialty_name = specialty_name;
    }
    public Long getId() {
        return lIndex;
    }
}
