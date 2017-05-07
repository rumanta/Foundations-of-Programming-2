
public class OwjekSporty extends Owjek{
	private double protectionCost;
	private int limaKmPe;

	public OwjekSporty(){
		super(3000);
		limaKmPe = 20000;
	}

	//Method untuk menghitung biaya keseluruhan dari perjalanan ow-jek sporty
	public String getCost(double jarak){
		jarak = jarak/1000;
		double kmsel=0;
		double promo = getPromo(jarak);
		if(jarak - 5 > 0)
			kmsel = getCostPerKm() * (jarak-5);
		double total = limaKmPe + kmsel - promo;
		protectionCost = 0.1 * total;
		return String.format(
				"[Jarak] %s KM %n[TipeO] Sporty %n[5KMPe] %s (+) %n[KMSel] %s (+) %n[Promo] %s (-) %n[Prtks] %s (+) %n[TOTAL] %s ",
				String.valueOf(jarak).replace('.',','),
				super.getRupiah(limaKmPe),
				super.getRupiah(kmsel),
				super.getRupiah(promo),
				super.getRupiah(protectionCost),
				super.getRupiah(total+protectionCost));
	}

	//Promo pada ow-jek regular 60%(8 km pertama)
	public double getPromo(double jarak){
		if(jarak >= 8)
			return 0.6 * (limaKmPe + (3 * getCostPerKm()));
		else if(jarak < 5)
			return 0.6 * limaKmPe;

		return 0.6 * (limaKmPe + (jarak-5) * getCostPerKm());
	}
}
