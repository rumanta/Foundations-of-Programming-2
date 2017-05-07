import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
	private Tim[] team;
	private Tim[][] match;
	private Pemain[] listPlayer;
	private boolean initState;
	private boolean finish;

	String[] arrayOfTeamName = {"Gajah", "Rusa", "Belalang", "Kodok", "Kucing",
            "Tupai", "Rajawali", "Siput", "Kumbang", "Semut", "Ular", "Harimau",
            "Kuda", "Serigala", "Panda", "Kadal", "Ayam", "Bebek"
            };
	String[] arrayOfPlayerName = {"Arnold", "Kaidou", "Chopper", "Pica", "Enel",
              "Zoro", "Pedro", "Beckman", "Ace", "Shiryu", "Sakazuki", "Marco",
              "Garp", "Dadan", "Sengoku", "Sanji", "Magellan", "Dragon", "Sabo",
              "Smoker", "Luffy", "Franky", "Borsalino", "Buggy", "Crocodile",
              "Shanks", "Yasopp", "Coby", "Burgess", "Usopp", "Law", "Kid", "Bege",
              "Yonji", "Doffy", "Edward", "Mihawk", "Shanks", "Jinbei", "Killer",
              "Robin", "Roger", "Shiki", "Rayleigh", "Robb", "Kuma", "Moriah",
              "Teach", "Pagaya", "Conis", "Hachi", "Brook", "Kinemon", "Vergo",
              "Caesar", "Momo", "Mohji", "Cabaji", "Jozu", "Vista", "Doma", "Augur",
              "Drake", "Ivankov", "Charlotte", "Bellamy", "Demaro", "Dorry",
              "Brogy", "Kuro", "Zeff", "Gin", "Pearl", "Alvide", "Apoo", "Kuzan",
              "Nami", "Brook", "Hancock", "Koala"
              };
	
	int gameCounter=0;
	
	public void setInitState(boolean initState){
		this.initState = initState;
	}

	public boolean getInitState(){
		return initState;
	}
	
	public Tim[] getTeam(){
		return team;
	}
	public Pemain[] getListPlayer() {
		return listPlayer;
	}

	public void setListPlayer(Pemain[] listPlayer) {
		this.listPlayer = listPlayer;
	}

	public void init(int numberOfTeam){
	
		team = new Tim [numberOfTeam];										//arrTeam[0]=Team1, arrTeam[1]=Team2...
		listPlayer = new Pemain[numberOfTeam*5];
		ArrayList<String> usedNames = new ArrayList<String>(4);		//Penampung untuk nama yang sudah terpakai
		ArrayList<Integer> usedNumber = new ArrayList<Integer>(4);
		ArrayList<String> usedTeams = new ArrayList<String>(numberOfTeam);
		
		int n=0;															//Index untuk listPlayer
		for(int order=0; order < numberOfTeam; order++){					//Looping instansiasi tim
			String namaTim;
			while(true){
				int randomTeam = ThreadLocalRandom.current().nextInt(0, 18);
				namaTim = arrayOfTeamName[randomTeam];
				if(!usedTeams.contains(namaTim)) break;						//Looping ulang jika nama tim sudah diambil
			}
			team[order] = new Tim(namaTim);
			usedTeams.add(namaTim);
			
			for(int i=0; i < 5; i++){										//Instansiasi 5 pemain untuk tim
				String nama;
				int playerNum;
				
				while(true){
					int randomPlayer = ThreadLocalRandom.current().nextInt(0, 80);
					nama = arrayOfPlayerName[randomPlayer];
					if (!usedNames.contains(nama)) break;					//Looping ulang jika nama sudah terpakai
				}
				while(true){
					playerNum = ThreadLocalRandom.current().nextInt(1, 100);
					if(!usedNumber.contains(playerNum)) break;				//Looping ulang jika nomor sudah diambil
				}
				
				
				team[order].getDaftarPemain()[i] = new Pemain(nama, playerNum, namaTim);  //Instansiasi pemain ke dalam tim
				listPlayer[n] = team[order].getDaftarPemain()[i];
				usedNames.add(nama); usedNumber.add(playerNum);
				n++;
			}	
		}
	}
	
	public void matchingUP(int numberOfTeam){								//Method untuk membentuk bagan pertandingan
		
		int combinations = numberOfTeam * (numberOfTeam-1)/2;
		match = new Tim[combinations][2];
		
		for(int i=0,j=0; i < team.length; i++){								//Meng-assign satu per satu tim vs tim
			for(int k=i; k < team.length; k++){
				if(k+1 == team.length) break;								//Misal ada 4 tim, maka akan ada 6 pertandingan
				match[j][0] = team[i];										//[[Tim1 vs Tim2], [Tim1 vs Tim3], [Tim1 vs Tim4],
				match[j][1] = team[k+1];									//[Tim2 vs Tim3], [Tim2 vs Tim4], [Tim3 vs Tim4]]
				j++;
			}
		}
	}
	
	public void nextGameAuto(){
		int playingTeam = 0;
		int statistics[][] = new int [2][4];                                //Membuat penyimpan statistik utk 1 pertandingan
		
		while(playingTeam <= 1){
			
			//RANDOM Gol
			int gol = ThreadLocalRandom.current().nextInt(0, 6);
			statistics[playingTeam][0] = gol;
			
			match[gameCounter][playingTeam].setJumlahGol(gol);
			if(playingTeam == 0){
				match[gameCounter][playingTeam+1].setJumlahKebobolan(gol);
			}
			else{
				match[gameCounter][playingTeam-1].setJumlahKebobolan(gol);
			}
			if(gol != 0){
				for(int i=0; i <= gol; i++){                                //Merandom pemain yang mendapatkan gol
					int player = ThreadLocalRandom.current().nextInt(0, 5);
					match[gameCounter][playingTeam].getDaftarPemain()[player].setJumlahGol(1);
				}
			}
			
			//RANDOM Kartu Kuning
			int kuning = ThreadLocalRandom.current().nextInt(0, 4);
			statistics[playingTeam][1] = kuning;
			if(kuning != 0){
				int player;
				for(int i=0; i <= kuning; i++){
				    //Merandom pemain yang belum mendapatkan kartu kuning 2 kali
					while(true){
						player = ThreadLocalRandom.current().nextInt(0, 5);
						if(match[gameCounter][playingTeam].getDaftarPemain()[player].getTemporaryKuning() < 2)
							break;
					}
					match[gameCounter][playingTeam].getDaftarPemain()[player].setKartuKuning(1);
					match[gameCounter][playingTeam].getDaftarPemain()[player].setJumlahPelanggaran(1);
					match[gameCounter][playingTeam].getDaftarPemain()[player].setTemporaryKuning(
							match[gameCounter][playingTeam].getDaftarPemain()[player].getTemporaryKuning()+1
							);
					//Pemain mendapat km jika sudah terkena 2 kk dalam 1 pertandingan
					if(match[gameCounter][playingTeam].getDaftarPemain()[player].getTemporaryKuning() == 2){
						match[gameCounter][playingTeam].getDaftarPemain()[player].setKartuMerah(1);
						statistics[playingTeam][2] += 1;
					}
				}
			}
			
			//RANDOM Kartu Merah
			int merah = ThreadLocalRandom.current().nextInt(0, 3);
			statistics[playingTeam][2] = merah;
			if(merah != 0){
				for(int i=0; i <= merah; i++){
					int player;
					//Merandom pemain yang belum terkena kartu kuning 2 kali atau kartu merah
					while(true){
						player = ThreadLocalRandom.current().nextInt(0, 5);
						if(match[gameCounter][playingTeam].getDaftarPemain()[player].getTemporaryKuning() < 2) 
							if(match[gameCounter][playingTeam].getDaftarPemain()[player].getTemporaryMerah() == 0) 
								break;
					}
					match[gameCounter][playingTeam].getDaftarPemain()[player].setKartuMerah(1);
					match[gameCounter][playingTeam].getDaftarPemain()[player].setTemporaryMerah(1);
					match[gameCounter][playingTeam].getDaftarPemain()[player].setJumlahPelanggaran(1);
				}
			}
			
			//RANDOM Pelanggaran
			int pelanggaran = ThreadLocalRandom.current().nextInt(0, 6);
			statistics[playingTeam][3] = pelanggaran;
			if(pelanggaran != 0){
				for(int i=0; i <= pelanggaran; i++){
					int player = ThreadLocalRandom.current().nextInt(0, 5);
					match[gameCounter][playingTeam].getDaftarPemain()[player].setJumlahPelanggaran(1);
				}
			}
			playingTeam++;
		}
		//Menentukan tim yang menang dan kalah atau seri berdasarkan golnya
		if(statistics[0][0] > statistics[1][0]){
			match[gameCounter][0].setJumlahMenang(1);
			match[gameCounter][1].setJumlahKalah(1);
		}
		else if(statistics[0][0] < statistics[1][0]){
			match[gameCounter][1].setJumlahMenang(1);
			match[gameCounter][0].setJumlahKalah(1);
		}
		else {
			match[gameCounter][0].setJumlahSeri(1);
			match[gameCounter][1].setJumlahSeri(1);
		}
		//Print out statistik pertandingan dari 2 tim
		System.out.println(String.format("\nStatistika Pertandingan Tim %s VS Tim %s", match[gameCounter][0].getNamaTim(), match[gameCounter][1].getNamaTim()));
		for(int i=0; i <= 1; i++){
			System.out.println(String.format("\n%-14s: %s" , "Tim", match[gameCounter][i].getNamaTim()));
	    	System.out.println(String.format("%-14s: %s" , "Gol",statistics[i][0]));
	    	System.out.println(String.format("%-14s: %s" , "Pelanggaran", statistics[i][3]));
	    	System.out.println(String.format("%-14s: %s" , "Kartu Kuning", statistics[i][1]));
	    	System.out.println(String.format("%-14s: %s" , "Kartu Merah", statistics[i][2]));
		}
		for(int i=0; i <= 1; i++)	//Mereset perolehan kartu kuning & merah sementara setiap pemain
			for(int j=0; j < match[gameCounter][i].getDaftarPemain().length; j++){
				match[gameCounter][i].getDaftarPemain()[j].setTemporaryKuning(0);
				match[gameCounter][i].getDaftarPemain()[j].setTemporaryMerah(0);
			}
		if(gameCounter == match.length-1)
			this.setFinished();
		Liga liga = new Liga(team, listPlayer);
		liga.sortTeam();
		gameCounter++;
	}
	
	public void nextGameManual(String[] arg){
		int cekGol=0; int cekTim=0; int cekPlayer=0;
		int[][] statistics = new int[2][4];                                     //Membuat penyimpanan statistik 1 pertandingan
		
		for(int m=1; m < arg.length; m++){                                      //Mengecek apakah ada argumen "-g"
			String[] parts = arg[m].split(" ");
			for(int cek=0; cek < arg.length; cek++){
				if(parts[0].equals("g")) 
					cekGol++;
			}
		}
		if(cekGol != 0){
			for(int i=1; i < arg.length; i++){
				String[] part; String namaTim; int nomorPemain;
				try{
					part = arg[i].split(" ");
					namaTim = part[1];
					nomorPemain = Integer.parseInt(part[2]);
				}catch(ArrayIndexOutOfBoundsException e){
					System.err.println("Terdapat kesalahan input parameter");
					break;
				}
				for(int j = 0; j < team.length; j++){
					if (team[j].getNamaTim().equals(namaTim)){
						cekTim++;
						for(int k = 0; k < 5; k++){
							if(team[j].getDaftarPemain()[k].getNomorPemain() == nomorPemain){
								cekPlayer++;
								//===Argumen Gol===
								if(part[0].equals("g")){
									team[j].getDaftarPemain()[k].setJumlahGol(1);
									team[j].setJumlahGol(1);

									//input gol ke dalam penyimpanan statistik
									if(match[gameCounter][0].getNamaTim().equals(namaTim))
										statistics[0][0] += 1;
									else
										statistics[1][0] += 1;
									
									if(match[gameCounter][0].getNamaTim().equals(namaTim))
										match[gameCounter][1].setJumlahKebobolan(1);
									else
										match[gameCounter][0].setJumlahKebobolan(1);
								}
								//===Argumen Kartu Kuning===
								else if(part[0].equals("kk")){
									team[j].getDaftarPemain()[k].setTemporaryKuning(team[j].getDaftarPemain()[k].getTemporaryKuning()+1);
									if(team[j].getDaftarPemain()[k].getTemporaryKuning() <= 2){     //Jika pemain belum mendapatkan 2 kk
										if(team[j].getDaftarPemain()[k].getTemporaryMerah() == 0){  //Jika pemain belum mendapatkan kk
											team[j].getDaftarPemain()[k].setKartuKuning(1);         //Maka pemain boleh mendapat kk
											team[j].getDaftarPemain()[k].setJumlahPelanggaran(1);

											//Input kk ke dalam penyimpan statistik
											if(match[gameCounter][0].getNamaTim().equals(namaTim)){
												statistics[0][1] += 1;
												statistics[0][3] += 1;
											}
											else{
												statistics[1][1] += 1;
												statistics[1][3] += 1;
											}
										}
									}
									//Jika pemain sudah mendapatkan 2 kk, maka mendapatkan 1 km
									if(team[j].getDaftarPemain()[k].getTemporaryKuning() == 2){
										if(team[j].getDaftarPemain()[k].getTemporaryMerah() == 0){
											team[j].getDaftarPemain()[k].setKartuMerah(1);
											team[j].getDaftarPemain()[k].setTemporaryMerah(1);
											if(match[gameCounter][0].getNamaTim().equals(namaTim))
												statistics[0][2] += 1;
											else
												statistics[1][2] += 1;
										}
									}
									
								}
								//===Argumen Kartu Merah===
								else if(part[0].equals("km")){
								    //Jika pemain belum pernah mendapatkan km dalam pertandingan
									if(team[j].getDaftarPemain()[k].getTemporaryMerah() == 0){
										team[j].getDaftarPemain()[k].setKartuMerah(1);
										team[j].getDaftarPemain()[k].setTemporaryMerah(1);
										team[j].getDaftarPemain()[k].setJumlahPelanggaran(1);

										//Input kartu merah ke dalam penyimpanan statistik
										if(match[gameCounter][0].getNamaTim().equals(namaTim)){
											statistics[0][2] += 1;
											statistics[0][3] += 1;
										}
										else{
											statistics[1][2] += 1;
											statistics[1][3] += 1;
										}
									}
								}	
								else if(part[0].equals("p")){
									team[j].getDaftarPemain()[k].setJumlahPelanggaran(1);
									
									if(match[gameCounter][0].getNamaTim().equals(namaTim))
										statistics[0][3] += 1;
									else
										statistics[1][3] += 1;
								}
							}
						}
					}
				}
			}
		}
		for(int i=0; i <= 1; i++)	//Mereset perolehan kartu kuning & merah sementara setiap pemain
			for(int j=0; j < match[gameCounter][i].getDaftarPemain().length; j++){
				match[gameCounter][i].getDaftarPemain()[j].setTemporaryKuning(0);
				match[gameCounter][i].getDaftarPemain()[j].setTemporaryMerah(0);
			}
		if(cekGol != 0 && ((arg.length-1) == cekTim) && ((arg.length-1) == cekPlayer)){	//Cek jika tidak ada kesalahan input dari user
			//Menentukan tim yang menang dan kalah atau seri berdasarkan selisih gol
            if(statistics[0][0] > statistics[1][0]){
				match[gameCounter][0].setJumlahMenang(1);
				match[gameCounter][1].setJumlahKalah(1);
			}
			else if(statistics[0][0] < statistics[1][0]){
				match[gameCounter][1].setJumlahMenang(1);
				match[gameCounter][0].setJumlahKalah(1);
			}
			else {
				match[gameCounter][0].setJumlahSeri(1);
				match[gameCounter][1].setJumlahSeri(1);
			}
			//Print out statistik pertandingan
			System.out.println(String.format("\nStatistika Pertandingan Tim %s VS Tim %s", match[gameCounter][0].getNamaTim(), match[gameCounter][1].getNamaTim()));
			for(int i=0; i <= 1; i++){
				System.out.println(String.format("\n%-14s: %s" , "Tim", match[gameCounter][i].getNamaTim()));
		    	System.out.println(String.format("%-14s: %s" , "Gol",statistics[i][0]));
		    	System.out.println(String.format("%-14s: %s" , "Pelanggaran", statistics[i][3]));
		    	System.out.println(String.format("%-14s: %s" , "Kartu Kuning", statistics[i][1]));
		    	System.out.println(String.format("%-14s: %s" , "Kartu Merah", statistics[i][2]));
			}
			if(gameCounter == match.length-1)
				this.setFinished();
			Liga liga = new Liga(team, listPlayer);
			liga.sortTeam();
			gameCounter++;
		}
		else{
		    //Handling error jika input salah
			if(cekGol == 0)
				System.err.println("Argumen gol \"-g\" harus disertakan dalam perintah nextGame mode manual");
			else{
				if(arg.length-1 != cekTim)
					System.err.println("Terdapat kesalahan input tim yang bermain");
				if(arg.length-1 != cekPlayer)
					System.err.println("Terdapat kesalahan input nomor pemain");
			}
		}
			
	}
	
	public void nextGameAll(){
		while(gameCounter < match.length){
			nextGameAuto();
		}
	}
	public void showGame(){
		System.out.println(String.format("%s VS %s", match[gameCounter][0].getNamaTim(), match[gameCounter][1].getNamaTim()));
	}
	
	public void showTim(String namaTim){
		int counter=0;												//Counter untuk mengecek adanya tim yang dicari
		for(int i=0; i < team.length; i++){
			if(team[i].getNamaTim().equals(namaTim)){
				counter++;
				System.out.println(String.format(" %-7s|%5s%-9s|%2s%-5s|%2s%-13s|%2s%-14s|%2s%-13s","Nomor","","Nama","","Gol","","Pelanggaran","","Kartu Kuning","","Kartu Merah"));
				System.out.println("-----------------------------------------------------------------------------");
				for(int j=0; j < 5; j++){
					System.out.println(String.format(" %2s%-5s|%4s%-10s|%3s%-4s|%7s%-8s|%8s%-8s|%7s%-7s",
							"",team[i].getDaftarPemain()[j].getNomorPemain(),"",
							team[i].getDaftarPemain()[j].getNamaPemain(),"",
							team[i].getDaftarPemain()[j].getJumlahGol(),"",
							team[i].getDaftarPemain()[j].getJumlahPelanggaran(),"",
							team[i].getDaftarPemain()[j].getKartuKuning(),"",
							team[i].getDaftarPemain()[j].getKartuMerah()
							));
				}
			}
		}
		if(counter == 0)
			System.out.println("Nama tim tidak dapat ditemukan");
	}
	
	public void showPemain(String namaTim, String pemain){
		int counterTim = 0;								//Counter untuk mengecek adanya tim yang dicari
		int counterPemain = 0;							//Counter untuk mengecek adanya pemain yang dicari
		String nama=""; int nomor=0;
		try{                                            //Menentukan apakah yg dicari adalah nomor atau nama pemain
			nomor = Integer.parseInt(pemain);
		}catch(NumberFormatException e){
			nama = pemain;
		}
		
		if(nomor == 0){
		    //Jika yang dicari adalah nama pemain
			for(int i=0; i < team.length; i++){
				if(team[i].getNamaTim().equals(namaTim)){
					counterTim++;
					for(int j=0; j < 5; j++){
						if(team[i].getDaftarPemain()[j].getNamaPemain().equals(nama)){
							counterPemain++;
							System.out.println(String.format("\n%-14s: %s" , "Nomor", team[i].getDaftarPemain()[j].getNomorPemain()));
					    	System.out.println(String.format("%-14s: %s" , "Nama", nama));
					    	System.out.println(String.format("%-14s: %s" , "Gol", team[i].getDaftarPemain()[j].getJumlahGol()));
					    	System.out.println(String.format("%-14s: %s" , "Pelanggaran", team[i].getDaftarPemain()[j].getJumlahPelanggaran()));
					    	System.out.println(String.format("%-14s: %s" , "Kartu Kuning", team[i].getDaftarPemain()[j].getKartuKuning()));
					    	System.out.println(String.format("%-14s: %s" , "Kartu Merah", team[i].getDaftarPemain()[j].getKartuMerah()));
						}
					}
				}
			}
		}
		else{
		    //Jika yang dicari adalah nomor pemain
			for(int i=0; i < team.length; i++){
				if(team[i].getNamaTim().equals(namaTim)){
					counterTim++;
					for(int j=0; j < 5; j++){
						if(team[i].getDaftarPemain()[j].getNomorPemain() == nomor){
							counterPemain++;
							System.out.println(String.format("\n%-14s: %s" , "Nomor", team[i].getDaftarPemain()[j].getNomorPemain()));
					    	System.out.println(String.format("%-14s: %s" , "Nama", team[i].getDaftarPemain()[j].getNamaPemain()));
					    	System.out.println(String.format("%-14s: %s" , "Gol", team[i].getDaftarPemain()[j].getJumlahGol()));
					    	System.out.println(String.format("%-14s: %s" , "Pelanggaran", team[i].getDaftarPemain()[j].getJumlahPelanggaran()));
					    	System.out.println(String.format("%-14s: %s" , "Kartu Kuning", team[i].getDaftarPemain()[j].getKartuKuning()));
					    	System.out.println(String.format("%-14s: %s" , "Kartu Merah", team[i].getDaftarPemain()[j].getKartuMerah()));
						}
					}
				}
			}
		}
		//Handling error jika nama tim atau nama pemain atau nomor pemain tidak ada
		if(counterTim == 0)
			System.err.println(String.format("Nama tim %s tidak dapat ditemukan",namaTim));
		else if(counterPemain == 0)
			if(nomor == 0)
				System.err.println(String.format("Pemain dengan nama %s tidak dapat ditemukan di tim %s",nama,namaTim));
			else
				System.err.println(String.format("Pemain dengan nomor %d tidak dapat ditemukan di tim %s",nomor,namaTim));
	}
	
	public void setFinished(){
		this.finish = true;
	}
	public boolean isFinished(){
		return finish;
	}
	
	public void showKlasemen(){
		Liga liga = new Liga(team, listPlayer);
		liga.sortTeam();
		System.out.println(String.format(" %-10s|%2s%-10s|%2s%-12s|%2s%-18s|%2s%-8s|%2s%-7s|%2s%-6s|%2s%-6s",
				"Peringkat","","Nama Tim","","Jumlah Gol","","Jumlah Kebobolan","","Menang","","Kalah","","Seri","","Poin"));
		System.out.println("----------------------------------------------------------------------------------------------------");
		int peringkat=1;
		//Iterasi print untuk setiap tim dalam liga
		for(Tim tim : liga.getSortedTeam()){
			System.out.println(String.format(" %5s%-5s|%2s%-10s|%6s%-8s|%10s%-10s|%4s%-6s|%4s%-5s|%3s%-5s|%3s%-5s",
					"",peringkat,"",
					tim.getNamaTim(),"",
					tim.getJumlahGol(),"",
					tim.getJumlahKebobolan(),"",
					tim.getJumlahMenang(),"",
					tim.getJumlahKalah(),"",
					tim.getJumlahSeri(),"",
					tim.getJumlahPoin()
					));
			peringkat++;
		}
	}
	
	public void showPencetakGol(){
		Liga liga = new Liga(team, listPlayer);
		liga.sortPencetakGol();
		System.out.println(String.format(" %-10s|%2s%-13s|%2s%-10s|%2s%-12s",
				"Peringkat","","Nama Pemain","","Nama Tim","","Jumlah Gol"));
		System.out.println("-------------------------------------------------------");
		int peringkat=1;
		for(int i=0; i < 10; i++){
			System.out.println(String.format(" %4s%-6s|%4s%-11s|%2s%-10s|%6s%-8s",
					"",peringkat,"",
					liga.getSortedGol().get(i).getNamaPemain(),"",
					liga.getSortedGol().get(i).getNamaTim(),"",
					liga.getSortedGol().get(i).getJumlahGol()
					));
			peringkat++;
		}
	}
}


