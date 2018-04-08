package dunemask.objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ArrayListState{
	/***Version*/
    final static double version = 5.8;
		private HashMap<String,ArrayList<String>> map;
		public ArrayListState() {
			setMap(new HashMap<String,ArrayList<String>>());
			
		}
		
		/** Remove State
		 * @param state State Removed
		 * 
		 * */
		public void removeState(String state) {
			map.remove(state, map.get(state));
		}

		
		/**@param list List to be added
		 * @param state Name for the state
		 * 
		 * */
		public void addState(String[] list,String state) {
			map.put(state, new ArrayList<String>());
			map.get(state).addAll(Arrays.asList(list));
		}
		/**
		 * @return the state
		 * */
		public String[] getStateAsArray(String state){
			return this.getState(state).toArray(new String[getState(state).size()]);
		}
		
		/**@param list List to be added
		 * @param state Name for the state
		 * 
		 * */
		public void addState(ArrayList<String> list,String state) {
			map.put(state, new  ArrayList<String>());
			map.get(state).addAll(list);
		}
		
		
		/** Replaces all states that are same and merges all others
		 * @param al ArrayListState
		 * */
		public void merge(ArrayListState al) {
			HashMap<String,ArrayList<String>> mp = al.getMap();
			this.getMap().putAll(mp);
			
		}
		/** Replaces all states that are same and merges all others
		 * @param mp HashMap ((HashMap<String, ArrayList<String>>)
		 * */
		public void merge(HashMap<String,ArrayList<String>> mp) {
			this.getMap().putAll(mp);
			
		}
		/**
		 * @return the state
		 * */
		public ArrayList<String> getState(String state){
			return map.get(state);
		}
		
		
		/**
		 * @return the map
		 */
		public HashMap<String,ArrayList<String>> getMap() {
			return map;
		}
		/**
		 * @param map the map to set
		 */
		public void setMap(HashMap<String,ArrayList<String>> map) {
			this.map = map;
		}
		
	}	
