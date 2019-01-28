package com.seeyetech.MVP.seeye;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kaitao on 1/25/19.
 */

public class Constants {

    public static final Map<String,Integer> typeMap = new HashMap<String, Integer>()
    {
        {
            put("chair", R.drawable.chair);
            put("bed", R.drawable.beds);
            put("table", R.drawable.tables);
            put("sofa", R.drawable.sofa);
            put("carbinet", R.drawable.cabinet);
            put("others", R.drawable.sofa);
            put("saved", R.drawable.pin);
        }
    };
}
