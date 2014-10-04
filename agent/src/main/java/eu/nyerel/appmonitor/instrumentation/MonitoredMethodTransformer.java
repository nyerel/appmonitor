package eu.nyerel.appmonitor.instrumentation;

import eu.nyerel.appmonitor.model.Method;
import eu.nyerel.appmonitor.monitoring.CallStackHolder;
import javassist.*;

/**
 * @author Rastislav Papp (rastislav.papp@gmail.com)
 */
public class MonitoredMethodTransformer {

	private final CtClass ctClass;
	private final CtMethod method;

	public MonitoredMethodTransformer(CtClass ctClass, CtMethod method) {
		this.ctClass = ctClass;
		this.method = method;
	}

	public void transform() throws NotFoundException, CannotCompileException {
		System.out.println("1");
		ctClass.addMethod(getCopy());
		System.out.println("2");
		MonitoredMethodBodyBuilder monitoredMethodBodyBuilder = new MonitoredMethodBodyBuilder();
		System.out.println("3");
		String body = monitoredMethodBodyBuilder.build();
		System.out.println("new body = \n" + body);
		method.setBody(body);
	}

	private boolean returnsVoid() throws NotFoundException {
		return method.getReturnType().equals(CtClass.voidType);
	}

	private String copyName() {
		return method.getName() + "__$AMonClone";
	}

	private CtMethod getCopy() throws CannotCompileException {
		return CtNewMethod.copy(method, copyName(), ctClass, null);
	}

	private static final String GET_CALL_STACK = CallStackHolder.class.getName() + ".getCallStack()";

	private class MonitoredMethodBodyBuilder {

		private StringBuilder body;

		public String build() throws NotFoundException {
			System.out.println("building body");
			body = new StringBuilder();
			System.out.println("1" + body);
			callEnterMethod();
			System.out.println("2" + body);
			openTry();
			System.out.println("3" + body);
			callInternalMethod();
			System.out.println("4" + body);
			closeTryAndOpenFinally();
			System.out.println("5" + body);
			callExitMethod();
			System.out.println("6" + body);
			closeFinally();
			System.out.println("7" + body);
			return body.toString();
		}

		private void closeFinally() {
			body.append("}");
		}

		private void closeTryAndOpenFinally() {
			body.append("} finally {\n");
		}

		private void callInternalMethod() throws NotFoundException {
			if (!returnsVoid()) {
				body.append("return ");
			}
			body.append(copyName()).append("($$);\n");
		}

		private void openTry() {
			body.append("try {\n");
		}

		private StringBuilder callEnterMethod() {
			return body.append(GET_CALL_STACK).append(".enterMethod(")
					.append("new ").append(Method.class.getName()).append("(")
						.append("$class, \"").append(method.getName()).append("\"));\n");
		}

		private void callExitMethod() {
			body.append(GET_CALL_STACK).append(".exitMethod(System.currentTimeMillis() - __$AMonStart);\n");
		}

	}

}
