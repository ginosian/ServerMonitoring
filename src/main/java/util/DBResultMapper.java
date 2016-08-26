package util;

import interfaces.DBColumn;

import java.beans.Transient;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marta.ginosyan on 8/26/2016.
 */
public class DBResultMapper <T> {

    @SuppressWarnings("unchecked")
    private static DBResultMapper thisInstance;

    @SuppressWarnings("unchecked")
    public static <T> DBResultMapper<T> instance() {
        if(thisInstance == null) {
            synchronized (DBResultMapper.class) {
                if(thisInstance == null)
                    thisInstance = new DBResultMapper<T>();
            }
        }
        return thisInstance;
    }

    private DBResultMapper() { }

    public T toObject(ResultSet rs, Class<T> toClazz) {
        T obj = null;
        Field[] fields = toClazz.getDeclaredFields();
        try {
            if(rs.next() && fields != null && fields.length > 0) {
                obj = toClazz.newInstance();
                populateFields(rs, obj, fields);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return obj;
    }

    public List<T> toList(ResultSet rs, Class<T> toClazz) {
        List<T> listObj = new ArrayList<T>();
        T obj = null;
        Field[] fields = toClazz.getDeclaredFields();
        if(fields == null || fields.length == 0) return listObj;
        try {
            while(rs.next()) {
                obj = toClazz.newInstance();
                populateFields(rs, obj, fields);
                listObj.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listObj;
    }


    private void populateFields(ResultSet rs, T obj, Field[] fields)
            throws IllegalAccessException, SQLException {
        String fieldName = null;
        for(Field f : fields) {
            if("serialVersionUID".equals(f.getName())) continue;
            f.setAccessible(true);
            fieldName = f.getName();
            Class fieldType = f.getType();
            Annotation[] annotations = f.getDeclaredAnnotations();
            if(annotations != null && annotations.length > 0) {
                if(annotations[0].annotationType() == DBColumn.class) {
                    DBColumn colAnn = (DBColumn) annotations[0];
                    fieldName = colAnn.name();
                    if(colAnn.type() != Object.class)
                        fieldType = colAnn.type();
                } else continue;
            }
            if(fieldType == String.class)
                f.set(obj, rs.getString(fieldName));

            if(fieldType == Integer.class)
                f.set(obj, rs.getInt(fieldName));

            if(fieldType == Boolean.class)
                f.set(obj, rs.getBoolean(fieldName));
        }
    }

}
