package lab03;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.JOptionPane;

public class WriteToFile implements Runnable {
	private MTQueue _cmdQ;
	private static File _data;
	private static RandomAccessFile _store;
	private long _storeLength = 0;

	public WriteToFile(MTQueue mtq) {
		_cmdQ = mtq;
		try {
			if (_data == null) {
				_data = new File(System.getProperty("user.dir")
						+ "\\datafiles\\lab02\\data.txt");
			}
			if (_store == null) {
				_store = new RandomAccessFile(_data, "rw");
				_storeLength = _store.length();
				if (_storeLength < ((MTQueue.MAX_RECORD_LENGTH + 2)
						* MTQueue.RECORD_COUNT)) {
					for (int i = 0; i < 20; ++i) {
						try {
							_store.writeUTF(MTQueue.repeatChar(71, ' '));
						} catch (IOException e) {
							JOptionPane.showMessageDialog(null,
									"There was a problem writing to the file: "
											+ e.getMessage(),
									"Uh oh", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"There was a problem opening the file for writing: "
							+ e.getMessage(),
					"Uh oh", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
	}

	@Override
	public void run() {
		while (true) {
			String strToExecute = _cmdQ.MTGet();
			if (strToExecute == null) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				continue;
			} else if (strToExecute.compareToIgnoreCase("end") == 0) {
				break;
			}
			String idPortion = strToExecute.substring(0, 5);
			int intId = 0;
			try {
				intId = Integer.parseInt(idPortion.trim());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}

			int bytePos = intId > 0 ? (intId - 1) * MTQueue.MAX_RECORD_LENGTH
					: 0;

			try {
				_store.seek(bytePos);
				_store.writeUTF(strToExecute);
				_store.seek(bytePos);
				System.out.println(_store.readUTF());
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null,
						"There was a problem writing to the file: "
								+ e.getMessage(),
						"Uh oh", JOptionPane.ERROR_MESSAGE);
			}

		}

	}

}
