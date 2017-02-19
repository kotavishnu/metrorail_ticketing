package in.metrorail.rating;

public class Line {
	String name;
	Station first;
	Station last;
	
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	public void displayLinePath(){
		System.out.println("Line Name "+name);
		Station tmp=first;
		while(tmp!=null){
			System.out.println(tmp);
			tmp=tmp.next;
		}
	}
	public void displayLine(){
		System.out.println("Line Name "+name);
		Station tmp=first;
		while(tmp!=null){
			System.out.println(tmp.displayStation()+"-->");
			if(tmp.next==null){
				if(name.equals("Line1")){
					tmp=tmp.line1Next;
					continue;
				}
				if(name.equals("Line2")){
					tmp=tmp.line2Next;
					continue;
				}
				if(name.equals("Line3")){
					tmp=tmp.line3Next;
					continue;
				}
			}else{
				tmp=tmp.next;
			}
		}
	}
	public void addStation(Station st){
		if(first==null){
			first=st;
			last=st;
			return;
		}
		if(st.isJunction){
			if(name.equals("Line1")){
				last.next=st;
				st.line1Prev=last;
				last=st;
				return;
			}
			if(name.equals("Line2")){
				last.next=st;
				st.line2Prev=last;
				last=st;
				return;
			}
			if(name.equals("Line3")){
				last.next=st;
				st.line3Prev=last;
				last=st;
				return;
			}
		}
		if(last!=null && last.isJunction){
			if(name.equals("Line1")){
				last.line1Next=st;
				st.prev=last;
				last=st;
				return;
			}
			if(name.equals("Line2")){
				last.line2Next=st;
				st.prev=last;
				last=st;
				return;
			}
			if(name.equals("Line3")){
				last.line3Next=st;
				st.prev=last;
				last=st;
				return;
			}
		}
		last.next=st;
		st.prev=last;
		last=st;
	}
	public void addStationToPath(Station st){
		if(first==null){
			first=st;
			last=st;
			return;
		}
		last.next=st;
		st.prev=last;
		last=st;
	}
	public static Line createLine1(){
		Line l=new Line();
		l.name="Line1";
		Station s=Station.getStation("Miyapur","A1",l.name);
		l.addStation(s);
		 s=Station.getStation("JNTU College","A2",l.name);
		l.addStation(s);
		s=Station.getStation("KPHB Colony","A3",l.name);
		l.addStation(s);
		s=Station.getStation("Kukatpally","A4",l.name);
		l.addStation(s);
		s=Station.getStation("Balanagar","A5",l.name);
		l.addStation(s);
		s=Station.getStation("Moosapet","A6",l.name);
		l.addStation(s);
		s=Station.getStation("Bharat Nagar","A7",l.name);
		l.addStation(s);
		s=Station.getStation("Erragadda","A8",l.name);
		l.addStation(s);
		s=Station.getStation("ESI Hospital","A9",l.name);
		l.addStation(s);
		s=Station.getStation("S R Nagar","A10",l.name);
		l.addStation(s);
		s=Station.getJunctionStation("X1");
		l.addStation(s);
		s=Station.getStation("Punjagutta","A12",l.name);
		l.addStation(s);
		s=Station.getStation("Irrum Manzil","A13",l.name);
		l.addStation(s);
		s=Station.getStation("Khairatabad","A14",l.name);
		l.addStation(s);
		s=Station.getStation("Lakdikapul","A15",l.name);
		l.addStation(s);
		s=Station.getStation("Assembly","A16",l.name);
		l.addStation(s);
		s=Station.getStation("Nampally","A17",l.name);
		l.addStation(s);
		s=Station.getStation("Gandhi Bhavan","A18",l.name);
		l.addStation(s);
		s=Station.getStation("Osmania Medical College","A19",l.name);
		l.addStation(s);
		s=Station.getJunctionStation("X2");
		l.addStation(s);
		s=Station.getStation("Malakpet","A21",l.name);
		l.addStation(s);
		s=Station.getStation("New Market","A22",l.name);
		l.addStation(s);
		s=Station.getStation("Musarambagh","A23",l.name);
		l.addStation(s);
		s=Station.getStation("Dilsukhnagar","A24",l.name);
		l.addStation(s);
		s=Station.getStation("Chaitanyapuri","A25",l.name);
		l.addStation(s);
		s=Station.getStation("Victoria Memorial","A26",l.name);
		l.addStation(s);
		s=Station.getStation("L B Nagar","A27",l.name);
		l.addStation(s);

		return l;
	}
	public static Line createLine2(){
		Line l=new Line();
		l.name="Line2";
		Station s=Station.getStation("JBS","B1",l.name);
		l.addStation(s);
		s=Station.getJunctionStation("X3");
		l.addStation(s);
		 s=Station.getStation("Secunderabad","B3",l.name);
		l.addStation(s);
		s=Station.getStation("Gandhi Hospital","B4",l.name);
		l.addStation(s);
		s=Station.getStation("Musheerabad","B5",l.name);
		l.addStation(s);
		s=Station.getStation("RTC Cross Roads","B6",l.name);
		l.addStation(s);
		s=Station.getStation("Chikkadpally","B7",l.name);
		l.addStation(s);
		s=Station.getStation("Narayanguda","B8",l.name);
		l.addStation(s);
		s=Station.getStation("Sultan Bazar","B9",l.name);
		l.addStation(s);
		s=Station.getJunctionStation("X2");
		l.addStation(s);
		s=Station.getStation("Salarjung Museum","B11",l.name);
		l.addStation(s);
		s=Station.getStation("Charminar","B12",l.name);
		l.addStation(s);
		s=Station.getStation("Shalibanda","B13",l.name);
		l.addStation(s);
		s=Station.getStation("Shamsher Gunj","B14",l.name);
		l.addStation(s);
		s=Station.getStation("Jungametta","B15",l.name);
		l.addStation(s);
		s=Station.getStation("Falaknuma","B16",l.name);
		l.addStation(s);
		return l;
	}
	public static Line createLine3(){
		Line l=new Line();
		l.name="Line3";
		Station s=Station.getStation("Nagole","C1",l.name);
		l.addStation(s);
		s=Station.getStation("Uppal","C2",l.name);
		l.addStation(s);
		s=Station.getStation("Survey of India","C3",l.name);
		l.addStation(s);
		s=Station.getStation("NGRI","C4",l.name);
		l.addStation(s);
		s=Station.getStation("Habsiguda","C5",l.name);
		l.addStation(s);
		s=Station.getStation("Tarnaka","C6",l.name);
		l.addStation(s);
		s=Station.getStation("Mettuguda","C7",l.name);
		l.addStation(s);
		s=Station.getStation("Secunderabad","C8",l.name);
		l.addStation(s);
		s=Station.getJunctionStation("X3");
		l.addStation(s);
		s=Station.getStation("Paradise","C10",l.name);
		l.addStation(s);
		s=Station.getStation("Rasool Pura","C11",l.name);
		l.addStation(s);
		s=Station.getStation("Prakash Nagar","C12",l.name);
		l.addStation(s);
		s=Station.getStation("Begumpet","C13",l.name);
		l.addStation(s);
		s=Station.getJunctionStation("X1");
		l.addStation(s);
		s=Station.getStation("Madhura Nagar","C15",l.name);
		l.addStation(s);
		s=Station.getStation("Yusuf Guda","C16",l.name);
		l.addStation(s);
		s=Station.getStation("Road No 5 Jubilee Hills","C17",l.name);
		l.addStation(s);
		s=Station.getStation("Jubilee Hills Check Post","C18",l.name);
		l.addStation(s);
		s=Station.getStation("Pedamma Temple","C19",l.name);
		l.addStation(s);
		s=Station.getStation("Madhapur","C20",l.name);
		l.addStation(s);
		s=Station.getStation("Durgam Chervu","C21",l.name);
		l.addStation(s);
		s=Station.getStation("HITEC City","C22",l.name);
		l.addStation(s);
		s=Station.getStation("Shilparamam","C23",l.name);
		l.addStation(s);
		return l;
	}
}
