import java.util.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.File;

public class Score{


	public static void main(String[] args){
		//Initialisation
		String name_folder = "Score/";
		String name_resultFile = "File/Score.txt";
		File folder = new File(name_folder);
		Writer resultFile = new Writer(name_resultFile);

		//MAIN
		if(folder.exists()){
			//System.out.println("Exists");
			String[] names_file = folder.list();
			int nbFiles = names_file.length;
			String[][] tab_tampon = new String[nbFiles][2];
			//classement des resultats, le tableau enregistre la position du pax
			int[][] classement = new int[nbFiles][2];

			for(int i = 0;i<nbFiles;i++) 
				{	
					//System.out.println(names_file[i]); 
					Reader file = new Reader(name_folder+names_file[i]);
					if(file.read() == -1){
						System.out.println("File "+names_file[i]+" empty");
						String [] result = {"vide","0"};;
						tab_tampon[i] = result;
					}else{
						String line = file.readLine();
						//System.out.println("lecture:"+line);
						//la focntion readline ne lit pas le premiere et parfois les deux premiers caractères c'est inquietant 
						String[] result = line.split(":");
						if(result.length == 2){
							tab_tampon[i] = result;
						}else{
							String[] result_tmp = {line,"0"};
							tab_tampon[i] = result_tmp;
							//System.out.println(" pax n"+i+", name:"+tab_tampon[i][0]+":"+tab_tampon[i][1]);

						}
					}
					//System.out.println(tab_tampon[i][0]);
					file.close();
				}


			String content = "Classement:\n";
			tri_insertion_classement(tab_tampon);
			for(int i=0;i<nbFiles;i++){
				content += "pax n"+i+", name:"+tab_tampon[i][0]+":"+tab_tampon[i][1]+"\n";
			}
			System.out.println(content);
			resultFile.write(content);
		}
		resultFile.close();
			
	}

	public static void tri_insertion_classement(String[][] tab){
		int i,j;
		String en_cours0;
		String en_cours1;

		for(i=1;i<tab.length;i++){
			en_cours0 = tab[i][0];
			en_cours1 = tab[i][1];
			for(j=i;j>0 && Integer.parseInt(tab[j-1][1]) < Integer.parseInt(en_cours1);j--){
				tab[j][0] = tab[j-1][0];
				tab[j][1] = tab[j-1][1];
			}
			tab[j][0] = en_cours0;
			tab[j][1] = en_cours1;
		}
	}
}