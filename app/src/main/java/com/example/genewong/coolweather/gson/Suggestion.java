package com.example.genewong.coolweather.gson;

import com.google.gson.annotations.SerializedName;

public class Suggestion {

    public Sport sport;

    @SerializedName("comf")
    public Comfort comfort;

    @SerializedName("cw")
    public CarWash carWash;


    public class Sport {
        @SerializedName("txt")
        public String info;
    }

    public class Comfort {
        @SerializedName("txt")
        public String info;
    }

    public class CarWash {
        @SerializedName("txt")
        public String info;
    }

}
