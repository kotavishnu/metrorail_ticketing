package in.metrorail.rating;

import java.util.ArrayList;
import java.util.List;

public class Lines {
	Line line1;
	Line line2;
	Line line3;
	static List<Line> paths = new ArrayList<Line>();
	static int minLength;
	static int cou;
	static int lineCount;
	static int TMP_COUNT;
	static int startLineLen = 0;
	static Station startStation;
	static Station destStation;

	public void createLines() {
		line1 = Line.createLine1();
		line2 = Line.createLine2();
		line3 = Line.createLine3();
	}

	public void findPath(String src, String dest) {
		findStation(line1.first, src);
	}

	public static Station findJunction(String src) {
		Station tmp = findInJunction(Station.ameerpet, src);
		if (tmp != null) {
			searchSrcInJunction(Station.ameerpet, src);
			return Station.ameerpet;
		}
		tmp = findInJunction(Station.mgbs, src);
		if (tmp != null)
			return Station.mgbs;
		tmp = findInJunction(Station.parade, src);
		if (tmp != null)
			return Station.parade;
		return null;
	}

	private static void searchSrcInJunction(Station junction, String src) {
		searchSrcInLine(junction.line1Next, src);
		searchSrcInLine(junction.line2Next, src);
		searchSrcInLine(junction.line3Next, src);
		searchSrcInLine(junction.line1Prev, src);
		searchSrcInLine(junction.line2Prev, src);
		searchSrcInLine(junction.line3Prev, src);
	}

	private static void searchSrcInLine(Station line, String src) {
		Station tmp = line;
		int len = 0;
		while (tmp != null) {
			len++;
			if (!tmp.isJunction && tmp.code.equals(src)) {
				startLineLen = len;
				startStation = tmp;
				return;
			}
			if (tmp.isJunction)
				break;
			tmp = tmp.next;
		}
		tmp = line;
		len = 0;
		while (tmp != null) {
			len++;
			if (!tmp.isJunction && tmp.code.equals(src)) {
				startLineLen = len;
				startStation = tmp;
				return;
			}
			if (tmp.isJunction)
				break;
			tmp = tmp.prev;
		}
	}

	private static Station findInJunction(Station line, String src) {
		Station tmp = findJunctionForLine(line.line1Next, src);
		if (tmp != null)
			return line.line1Next;
		tmp = findJunctionForLine(line.line2Next, src);
		if (tmp != null)
			return line.line2Next;
		tmp = findJunctionForLine(line.line3Next, src);
		if (tmp != null)
			return line.line3Next;

		tmp = findJunctionForLine(line.line1Prev, src);
		if (tmp != null)
			return line.line1Prev;
		tmp = findJunctionForLine(line.line2Prev, src);
		if (tmp != null)
			return line.line2Prev;
		tmp = findJunctionForLine(line.line3Prev, src);
		if (tmp != null)
			return line.line3Prev;
		return null;
	}

	public static Station findJunctionForLine(Station path, String src) {
		if (path == null)
			return null;
		Station tmp = path;
		int len = 0;
		while (tmp != null && !tmp.isJunction) {
			if(tmp.code.equals(src))return tmp;
			tmp = tmp.next;
			len++;
		}
		len = 0;
		tmp = path;
		while (tmp != null && !tmp.isJunction) {
			if(tmp.code.equals(src))return tmp;
			len++;
			tmp = tmp.prev;
		}
		return null;
	}

	public static Station findStation(Station path, String src) {
		if (path == null || path.isVisited)
			return null;
		Station tmp = path;
		path.isVisited = true;
		while (tmp != null) {
			if (tmp.code.equals(src))
				return tmp;
			if (tmp.isJunction)
				break;
			tmp = tmp.next;
			cou++;
		}

		if (tmp != null && tmp.isJunction) {
			Station found = findStation(tmp.line1Next, src);
			if (found != null)
				return found;
			found = findStation(tmp.line1Prev, src);
			if (found != null)
				return found;
			found = findStation(tmp.line2Next, src);
			if (found != null)
				return found;
			found = findStation(tmp.line2Prev, src);
			if (found != null)
				return found;
			found = findStation(tmp.line3Next, src);
			if (found != null)
				return found;
			found = findStation(tmp.line3Prev, src);
			if (found != null)
				return found;
		}

		return null;
	}

