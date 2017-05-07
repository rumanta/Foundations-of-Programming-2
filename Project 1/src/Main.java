import java.util.Scanner;

public class Main {
	
	public static void main(String[]args){
		Scanner input = new Scanner(System.in);
		Game game = new Game();
		while(game.isFinished() == false){
			System.out.print("\nWELCOME...\n[LIGAF12]=> ");
			String command = input.nextLine();
			String[] part = command.split(" ");
			
			//===============PERINTAH INIT X===============
			if (part[0].equals("init")){
				if(!game.getInitState()){
					int numberOfTeam=0;
					
					try{
					numberOfTeam = Integer.parseInt(part[1]);
					}catch (ArrayIndexOutOfBoundsException e){
						System.err.println("Perintah \"init x\" salah");
					}catch (NumberFormatException e){
						System.err.println("Silahkan gunakan angka untuk \'x\' pada perintah (init x)");
					}
					
					if (numberOfTeam >= 4 && numberOfTeam <= 10){
						game.init(numberOfTeam);
						game.matchingUP(numberOfTeam);
						//System.out.println((java.util.Arrays.toString(game.getTeam())));
						game.setInitState(true);
						System.out.println("Inisiasi tim dan pemain berhasil dilakukan");
					}
					else{
						System.err.println("Silahkan masukkan jumlah tim dalam range 4 - 10");
					}
				}
				else{
					System.err.println("Perintah \"init x\" sudah dilakukan");
				}
			}
	
		
			//===============PERINTAH NEXTGAME===============
			else if (part[0].toLowerCase().equals("nextgame")){
				if(game.getInitState()){
					String[] arg = command.split("-");
					System.out.println(java.util.Arrays.toString(arg));
					if(part.length == 1){
						game.nextGameAuto();
					}
					else if(arg[1].equals("all")){
						if(arg.length == 2){
							game.nextGameAll();
						}
						else{
							System.err.println("Perintah \"nextGame -all\" tidak menerima argumen lain");
						}
					}
					else{
						game.nextGameManual(arg);
					}
				}
				else{
					System.err.println("Perintah \"init x\" harus dijalankan terlebih dahulu");
				}
			}
			//===============PERINTAH SHOW===============
			else if (part[0].equals("show")){
				if(game.getInitState()){
					if(part[1].toLowerCase().equals("nextgame")){
						game.showGame();
					}
					else if(part[1].equals("tim")){
						try{
							game.showTim(part[2]);
						}catch(ArrayIndexOutOfBoundsException e){
							System.err.println("Gunakan perintah \"show tim [namaTim]\"");
						}
					}
					else if(part[1].equals("pemain")){
						try{
							game.showPemain(part[2], part[3]);
						}catch(ArrayIndexOutOfBoundsException e){
							System.err.println("Gunakan perintah \"show pemain [namaTim] [nomorPemain atau namaPemain]\"");
						}
					}
					else if(part[1].equals("klasemen")){
						game.showKlasemen();
					}
					else{
						System.err.println(String.format("Perintah \"%s\" tidak dikenal",part[1]));
					}
				}
				else{
					System.err.println("Perintah \"init x\" harus dijalankan terlebih dahulu");
				}
			}
			else{
				System.err.println("Perintah tidak dikenal");
			}
		}
		input.close();
		Liga liga = new Liga(game.getTeam(), game.getListPlayer());
		liga.sortTeam();
		System.out.println("\nLIGA FASILKOM MUSIM INI TELAH USAI\n");
		System.out.println("CHAMPION: " + liga.getSortedTeam().get(0).getNamaTim() + "\n\n");
		System.out.println("KLASEMEN\n");
		game.showKlasemen();
		System.out.println("\n\nTOP SCORE\n");
		game.showPencetakGol();
		System.out.println("\n\nGOODBYE...");
		
	}
}

