package com.soofound.framework.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface PersistableAnnotation
{
  boolean autoIncrement() default false;
  
  boolean join() default false;
  
  boolean primaryKey() default false;
  
  String associate();
}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.framework.annotation.PersistableAnnotation
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */