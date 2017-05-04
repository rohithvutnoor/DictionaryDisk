package dictionaryDisk;

public class ListNode {
	String meaning;
	int code;
	ListNode parent;
	ListNode nodes[];
	public ListNode() {
		this.meaning = "";
		this.code = 0;
		this.parent = null;
		this.nodes = new ListNode[26];
	}
}