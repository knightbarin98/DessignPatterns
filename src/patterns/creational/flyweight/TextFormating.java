package patterns.creational.flyweight;

import java.util.ArrayList;
import java.util.List;

public class TextFormating {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FormattedText ft = new FormattedText("This is a brave new world");
		ft.capitalize(10, 15);
		System.out.println(ft.toString());

		BetterFormattingText bft = new BetterFormattingText("Make America Great Again");
		bft.getRange(13, 17).capitalize = true;
		System.out.println(bft.toString());
	}

}

class FormattedText {
	private String plainText;
	private boolean[] capitalize;

	public FormattedText(String plainText) {
		this.plainText = plainText;
		capitalize = new boolean[plainText.length()];
	}

	public void capitalize(int start, int end) {
		for (int i = start; i <= end; i++) {
			capitalize[i] = true;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < plainText.length(); i++) {
			char c = plainText.charAt(i);
			sb.append(capitalize[i] ? Character.toUpperCase(c) : c);
		}
		return sb.toString();
	}

}

class BetterFormattingText {

	private String plainText;
	private List<TextRange> formatting = new ArrayList<BetterFormattingText.TextRange>();

	public BetterFormattingText(String plainText) {
		this.plainText = plainText;
	}

	public TextRange getRange(int start, int end) {
		TextRange range = new TextRange(start, end);
		formatting.add(range);
		return range;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < plainText.length(); i++) {
			char c = plainText.charAt(i);
			for (TextRange range : formatting) {
				if (range.covers(i) && range.capitalize) {
					c = Character.toUpperCase(c);
				}
			}
			sb.append(c);
		}
		return sb.toString();
	}

	public class TextRange {
		public int start, end;
		public boolean capitalize, bold, italic;

		public TextRange(int start, int end) {
			this.start = start;
			this.end = end;
		}

		public boolean covers(int position) {
			return position >= start && position <= end;
		}
	}
}