	public static int searchStation(Station junction, String src, int length,
			int level) {
		if (junction == null || junction.isVisited)
			return 0;
		// System.out.println("Junction "+junction+" path is length is "+length);
		if (junction.code.equals(src))
			return length;
		junction.isVisited = true;
		level++;
		int len = searchLinesForward(junction.line1Next, src, length);
		if (len > 0)
			return len;
		len = searchLinesBackward(junction.line1Next, src, length);
		if (len > 0)
			return len;
		len = searchLinesForward(junction.line2Next, src, length);
		if (len > 0)
			return len;
		len = searchLinesBackward(junction.line2Next, src, length);
		if (len > 0)
			return len;
		len = searchLinesForward(junction.line3Next, src, length);
		if (len > 0)
			return len;
		len = searchLinesBackward(junction.line3Next, src, length);
		if (len > 0)
			return len;

		len = searchLinesForward(junction.line1Prev, src, length);
		if (len > 0)
			return len;
		len = searchLinesBackward(junction.line1Prev, src, length);
		if (len > 0)
			return len;
		len = searchLinesForward(junction.line2Prev, src, length);
		if (len > 0)
			return len;
		len = searchLinesBackward(junction.line2Prev, src, length);
		if (len > 0)
			return len;
		len = searchLinesForward(junction.line3Prev, src, length);
		if (len > 0)
			return len;
		len = searchLinesBackward(junction.line3Prev, src, length);
		if (len > 0)
			return len;

		return nextJunctionSearchAll(junction, src, length, level);

	}

	private static int nextJunctionSearchAll(Station junction, String src,
			int length, int level) {
		int line1Len = length(junction.line1Next);
		int line2Len = length(junction.line2Next);
		int line3Len = length(junction.line3Next);
		int line1PrevLen = length(junction.line1Prev);
		int line2PrevLen = length(junction.line2Prev);
		int line3PrevLen = length(junction.line3Prev);

		Station forwardJunction = getUnVisitedJunctionForward(junction.line1Prev);
		Station backwardJunction = getUnVisitedJunctionBackward(junction.line1Prev);
		int len = 0;
		len = searchStation(forwardJunction, src, length + line1PrevLen, level);
		len = searchStation(backwardJunction, src, length + line1PrevLen, level);

		forwardJunction = getUnVisitedJunctionForward(junction.line2Prev);
		backwardJunction = getUnVisitedJunctionBackward(junction.line2Prev);
		len = searchStation(forwardJunction, src, length + line2PrevLen, level);
		len = searchStation(backwardJunction, src, length + line2PrevLen, level);

		forwardJunction = getUnVisitedJunctionForward(junction.line3Prev);
		backwardJunction = getUnVisitedJunctionBackward(junction.line3Prev);
		len = searchStation(forwardJunction, src, length + line3PrevLen, level);
		len = searchStation(backwardJunction, src, length + line3PrevLen, level);

		forwardJunction = getUnVisitedJunctionForward(junction.line1Next);
		backwardJunction = getUnVisitedJunctionBackward(junction.line1Next);
		len = searchStation(forwardJunction, src, length + line1Len, level);
		len = searchStation(backwardJunction, src, length + line1Len, level);

		forwardJunction = getUnVisitedJunctionForward(junction.line2Next);
		backwardJunction = getUnVisitedJunctionBackward(junction.line2Next);
		len = searchStation(forwardJunction, src, length + line2Len, level);
		len = searchStation(backwardJunction, src, length + line2Len, level);
		forwardJunction = getUnVisitedJunctionForward(junction.line3Next);
		backwardJunction = getUnVisitedJunctionBackward(junction.line3Next);
		len = searchStation(forwardJunction, src, length + line3Len, level);
		len = searchStation(backwardJunction, src, length + line3Len, level);

		return 0;
	}

