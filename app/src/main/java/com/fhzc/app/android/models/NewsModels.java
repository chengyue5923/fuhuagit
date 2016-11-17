package com.fhzc.app.android.models;

import java.io.Serializable;
import java.util.List;


/**
 * Created by fw on 16/10/13.
 */

public class NewsModels implements Serializable {
    List<NewsModel> items;
    public List<NewsModel>getItems(){
        return items;
    }

}
