package com.example.greendaogenerator;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class GreenDaoGenerator {
	private static final String PACAGE_NAME = "com.example.greendaosample";
	private static final String OUT_DIR = "../GreenDaoSample/src-gen";

    public static void main(String[] args)  throws Exception {
        Schema schema = new Schema(3, PACAGE_NAME);
        addCategoryEntitiy(schema, "Category");
        new DaoGenerator().generateAll(schema, OUT_DIR);
    }
    
//    private static void addEntitiy(Schema schema, String tableName) {
//        Entity entity = schema.addEntity(tableName);
//        entity.addIdProperty().autoincrement(); //以下に必要なプロパティを記述
//        entity.addStringProperty("title").notNull();
//    }

    private static void addCategoryEntitiy(Schema schema, String tableName) {
        Entity entity = schema.addEntity(tableName);
        entity.addIdProperty().autoincrement();
//        entity.addLongProperty("category_id").notNull();
        entity.addStringProperty("category_name").notNull();
        entity.addStringProperty("comment");
    }

}
