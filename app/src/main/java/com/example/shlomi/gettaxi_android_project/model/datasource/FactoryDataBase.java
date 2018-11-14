package com.example.shlomi.gettaxi_android_project.model.datasource;

public class FactoryDataBase {
    static IDataBase idb = null;
    public static IDataBase getDataBase()
    {
        //if(idb==null)

        return  idb;
    }
}
