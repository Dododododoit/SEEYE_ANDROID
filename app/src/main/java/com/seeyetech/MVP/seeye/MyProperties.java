package com.seeyetech.MVP.seeye;

/**
 * Created by kaitao on 1/25/19.
 */

public class MyProperties {
    public boolean isPinSet;
    public float pinX;
    public float pinY;

    private static MyProperties mInstance= null;

    public int someValueIWantToKeep;

    protected MyProperties(){
        isPinSet = false;
    }

    public static synchronized MyProperties getInstance() {
        if(null == mInstance){
            mInstance = new MyProperties();
        }
        return mInstance;
    }
}
