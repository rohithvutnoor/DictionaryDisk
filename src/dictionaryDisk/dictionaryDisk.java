package dictionaryDisk;

public class dictionaryDisk {
	private static void insert(String word, ListNode listNode) {
		ListNode node = listNode;
		int index = 0;
		while (index<word.length()) {
			if (node.nodes[word.charAt(index) - 'a'] == null) {
				node.nodes[word.charAt(index) - 'a'] = new ListNode();
				node.nodes[word.charAt(index) - 'a'].parent = node;
			}
			node = node.nodes[word.charAt(index) - 'a'];
			index++;
		}
		++node.code;
		node.meaning = word;
		System.out.println("Inserted");
	}

	
	
/*	private static void display(ListNode list) {
		ListNode current = list;
		//System.out.println("Code: " + current.code + " Mean: " + current.meaning);
			for (int j = 0; j < current.nodes.length-1; j++) {
				if (current.nodes[j] != null) {	
					System.out.println("Code: " + current.nodes[j].code + " Mean: " + current.nodes[j].meaning);
					display(current.nodes[j]);
				}
			}
	}
*/
	public static void main(String[] args) {
		ListNode list = new ListNode();
		insert("aa", list);
		insert("baa", list);
		insert("caa", list);
		insert("dbaa", list);
		insert("fbaa", list);
		insert("kbaaasda", list);
		insert("fza", list);
		//display(list);
	}

}
