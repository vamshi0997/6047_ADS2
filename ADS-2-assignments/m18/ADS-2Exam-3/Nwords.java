class Nwords implements Comparable<Nwords> {
	private String sword;
	private int freq;
	Nwords(String sword1, int freq1) {
		this.sword = sword1;
		this.freq = freq1;
	}
	String getWord() {
		return this.sword;
	}
	public int compareTo(Nwords that) {
		if (this.freq > that.freq) {
			return 1;
		}
		if (this.freq < that.freq) {
			return -1;
		}
		if(this.sword.length() > that.sword.length()) {
			return 1;
		}
		if(this.sword.length() < that.sword.length()) {
            return -1;
		}
		return 0;
	}

}