	private static int length(Station line) {
		Station tmp = line;
		int lenNext = 0;
		int lenPrev = 0;
		while (tmp != null) {
			lenNext++;
			if (lenNext > 20) {
				System.out.println("recursive calls issue with " + line);
				return 0;
			}
			if (tmp.isJunction)
				break;
			tmp = tmp.next;
		}

		tmp = line;
		while (tmp != null) {
			lenPrev++;
			if (lenPrev > 20) {
				System.out.println("recursive calls issue with " + line);
				return 0;
			}
			if (tmp.isJunction)
				break;
			tmp = tmp.prev;
		}
		return Math.max(lenPrev, lenNext);
	}

	private static Station getUnVisitedJunctionForward(Station line) {
		if (line == null)
			return null;
		Station tmp = line;
		int i = 0;
		while (tmp != null) {
			i++;
			if (tmp.isJunction && tmp.isVisited)
				return null;
			if (tmp.isJunction && !tmp.isVisited) {
				tmp.weightage = i;
				return tmp;
			}
			tmp = tmp.next;
		}
		return null;
	}

	private static Station getUnVisitedJunctionBackward(Station line) {
		if (line == null)
			return null;
		Station tmp = line;
		int i = 0;

		while (tmp != null) {
			i++;
			if (tmp.isJunction && tmp.isVisited)
				return null;
			if (tmp.isJunction && !tmp.isVisited) {
				tmp.weightage = i;
				return tmp;
			}
			tmp = tmp.prev;
		}
		return null;
	}

	private static Station getNextJunction(Station jc) {
		if (jc == null || jc.isVisited)
			return null;
		Station tmp = jc;
		while (tmp != null) {
			if (tmp.next != null && tmp.next.isJunction)
				return tmp.next;
			tmp = tmp.next;
		}
		return null;
	}

	private static int searchLinesForward(Station line, String src, int length) {
		//System.out.println("Junction length is Forward " + length);
		if (line == null || line.isVisited)
			return 0;
		Station tmp = line;
		int i = 0;
		//System.out.println("searchLinesForward " + line);
		while (tmp != null) {
			i++;
			// tmp.isVisited=true;
			if (tmp.isJunction)
				break;
			if (tmp.code.equals(src)) {
				destStation = tmp;

				if (minLength > 0 && minLength > length + i)
					minLength = length + i;
				else if (minLength == 0)
					minLength = length + i;
				int len = checkSameSubLine(startStation, destStation);
				if (len > 0) {
					minLength = len;
					startLineLen = 0;
				}
				// System.out.println("GOT THE STATION "+tmp+" path is "+length);
				return length + i;
			}
			tmp = tmp.next;
		}
		return 0;
	}

	private static int checkSameSubLine(Station src, Station dest) {
		Station tmp = src;
		int len = 0;
		while (tmp != null) {
			len++;
			if (tmp.isJunction)
				break;
			if (tmp.code.equals(dest.code)) {
				return len;
			}
			tmp = tmp.next;
		}
		tmp = src;
		len = 0;
		while (tmp != null) {
			len++;
			if (tmp.isJunction)
				break;
			if (tmp.code.equals(dest.code)) {
				return len;
			}
			tmp = tmp.prev;
		}
		return 0;
	}

	private static int searchLinesBackward(Station line, String src, int length) {
		//System.out.println("Junction length is Backward " + length);
		if (line == null || line.isVisited)
			return 0;
		Station tmp = line;
		int i = 0;
		while (tmp != null) {
			// tmp.isVisited=true;
			i++;
			if (tmp.isJunction)
				break;
			if (tmp.code.equals(src)) {
				destStation = tmp;
				if (minLength > 0 && minLength > length + i)
					minLength = length + i;
				else if (minLength == 0)
					minLength = length + i;
				int len = checkSameSubLine(startStation, destStation);
				if (len > 0) {
					minLength = len;
					startLineLen = 0;
				}
				return length + i;
			}
			tmp = tmp.prev;
		}
		return 0;
	}
}
