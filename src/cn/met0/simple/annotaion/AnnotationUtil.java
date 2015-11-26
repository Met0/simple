package cn.met0.simple.annotaion;

import java.lang.reflect.Method;

/**
 * 被注解类的工具类
 * @author Met0
 *
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class AnnotationUtil {

	/**
	 * 获取Method注解的对象
	 * 
	 * @param method
	 *            Method对象
	 * @param AnnoName
	 *            获取的注解类
	 * @return
	 * @throws SecurityException
	 */
	public static Object getMethodAnnotation(Method method, Class AnnoName)
			throws SecurityException {
		return method.getAnnotation(AnnoName);
	}

	/**
	 * 获取Field 注解的对象
	 * 
	 * @param field
	 *            Field 对象
	 * @param AnnoName
	 *            获取的注解类
	 * @return
	 * @throws SecurityException
	 */
	public static Object getFliedAnnotationValue(Method field, Class AnnoName)
			throws SecurityException {
		return field.getAnnotation(AnnoName);

	}

	/**
	 * 获取Class 注解的对象
	 * @param cls Class
	 * @param AnnoName 注解类
	 * @return
	 */
	public static Object getClassAnnotation(Class cls,Class AnnoName){
		return cls.getAnnotation(AnnoName);
	}

}
