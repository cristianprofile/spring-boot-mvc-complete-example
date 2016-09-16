package com.mylab.cromero.service;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by cristianromeromatesanz on 16/09/16. (Test final class witn powermock)
 */
public class  ClassWithStaticAutowired implements IClassWithStaticAutowired {

    @Autowired
    private FinalClass finalClass;

    @Override
    public String method1(String parametro1) {

        String localHostname = finalClass.getLocalHostname(parametro1);

        return localHostname;
    }
}
