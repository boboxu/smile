/*
 * Copyright (C) 2011 Markus Junginger, greenrobot (http://greenrobot.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.greenrobot.daogenerator.gentest;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

/**
 * Generates entities and DAOs for the example project DaoExample.
 * 
 * Run it as a Java application (not Android).
 * 
 * @author Markus
 */
public class ExampleDaoGenerator {

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(3, "com.heme.logic.module");

//        addNote(schema);
//        addCustomerOrder(schema);
//        addPerson(schema);
        addCommonMsg(schema);

        new DaoGenerator().generateAll(schema, "../Smile/src");
    }

    private static void addNote(Schema schema) {
        Entity note = schema.addEntity("Note");
        note.addIdProperty();
        note.addStringProperty("text").notNull();
        note.addStringProperty("comment");
        note.addDateProperty("date");
    }

    private static void addCustomerOrder(Schema schema) {
        Entity customer = schema.addEntity("Customer");
        customer.addIdProperty();
        customer.addStringProperty("name").notNull();

        Entity order = schema.addEntity("Order");
        order.setTableName("ORDERS"); // "ORDER" is a reserved keyword
        order.addIdProperty();
        Property orderDate = order.addDateProperty("date").getProperty();
        Property customerId = order.addLongProperty("customerId").notNull().getProperty();
        order.addToOne(customer, customerId);

        ToMany customerToOrders = customer.addToMany(order, customerId);
        customerToOrders.setName("orders");
        customerToOrders.orderAsc(orderDate);
    }

    
    private static void addCommonMsg(Schema schema)
    {
    	Entity commonMsg = schema.addProtobufEntity("CommonMsg");
    	commonMsg.addLongProperty("Uint64MsgId").primaryKey();
    	commonMsg.addLongProperty("Uint64FromUid").notNull();
    	commonMsg.addLongProperty("Uint64ToUid");
    	commonMsg.addLongProperty("Uint64ToGid");
    	commonMsg.addIntProperty("Uint32MsgType").notNull();

    	commonMsg.addIntProperty("Uint32ContentType");
    	commonMsg.addStringProperty("StrTextMsg");
    	
//    	commonMsg.addProperty(propertyType, propertyName)
//    	//picmsginfo
//    	addPicMsgInfoProperty(commonMsg);
//    	//voicemsginfo
//    	addVoiceMsgInfoProperty(commonMsg);
//    	//videomsginfo
//    	addVideoMsgInfoProperty(commonMsg);
    	commonMsg.addLongProperty("Uint64Time");
    	commonMsg.addIntProperty("Uint32TrunctFlag");
    }
    
    private static void addPicMsgInfoProperty(Entity entity)
    {
    	if (entity == null) return;
    	
		entity.addStringProperty("StrPicUrl").notNull();
		entity.addStringProperty("StrThumbUrl");
		entity.addIntProperty("Uint32Size");
		entity.addStringProperty("StrPicType");
		entity.addIntProperty("Uint32PicWidth");
		entity.addIntProperty("Uint32PicHeight"); 
    }

    private static void addVoiceMsgInfoProperty(Entity entity)
	{
    	if (entity == null) return;
    	
		entity.addStringProperty("StrVoiceUrl").notNull();
		entity.addIntProperty("Uint32Size");
		entity.addIntProperty("Uint32Duration"); 
	}
    
    private static void addVideoMsgInfoProperty(Entity entity)
	{
    	if (entity == null) return;
    	
		entity.addStringProperty("StrVideoUrl").notNull();
		entity.addStringProperty("StrThumbUrl");
		entity.addIntProperty("Uint32Size");
		entity.addIntProperty("Uint32Duration"); 
		entity.addStringProperty("StrVideoFormat");
		entity.addIntProperty("Uint32VideoWidth");
		entity.addIntProperty("Uint32VideoHeight"); 
	}
}
