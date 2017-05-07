
public class OwjekExclusive extends Owjek{
	private int fixedCost;
	private double protectionCost;

	public OwjekExclusive(){
		super(5000);
		fixedCost = 10000;
	}

	//Method untuk menghitung biaya keseluruhan dari perjalanan ow-jek exclusive
	public String getCost(double jarak){
		jarak = jarak/1000;
		double kmsel = jarak * getCostPerKm();
		double promo = getPromo(jarak);

		double total = fixedCost + kmsel - promo;
		protectionCost = 0.05 * total;
		return String.format(
				"[Jarak] %s KM %n[TipeO] Exclusive %n[Fixed] %s (+) %n[KMSel] %s (+) %n[Promo] %s (-) %n[Prtks] %s (+) %n[TOTAL] %s ",
				String.valueOf(jarak).replace('.',','),
				super.getRupiah(fixedCost),
				super.getRupiah(kmsel),
				super.getRupiah(promo),
				super.getRupiah(protectionCost),
				super.getRupiah(total+protectionCost));
	}

	//Promo pada ow-jek exclusive 50%(total biaya)
	public double getPromo(double jarak){
		return 0.5 * (fixedCost + (jarak * getCostPerKm()));
	}
}