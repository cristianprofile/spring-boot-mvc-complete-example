package com.mylab.cromero.service.powermock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by cristianromeromatesanz on 16/09/16.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(FinalClass.class)
public class StaticClassTest {

    @Mock
    private FinalClass finalClass;

    @InjectMocks
    private ClassWithFinalAutowired claseConStatic;

    @Test
    public void testInjectFinalClass() {

        final String request = "hello";
        final String response = "goodBye";

        when(finalClass.getLocalHostname(request)).thenReturn(response);
        String responseMethod = claseConStatic.method1(request);

        System.out.println("response = " + responseMethod);

        assertEquals(responseMethod, response);

    }


}
