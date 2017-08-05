import java.util.*;

public class PrefixSolution {
	
	public static void main(String[] args) {
		ArrayList<String> a = new ArrayList<>();
		a.add("zebra");
		a.add("dog");
		a.add("duck");
		a.add("dove");
		System.out.println(prefix(a));
	}
	
	public static ArrayList<String> prefix(ArrayList<String> a) {
	    // find a list of indexes where the character at that index completes a prefix
	    ArrayList<Integer> index = new ArrayList<>();
	    boolean[] done = new boolean[a.size()];
	    for (int i = 0; i < a.size(); i++) {
	        index.add(1);
	    }
	    Map<String, List<Integer>> m = new HashMap<>();
	    while (!allDone(done)) {
	    	System.out.println(m);
    	    for (int i = 0; i < a.size(); i++) {
    	        // only look for the unfinished
    	        if (!done[i]) {
    	        	
    	        	
    	            String p = a.get(i).substring(0, index.get(i));
        	        if (m.containsKey(p)) {
        	            // if this prefix appeared before
        	            // we increment the indexes of these prefixes by 1
        	            List<Integer> l = m.get(p);
        	            l.add(i);
        	            m.put(p, l);
        	        }
        	        else {
        	            // first appears
        	            List<Integer> l = new ArrayList<>();
        	            l.add(i);
        	            m.put(p, l);
        	        }
    	        }
    	    }
    	    // then we increment the indexes
    	    // label the correct prefixes
    	    for (String s : m.keySet()) {
    	        List<Integer> l = m.get(s);
    	        if (l.size() > 1) {
    	            for (int i : l) {
    	                index.set(i, index.get(i) + 1);
    	            }
    	        }
    	        else {
    	            done[l.get(0)] = true;
    	        }
    	    }
    	    // then remove those pairs with a list longer than 1
    	    // to prevent increment them again
    	    Iterator<String> iter = m.keySet().iterator();
    	    while (iter.hasNext()) {
    	        String s = iter.next();
    	        List<Integer> l = m.get(s);
    	        if (l.size() > 1)
    	            iter.remove();
    	    }

	    }
	    // get result
	    ArrayList<String> r = new ArrayList<>();
	    for (int i = 0; i < index.size(); i++) {
	        r.add(a.get(i).substring(0, index.get(i)));
	    }
	    return r;
	}
	
	public static boolean allDone(boolean[] done) {
	    for (boolean b : done) {
	        if (!b) {
	            return false;
	        }
	    }
	    return true;
	}
}
