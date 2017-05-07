public class Tim {
	
	private String namaTim;
	private Pemain[] daftarPemain;
	private int jumlahMenang;
	private int jumlahKalah;
	private int jumlahSeri;
	private int jumlahPoin;
	private int jumlahGol;
	private int jumlahKebobolan;
	
	public Tim(String namaTim){
		this.namaTim = namaTim;
		this.daftarPemain = new Pemain[5];
		jumlahMenang = 0;
		jumlahKalah = 0;
		jumlahSeri = 0;
		jumlahPoin = 0;
		jumlahGol = 0;
		jumlahKebobolan = 0;
	}
//	@Override
//	public String toString(){
//		return namaTim + "\n" + java.util.Arrays.toString(daftarPemain);
//	}

	public String getNamaTim() {
		return namaTim;
	}
	public void setNamaTim(String namaTim) {
		this.namaTim = namaTim;
	}
	public Pemain[] getDaftarPemain() {
		return daftarPemain;
	}
	public void setDaftarPemain(Pemain[] daftarPemain) {
		this.daftarPemain = daftarPemain;
	}
	public int getJumlahMenang() {
		return jumlahMenang;
	}
	public void setJumlahMenang(int jumlahMenang) {
		this.jumlahMenang += jumlahMenang;
	}
	public int getJumlahKalah() {
		return jumlahKalah;
	}
	public void setJumlahKalah(int jumlahKalah) {
		this.jumlahKalah += jumlahKalah;
	}
	public int getJumlahSeri() {
		return jumlahSeri;
	}
	public void setJumlahSeri(int jumlahSeri) {
		this.jumlahSeri += jumlahSeri;
	}
	public int getJumlahGol() {
		return jumlahGol;
	}
	public void setJumlahGol(int jumlahGol) {
		this.jumlahGol += jumlahGol;
	}
	public int getJumlahKebobolan() {
		return jumlahKebobolan;
	}
	public void setJumlahKebobolan(int jumlahKebobolan) {
		this.jumlahKebobolan += jumlahKebobolan;
	}
	public void setJumlahPoin(int jumlahPoin) {
		this.jumlahPoin = jumlahPoin;
	}
	public int getJumlahPoin() {
		return (jumlahMenang*3) + (jumlahSeri*1);
	}
	
}
