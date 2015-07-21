package lab02;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

public class FantasyFootball {
	protected final static int MAX_RECORD_LENGTH = 71;
	protected final static int RECORD_COUNT = 20;

	public FantasyFootball() {
		// TODO Auto-generated constructor stub
	}

	public static String padLeft(String str, int x) {
		return padLeft(str, x, ' ');
	}

	public static String padRight(String str, int x) {
		return padRight(str, x, ' ');
	}

	public static String padZeros(String str, int x) {
		return padLeft(str, x, '0');
	}

	public static String padLeft(String str, int x, char c) {
		String padding = new String(new char[x - str.length()]).replace('\0',
				c);
		return (str.length() >= x) ? str : padding + str;
	}

	public static String padRight(String str, int x, char c) {
		String padding = new String(new char[x - str.length()]).replace('\0',
				c);
		return (str.length() >= x) ? str : str + padding;
	}

	public static String repeatChar(int freq, char c) {
		return new String(new char[freq]).replace('\0', c);
	}

	public static void main(String[] args) {
		String cmd = "";
		File data = new File(System.getProperty("user.dir")
				+ "\\datafiles\\lab02\\data.txt");
		RandomAccessFile store = null;
		long storeLength = 0;
		try {
			store = new RandomAccessFile(data, "rw");
			storeLength = store.length();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"There was a problem opening the file for writing: "
							+ e.getMessage(),
					"Uh oh", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}

		if (storeLength < ((MAX_RECORD_LENGTH + 2) * RECORD_COUNT)) {
			for (int i = 0; i < 20; ++i) {
				try {
					store.writeUTF(repeatChar(71, ' '));
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null,
							"There was a problem writing to the file: "
									+ e.getMessage(),
							"Uh oh", JOptionPane.ERROR_MESSAGE);
				}
			}
		}

		while (cmd.compareToIgnoreCase("end") != 0 && cmd != null) {
			cmd = JOptionPane.showInputDialog(null,
					"Please enter a command: new, old, or end",
					"Fantasy Football", JOptionPane.QUESTION_MESSAGE);
			if (cmd.compareToIgnoreCase("new") == 0) {
				String id = "", name = "", team = "", skill = "", date = "";

				// ID
				int intId = 0;
				while (true) {
					try {
						id = JOptionPane.showInputDialog(
								"Please enter an ID between 1 and 20, inclusively.");
						intId = Integer.parseInt(id);
						if (id == null) {
							System.exit(0);
						} else if (intId > 20 || intId < 1) {
							throw new Exception(
									"Number must be between 1 and 20.");
						} else {
							id = padLeft(id, 5);
							break;
						}
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null,
								"There was a problem with your entry: "
										+ e.getMessage(),
								"Uh oh", JOptionPane.ERROR_MESSAGE);
						continue;
					}
				}

				// Name
				while (true) {
					try {
						name = JOptionPane.showInputDialog(
								"Please enter the player's name (26 char max).");
						if (name == null) {
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
								"There was a problem with your entry: "
										+ e.getMessage(),
								"Uh oh", JOptionPane.ERROR_MESSAGE);
						continue;
					}
				}

				// Team
				while (true) {
					try {
						team = JOptionPane.showInputDialog(
								"Please enter the player's team name (26 char max).");
						if (team == null) {
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
								"There was a problem with your entry: "
										+ e.getMessage(),
								"Uh oh", JOptionPane.ERROR_MESSAGE);
						continue;
					}
				}

				// Skill
				int intSkill = 0;
				while (true) {
					try {
						skill = JOptionPane.showInputDialog(
								"Please enter the player's skill level between 0 and 99, inclusively.");
						if (skill == null) {
							System.exit(0);
						}
						intSkill = Integer.parseInt(skill);
						if (intSkill > 99 || intSkill < 0) {
							throw new Exception(
									"Number must be between 0 and 99.");
						} else {
							skill = padLeft(skill, 5);
							break;
						}
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null,
								"There was a problem with your entry: "
										+ e.getMessage(),
								"Uh oh", JOptionPane.ERROR_MESSAGE);
						continue;
					}
				}

				// Draft Date
				while (true) {
					try {
						date = JOptionPane.showInputDialog(
								"Please enter the player's draft date (format: YYYYMMDD, e.g. \"20150710\").");
						if (date == null) {
							System.exit(0);
						} else if (date.length() != 8) {
							throw new Exception("Please use format YYYYMMDD");
						}

						Calendar cal = Calendar.getInstance();
						int year = Integer.parseInt(date.substring(0, 4)),
								month = Integer.parseInt(date.substring(4, 6)),
								day = Integer.parseInt(date.substring(6, 8));
						cal.set(year, month, day);
						String newDate = Integer
								.toString(cal.get(Calendar.YEAR))
								+ padZeros(Integer
										.toString(cal.get(Calendar.MONTH)), 2)
								+ padZeros(
										Integer.toString(
												cal.get(Calendar.DAY_OF_MONTH)),
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
								"There was a problem with your entry: "
										+ e.getMessage(),
								"Uh oh", JOptionPane.ERROR_MESSAGE);
						continue;
					}
				}

				String result = id + name + team + skill + date;

//				JOptionPane.showMessageDialog(null,
//						"Here's your string: " + result);
				int bytePos = intId > 0 ? (intId - 1) * MAX_RECORD_LENGTH : 0;

				int bytepos = intId == 0 ? 0 : (intId - 1) * MAX_RECORD_LENGTH;

				try {
					store.seek(bytePos);
					store.writeUTF(result);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null,
							"There was a problem writing to the file: "
									+ e.getMessage(),
							"Uh oh", JOptionPane.ERROR_MESSAGE);
				}
			} else if (cmd.compareToIgnoreCase("old") == 0) {
				String id = "", name = "", team = "", skill = "", date = "";

				int intId = 0;
				while (true) {
					try {
						id = JOptionPane.showInputDialog(
								"Please enter an ID to retrieve "
										+ "between 1 and 20, inclusively.");
						intId = Integer.parseInt(id);
						if (id == null) {
							System.exit(0);
						} else if (intId > 20 || intId < 1) {
							throw new Exception(
									"Number must be between 1 and 20.");
						} else {
							id = padLeft(id, 5);
							break;
						}
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null,
								"There was a problem with your entry: "
										+ e.getMessage(),
								"Uh oh", JOptionPane.ERROR_MESSAGE);
						continue;
					}
				}

				int bytePos = intId > 0 ? (intId - 1) * MAX_RECORD_LENGTH : 0;
				String result = "";
				try {
					store.seek(bytePos);
					result = store.readUTF();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null,
							"There was a seeking/reading the file: "
									+ e.getMessage(),
							"Uh oh", JOptionPane.ERROR_MESSAGE);
					System.exit(1);
				}
				name = result.substring(5, 31);
				team = result.substring(31, 57);
				skill = result.substring(57, 63);
				date = result.substring(63);

				JOptionPane.showMessageDialog(null,
						"Name: " + name.trim() + "\nTeam: " + team.trim() + "\nSkill: "
								+ skill.trim() + "\nDraft date: " + date.trim(),
						"Results", JOptionPane.INFORMATION_MESSAGE);
//				System.out.println(name);
//				System.out.println(team);
//				System.out.println(skill);
//				System.out.println(date);

			}
		}
		System.exit(0);

	}

}
