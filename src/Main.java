import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int n;
		Scanner sc = new Scanner(System.in);
		Random rand = new Random();
		PlayerStatus jucator = new PlayerStatus ("Cosmin", 4, 21000, 100, "sniper", 5000, 6000);
		PlayerStatus opponent = new PlayerStatus ("Luigi", 3, 200000, 99, "kalashnikov", 2, 4);
		PlayerStatus.setGameName("Super Mario");
		System.out.println("Bine ati venit in jocul: " + PlayerStatus.getGameName());

		System.out.println("1. Vizualizare nickname, lives and score pt tine ");
		System.out.println("2. Vizualizare nickname, lives and score adversar ");
		System.out.println("3. Modifica numele tau ");
		System.out.println("4. Continua aventura si cauta un artifact! ");
		System.out.println("5. Simuleaza un razboi! ");
		System.out.println("6. Cumpara alta arma! ");
		System.out.println("7. Muta jucatorul pe harta! ");
		System.out.println("8. Ataca adversarul! ");
		System.out.println("9. Afla distanta dintre jucatori! ");
		System.out.println("10. Iesire! ");
		do {
			System.out.println("\nIntroduceti optiunea dumneavoastra (10 pt a iesi): ");
			n = sc.nextInt();
			switch (n) {
				case 1:
					System.out.println("Ati ales vizualizare detalii jucator: ");
					System.out.println("Nickname: " + jucator.getNickname());
					System.out.println("Lives: " + jucator.getLives());
					System.out.println("Score: " + jucator.getScore());
					System.out.println("Health: " + jucator.getHealth());
					System.out.println("Arma: " + jucator.getWeaponInHand());
					System.out.println("Pozitia X: " + jucator.getPositionX());
					System.out.println("Pozitia Y: " + jucator.getPositionY());
					break;
		
				case 2:
					System.out.println("Ati ales vizualizare detalii adversar: ");
					System.out.println("Nickname: " + opponent.getNickname());
					System.out.println("Lives: " + opponent.getLives());
					System.out.println("Score: " + opponent.getScore());
					System.out.println("Health: " + opponent.getHealth());
					System.out.println("Arma: " + opponent.getWeaponInHand());
					System.out.println("Pozitia X: " + opponent.getPositionX());
					System.out.println("Pozitia Y: " + opponent.getPositionY());
					break;
					
				case 3:
					System.out.println("Ati ales modificarea numelui --> Introduceti noul nume: ");
					sc.nextLine();
					String s = sc.nextLine();
					System.out.println("s: " + s);
					jucator.setNickname(s);
					break;
				
				case 4:
					System.out.println("Ati ales find artifact: ");
					int artCode = rand.nextInt(500);
					System.out.println("Ati gasit artifactul cu nr " + artCode);
					jucator.findArtifact(artCode);
					System.out.println("Lives: " + jucator.getLives());
					System.out.println("Score: " + jucator.getScore());
					System.out.println("Health: " + jucator.getHealth());					
					break;
					
				case 5:
					System.out.println("Ai ales sa simulezi un atac! Sa vedem daca e o idee buna! ");
					System.out.println("Vom simula atacul impotriva jucatorului " + opponent.getNickname());
					System.out.println("Distanta dintre voi este: " + jucator.distantaJucatori(opponent));
					if (jucator.shouldAttackOpponent(opponent))
						System.out.println("Ai castiga duelul");
					else System.out.println("Nu e deloc o idee buna sa ataci! Ai pierde!");
					break;
				
				case 6:
					System.out.println("Bine ai venit in magazia cu arme! Ce arma ai dori sa cumperi? ");
					System.out.println("knife - cost 1000 puncte ");
					System.out.println("sniper- cost 10000 puncte ");
					System.out.println("kalashnikov - cost 20000 puncte ");
					sc.nextLine();
					String weapon = sc.nextLine();
					System.out.println("Ai ales sa cumperi un " + weapon);
					jucator.setWeaponInHand(weapon);
					System.out.println("Ai mai ramas cu " + jucator.getScore());

					break;
					
				case 7:
					System.out.println("Ai ales sa te misti pe harta!");
					System.out.println("Coordonatele actuale vor fi: X = " + jucator.getPositionX() + " si Y = " + jucator.getPositionY());
					double posX, posY;
					sc.nextLine();
					System.out.println("Introdu noua coordonata X: ");
					posX = sc.nextDouble();
					System.out.println("Introdu noua coordonata Y: ");
					posY = sc.nextDouble();
					jucator.setPosition(posX, posY);
					break;
					
				case 8:
					System.out.println("Ai ales sa ataci oponentul: ");
					jucator.attackOpponent(opponent);
					break;
					
				case 9:
					System.out.println("Distanta dintre jucatori este " + jucator.distantaJucatori(opponent));
					break;
					
				default:
					System.out.println("Optiune incorecta! Reincercati");

			}
		} while ((n != 10) && (jucator.getLives() > 0));
		if (n == 10)
			System.out.println("Va mai asteptam pe la noi!");
		if (jucator.getLives() <= 0)
			System.out.println("Game Over Mario!");


		sc.close();
	}

}
