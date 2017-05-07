import java.util.Scanner;

public class Company {

	public static void main(String[] args){
		System.out.println("Welcome to OW-JEK...");
		Scanner input = new Scanner(System.in);

		while(true){
			String command = input.nextLine();
			String[] perintah = command.split(" ");

// =============================== SHOW MAP ===============================
			if(perintah.length == 2){
				if(perintah[0].equalsIgnoreCase("show") && perintah[1].equalsIgnoreCase("map")){
					Map map = new Map();
					map.print();
				}
				else{
					System.err.println("Format \"" + command + "\" salah");
				}
			}

// ========================= GO FROM [koor] TO [koor] =========================
			else if(perintah.length == 8){

				if(perintah[0].equalsIgnoreCase("go") && perintah[1].equalsIgnoreCase("from") &&
				   perintah[3].equalsIgnoreCase("to") && perintah[5].equalsIgnoreCase("with") &&
				   perintah[6].equalsIgnoreCase("OW-JEK")){

					Owjek ojek = null;

					if(perintah[7].equalsIgnoreCase("regular")){
						ojek = new OwjekRegular();
					}
					else if(perintah[7].equalsIgnoreCase("sporty")){
						ojek = new OwjekSporty();
					}
					else if(perintah[7].equalsIgnoreCase("exclusive")){
						ojek = new OwjekExclusive();
					}
					else{
						System.err.println("Tipe OW-JEK " + perintah[7] + " tidak dikenal");
					}

					if(ojek != null){
						System.out.println(ojek.findPath(perintah[2], perintah[4]));
						if(ojek.isPathFound()){
							System.out.println("Terimakasih telah melakukan perjalanan dengan OW-JEK.");
							System.out.println(ojek.getCost(ojek.getJarak()));
						}
					}
				}
				else{
					System.err.println("Format \"" + command + "\" salah");
				}
			}
// ================================ EXIT ====================================
			else if(perintah[0].equalsIgnoreCase("exit") && perintah.length==1){
				System.out.println("Exiting...");
				input.close();
				break;
			}

			else{
				System.err.println("Perintah tidak dikenal");
			}
		}
	}
}