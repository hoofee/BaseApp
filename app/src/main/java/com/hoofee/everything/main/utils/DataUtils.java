package com.hoofee.everything.main.utils;

import com.hoofee.everything.main.anotation.FeildConnectAnnotation;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * user hufei
 * date 2016/3/25:16:00
 * describe:
 */
public class DataUtils {

    /**
     * 将一个List数据里面每一个对象的指定字段转化成一个List
     *
     * @param dataList  传入的List,List里面装的是Model对象
     * @param fieldName Model对象的字段名
     * @param <T>       大的List里的类型
     * @param <S>       目标集合里数据的类型
     * @return 目标集合
     */
    public static <T, S> ArrayList<S> getFieldList(List<T> dataList, String fieldName) {
        ArrayList<S> targetList = new ArrayList<>();
        for (T model : dataList) {
            try {
                Field field = model.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                targetList.add((S) field.get(model));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        if (targetList.size() > 0)
            return targetList;
        else
            return null;
    }

    /**
     * Model之间的复制，前提：注解标签要加在tagetClass的字段名上
     *
     * @param tagetClass 目标字节码文件
     * @param srcModel
     * @param <T>
     * @param <S>
     * @return
     */
    public static <T, S> T getTargetModel(Class<T> tagetClass, S srcModel) {
        if (srcModel == null) {
            return null;
        }

        List<String> srcFieldList = new ArrayList<>();
        for (Field field : srcModel.getClass().getDeclaredFields()) {
            srcFieldList.add(field.getName());
        }

        T targetModel;
        try {
            targetModel = tagetClass.newInstance();
            Field[] fields = tagetClass.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                FeildConnectAnnotation annotation = field.getAnnotation(FeildConnectAnnotation.class);
                if (annotation != null) {
                    String fieldName = annotation.value();
                    if (!StringUtils.isEmpty(fieldName) && srcModel.getClass().getDeclaredField(fieldName) != null) {
                        Field srcField = srcModel.getClass().getDeclaredField(fieldName);
                        if (!field.getType().equals(srcField.getType())) {
                            continue;
                        }
                        srcField.setAccessible(true);
                        Object srcFieldValue = srcField.get(srcModel);
                        if(srcFieldValue!=null){
                            field.set(targetModel, srcFieldValue);
                        }
                    }
                } else if (srcFieldList.contains(field.getName())) {
                    Field srcField = srcModel.getClass().getDeclaredField(field.getName());
                    if (!field.getType().equals(srcField.getType())) {
                        continue;
                    }
                    srcField.setAccessible(true);
                    field.set(targetModel, srcField.get(srcModel));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            targetModel = null;
        }
        return targetModel;
    }

    /**
     * 相同对象之间更新数据
     *
     * @param srcModel    目标、结果对象
     * @param updateModel 新数据对象
     * @param <T>
     */
    public static <T> void updateModel(T srcModel, T updateModel, String... ignoreField) {
        Class<T> clazz = (Class<T>) updateModel.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            field.setAccessible(true);
            try {
                Object fieldValue = field.get(updateModel);
                //过滤空对象和空字符串
                if (fieldValue == null || (fieldValue instanceof String && StringUtils.isEmpty((String) fieldValue))) {
                    continue;
                }
                //过滤忽视字段
                boolean hasIgnore = false;
                for (String ignore : ignoreField) {
                    hasIgnore = fieldName.equals(ignore);
                    if (hasIgnore) {
                        break;
                    }
                }
                if (hasIgnore) {
                    continue;
                }

                field.set(srcModel, fieldValue);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    /**
     * List之间的字段复制，前提：注解标签要加在tagetClass的字段名上
     *
     * @param tagetClass
     * @param srcModelList
     * @param <T>
     * @param <S>
     * @return
     */
    public static <T, S> List<T> getTargetModelList(Class<T> tagetClass, List<S> srcModelList) {
        if (srcModelList == null || srcModelList.size() <= 0) {
            return null;
        }
        List<T> targetList = new ArrayList<>();

        for (S srcModel : srcModelList) {
            targetList.add(getTargetModel(tagetClass, srcModel));
        }

        if (targetList.size() <= 0)
            return null;
        return targetList;
    }

    /**
     * 将目标对象的具体字段附上值
     *
     * @param targetModel
     * @param fieldName
     * @param value
     */
    public static void setDataByFieldName(Object targetModel, String fieldName, Object value) {
        try {
            Field field = targetModel.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(targetModel, value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 在分组数据中查找
     *
     * @param groupList 每组里面的个数集合
     * @param index     查找的索引，按照所有组加起来的集合总数计算
     * @return 包含两个数字的集合，第一个表示当前是第几组（从0开始），第二个表示索引在当前组的第几个位置（从0开始）
     */
    public static List<Integer> getGroupIndex(List<Integer> groupList, int index) {
        //LogUtils.i("getGroupIndex" + groupList.toString() + "---------" + index);
        List<Integer> data = new ArrayList<>();
        int temp = index + 1;
        int preSum = 0;
        for (int i = 0; i < groupList.size(); i++) {
            temp -= groupList.get(i);
            if (i >= 1) {
                preSum += groupList.get(i);
            }
            if (temp <= 0) {
                data.add(i);
                data.add(index - preSum);
                //LogUtils.i("getGroupIndex" + data.toString());
                return data;
            }
        }
        return null;
    }

    /**
     * 将一个Model中要提交的字段封装成一个JSONObject
     *
     * @param srcModel
     * @param fieldNames 如果字段名和要放置的名字不一样用“=”隔开，左边是字段名，右边是JSONObject的key
     * @return
     */
    public static JSONObject getJSONObjectOfFieldName(Object srcModel, String... fieldNames) {
        if (srcModel == null)
            return null;
        JSONObject jsonObject = new JSONObject();
        Class clazz = srcModel.getClass();
        for (String fieldName : fieldNames) {
            String realFieldName;
            String jsonObjectKey;
            if (fieldName.contains("=")) {
                String[] arr = fieldName.split("=");
                realFieldName = arr[0];
                jsonObjectKey = arr[1];
            } else {
                realFieldName = fieldName;
                jsonObjectKey = fieldName;
            }
            try {
                Field field = clazz.getDeclaredField(realFieldName);
                field.setAccessible(true);
                jsonObject.put(jsonObjectKey, field.get(srcModel));
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                continue;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                continue;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (jsonObject.length() > 0)
            return jsonObject;
        return null;
    }

    /**
     * 链式构建 JSONObject
     */
    public static class JSONObjectBuider {

        private JSONObject jsonObject;

        public JSONObjectBuider() {
            jsonObject = new JSONObject();
        }

        public JSONObject buide() {
            return jsonObject;
        }

        public JSONObjectBuider putElement(String key, Object value) {
            try {
                jsonObject.put(key, value);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return this;
        }

        public static JSONObject buildJsonObjectByJsonString(String jsonStr) {
            try {
                return new JSONObject(jsonStr);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
