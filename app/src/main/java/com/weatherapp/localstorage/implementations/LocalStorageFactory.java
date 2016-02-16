package com.weatherapp.localstorage.implementations;

import android.content.Context;

import com.weatherapp.localstorage.interfaces.LocalStorageType;
import com.weatherapp.localstorage.interfaces.LocalStorageInterface;

import java.util.HashMap;

/**
 * Created by shahbaz on 2/16/2016.
 */
public class LocalStorageFactory {

    private static HashMap<LocalStorageType, LocalStorageInterface> storageMap = new HashMap();

    public static LocalStorageInterface getLocalStorageManager(Context context, LocalStorageType type)
    {
        initStorage(context);
        return storageMap.get(type);
    }

    private static void initStorage(Context context)
    {
        if(storageMap.size() == 0)
        {
            storageMap.put(LocalStorageType.SHARED_PREFRENCES, new SharedPreferenceStorage(context));
            storageMap.put(LocalStorageType.FILE, new FileStorage(context));
            storageMap.put(LocalStorageType.DATABAE, new DatabaseStorage(context));
        }
    }
}
