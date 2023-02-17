
public class BoolNode extends Node {

		//private variable
		private Boolean bool;

		@Override
		//toStrring override
		public String ToString() {
			// TODO Auto-generated method stub
			return bool.toString();
		}
	    //class constructor 
		public BoolNode(Boolean bool) {
			this.bool = bool;
		}
		
		//ssetters and getters 
		public void setBool(Boolean bool) {
			this.bool = bool;
		}

		public Boolean getBool() {
			return bool;
		}

		}
