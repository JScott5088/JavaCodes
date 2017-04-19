package com.example.crystalball;

import java.util.Random;

public class CrystalBall
{

    public String mAnswers[] = {
        "It is certain", "It is decidely so", "All signs say YES", "The stars are not aligned", "My reply is no", "It is doubtful", "Better not tell you now", "Concentrate and ask again", "Unable to answer now", "It is hard to say"
    };

    public CrystalBall()
    {
    }

    public String getAnAnswer()
    {
        int i = (new Random()).nextInt(mAnswers.length);
        return mAnswers[i];
    }
}
