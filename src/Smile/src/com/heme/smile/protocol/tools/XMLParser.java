package com.heme.smile.protocol.tools;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;



public abstract class XMLParser extends IDataLoaderParser implements ContentHandler {
	
	public abstract Object getResult();
	
	@Override
	public Object parse(byte[] content) {
		InputStream is = new ByteArrayInputStream(content);
	    SAXParserFactory factory = SAXParserFactory.newInstance(); 
	    try {
	    	SAXParser parser = factory.newSAXParser();
	    	XMLReader xmlReader = parser.getXMLReader();
	    	xmlReader.setContentHandler(this);
	    	xmlReader.parse(new InputSource(is));
	    } catch (ParserConfigurationException e) {
//	    	mErrCode = ProtocolInfo.ERROR_DATALOADER_PARSE;
	    	return null;
	    } catch (SAXException e) {
//	    	mErrCode = ProtocolInfo.ERROR_DATALOADER_PARSE;
	    	return null;
	    } catch (IOException e) {
//	    	mErrCode = ProtocolInfo.ERROR_DATALOADER_PARSE;
	    	return null;
		}
	    return getResult();
	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {
		
	}

	public void endDocument() throws SAXException {

	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
	
	}

	public void startDocument() throws SAXException {
		
	}

	public void startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {
		
	}

}
