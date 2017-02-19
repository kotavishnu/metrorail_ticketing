package in.metrorail.rating;

import java.util.HashMap;
import java.util.Map;

public class Station {
	String name;
	String code;
	boolean isJunction;
	Station next;
	Station prev;
	Station line1Next;
	Station line1Prev;
	Station line2Next;
	Station line2Prev;
	Station line3Next;
	Station line3Prev;
	int weightage;
	boolean isVisited;
	String lineName;
	static Map<String,Station> stationMap=new HashMap<String, Station>();
	public static Station mgbs=new Station("MG Bus station","X2",true);
	public static 	Station ameerpet=new Station("Ameerpet","X1",true);
	public static Station parade=new Station("Parade Grounds","X3",true);
	public Station(String name,String code,String lineName){
		this.name=name;
		this.code=code;
		this.lineName=lineName;
	}
	private Station(String name,String code,boolean isJunction){
		this.name=name;
		this.code=code;
		this.isJunction=isJunction;
	}
	
	public static Station getStation(String name,String code,String lineName){
		Station st=stationMap.get(code);
		if(st==null){
			st=new Station(name,code,lineName);
			stationMap.put(code, st);
		}
		return st;
	}
	public static Station getJunctionStation(String code){
		if(code.equals("X1"))return ameerpet;
		if(code.equals("X2"))return mgbs;
		if(code.equals("X3"))return parade;
		return null;
	}
	
	public String displayStation() {
		return "Station [name=" + name + ", code=" + code + ", isJunction="
				+ isJunction ;
	}
	
	@Override
	public String toString() {
		return "Station [name=" + name + ", code=" + code + ", isJunction="
				+ isJunction + ", weightage=" + weightage + ", isVisited=" + isVisited + "]";
	}
}
