package eu.nyerel.appmonitor;

import eu.nyerel.appmonitor.instrumentation.MonitorClassFileTransformer;

import java.lang.instrument.Instrumentation;

/**
 * @author Rastislav Papp (rastislav.papp@gmail.com)
 */
public class Agent {

	public static void premain(String args, Instrumentation inst) {
		System.out.println("pre-main method called");
		inst.addTransformer(new MonitorClassFileTransformer());
	}

}
