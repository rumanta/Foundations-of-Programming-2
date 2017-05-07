import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

abstract public class Owjek{
	private int costPerKm;

// ===== Variabel yang digunakan untuk shortest path =========
	private final static int maxValue = 5000; //Infinity Value
	private int shortest = maxValue;
	private int counter = 0;
	private char[][] resultMap = null;
	private int jarak;
	private boolean pathFound;
// ===========================================================

	public Owjek(int costPerKm){
		this.costPerKm = costPerKm;
	}

	public int getCostPerKm() {
		return costPerKm;
	}

	public void setCostPerKm(int costPerKm) {
		this.costPerKm = costPerKm;
	}

	public boolean isPathFound() {
		return pathFound;
	}

	public int getJarak(){
		return jarak * 100;
	}

	//Method untuk menyimpan hasil map sementara dari hasil shortest path
	public void copyMap(Map map){
		resultMap = new char[50][100];
		for(int i=0; i < 50; i++){
			for(int j=0; j < 100; j++){
				resultMap[i][j] = map.get(i,j);
			}
		}
	}
	//Method untuk mengkonversi format angka menjadi bentuk rupiah
	public String getRupiah(double cost){
		DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
		DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

		formatRp.setCurrencySymbol("Rp ");
		formatRp.setMonetaryDecimalSeparator(',');
		formatRp.setGroupingSeparator('.');

		kursIndonesia.setDecimalFormatSymbols(formatRp);
		return String.format("%s", kursIndonesia.format(cost));
	}

	//Abstract method untuk di override oleh subclasses
	abstract public String getCost(double cost);
	abstract public double getPromo(double jarak);

	//Method untuk mengkonversi dari koordinat map menjadi index pada array map
	public int getKoor(String posisi){
		int koor=0;
		try{
			//Jika koordinat yang dituju adalah A - E
			if(posisi.charAt(0) >= 65 && posisi.charAt(0) <= 69){
				for(int i=0; i < posisi.charAt(0)-65; i++){
					koor += 10;
				}
				koor += Integer.parseInt(posisi.substring(1,2));
				return koor;
			}
			//Jika koordinat yang dituju adalah Q - Z
			else if(posisi.charAt(0) >= 81 && posisi.charAt(0) <= 90){
				for(int i=0; i < posisi.charAt(0)-81; i++){
					koor += 10;
				}
				koor += Integer.parseInt(posisi.substring(1,2));
				return koor;
			}
		}
		//Error jika koordinat berupa (ABCDE) => B tidak bisa dikonversi menjadi integer
		catch(NumberFormatException e){
			return -1;
		}
		return -1;
	}

	//Method untuk mengeksekusi pencarian rute untuk owjek
	public String findPath(String start, String finish){

		if(start.length()==4 && finish.length()==4){
			Map map = new Map();

			//Inisiasi koordinat
			int startX = getKoor(start.substring(0,2));
			int startY = getKoor(start.substring(2));
			int finishX = getKoor(finish.substring(0,2));
			int finishY = getKoor(finish.substring(2));

			//Cek apakah koordinat valid
			if(startX == -1 || startY == -1 || finishX == -1 || finishY == -1)
				return "Koordinat tidak valid";

			//Cek tembok atau bukan
			if(map.get(startX,startY) == '#')
				return "Input tidak valid: " + start + " bukan jalan";
			else if(map.get(finishX,finishY) == '#')
				return "Input tidak valid: " + finish + " bukan jalan";

			//Set finish point
			map.set('F', finishX, finishY);

			//Eksekusi method rekursif shortestPath
			jarak = shortestPath(startX, startY, counter, map);

			if(resultMap == null){
				return "Tidak ada jalan";
			}
			//Set start point
			resultMap[startX][startY] = 'S';
			pathFound = true;

			return getMap();
		}
		return "Format koordinat salah";
	}

	//Method untuk mereturn map hasil shortestPath
	public String getMap(){
		String map = "";
		int jjPosAlphabet = 81;
        int jjPosNumber = 0;

        //Mencetak huruf serta angka koordinat pada baris atas
        map += "   ";
        for (int j = 0; j<100; j++){
            if (j % 10 == 0)
            	map += (char)jjPosAlphabet++;
            else
            	map += " ";
        }
        map += "\n";

        map += "   ";
        for (int j = 0; j<100; j++){
            map += jjPosNumber++;
            if (jjPosNumber >= 10) jjPosNumber = 0;
        }

        map += "\n";

        //Mencetak huruf serta angka pada kolom paling kiri
        int iiPosAlphabet = 65;
        int iiPosNumber = 0;

        for (int i = 0; i<50; i++){

            if (i % 10 == 0) map += (char)iiPosAlphabet++;
            else map += " ";

            map += iiPosNumber++;
            if (iiPosNumber >= 10) iiPosNumber = 0;

            map += " ";

            //Mencetak map
            for (int j = 0; j<100; j++){
                map += resultMap[i][j];
            }
            map += "\n";
        }

        return map;
	}

	public int shortestPath(int x, int y, int count, Map map){

		counter++;

		//Base case - if finish point
		if(map.get(x,y) == 'F'){

			shortest = count;
			this.copyMap(map);
			return count;
		}

		//Reject case - if we hit a wall
		if(map.get(x,y) == '#' || map.get(x,y) == '.'){
			return maxValue;
		}
		//Reject case - if we already have shorter path
		if(count == shortest){
			return maxValue;
		}

		//Menandai jalur yang telah dilangkahi
		map.set('.',x,y);

		//Set titik awal dan titik2 tujuan menjadi nilai infinite
		int result = maxValue;
		int new_result = maxValue;

		//Langkah ke atas
		new_result = shortestPath(x-1,y,count+1,map);
		if(new_result < result)
			result = new_result;

		//Langkah ke kanan
		new_result = shortestPath(x,y+1,count+1,map);
		if(new_result < result)
			result = new_result;

		//Langkah ke bawah
		new_result = shortestPath(x+1,y,count+1,map);
		if(new_result < result)
			result = new_result;

		//Langkah ke kiri
		new_result = shortestPath(x,y-1,count+1,map);
		if(new_result < result)
			result = new_result;

		//Tidak jadi menandai jalur jika tidak ada jalan
		map.set(' ',x,y);

		if(result < maxValue){
			return result;
		}

		//Jika jalan buntu maka putar balik
		return maxValue;
	}
}
