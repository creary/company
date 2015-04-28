/*  1:   */package com.soofound.framework.annotation;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.Persistable;
/*  4:   */import java.util.ArrayList;
/*  5:   */import java.util.HashMap;
/*  6:   */import java.util.Iterator;
/*  7:   */import java.util.List;
/*  8:   */import java.util.Map;
/*  9:   */
/* 10:   */public final class PersistableAttributeFactory
/* 11:   */{
/* 12:12 */  private static PersistableAttributeFactory factory = new PersistableAttributeFactory();
/* 13:   */  private final Map<Class, PersistableAttribute> persistAttributes;
/* 14:   */  
/* 15:   */  private PersistableAttributeFactory() {
/* 16:16 */    this.persistAttributes = new HashMap();
/* 17:   */  }
/* 18:   */  
/* 23:   */  public synchronized PersistableAttribute getPersistableAttribute(Class<? extends Persistable> clazz)
/* 24:   */  {
/* 25:25 */    if (this.persistAttributes.containsKey(clazz)) {
/* 26:26 */      return (PersistableAttribute)this.persistAttributes.get(clazz);
/* 27:   */    }
/* 28:28 */    PersistableAttribute attribute = fetchPersistAttributes(clazz);
/* 29:29 */    this.persistAttributes.put(clazz, attribute);
/* 30:   */    
/* 31:31 */    return attribute;
/* 32:   */  }
/* 33:   */  
/* 34:   */  public synchronized FieldAttribute getFieldAttribute(Class<? extends Persistable> clazz, String attributeName) {
/* 35:35 */    PersistableAttribute pa = getPersistableAttribute(clazz);
/* 36:36 */    for (FieldAttribute fa : pa.getFieldAttributes()) {
/* 37:37 */      if (fa.getName().equals(attributeName))
/* 38:38 */        return fa;
/* 39:   */    }
/* 40:40 */    return null;
/* 41:   */  }
/* 42:   */  
/* 47:   */  private PersistableAttribute fetchPersistAttributes(Class<? extends Persistable> clazz)
/* 48:   */  {
/* 49:49 */    if ((clazz == null) || (!clazz.isAnnotationPresent(PersistableAnnotation.class))) {
/* 50:50 */      throw new IllegalArgumentException(clazz.getName() + "不是有效的持久化注释类型");
/* 51:   */    }
/* 52:52 */    PersistableAttribute attr = new PersistableAttribute();
/* 53:53 */    PersistableAnnotation _tann = (PersistableAnnotation)clazz.getAnnotation(PersistableAnnotation.class);
/* 54:54 */    attr.setTableName(_tann.associate());
/* 55:   */    
/* 56:56 */    List<Class> clazzs = new ArrayList();
/* 57:57 */    Class _clazz = clazz;
/* 58:   */    for (;;) {
/* 59:59 */      clazzs.add(_clazz);
/* 60:60 */      if (_clazz.getSuperclass() == Object.class)
/* 61:   */        break;
/* 62:62 */      _clazz = _clazz.getSuperclass(); }
/* 63:   */    int j;
/* 64:64 */    int i; for (Iterator localIterator = clazzs.iterator(); localIterator.hasNext(); 
/* 65:   */        
/* 66:66 */        i < j)
/* 67:   */    {
/* 68:64 */      Class clz = (Class)localIterator.next();
/* 69:65 */      java.lang.reflect.Field[] fields = clz.getDeclaredFields();
/* 70:66 */      java.lang.reflect.Field[] arrayOfField1; j = (arrayOfField1 = fields).length;i = 0;continue;java.lang.reflect.Field field = arrayOfField1[i];
/* 71:67 */      if (field.isAnnotationPresent(PersistableAnnotation.class))
/* 72:   */      {
/* 74:70 */        PersistableAnnotation ann = (PersistableAnnotation)field.getAnnotation(PersistableAnnotation.class);
/* 75:71 */        if (ann != null)
/* 76:   */        {
/* 77:73 */          FieldAttribute fa = new FieldAttribute();
/* 78:74 */          fa.setAttribute(field.getName());
/* 79:75 */          fa.setName(ann.associate());
/* 80:76 */          fa.setPrimaryKey(ann.primaryKey());
/* 81:77 */          fa.setAutoIncrement(ann.autoIncrement());
/* 82:78 */          fa.setJoin(ann.join());
/* 83:79 */          attr.add(fa);
/* 84:   */        }
/* 85:   */      }
/* 86:66 */      i++;
/* 87:   */    }
/* 88:   */    
/* 102:82 */    return attr;
/* 103:   */  }
/* 104:   */  
/* 105:   */  public static PersistableAttributeFactory getFactory() {
/* 106:86 */    return factory;
/* 107:   */  }
/* 108:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.framework.annotation.PersistableAttributeFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */