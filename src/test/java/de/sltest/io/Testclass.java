package de.sltest.io;

import java.io.IOException;

import de.slfw.io.file.FileOperationListener;
import de.slfw.io.file.FileOperator;
import de.slfw.io.compare.FileCompare;



public class Testclass implements FileOperationListener{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Testclass cl = new Testclass();
		FileOperator.addListener(cl);
		cl.start();
	}
		/*
		FileOperator.printList(FileOperator.listOnlyFilesInDirectory("testfolder"));
		FileOperator.renameExtentions("testfolder", "txt",true);
		FileOperator.printList(FileOperator.listOnlyFilesInDirectory("testfolder"));
		*/

	private void start(){
		
		
			FileOperator.numberFiles("testfolder2/directory1",true);

		
		
		/*
		File f = new File("C:/freigabe/blafu.txt");
		try {
			f.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileOperator.renameExtention(f, "sla");
		*/
		
		/*
		File f = new File("C:/test/test2/test3/test.txt");
		
		try {
			
			FileOperator.createNewFile("C:/test/test2.txt", "halloUnd\n So");
			
			f.createNewFile();
			System.out.println(f.getPath());
			System.out.println(f.getParent());
			System.out.println(f.getAbsolutePath());
			System.out.println(f.compareTo(new File("C:/test/test2/test.txt")));
			System.out.println(f.getFreeSpace());
			System.out.println(f.getUsableSpace());
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		/*
			Vector<String> vec = new Vector<String>();
			vec.add("a");
			vec.add("b");
			vec.add("c");
			
			for(String a : vec){
				vec.remove("a");
			}
			
			*/
		//FileOperator.getAllFiles("testfolder");
		
			//FileOperator.getAllLinesWithString(new File("C:/Users/alex/test/test2.txt"),new File("C:/Users/alex/test/test.txt"), " super ", 200000, this);
			//FileOperator.printList(FileOperator.listAllFilesInDirectory("D:/Java/MyFramework/SlangersFW/testfolder"));
			
			//FileOperator.getAllLinesWithString(new File("C:/Users/alex/test/MIKROTYP AMaster_073_Testlieferung.dat"),new File("C:/Users/alex/test/test.txt"), " N�rnberg ",  this);
			//FileOperator.getNumberOfLinesWithString(new File("C:/Users/alex/test/MIKROTYP AMaster_073_Testlieferung.dat"),new File("C:/Users/alex/test/test.txt"), " N�rnberg ",900,  this);
			
			//System.out.println("abcde.txt".indexOf(".txt"));
			//-->FileOperator.printList(FileOperator.getAllFilesWithExtention("testfolder", ".sla"));
		/*
			try {
				FileOperator.readFileByBytes(new File("testfolder/document1.sla"),new File("testfolder/document1_1.sla"),0, 13000000);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("bla");
				e.printStackTrace();
			}
		*/
			//System.out.println(FileOperator.compare(new File("testfolder/document1.sla"), new File("testfolder/document2.sla")));
			
			
//			try {
//				System.out.println("testfolder <--> testfolder2");
//				FileCompare.printMissmatches(FileCompare.compareDirectories("testfolder", "testfolder2"));
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			System.out.println("----------blubbb------------------");
			try {
				System.out.println("testfolder <--> testfolder2");
				FileCompare.printMissmatches(FileCompare.compareDirectories("C:\\Users\\b060560\\Desktop\\cache\\nvg-web.war", "C:\\Users\\b060560\\Desktop\\cache\\nvg-web.war_alt", false));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

	public void fire(String msg) {
		System.out.println(msg);
	}

	public void fire(String msg, String type) {
		System.out.println(msg + " " + type);
	}

}
