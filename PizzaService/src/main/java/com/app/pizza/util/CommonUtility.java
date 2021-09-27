package com.app.pizza.util;
import java.util.List;

public class CommonUtility {

    public static float totalSumOfList(List<Float> m)
    {
        float sum = 0;
        for(int i = 0; i < m.size(); i++)
        {
            sum += m.get(i);
        }
        return sum;
    }
}
