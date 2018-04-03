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
