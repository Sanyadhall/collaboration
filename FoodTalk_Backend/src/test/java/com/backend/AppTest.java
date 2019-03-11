package com.backend;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.backend.config.DBConfig;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @BeforeClass
    public static void init(){
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
        context.register(DBConfig.class);
        context.refresh();
    }
 
    
    @Test
    @Ignore
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
}
