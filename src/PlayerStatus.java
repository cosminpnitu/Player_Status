import java.lang.Math;

public class PlayerStatus {
	String nickname;
	int score;
	int lives;
	int health;
	String weaponInHand;
	double positionX;
	double positionY;
	static private String gameName;
	
	public PlayerStatus (String nickname) {
		this.nickname = nickname;
	}
	public PlayerStatus (String nickname, int lives) {
		this.nickname = nickname;
		this.lives = lives;
	}
	public PlayerStatus (String nickname, int lives, int score) {
		this.nickname = nickname;
		this.lives = lives;
		this.score = score;
	}
	public PlayerStatus (String nickname, int lives, int score, int health, String weaponInHand, double positionX, double positionY) {
		this.nickname = nickname;
		this.lives = lives;
		this.score = score;
		this.health = health;
		this.weaponInHand = weaponInHand;
		this.positionX = positionX;
		this.positionY = positionY;
	}

	public int getLives() {
		return lives;
	}
	public void setLives(int lives) {
		this.lives = lives;
	}
	public int getScore() {
		if (this.score > 0)
			return score;
		return 0;
	}
	public void findArtifact(int artifactCode) {
		if (isPerfect (artifactCode)) {
			this.score += 5000;
			this.lives ++;
			this.health = 100;
			System.out.println("Bravo! Ai gasit un artifact perfect");
		}
		else if (isPrime (artifactCode)) {
				this.score += 1000;
				this.lives += 2;
				if (this.health >= 75)
					this.health = 100;
				else this.health += 25;
				System.out.println("Bravo! Ai gasit un artifact prim");

		}	
		else if ((artifactCode % 2 == 0) && (sumaCifre (artifactCode) % 3 == 0)) {
//			if (this.score > 3000)
					this.score -= 3000;
//			else this.score = 0;
			System.out.println("Ghinion! Ai gasit un artifact capcana");

			if (this.health < 25) {
				this.lives --;
				this.health = 100;
			}
			else this.health -= 25;
		}
		else {
			this.score += artifactCode;	
			System.out.println("Ai gasit un artifact normal");
		}
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public String getWeaponInHand() {
		return weaponInHand;
	}
	public boolean setWeaponInHand(String weaponInHand) {
		if (!((weaponInHand.equals("knife")) || (weaponInHand.equals("kalashnikov")) || (weaponInHand.equals("sniper")))) {
			System.out.println("Nu avem arma ceruta in magazie!");
			return false;
		}
		if ((weaponInHand.equals("knife")) && (this.score >= 1000)) {
			this.weaponInHand = weaponInHand;
			score -= 1000;
			return true;
		} else if ((weaponInHand.equals("sniper")) && (this.score >= 10000)) {
				this.weaponInHand = weaponInHand;
				score -= 10000;
				return true;
		} else if ((weaponInHand.equals("kalashnikov")) && (this.score >= 20000)) {
			this.weaponInHand = weaponInHand;
			score -= 20000;
			return true;
		} else System.out.println("Mai strange bani!");
		return false;
	}
	public double getPositionX() {
		return positionX;
	}
	public void setPosition(double positionX, double positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
	}
	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}
	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}
	public double getPositionY() {
		return positionY;
	}
	public void attackOpponent (PlayerStatus opponent) {
			if (sameWeapon (opponent.weaponInHand)) 
				if (((3 * this.health + this.score / 1000) / 4) <= ((3 * opponent.health + opponent.score / 1000) / 4)) {
					System.out.println("Ai pierdut lupta avand aceeasi arma! Vei pierde 1 viata si 15000");
					this.lives --;
					this.score -= 15000;
					opponent.score += 15000;
				} else {
					System.out.println("Ai castigat lupta cu aceeasi arma! Ai luat 15000 puncte adversarului");
					this.score += 15000;
					opponent.score -= 15000;
				}
			if ((!sameWeapon (opponent.weaponInHand)) && (distantaJucatori(opponent) > 1000)) 
				if ((this.weaponInHand.equals("sniper")) || (opponent.weaponInHand.equals("knife"))) {
					System.out.println("Ai castigat lupta la distanta ! Ai luat 10000 puncte adversarului");
					this.score += 10000;
					opponent.score -= 10000;
				} else {
					System.out.println("Ai pierdut lupta avand arme diferite! Vei pierde 1 viata si 10000");
					this.lives --;
					this.score -= 10000;
				}

			if ((!sameWeapon (opponent.weaponInHand)) && (distantaJucatori(opponent) < 1000)) 
				if ((this.weaponInHand.equals("kalashnikov")) || (opponent.weaponInHand.equals("knife"))) {
					System.out.println("Ai castigat lupta la distanta ! Ai luat 5000 puncte adversarului");
					this.score += 5000;
					opponent.score -= 5000;
				} else {
					System.out.println("Ai pierdut lupta avand arme diferite! Vei pierde 1 viata si 5000");
					this.lives --;
					this.score -= 5000;
					opponent.score += 5000;
				}
	}
	protected static String getGameName() {
		return gameName;
	}
	protected static void setGameName(String gameName) {
		PlayerStatus.gameName = gameName;
	}
	public void movePlayerTo(double positionX, double positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
	}
	public boolean shouldAttackOpponent (PlayerStatus opponent) {
		if ((sameWeapon (opponent.weaponInHand)) && 
			(((3 * this.health + this.score / 1000) / 4) >= ((3 * opponent.health + opponent.score / 1000) / 4)))
				return true;
		if ((!sameWeapon (opponent.weaponInHand)) && (distantaJucatori(opponent) > 1000)) {
			if ((this.weaponInHand.equals("sniper")) || (opponent.weaponInHand.equals("knife")))
				return true;
		} else if ((this.weaponInHand.equals("kalashnikov")) || (opponent.weaponInHand.equals("knife")))
			return true;
		return false;
	}
	private boolean sameWeapon (String weaponInHand) {
		if (this.weaponInHand == weaponInHand)
			return true;
		return false;
	}
	private static boolean isPerfect (int n) {
		int sum = 1;
		for (int i = 2; i <= n / 2; i++) 
			if (n % i == 0)
				sum += i;
		if (sum == n)
			return true;
		else return false;
	}
	private static boolean isPrime (int n) {
		for (int i = 2; i <= n / 2; i++) 
			if (n % i == 0)
				return false;
		return true;
	}
	private static int sumaCifre (int n) {
		int suma = 0;
		while (n > 0) {
			suma += n % 10;
			n /= 10;
		}
		return suma;
	}
	public double distantaJucatori (PlayerStatus opponent) {
		return (Math.sqrt((this.positionX - opponent.positionX) * (this.positionX - opponent.positionX) + 
				(this.positionY - opponent.positionY) * (this.positionY - opponent.positionY)));
	}
}
