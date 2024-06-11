package com.github.imyuyu.sqltoy.indexer;

public enum SQLToyBeanIndexType {

    /**
     * sqlId
     */
    SQL_ID,

    /**
     * 缓存翻译ID
     */
    TRANSLATE_ID

    ;
    public static SQLToyBeanIndexType get(int ordinal) {
        for (SQLToyBeanIndexType type : SQLToyBeanIndexType.values()) {
            if (type.ordinal() == ordinal) {
                return type;
            }
        }
        return null;
    }
}
