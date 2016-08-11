package org.cc.foo;

/**
 * 程序入口
 * @author cc
 *
 */
public class Main {
	
	public static void main(String[] args) {
		
		LinkmanGUI gui=LinkmanGUI.getInstance();
		
		while(true){
			gui.showBanner();
			
			int choose=gui.readChoose();

			switch (choose) {
			case 1:
				gui.add();
				break;
			case 2:
				gui.delete();
				break;
			case 3:
				gui.modify();
				break;
			case 4:
				gui.queryAll();
				break;
			case 5:
				gui.queryByName();
				break;
			case 6:
				gui.exit();
				break;
			default:
				gui.showErrorMessage();
			}
			
			System.out.println();
		}
	}
}
