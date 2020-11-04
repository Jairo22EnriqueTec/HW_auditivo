package org.helloworld.auditivo;



import android.content.Context;

import org.junit.Test;
import org.junit.Assert.*;
import androidx.test.platform.app.InstrumentationRegistry;

import static org.junit.Assert.assertEquals;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ExampleInstumentedTest {
@Test
	public void AppContext(){
	Context appcontext= InstrumentationRegistry.getInstrumentation().getTargetContext();
	assertEquals("org.helloworld.auditivo", appcontext.getPackageName());
}

}