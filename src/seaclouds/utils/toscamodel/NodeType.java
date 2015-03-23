package seaclouds.utils.toscamodel;

import java.util.HashMap;
import java.util.Iterator;

public class NodeType {
	public NodeType(NodeType st, String desc, HashMap<String,Property> ppt, HashMap<String,Value> attrb)
	{
		supertype = st;
		description = desc;
		properties = ppt;
		attributes = attrb;
	}
	final NodeType supertype;
	final String description;
	final private HashMap<String,Property> properties;
	final private HashMap<String,Value> attributes;
	public final Property getProperty(final String name) {
		return properties.get(name);
	}


	public class iterator implements Iterator<Property> {

		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		public Property next() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

	public Iterator<Property> iterator() {
		return null;
	}
}
