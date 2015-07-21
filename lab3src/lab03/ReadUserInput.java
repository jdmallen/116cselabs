package lab03;

import java.util.Calendar;

import javax.swing.JOptionPane;

public class ReadUserInput implements Runnable {
	private MTQueue _cmdQ;
	
	public ReadUserInput(MTQueue mtq){
		_cmdQ = mtq;
	}
	@Override
	public void run() {
		String cmd = "";
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
							id = MTQueue.padLeft(id, 5);
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
							name = MTQueue.padRight(name, 26);
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
							team = MTQueue.padRight(team, 26);
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
							skill = MTQueue.padLeft(skill, 5);
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
								+ MTQueue.padZeros(Integer
										.toString(cal.get(Calendar.MONTH)), 2)
								+ MTQueue.padZeros(
										Integer.toString(
												cal.get(Calendar.DAY_OF_MONTH)),
										2);
						if (JOptionPane.showConfirmDialog(null,
								"Did you mean " + newDate + "?", "Confirm date",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE) == JOptionPane.NO_OPTION) {
							continue;
						}
						date = MTQueue.padLeft(newDate, 9);
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
				_cmdQ.MTPut(result);
			}

		}
		_cmdQ.MTPut("end");
	}
} 
