package eu.nyerel.appmonitor.instrumentation;

import javassist.*;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Rastislav Papp (rastislav.papp@gmail.com)
 */
public class MonitorClassFileTransformer implements ClassFileTransformer {

	public static final List<String> TRANSFORM_PACKAGES = new ArrayList<String>() {{
		add("eu.nyerel.appmonitor.web");
	}};

	private static final Set<ClassLoader> REGISTERED_CLASS_LOADERS = new HashSet<ClassLoader>();
	private static final ClassPool CLASS_POOL = ClassPool.getDefault();

	@Override
	public byte[] transform(ClassLoader loader,
							String className,
							Class<?> classBeingRedefined,
							ProtectionDomain protectionDomain,
							byte[] classfileBuffer) throws IllegalClassFormatException {
		className = canonizeClassName(className);
		if (shouldTransform(className)) {
			registerClassLoader(loader);
			return transformClass(className, classfileBuffer);
		} else {
			return classfileBuffer;
		}
	}

	private void registerClassLoader(ClassLoader loader) {
		if (!REGISTERED_CLASS_LOADERS.contains(loader)) {
			CLASS_POOL.insertClassPath(new LoaderClassPath(loader));
			REGISTERED_CLASS_LOADERS.add(loader);
		}
	}

	private String canonizeClassName(String className) {
		return className.replace("/", ".");
	}

	private byte[] transformClass(String className, byte[] classfileBuffer) {
		System.out.println("Transforming class: " + className);
		ClassPool classPool = ClassPool.getDefault();
		try {
			CtClass ctClass = classPool.get(className);
			for (CtMethod method : ctClass.getDeclaredMethods()) {
				if (!method.isEmpty()) {
					System.out.println("Transforming method: " + method.getLongName());
					new MonitoredMethodTransformer(ctClass, method).transform();
				}
			}
			return ctClass.toBytecode();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return classfileBuffer;
	}

	private boolean shouldTransform(String className) {
		for (String packageName: TRANSFORM_PACKAGES) {
			if (isFromPackage(className, packageName)) {
				return true;
			}
		}
		return false;
	}

	private boolean isFromPackage(String className, String packageName) {
		return className.startsWith(packageName);
	}

}