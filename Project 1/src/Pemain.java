public class Pemain {
	private String namaPemain;
	private int nomorPemain;
	private String namaTim;
	private int jumlahGol;
	private int jumlahPelanggaran;
	private int jumlahKartuKuning;
	private int temporaryKuning;
	private int jumlahKartuMerah;
	private int temporaryMerah;
	
	public Pemain(String namaPemain, int nomorPemain, String namaTim){
		this.namaPemain = namaPemain;
		this.nomorPemain = nomorPemain;
		this.namaTim = namaTim;
		this.jumlahGol = 0;
		this.jumlahPelanggaran = 0;
		this.jumlahKartuKuning = 0;
		this.jumlahKartuMerah = 0;
	}
	
//	@Override
//	public String toString(){
//		return namaPemain + "\n" + nomorPemain;
//	}
	public String getNamaPemain() {
		return namaPemain;
	}
	public void setNamaPemain(String namaPemain) {
		this.namaPemain = namaPemain;
	}
	public int getNomorPemain() {
		return nomorPemain;
	}
	public void setNomorPemain(int nomorPemain) {
		this.nomorPemain = nomorPemain;
	}
	public String getNamaTim() {
		return this.namaTim;
	}
	public void setNamaTim(String namaTim) {
		this.namaTim = namaTim;
	}
	public int getJumlahGol() {
		return jumlahGol;
	}
	public void setJumlahGol(int jumlahGol) {
		this.jumlahGol += jumlahGol;
	}
	public int getJumlahPelanggaran() {
		return jumlahPelanggaran;
	}
	public void setJumlahPelanggaran(int jumlahPelanggaran) {
		this.jumlahPelanggaran += jumlahPelanggaran;
	}
	public int getKartuKuning() {
		return jumlahKartuKuning;
	}
	public void setKartuKuning(int jumlahKartuKuning) {
		this.jumlahKartuKuning += jumlahKartuKuning;
	}
	public int getTemporaryKuning(){
		return temporaryKuning;
	}
	public void setTemporaryKuning(int temporaryKuning){
		this.temporaryKuning = temporaryKuning;
	}
	public int getKartuMerah() {
		return jumlahKartuMerah;
	}
	public void setKartuMerah(int jumlahKartuMerah) {
		this.jumlahKartuMerah += jumlahKartuMerah;
	}
	public int getTemporaryMerah(){
		return temporaryMerah;
	}
	public void setTemporaryMerah(int temporaryMerah){
		this.temporaryMerah = temporaryMerah;
	}
}
