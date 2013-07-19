package com.heme.smile.test;

import com.example.tutorial.AddPerson;
import com.example.tutorial.ListPeople;

public class ProtobufTest {
	public static void Test() 
	{
		String[] argsStrings = {"/mnt/sdcard/testhemi/persondata"};
		try {
			AddPerson.addPerson(argsStrings);
			ListPeople.listPerson(argsStrings);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	
}
