package lab02;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

public class FantasyFootball {

	public FantasyFootball() {
		// TODO Auto-generated constructor stub
	}

	public static String padLeft(String str, int x) {
		return String.format("%1$" + x + "s", str);
	}

	public static String padRight(String str, int x) {
		return String.format("%1$-" + x + "s", str);
	}
	
	public static String padZeros(String str, int x){
		return String.format("%0" + Integer.toString(x) + "d", str);
	}

	public static void main(String[] args) {
		
		File data = new File(
				System.getProperty("user.dir") + "\\datafiles\\lab02\\data.txt");
		RandomAccessFile store = null;
		try {
			store = new RandomAccessFile(data, "rw");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
				"There was a problem opening the file for writing: " + e.getMessage(),
				"Uh oh", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}

		String spaces = new String(new char[71]).replace('\0', ' ');
		for (int i = 0; i < 20; ++i) {
			try {
				store.writeUTF(spaces);
			} catch (IOException e) {JOptionPane.showMessageDialog(null,
					"There was a problem writing to the file: " + e.getMessage(),
					"Uh oh", JOptionPane.ERROR_MESSAGE);
			}
		}

		String id = "", name = "", team = "", skill = "", date = "";

		// ID
		int intId = 0;
		while(true) {
			try {
				id = JOptionPane.showInputDialog(
						"Please enter an ID between 1 and 20, inclusively.");
				intId = Integer.parseInt(id);
				if (id == null){
					System.exit(0);
				} else if (intId > 20 || intId < 1) {
					throw new Exception("Number must be between 1 and 20.");
				} else {
					id = padLeft(id, 5);
					break;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"There was a problem with your entry: " + e.getMessage(),
						"Uh oh", JOptionPane.ERROR_MESSAGE);
				continue;
			}
		}

		// Name
		while(true) {
			try {
				name = JOptionPane.showInputDialog(
						"Please enter the player's name (26 char max).");
				if (name == null){
					System.exit(0);
				} else if (name.length() < 1 || name.length() > 26) {
					throw new Exception(
							"Name must be between 1 and 26 characters in length.");
				} else {
					name = padRight(name, 26);
					break;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"There was a problem with your entry: " + e.getMessage(),
						"Uh oh", JOptionPane.ERROR_MESSAGE);
				continue;
			}
		}

		// Team
		while(true) {
			try {
				team = JOptionPane.showInputDialog(
						"Please enter the player's team name (26 char max).");
				if (team == null){
					System.exit(0);
				} else if (team.length() < 1 || team.length() > 26) {
					throw new Exception(
							"Team name must be between 1 and 26 characters in length.");
				} else {
					team = padRight(team, 26);
					break;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"There was a problem with your entry: " + e.getMessage(),
						"Uh oh", JOptionPane.ERROR_MESSAGE);
				continue;
			}
		}

		// Skill
		int intSkill = 0;
		while(true) {
			try {
				skill = JOptionPane.showInputDialog(
						"Please enter the player's skill level between 0 and 99, inclusively.");
				if (skill == null){
					System.exit(0);
				}
				intSkill = Integer.parseInt(skill);
				if (intSkill > 99 || intSkill < 0) {
					throw new Exception("Number must be between 0 and 99.");
				} else {
					skill = padLeft(skill, 5);
					break;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"There was a problem with your entry: " + e.getMessage(),
						"Uh oh", JOptionPane.ERROR_MESSAGE);
				continue;
			}
		}

		// Draft Date
		Date draftDate = null;
		while (true) {
			try {
				date = JOptionPane.showInputDialog(
						"Please enter the player's draft date (format: YYYYMMDD, e.g. \"20150710\").");
				if (date == null){
					System.exit(0);
				} else if (date.length() != 8){
					throw new Exception("Please use format YYYYMMDD");
				}

				Calendar cal = Calendar.getInstance();
				cal.set(Integer.parseInt(date.substring(0, 3)),
						Integer.parseInt(date.substring(4, 5)),
						Integer.parseInt(date.substring(6, 7)));
				String newDate = Integer.toString(cal.get(Calendar.YEAR))
						+ padZeros(Integer.toString(cal.get(Calendar.MONTH) + 1), 2)
						+ padZeros(Integer.toString(cal.get(Calendar.DAY_OF_MONTH)),
								2);
				if (JOptionPane.showConfirmDialog(null,
						"Did you mean " + newDate + "?", "Confirm date",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE) == JOptionPane.NO_OPTION) {
					continue;
				}
				date = padLeft(newDate, 9);
				break;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"There was a problem with your entry: " + e.getMessage(),
						"Uh oh", JOptionPane.ERROR_MESSAGE);
				continue;
			}
		}

		String result = id + name + team + skill + date;

		JOptionPane.showMessageDialog(null, "Here's your string: " + result);

		// try {
		// store.writeUTF(result);
		// } catch (IOException e) {
		// JOptionPane.showMessageDialog(null,
		// "There was a problem writing to the file: " + e.getMessage(),
		// "Uh oh", JOptionPane.ERROR_MESSAGE);
		// }

	}

}
