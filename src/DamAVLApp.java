package QuizGen;

import java.io.*;
import java.util.*;
public class DamAVLApp{

	private static AVLTree<DamAVL> newTree;
	static String[] dam;
	static Scanner filename;
		
	public static void readFile()throws IOException, FileNotFoundException{
		try{
		filename = new Scanner(new FileInputStream("Data.csv"));
		filename.nextLine();
	}
		
		catch(Exception e){
			System.out.println("Not found");
		}

		int counter = 0;
		while(filename.hasNextLine()){
			dam = filename.nextLine().split(",");

			if (dam.length > 42) 
				newTree.insert(new DamAVL(dam[2].trim(), dam[10].trim(), dam[42].trim()));
			else if (dam.length >10)
				newTree.insert(new DamAVL(dam[2].trim(), dam[10].trim(),""));
			else if (dam.length >2)
				newTree.insert(new DamAVL(dam[2].trim(),"",""));
				}
	}

	public static void printAllDams(){
		newTree.inOrder();
	}

	public static void printDam(String dam){
      DamAVL temp = new DamAVL(dam, " ", " ");
		if (newTree.find(temp) !=null)
			newTree.visit(newTree.find(temp));
		else 
			System.out.println("Dam Not Found");
		}


	public static void main (String [] args) throws IOException, FileNotFoundException{
		newTree = new AVLTree<DamAVL>(); 
		readFile();
		if (args.length == 0){
			printAllDams();
		}
		else{
			printDam(args[0]);
		}
		System.out.println();
		System.out.println("instrumentation for insert" + " "+newTree.icount);
		System.out.println("instrumentation for serch" + " "+newTree.ocount);

		
	/*	File file = new File("Output.txt");
		int j = 1;
		while(file.exists()) {
			j++;
			file = new File("Output"+j+".txt");
			}
				
		BufferedWriter output = null;
		try {
			output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"utf-8"));
			output.write(instrumentation());
			}
		catch (IOException e){
			System.out.println("Could not write to file");
			}
		finally {
			if(output !=null){
				output.close();
				}
			}
	}*/
}
}


