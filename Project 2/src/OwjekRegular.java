
public class OwjekRegular extends Owjek{
	private int duaKmPe;

	public OwjekRegular(){
		super(1000);
		this.duaKmPe = 3000;
	}

	//Method untuk menghitung biaya keseluruhan dari perjalanan ow-jek regular
	public String getCost(double jarak){
		jarak = jarak/1000;
		double kmsel=0;
		double promo = getPromo(jarak);
		if(jarak - 2 > 0)
			kmsel = getCostPerKm() * (jarak-2);
		double total = duaKmPe + kmsel - promo;
		return String.format(
				"[Jarak] %s KM %n[TipeO] Regular %n[2KMPe] %s (+) %n[KMSel] %s (+) %n[Promo] %s (-) %n[TOTAL] %s ",
				String.valueOf(jarak).replace('.',','),
				super.getRupiah(duaKmPe),
				super.getRupiah(kmsel),
				super.getRupiah(promo),
				super.getRupiah(total));
	}
	//Promo pada ow-jek regular 40%(6 km pertama)
	public double getPromo(double jarak){
		if(jarak >= 6)
			return 0.4 * (duaKmPe + (4 * getCostPerKm()));
		else if(jarak < 2)
			return 0.4 * duaKmPe;

		return 0.4 * (duaKmPe + (jarak-2) * getCostPerKm());
	}
}
