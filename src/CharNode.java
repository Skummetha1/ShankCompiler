
public class CharNode extends Node {

		//private variable
		private char cha;

		public char getCha() {
			return cha;
		}
		public void setCha(char cha) {
			this.cha = cha;
		}
		@Override
		//toStrring override
		public String ToString() {
			// TODO Auto-generated method stub
			return String.valueOf(cha);
		}
	    //class constructor 
		public CharNode(char cha) {
			this.cha = cha;
		}
		}

