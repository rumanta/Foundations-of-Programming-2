import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;

public class Liga {
	private ArrayList<Tim> sortedTeam;
	private Tim[] listTim;
	private ArrayList<Pemain> sortedGol;
	private Pemain[] listPemain;
	
	public Liga(Tim[] team, Pemain[] listPlayer){
		this.listTim = Arrays.copyOf(team, team.length);					//Membuat copy isian array team
		this.listPemain = Arrays.copyOf(listPlayer, listPlayer.length);		//Membuat copy isian array pemain
		this.sortedTeam = new ArrayList<Tim>(this.listTim.length);			//Inisiasi arraylist tim
		this.sortedGol = new ArrayList<Pemain>(this.listPemain.length);		//Inisiasi arraylist pemain
	}


	public ArrayList<Pemain> getSortedGol() {
		return sortedGol;
	}


	public void setSortedGol(ArrayList<Pemain> sortedGol) {
		this.sortedGol = sortedGol;
	}


	public ArrayList<Tim> getSortedTeam() {
		return sortedTeam;
	}


	public void setSortedTeam(ArrayList<Tim> sortedTeam) {
		this.sortedTeam = sortedTeam;
	}
	public void sortPencetakGol(){
		//Iterasi pemasukkan pemain ke dalam arraylist pemain
		for(Pemain pemain : listPemain){
			this.sortedGol.add(pemain);
		}
		//Sorting menggunakan collections
		Collections.sort(sortedGol, new GolComparator());
	}
	
	public void sortTeam(){
		//Iterasi pemasukkan tim ke dalam arraylist tim
		for(Tim tim : listTim){
			this.sortedTeam.add(tim);
		}
		//Sorting menggunakan collections
		Collections.sort(sortedTeam, new TimComparator());
	}
}

//Kelas yang mengimplementasikan method compare dari kelas Comparator
class TimComparator implements Comparator<Tim> {
    @Override		//Memodifikasi method compare dalam kelas Comparator
    public int compare(Tim o1, Tim o2) {
    	if(o1 != null && o2 != null){
	    	if(o2.getJumlahPoin() - o1.getJumlahPoin() != 0)  //Urutan berdasarkan banyaknya jumlah poin tim
	    		return o2.getJumlahPoin() - o1.getJumlahPoin();
	    		if(o2.getJumlahGol() - o1.getJumlahGol() != 0) //Urutan berdasarkan banyaknya jumlah gol tim
	    			return o2.getJumlahGol() - o1.getJumlahGol();	  
	    	return o1.getJumlahKebobolan() - o2.getJumlahKebobolan(); //Urutan berdasarkan sedikitnya jumlah kebobolan tim
    	}
    	return -1;
    }
}

class GolComparator implements Comparator<Pemain> {
    @Override		//Memodifikasi method compare dalam kelas Comparator
    public int compare(Pemain o1, Pemain o2) {
    	if(o1 != null && o2 != null){
	    	if(o2.getJumlahGol() - o1.getJumlahGol() != 0)					//Urutan berdasarkan banyaknya jumlah gol pemain
	    		return o2.getJumlahGol() - o1.getJumlahGol();				
	    	return o1.getJumlahPelanggaran() - o2.getJumlahPelanggaran();	//Urutan berdasarkan sedikitnya jumlah pelanggaran pemain
    	}
    	return -1;
    }
}


