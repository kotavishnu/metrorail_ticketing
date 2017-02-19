package in.metrorail.rating;

public class Ticketing {

	public static void main(String[] args) throws CloneNotSupportedException {
		if(args!=null && args.length!=2){
			System.out.println("Usage Ticketing <SRC CODE> <DEST CODE>");
			System.exit(0);
		}
		String src=args[0];
		String dest=args[1];
		Lines ls=new Lines();
		ls.createLines();
		ls.line1.displayLine();;
		ls.line2.displayLine();;
		ls.line3.displayLine();
		Station junction=ls.findJunction(src);
		System.out.println("Junction is "+junction);
		int minLen=ls.searchStation(junction, dest,0,0);
		int totLength=Lines.minLength+Lines.startLineLen;
		System.out.println("Paths is length "+totLength);
		System.out.println("Start station is "+Lines.startStation+" LINE IS "+Lines.startStation.lineName);
		System.out.println("Destination station is "+Lines.destStation+" LINE IS "+Lines.destStation.lineName);
		if(totLength<=3)
			System.out.println("Charge is 10.00 RS");
		else{
			int extraStations=totLength-3;
			double price=0.0d;
			switch(Lines.startStation.lineName){
				case "Line1": price=extraStations*2.5d;break;
				case "Line2": price=extraStations*2;break;
				case "Line3": price=extraStations*3;break;
			}
			System.out.println("Print charges are "+(price+10));
		}
	}
}
